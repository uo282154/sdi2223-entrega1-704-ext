package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    private Chat chat;

    private LocalDateTime createdAt;

    @OneToOne
    private User sender;


    public Message(String text, LocalDateTime createdAt, Chat chat, User sender) {
        this.text = text;
        this.createdAt = createdAt;
        this.chat = chat;
        this.sender = sender;
    }

    public Message() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
