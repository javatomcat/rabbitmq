package com.carry.main.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String name;
    private String messageId;

    public Order() {
    }

    public Order(int id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}