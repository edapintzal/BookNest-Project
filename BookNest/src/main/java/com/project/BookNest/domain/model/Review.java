package com.project.BookNest.domain.model;

public class Review {

    private String bookname;
    private String username;
    private String content;
    private int rating;


    public Review(String bookname, String username, String content, int rating) {

        this.bookname = bookname;
        this.username = username;
        this.content = content;
        this.rating = rating;

    }
    public Review(){

    }

    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating = rating;
    }
    public String getBookname() {
        return bookname;
    }


    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
