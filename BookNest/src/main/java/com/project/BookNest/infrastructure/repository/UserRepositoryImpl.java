package com.project.BookNest.infrastructure.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.BookNest.application.service.RegisterService;
import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.model.Cart;
import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;
import com.project.BookNest.domain.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final String file_path = "users.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public UserRepositoryImpl() {
        ensureJsonFileExists();
    }



    public boolean isEmptyCart(User member){
        return member.isEmptyCart();
    }
    public boolean deleteFromCart(User member,String num) {
        return member.deleteFromCart(num);
    }
    public void addToCart(User member, Book book) {
        List<Member> members = getUsers();
        for(Member a: members ){
            if(a.getUsername().equals(member.getUsername())){
                a.addToCart(book);
                member.addToCart(book);
                break;

            }
        }
        saveUsers(members);
    }
    public void deleteCart(User member){
        List<Member> users = getUsers();

        for(Member user: users){
            if(member.getUsername().equals(user.getUsername())){
                user.deleteCartItems();
                member.deleteCartItems();
                break;
            }
        }
        saveUsers(users);
    }

    public void saveCart(User member){
        List<Member> users = getUsers();

        for(Member user: users){
            if(member.getUsername().equals(user.getUsername())){
                user.cart=member.getCart();
                break;
            }
        }
        saveUsers(users);
    }
    //jsondan getUsers() ile tum userları cekiyoruz listeye, daha sonra yeni Userimizi ekleyip listeye geri jsona kaydediyoruz
    public boolean addUser(Member user) {
        List<Member> users = getUsers();
        if(!ifExists(user,users)) {
            users.add(user);
            saveUsers(users);
            System.out.println("Registration is completed. Welcome to BookNest!");
            return false;

        }
        else{
            System.out.println("User already exists.");
            return true;

        }



    }
    public boolean ifExists(Member user, List<Member> users) {
        boolean exists = false;
        for(User u: users){
            if(u.getUsername().equals(user.getUsername())){
                exists=true;
                break;
            }
        }
        return exists;
    }
    //json dosyasından tum userların oldugu listeyi dondurur
    public List<Member> getUsers() {

        try {
            return mapper.readValue(new File(file_path),new TypeReference<List<Member>>(){});
        }catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //json'a kaydetme fonksiyonu listin
    public void saveUsers(List<Member> users) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(file_path),users);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //json dosyası yoksa yaratır
    private void ensureJsonFileExists() {
        File file = new File(file_path);
        if (!file.exists()) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<Member>());
                System.out.println("Created empty users.json file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //login yaptıgında kullanıcı jsonda var mı yok mu listeden bakar
    public boolean validate(String login, String password) {
        List<Member> usersList = getUsers();
        for (Member user1 : usersList) {
            if(user1.getUsername().equals(login) && user1.getHashedPassword().equals(RegisterService.hashPassword(password))) {
                System.out.println("Successfully logged in");
                System.out.println("Welcome " + login+"!");
                return false;
            }
        }

        System.out.println("Incorrect username or password");
        return true;
    }
    public Member getUser(String login) {
        List<Member> usersList = getUsers();
        for(Member user : usersList){
            if(user.getUsername().equals(login)){
                return user;
            }
        }
        System.out.println("NO USER FOUND");
        return null;
    }
    public void deleteFromCartJson(User member, String choice) {
        Book book = member.getBook(choice);
        List<Member> users = getUsers();
        for(Member user : users){
            if(user.getUsername().equals(member.getUsername())) {
                user.getCart().getBooks().remove(Integer.parseInt(choice));
            }
        }
        saveUsers(users);
    }


}
