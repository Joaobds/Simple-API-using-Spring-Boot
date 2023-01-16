package br.com.application.backendproject.models;

import jakarta.persistence.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "address")
public class Address {

    @Id
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

    @Column(nullable = false)
    private long idPerson;

    @Column(nullable = false)
    private boolean mainAddress;

    public Address(Long id, String street, String cep, String number, String city, long idPerson, boolean mainAddress) {
        this.id = id;
        this.street = street;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.idPerson = idPerson;
        this.mainAddress = mainAddress;
    }

    public Address(){

    }


}
