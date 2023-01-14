package br.com.application.backendproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.application.backendproject.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{ 
    
    
}
