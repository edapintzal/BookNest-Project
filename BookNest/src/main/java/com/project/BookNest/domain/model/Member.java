package com.project.BookNest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {


    public Cart cart = new Cart();

    @JsonIgnore
    List<Order> order = new ArrayList<>();
    public Member(String username, String password) {
        super(username, password);


    }
    public Member() {

    }
    @Override
    public void addToCart(Book book){
        cart.addToCart(book);
    }
    @Override
    public boolean showCart(){
        return cart.showCart();
    }
    @Override
    public Cart getCart() {
        return cart;
    }
    @Override
    public void deleteCartItems(){
        cart.deleteItems();
    }
    @Override
    public boolean deleteFromCart(String num){
        return cart.deleteFromCart(num);
    }
    @JsonIgnore
    public boolean isEmptyCart(){
        return cart.isEmptyCart();
    }
    @Override
    public Book getBook(String choice){
        return cart.getBook(choice);
    }



}
