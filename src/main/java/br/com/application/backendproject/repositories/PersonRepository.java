package br.com.application.backendproject.repositories;


import br.com.application.backendproject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{ 
    
    
}
