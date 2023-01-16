package br.com.application.backendproject.resources;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.application.backendproject.models.Address;
import br.com.application.backendproject.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping(value = "persons/addresses") 
public class AddressResource {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "Buscar todos os endereços")
    @GetMapping
    public ResponseEntity<List<Address>>  listAll(){ 
        List<Address> addresses = addressService.listAllAddresses(); 
        return ResponseEntity.ok().body(addresses);  
    }

    @Operation(summary = "Buscar todos os endereços de uma pessoa")
    @GetMapping("/{idPerson}") 
    public ResponseEntity<List<Address>> findPersonAddress(@PathVariable("idPerson") Long id){
        List<Address> address = addressService.findPersonAddresses(id);
        return ResponseEntity.ok().body(address); 
    }

    @Operation(summary = "Criar um endereço")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Address address) throws ParseException, JsonMappingException, JsonProcessingException{
        ResponseEntity<Object> returnAddres = addressService.save(address);
        return ResponseEntity.status(returnAddres.getStatusCode()).body(returnAddres);
    }

    
    @Operation(summary = "Editar um endereço")
    @PutMapping
    public ResponseEntity<Address> edit(@RequestBody Address address){
        address = addressService.edit(address);
        return ResponseEntity.ok().body(address); 
    }

    @Operation(summary = "Deletar um endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){   
        addressService.delete(id); 
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
