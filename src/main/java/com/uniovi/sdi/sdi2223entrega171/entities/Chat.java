package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="chat")
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "chat", cascade=CascadeType.ALL)
    private List<Message> messages;

    @ManyToOne
    private Offer offer;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    public Chat(User sender, User receiver, Offer offer) {
        this.sender = sender;
        this.receiver = receiver;
        this.offer = offer;
        this.messages = new ArrayList<Message>();
    }

    public Chat() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}

