package com.project.BookNest.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;
@JsonSerialize
public class Cart {

    @JsonProperty
    private List<Book> books = new ArrayList<Book>();

    public void addToCart(Book book){
        books.add(book);
    }
    public boolean showCart(){
        int counter = 0;
        if(books.isEmpty()){

            return false;
        }
        System.out.println("Your cart is: \n");
        for(Book book : books){
            System.out.println(counter+") "+book.getPrice()+ "  |  "+book.getTitle());
            counter++;
        }
        return true;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void deleteItems(){

       books.clear();

    }
    public boolean deleteFromCart(String choice){
        int count=0;
        boolean flag=false;
        for(Book book : books){

            if(count==Integer.parseInt(choice)){
                books.remove(count);
                flag=true;
                break;
            }
            count++;

        }
        return flag;


    }
    @JsonIgnore
    public boolean isEmptyCart(){
        return books.isEmpty();
    }
    public Book getBook(String choice){
        int count=0;
        for(Book book : books){
            if(count==Integer.parseInt(choice)){
                return book;
            }
            count++;
        }
        return null;
    }
}
