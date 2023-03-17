package com.uniovi.sdi.sdi2223entrega171.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
@Entity
@Table(name="logItem")
public class Log {
    @Id
    @GeneratedValue
    private long id;
    public enum LogItemType {
        PET, ALTA, LOGIN_EX, LOGIN_ERR, LOGOUT ;
        @Override
        public String toString() {
            return this.name().replace('_', '-');
        }
    }

    private LogItemType type;
    private Timestamp timestamp; //new Timestamp(System.currentTimeMillis())
    private String user;
    private String action;
    private String description;

    public Log() {
    }

    public Log(long id, LogItemType type, Timestamp timestamp, String user, String action, String description) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.user = user;
        this.action = action;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
