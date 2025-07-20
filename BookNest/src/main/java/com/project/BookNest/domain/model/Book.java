package com.project.BookNest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = Book.BookBuilder.class)
public class Book {

    private  String id;
    private  String title;
    private  String author;
    private  String mainGenre;
    private  String subGenre;
    private  String type;
    private  String price;
    @JsonIgnore
    private  double rating;
    @JsonIgnore
    private  double numberOfPeopleRated;
    private  String url;



    private List<Review> reviews = new ArrayList<Review>();
    public Book(){};

    private Book(BookBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.mainGenre = builder.mainGenre;
        this.subGenre = builder.subGenre;
        this.type = builder.type;
        this.price = builder.price;
        this.rating = builder.rating;
        this.numberOfPeopleRated = builder.numberOfPeopleRated;
        this.url = builder.url;
        this.reviews = builder.reviews != null ? builder.reviews : new ArrayList<>();

    }



    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getMainGenre() {
        return mainGenre;
    }

    public void setMainGenre(String mainGenre) {
        this.mainGenre = mainGenre;
    }
    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public double getNumberOfPeopleRated() {
        return numberOfPeopleRated;
    }

    public void setNumberOfPeopleRated(double numberOfPeopleRated) {
        this.numberOfPeopleRated = numberOfPeopleRated;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void printBook(){
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Main Genre: " + mainGenre);
        System.out.println("Sub Genre: " + subGenre);
        System.out.println("Type: " + type);
        System.out.println("Price: " + price);
    }


    @JsonPOJOBuilder(withPrefix = "")
    public static class BookBuilder{

        private String id;
        private String title;
        private String author;
        private String mainGenre;
        private String subGenre;
        private String type;
        private String price;
        private double rating;
        private double numberOfPeopleRated;
        private String url;
        private List<Review> reviews;

        public BookBuilder reviews(List<Review> reviews){
            this.reviews = reviews;
            return this;
        }

        public BookBuilder id(String id){
            this.id = id;
            return this;
        }
        public BookBuilder title(String title){
            this.title = title;
            return this;
        }
        public BookBuilder author(String author){
            this.author = author;
            return this;
        }
        public BookBuilder mainGenre(String mainGenre){
            this.mainGenre = mainGenre;
            return this;
        }
        public BookBuilder subGenre(String subGenre){
            this.subGenre = subGenre;
            return this;
        }
        public BookBuilder type(String type){
            this.type = type;
            return this;
        }
        public BookBuilder price(String price){
            this.price = price;
            return this;
        }
        public BookBuilder rating(double rating){
            this.rating = rating;
            return this;
        }
        public BookBuilder numberOfPeopleRated(double numberOfPeopleRated){
            this.numberOfPeopleRated = numberOfPeopleRated;
            return this;
        }
        public BookBuilder url(String url){
            this.url = url;
            return this;
        }
        public Book build(){
            return new Book(this);
        }




    }



}
