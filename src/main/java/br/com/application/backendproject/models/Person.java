package br.com.application.backendproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity 
@Table(name = "person")
public class Person implements Serializable{
   
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @Column(nullable = false)
    private Date birthdate;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idPerson")
    private List<Address> addresses = new ArrayList<>();


    public Person(Long id, String name, Date birthdate, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.addresses = addresses;
    
    }
    
    public Person(){

    }
     
}

