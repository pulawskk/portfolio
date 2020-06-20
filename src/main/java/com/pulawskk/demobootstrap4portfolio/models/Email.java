package com.pulawskk.demobootstrap4portfolio.models;

public class Email {
    private String from;
    private String phoneNumber;
    private String name;
    private String message;

    public Email(String from, String phoneNumber, String name, String message) {
        this.from = from;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
