package br.com.application.backendproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.application.backendproject.models.Person;
import br.com.application.backendproject.repositories.AddressRepository;
import br.com.application.backendproject.repositories.PersonRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
public class PersonServiceTest {
    @Mock
    PersonRepository personRepository;

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    PersonService personService;

    @Mock
    Person person;

    @BeforeEach
    public void setUp() throws ParseException{
        person = new Person();
        java.util.Date birthDateInJava = new SimpleDateFormat("yyyy-MM-dd").parse("2003-05-30"); 
        java.sql.Date birthDate = new java.sql.Date(birthDateInJava.getTime());
        person.setId(1L);
        person.setName("João Gabriel Batista dos Santos");
        person.setBirthdate(birthDate);
    }


    @Test
    public void findByIdTest() throws ServiceNotFoundException{
        Person expectedPerson = person;
        Mockito.doReturn(Optional.of(expectedPerson)).when(personRepository).findById(person.getId());

        Person actualPerson = personService.findById(expectedPerson.getId());
        assertNotNull(actualPerson.getId());
        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getName(), actualPerson.getName());
        assertEquals(expectedPerson.getBirthdate(), actualPerson.getBirthdate());
        assertEquals(expectedPerson.getAddresses(), actualPerson.getAddresses());

    }

    @Test
    public void listAllPersonsTest(){
        List<Person> persons = new ArrayList();
        persons.add(person);
        
        Mockito.when(personRepository.findAll()).thenReturn(persons);
        
        List<Person> expectedPersons = personService.listAllPersons();
        assertEquals(expectedPersons, persons);
        verify(personRepository).findAll();
    }

    @Test
    public void saveTest(){
        Mockito.when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(person);
        
        Person created = personService.save(person);
        MatcherAssert.assertThat("NOT PASSED", created.getName().equals(person.getName()));
        verify(personRepository).save(person);
    }

    @Test
    public void deleteTest(){     
        Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        
        Person ActualPerson = personService.findById(person.getId());
        MatcherAssert.assertThat("NOT PASSED", person.getName().equals(ActualPerson.getName()));

        personService.delete(person.getId());
        verify(personRepository).deleteById(person.getId());

    }

}