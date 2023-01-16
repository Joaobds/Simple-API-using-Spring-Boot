package br.com.application.backendproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.junit.jupiter.api.Test;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import br.com.application.backendproject.models.Address;
import br.com.application.backendproject.repositories.AddressRepository;
import br.com.application.backendproject.repositories.PersonRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
public class AddressServiceTest {
    @Mock
    AddressRepository addressRepository;
    
    @Mock
    PersonRepository personRepository;

    @InjectMocks
    AddressService addressService;

    @Mock
    Address address;

    @BeforeEach
    public void setUp() throws ParseException{
        address = new Address();
        address.setId(1L);
        address.setStreet("Rua X");
        address.setNumber("34");
        address.setCity("Aracaju");
        address.setCep("49048500");
        address.setIdPerson(1L);
        address.setMainAddress(true);
    }

    @Test
    public void shouldListAllAddressesTest(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        
        Mockito.when(addressRepository.findAll()).thenReturn(addresses); 
        
        List<Address> expectedAddresses = addressService.listAllAddresses();
        assertEquals(expectedAddresses, addresses);
        verify(addressRepository).findAll();
    }


    @Test
    public void shouldFindByIdTest() throws ServiceNotFoundException{
        Address expectedAddress = address;
        Mockito.doReturn(Optional.of(expectedAddress)).when(addressRepository).findById(address.getId());

        Address actualAddres = addressService.findById(expectedAddress.getId());
        assertNotNull(expectedAddress.getId());
        assertEquals(expectedAddress.getStreet(), actualAddres.getStreet());
        assertEquals(expectedAddress.getNumber(), actualAddres.getNumber());
        assertEquals(expectedAddress.getCity(), actualAddres.getCity());
        assertEquals(expectedAddress.getCep(), actualAddres.getCep()); 
        assertEquals(expectedAddress.getIdPerson(), actualAddres.getIdPerson());
        assertEquals(expectedAddress.isMainAddress(),actualAddres.isMainAddress());
    }

    @Test
    public void shouldSaveTest() throws ParseException, JsonMappingException, JsonProcessingException{
        Mockito.when(addressRepository.save(ArgumentMatchers.any(Address.class))).thenReturn(address);
        
        ResponseEntity<Object> returnedResponse = addressService.save(address);
        assertEquals(org.springframework.http.HttpStatus.CREATED, returnedResponse.getStatusCode());
       

        
    }

    @Test
    public void shouldDeleteTestWhenExists(){     
        Mockito.when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        
        Address ActualAddress = addressService.findById(address.getId());
        MatcherAssert.assertThat("NOT PASSED", address.getId().equals(ActualAddress.getId()));

        addressService.delete(address.getId());
        verify(addressRepository).deleteById(address.getId());

    }

}
