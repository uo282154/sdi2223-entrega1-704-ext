package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="offer")
public class Offer {


    public static String STATUS_ACTIVE="ACTIVE";
    public static String STATUS_SOLD="SOLD";

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private double price;

    //@Enumerated(EnumType.STRING)
    private String status;

    private LocalDate createAt;

    @OneToMany(mappedBy="offer")
    private List<Chat> chats;

    @ManyToOne
    private User creator; //usuario que crea la oferta

    @ManyToOne
    private User buyer; // usuario que ha comprado la oferta




    private String creatorEmail; // para mostrar en la lista de ofertas compradas
    public Offer() {
        //La oferta tiene que tener estado created (en el offerController al crearla)
        //this.createAt = LocalDate.now(); (en el controller¿?)
        this.status=STATUS_ACTIVE;
    }

    public Offer(String title, String description, double price, User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = STATUS_ACTIVE;         //En el controller?¿
        this.createAt = LocalDate.now();
        this.creator = user;
    }

    public Offer(String title, String description, double price, User user, User buyer) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = STATUS_ACTIVE;         //En el controller?¿
        this.createAt = LocalDate.now();
        this.creator = user;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }
    /*
    public void setCreateAt(String createAt) {
        this.createAt = LocalDate.parse(createAt);
    }*/

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

}
