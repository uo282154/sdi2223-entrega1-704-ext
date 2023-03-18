package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name="logItem")
public class Log {
    @Id
    @GeneratedValue
    private long id;

    public enum LogItemType {
        PET("PET"), ALTA("ALTA"), LOGIN_EX("LOGIN-EX"), LOGIN_ERR("LOGIN-ERR"), LOGOUT("LOGOUT") ;

        String name;
        LogItemType(String name) { this.name = name;}

    }
    @Enumerated(EnumType.STRING)
    private LogItemType type;
    private Timestamp timestamp; //new Timestamp(System.currentTimeMillis())
    private String description;

    public Log() {
    }

    public Log(LogItemType type, Timestamp timestamp, String description) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.description = description;
    }

    public LogItemType getType() {
        return type;
    }
    public void setType(LogItemType type) {
        this.type = type;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
