package com.project.BookNest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;
    @JsonIgnore
    private String password;
    @JsonProperty("password")
    private String hashedPassword;




    public User(){

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean showCart(){
        return false;
    }
    public Cart getCart(){
        return null;
    }
    public void deleteCartItems(){

    }



    public void addToCart(Book book){

    }
    public boolean deleteFromCart(String num){
        return false;
    }
    public boolean isEmptyCart(){
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Book getBook(String choice){
        return null;
    }
}

