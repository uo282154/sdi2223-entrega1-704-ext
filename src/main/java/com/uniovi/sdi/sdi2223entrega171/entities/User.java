package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.*;
import java.util.Set; //Colección que no admite duplicados
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String password;
    @Transient //propiedad que no se almacena en la tabla.
    private String passwordConfirm;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private String role;
    private double money;

    public Set<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(Set<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }

    public Set<Offer> getSoldOffers() {
        return soldOffers;
    }

    public void setSoldOffers(Set<Offer> soldOffers) {
        this.soldOffers = soldOffers;
    }

    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL)
    private Set<Offer> boughtOffers;

    @OneToMany(mappedBy = "creator",cascade = CascadeType.ALL)
    private Set<Offer> soldOffers;

    public User(String email, String name, String surname) {
        super();
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.money = 100;
    }
    public User() { }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getEmail() {return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String SurName) {
        this.surname = SurName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public double getMoney() { return this.money; }
    public void setMoney(double money) {this.money = money;}

    public String getFullName() {
        return this.name + " " + this.surname;
    }


}
