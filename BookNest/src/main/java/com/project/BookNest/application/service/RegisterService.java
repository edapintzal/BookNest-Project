package com.project.BookNest.application.service;

import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;
import com.project.BookNest.infrastructure.repository.UserRepositoryImpl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

////this class is responsible for Register
public class RegisterService {

    UserRepositoryImpl userRepository;
    public RegisterService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
    //user'reposuna registerdan dolayı yeni kullanıcı ekler fakat öncesinde şifresini hashler
    public boolean addUser(Member user) {
        String hash = hashPassword(user.getPassword());
        user.setHashedPassword(hash);
        return userRepository.addUser(user);
    }
    //şifre hashleme ve user'in hashedpassword değişkenine atama
    public static String hashPassword(String password) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');  // başına 0 ekle (tek haneliyse)
                hexString.append(hex);
            }
            return hexString.toString();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return "0";

    }

}
