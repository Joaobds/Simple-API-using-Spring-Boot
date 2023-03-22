package br.com.application.backendproject.resources;

import br.com.application.backendproject.models.Person;
import br.com.application.backendproject.services.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping(value = "/persons") 
public class PersonResource {
    @Autowired
    private PersonService personService;
    
    @Operation(summary = "Buscar todas as pessoas")
    @GetMapping
    public ResponseEntity<List<Person>> listAll(){ 
        List<Person> persons = personService.listAllPersons(); 
        return ResponseEntity.ok().body(persons);     
    }


    @Operation(summary = "Buscar pessoa pelo ID")
    @GetMapping(value = "/{id}") 
    public ResponseEntity<Person> findPerson(@PathVariable("id") Long id){
        Person person = personService.findById(id);
        return ResponseEntity.ok().body(person); 
    }    


    
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person ) throws JsonMappingException, JsonProcessingException, ParseException{
            person = personService.save(person);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
            return ResponseEntity.created(uri).body(person); 
    }

    @Operation(summary = "Editar uma pessoa")
    @PutMapping
    public ResponseEntity<Person> edit(@RequestBody Person person){
        person = personService.edit(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);   
    }

    @Operation(summary = "Deletar uma pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){ 
        personService.delete(id); 
        return ResponseEntity.ok().build();  
    }
}
