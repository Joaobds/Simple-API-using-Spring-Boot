package br.com.application.backendproject.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.application.backendproject.models.Person;
import br.com.application.backendproject.services.PersonService;


@RestController
@RequestMapping(value = "/persons") 
public class PersonResource {
    @Autowired
    private PersonService personService;
    
  

    @GetMapping
    public ResponseEntity<List<Person>> listAll(){ 
        List<Person> persons = personService.listAllPersons(); 
        return ResponseEntity.ok().body(persons);     
    }

    @GetMapping(value = "/{id}") 
    public ResponseEntity<Person> findPerson(@PathVariable("id") Long id){
        Person person = personService.findById(id);
        return ResponseEntity.ok().body(person); 
    }    

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
            person = personService.save(person);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
            return ResponseEntity.created(uri).body(person); 
    }

    @PutMapping
    public ResponseEntity<Person> edit(@RequestBody Person person){
        person = personService.edit(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){ 
        personService.delete(id); 
        return ResponseEntity.ok().build();  
    }
}
