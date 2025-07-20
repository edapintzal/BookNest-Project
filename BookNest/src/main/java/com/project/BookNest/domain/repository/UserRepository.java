package com.project.BookNest.domain.repository;

import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;

import java.util.List;

public interface UserRepository {
    //for registration
    public boolean addUser(Member user);
    //return list with User objects
    public List<Member> getUsers();
    //writes list with User objects to jSon
    public void saveUsers(List<Member> users);
    //validation for login
    public boolean validate(String username, String password);
    public Member getUser(String login);
    public void saveCart(User user);
    public void deleteCart(User member);
}
