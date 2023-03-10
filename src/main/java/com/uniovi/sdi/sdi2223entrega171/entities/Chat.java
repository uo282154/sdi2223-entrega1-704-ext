package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="chat")
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    public Chat(Long id, List<Message> messages, User sender, User receiver) {
        this.id = id;
        this.messages = messages;
        this.sender = sender;
        this.receiver = receiver;
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
}

