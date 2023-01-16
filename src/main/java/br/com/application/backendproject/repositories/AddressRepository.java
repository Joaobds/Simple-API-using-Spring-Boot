package br.com.application.backendproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.application.backendproject.models.Address;
import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Long>{ 
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
        value = "update address SET main_address = false WHERE id_person = ?1", 
        nativeQuery = true)
      Integer setAllOthersAddressAsNotMain(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
        value = "delete from address WHERE id_person = ?1", 
        nativeQuery = true)
      Integer deleteAllPersonAddresses(Long id);


    @Transactional
    @Query(
        value = "SELECT * FROM Address a where a.id_person = ?1", 
        nativeQuery = true)
        List<Address> findPersonAddresses(Long id);
}
