package br.com.application.backendproject.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;

import br.com.application.backendproject.models.Address;
import br.com.application.backendproject.models.Person;
import br.com.application.backendproject.services.AddressService;
import br.com.application.backendproject.services.utils.Util;

@RunWith(SpringRunner.class)
@DataJpaTest 
public class AddressRepositoryTest {
    
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;


    @Test
    public void shouldSetAllOthersAddressAsNotMainTest() throws ParseException{
        
        Person person = new Person();
        Date date = Util.convertJavaDateToSQLDate("2003-05-30");
        person.setName("João Gabriel Batista dos Santos");
        person.setBirthdate(date);
        
        Address firstAddress = new Address();
        firstAddress.setStreet("Rua X");
        firstAddress.setNumber("34");
        firstAddress.setCity("Aracaju");
        firstAddress.setCep("49048500");
        firstAddress.setMainAddress(true);

        Address secondAddress = new Address();
        secondAddress.setStreet("Rua Y");
        secondAddress.setNumber("26");
        secondAddress.setCity("Aracaju");
        secondAddress.setCep("49048500");
        secondAddress.setMainAddress(true);
        
        person = personRepository.save(person);
        
        firstAddress.setIdPerson(person.getId());
        secondAddress.setIdPerson(person.getId());

        firstAddress = addressRepository.save(firstAddress);
        addressRepository.setAllOthersAddressAsNotMain(firstAddress.getIdPerson());
        
        secondAddress = addressRepository.save(secondAddress);

        firstAddress = addressRepository.findById(firstAddress.getId()).get();

        assertEquals(firstAddress.isMainAddress(), false);
        assertEquals(secondAddress.isMainAddress(), true);
    }

    @Test
    public void shouldDeleteAllPersonAddressesTest() throws ParseException{

        Person person = new Person();
        Date date = Util.convertJavaDateToSQLDate("2003-05-30");
        person.setName("João Gabriel Batista dos Santos");
        person.setBirthdate(date);

        person = personRepository.save(person);

        Address firstAddress = new Address();
        firstAddress.setStreet("Rua X");
        firstAddress.setNumber("34");
        firstAddress.setCity("Aracaju");
        firstAddress.setCep("49048500");
        firstAddress.setMainAddress(true);

        Address secondAddress = new Address();
        secondAddress.setStreet("Rua Y");
        secondAddress.setNumber("26");
        secondAddress.setCity("Aracaju");
        secondAddress.setCep("49048500");
        secondAddress.setMainAddress(false);

        firstAddress.setIdPerson(person.getId());
        secondAddress.setIdPerson(person.getId());

        firstAddress = addressRepository.save(firstAddress);
        secondAddress = addressRepository.save(secondAddress);

        int expectedNumber = addressRepository.deleteAllPersonAddresses(person.getId());

        person = personRepository.findById(person.getId()).get();
        
        assertEquals(new ArrayList<Address>(), person.getAddresses());
        assertEquals(expectedNumber, 2);

    }

    @Test
    public void shouldfindPersonAddressesTest() throws ParseException{

        Person person = new Person();
        Date date = Util.convertJavaDateToSQLDate("2003-05-30");
        person.setName("João Gabriel Batista dos Santos");
        person.setBirthdate(date);

        person = personRepository.save(person);

        Address firstAddress = new Address();
        firstAddress.setStreet("Rua X");
        firstAddress.setNumber("34");
        firstAddress.setCity("Aracaju");
        firstAddress.setCep("49048500");
        firstAddress.setMainAddress(true);
        firstAddress.setIdPerson(person.getId());



        Address secondAddress = new Address();
        secondAddress.setStreet("Rua Y");
        secondAddress.setNumber("26");
        secondAddress.setCity("Aracaju");
        secondAddress.setCep("49048500");
        secondAddress.setMainAddress(false);
        secondAddress.setIdPerson(person.getId());

        firstAddress = addressRepository.save(firstAddress);
        secondAddress = addressRepository.save(secondAddress);

        
        Person fakePerson = new Person();
        Date secondPersonDate = Util.convertJavaDateToSQLDate("2003-05-20");
        fakePerson.setName("Pessoa falsa");
        fakePerson.setBirthdate(secondPersonDate);

        fakePerson = personRepository.save(fakePerson);

        Address fakesAddress = new Address();
        fakesAddress.setStreet("Rua ");
        fakesAddress.setNumber("34");
        fakesAddress.setCity("Aracaju");
        fakesAddress.setCep("49048500");
        fakesAddress.setMainAddress(true);
        fakesAddress.setIdPerson(fakePerson.getId());

        fakesAddress = addressRepository.save(fakesAddress);

        List<Address> fakeAddresses = addressRepository.findPersonAddresses(fakePerson.getId());

        List<Address> addresses = addressRepository.findPersonAddresses(person.getId());

        assertEquals(addresses.size(), 2);
        assertEquals(fakeAddresses.size(), 1);
        assertNotEquals(fakeAddresses.size(), addresses.size());     
        
    }

}
