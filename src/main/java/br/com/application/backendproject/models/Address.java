package br.com.application.backendproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String city;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson")
    private Person person;

    @Column(nullable = false)
    private boolean mainAddress;

    public Address(Long id, String street, String cep, String number, String city, Person person, boolean mainAddress) {
        this.id = id;
        this.street = street;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.person = person;
        this.mainAddress = mainAddress;
    }

    public Address(){

    }


}
