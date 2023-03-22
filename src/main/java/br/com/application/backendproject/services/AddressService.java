package br.com.application.backendproject.services;

import br.com.application.backendproject.models.Address;
import br.com.application.backendproject.models.ResponseHandler;
import br.com.application.backendproject.repositories.AddressRepository;
import br.com.application.backendproject.services.exceptions.DatabaseException;
import br.com.application.backendproject.services.exceptions.ResourceNotFoundException;
import br.com.application.backendproject.services.utils.AddressUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> listAllAddresses(){
        return addressRepository.findAll();  
    }

    public Address findById(Long id){ 
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(()->new ResourceNotFoundException("Address", id));
    }

    public ResponseEntity<Object> save(Address address) throws ParseException, JsonMappingException, JsonProcessingException{ 
        try{
            boolean isValidCep = AddressUtilities.cepValidator(address.getCep());
            if(isValidCep){
                if(address.isMainAddress() && address.getPerson().getId() > 0){
                    addressRepository.setAllOthersAddressAsNotMain(address.getPerson().getId());
                }
                address = addressRepository.save(address);      
                return ResponseHandler.generateResponse("Endereço adicionado com sucesso!", org.springframework.http.HttpStatus.CREATED, address);
            }
            return ResponseHandler.generateResponse("CEP INVÁLIDO", org.springframework.http.HttpStatus.BAD_REQUEST, null);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Address");
        }      
    }

    public Address edit(Address address){
        try{
            if(address.isMainAddress() && address.getPerson().getId() > 0){
                addressRepository.setAllOthersAddressAsNotMain(address.getPerson().getId());
            }
            return addressRepository.save(address);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Address");
        }
    }

    public void delete(Long id){
        try{
            addressRepository.deleteById(id);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Address");
        }
    }
    public List<Address> findPersonAddresses(Long id){
        return addressRepository.findPersonAddresses(id);   
    }

}
