package br.com.application.backendproject.services;
import java.util.Optional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.application.backendproject.models.Address;
import br.com.application.backendproject.repositories.AddressRepository;
import br.com.application.backendproject.services.exceptions.DatabaseException;
import br.com.application.backendproject.services.exceptions.ResourceNotFoundException;

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

    public Address save(Address address){ 
        try{
            if(address.isMainAddress() && address.getIdPerson() > 0){
                addressRepository.setAllOthersAddressAsNotMain(address.getIdPerson());
            }
            address = addressRepository.save(address);      
            return address;        
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e, "Address");
        }      
    }

    public Address edit(Address address){  
        try{
            if(address.isMainAddress() && address.getIdPerson() > 0){
                addressRepository.setAllOthersAddressAsNotMain(address.getIdPerson());
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
