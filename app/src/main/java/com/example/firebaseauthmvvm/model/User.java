package com.example.firebaseauthmvvm.model;

public class User {
    String email,pass,uuid;
    boolean isLogged;

    public User(){

    }
    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;

    }
    public User(String email,String uuid,boolean isLogged){
        this.email = email;
        this.uuid =uuid;
        this.isLogged  = isLogged;
    }


    public boolean isLogged() {
        return isLogged;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getUuid() {
        return uuid;
    }




}
