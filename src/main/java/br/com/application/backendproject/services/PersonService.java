package br.com.application.backendproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.application.backendproject.models.Person;
import br.com.application.backendproject.repositories.AddressRepository;
import br.com.application.backendproject.repositories.PersonRepository;
import br.com.application.backendproject.services.exceptions.DatabaseException;
import br.com.application.backendproject.services.exceptions.ResourceNotFoundException;

@Service
public class PersonService {

    final static String DATE_FORMAT = "dd/MM/yyyy";

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Person> listAllPersons(){
        return personRepository.findAll();  
    }

    public Person findById(Long id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(()->new ResourceNotFoundException("Person", id));
    }

    public Person save(Person person){ 
        try{
            person = personRepository.save(person);      
            return person;
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Person");
        }      
    }

    public Person edit(Person person){ 
        try{
            if(person.getId() > 0){        
                person = personRepository.save(person);
            }
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Person");
        }
        return person;      
    }

    public void delete(Long id){ 
        try{
            addressRepository.deleteAllPersonAddresses(id);  
            personRepository.deleteById(id);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Person");
        }     
    }
}
