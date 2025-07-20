package com.project.BookNest.infrastructure.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.BookNest.domain.model.*;
import com.project.BookNest.domain.repository.BookRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private HashMap<String, Book> books = new HashMap<>();
    public static List<String> cartList = new ArrayList<>();
    private final String file_path = "books.json";
    private final ObjectMapper mapper = new ObjectMapper();


    public BookRepositoryImpl() {

        ensureJsonFileExists();
        books = getBooks();


    }
     public void addBook(Book newBook) throws IOException {
         if(getBookById(newBook.getId())!=null){
            System.out.println("This book already exists.");
         }
         else if (getBookById(newBook.getId()) == null) {
             books=getBooks();
             books.put(newBook.getId(), newBook);
             writeBookToJson();
             System.out.println("Book is added successfully.");
         }
     }
     public void deleteBook(String id) throws IOException {
        books=getBooks();
        books.remove(id);
        writeBookToJson();
         System.out.println("Book is deleted successfully");
     }
     public void updateBook(String id,int updateLocation , String newInfo) throws IOException {
        books=getBooks();
        if(updateLocation==1){
            books.get(id).setTitle(newInfo);
        }
        else if(updateLocation==2){
            books.get(id).setAuthor(newInfo);
        } else if (updateLocation==3) {
            books.get(id).setMainGenre(newInfo);
        } else if (updateLocation==4) {
            books.get(id).setSubGenre(newInfo);
        }
        else if (updateLocation==5) {
            books.get(id).setType(newInfo);
        }
        else if (updateLocation==6) {
            books.get(id).setPrice(newInfo);
        }
        else return;
        writeBookToJson();
         System.out.println("Book is updated successfully.");
     }
    public String getBooksId(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book.getId();
            }
        }
        return null;
    }
    public Book getBookById(String id) throws IOException {
        books = getBooks();
        if(books.get(id)==null){
            return null;
        }
        books.get(id).printBook();
        return books.get(id);
    }


    public void reviewBook(String review, String id, String person, int rating) throws IOException {
        books = getBooks();
        Book book = books.get(id);
        if(book != null){
            book.getReviews().add(new Review(book.getTitle(),person, review, rating));
            writeBookToJson();
        }
    }
    public static String getStarRating(double rating) {
        int avg = (int) rating;                // Tam yıldız sayısı
        boolean hasHalfStar = (rating - avg) >= 0.5; // 0.5 ve üstü ise yarım yıldız

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < avg; i++) {
            sb.append("★");
        }
        if (hasHalfStar) {
            sb.append("☆");
        }

        int empty = 5 - avg - (hasHalfStar ? 1 : 0);
        for (int i = 0; i < empty; i++) {
            sb.append("✩");
        }

        return sb.toString();
    }
    public double avgCalculation(List<Review> reviews){
        double sum =0;
        for(Review review: reviews){
            sum+=review.getRating();
        }
        return sum/reviews.size();
    }
    public void showReviews(String bookId) {
        books = getBooks(); // JSON'dan güncel veriyi oku
        Book book = books.get(bookId);

        if (book == null) {
            System.out.println("No book found with the given ID.");
            return;
        }

        List<Review> reviews = book.getReviews();

        if (reviews == null || reviews.isEmpty()) {
            System.out.println("No reviews yet for \"" + book.getTitle() + "\".");
            return;
        }

        System.out.println("Reviews for \"" + book.getTitle() + "\":");
        System.out.println("------------------------------------------------");
        for (Review review : reviews) {
            System.out.println("User: " + review.getUserName());
            System.out.println("Rating: " +review.getRating()  + "/5");
            System.out.println("Content: " + review.getContent());
            System.out.println("------------------------------------------------");
        }

        System.out.println(getStarRating(avgCalculation(reviews)));
        System.out.println();
    }

    public HashMap<String, Book> getBooks(){
        try {
            return mapper.readValue(new File(file_path),new TypeReference<HashMap<String, Book>>(){});
        }catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }
    private void writeBookToJson() throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(
                new File("books.json"),
                books);
    }


    private void ensureJsonFileExists() {
        File file = new File(file_path);
        if (!file.exists()) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file,new HashMap<String, Book>());
                System.out.println("Created empty users.json file.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadBooks() {
        String name = "Books_df.csv";
        String line;
        try(BufferedReader br = new BufferedReader( new FileReader(name))){
            br.readLine();
            while( (line = br.readLine()) != null){
                String[] names = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                Book book= new Book.BookBuilder()
                        .id(names[0])
                        .title(names[1])
                        .author(names[2])
                        .mainGenre(names[3])
                        .subGenre(names[4])
                        .type(names[5])
                        .price(names[6])
                        .rating(Double.parseDouble(names[7]))
                        .numberOfPeopleRated(Double.parseDouble(names[8]))
                        .url(names[9])
                        .build();

                books.put(names[0],book);

            }


        }catch(IOException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public Book idSearch(String id){
        return books.get(id);
    }
    public String nameSearch(String name){
        for(Book book : books.values()){
            if(book.getTitle().toLowerCase().contains(name.toLowerCase())){
                book.printBook();
                return book.getId();
            }
        }
        System.out.println("ERROR: No such book found by name.");
        return "";
    }
    public void authorSearch(String author){
        boolean flag = false;
        for(Book book : books.values()){
            if(book.getAuthor().toLowerCase().contains(author.toLowerCase())){
                book.printBook();
                System.out.println("--------------------------------");
                flag = true;
            }
        }
        if(!flag){
            System.out.println("ERROR: No such author found by author name.");
        }
    }
    public void mainGenreSearch(String genre){
        boolean flag = false;
        for(Book book : books.values()){
            if(book.getMainGenre().toLowerCase().contains(genre.toLowerCase())){
                book.printBook();
                System.out.println("--------------------------------");
                flag = true;
            }
        }
        if(!flag){
            System.out.println("ERROR: No such genre found by genre name.");
        }
    }

    public void addToCart(User member , String id){
        Book book = books.get(id);
        member.addToCart(book);
    }









}
