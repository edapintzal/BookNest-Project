package com.project.BookNest.presentation.ui;

import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;
import com.project.BookNest.presentation.api.AdminController;
import com.project.BookNest.presentation.api.BookController;
import com.project.BookNest.presentation.api.Director;
import com.project.BookNest.presentation.api.UserController;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //user controller ile register ve login kısımları kontrol edilecek, menu icinde kullanacagız
        Director director = new Director();
        AdminController adminController = director.createAdminController();
        UserController userController = director.createUserController();
        BookController bookController = director.createBookController();
        boolean flag = true;
        boolean isAdmin = false;
// csv dosyasını kullanmıyoruz artık, tum verılerı jsona attık
        Scanner scanner = new Scanner(System.in);
        User member = null;
        System.out.println("Welcome to BookNest. Place where you can buy and discuss books!");
        while (flag) {
            System.out.println("Please register or login. (1/2)" + "\n");

            String username = "";
            String password = "";

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    //kullanıcıdan login ve password alıp Stringlerin icine kaydedecegiz
                    System.out.println("Enter your username: ");
                    username = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    password = scanner.nextLine();

                    flag = userController.registerUser(new Member(username, password));
                    member =userController.getUser(username);

                    break;

                case "2":
                    System.out.println("Enter your username: ");
                    username = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    password = scanner.nextLine();
                    if(username.equals("admin") && password.equals("admin123")){
                        isAdmin=true;
                    }

                    flag = userController.login(username, password);
                    member = userController.getUser(username);






                    break;

                default:
                    System.out.println("Invalid input. Try again.");


            }
        }
        userSession(scanner,member,bookController,userController,isAdmin);


    }
    private static void userSession(Scanner scanner, User member, BookController bookController, UserController userController , Boolean isAdmin) throws IOException {
        boolean sessionActive = true;

        if(!isAdmin) {
            while (sessionActive) {
                System.out.println("\n--- User Menu ---");
                System.out.println("1. Search book by name");
                System.out.println("2. Search an author's book(s)");
                System.out.println("3. Search book by genre");
                System.out.println("4. View cart");
                System.out.println("5. Logout");

                System.out.print("Your choice: ");
                String menuChoice = scanner.nextLine();

                switch (menuChoice) {
                    case "1":
                        System.out.print("Enter book name: ");
                        String title = scanner.nextLine();
                        String id = bookController.nameSearch(title);
                        if(id.equals("")) break;
                        System.out.println("Would you like to add a review,show reviews,add to cart?(1/2/3)");
                        int book_choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (book_choice) {
                            case 1:
                                System.out.println("Enter your review content: ");
                                String reviewContent = scanner.nextLine().trim();
                                System.out.println("\n");
                                System.out.print("Enter your rating (1-5)");
                                int rating = Integer.parseInt(scanner.nextLine());
                                bookController.reviewBook(reviewContent, id, member.getUsername(), rating);
                                break;
                            case 2:
                                bookController.showReviews(id);
                                break;
                            case 3:
                                userController.addToCart(member, bookController.getBookById(id));


                                break;

                        }
                        break;

                    case "2":
                        System.out.print("Enter author name: ");
                        String author = scanner.nextLine();
                        bookController.authorSearch(author);
                        break;

                    case "3":
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        bookController.mainGenreSearch(genre);
                        break;


                    case "4":
                        System.out.println("-----------------------------------------");
                        boolean check = userController.showCart(member);
                        System.out.println();

                        if (!check) {
                            System.out.println("CART IS EMPTY");
                            break;
                        }

                        System.out.println("Type the number of the row if you want to delete a book from the cart.");
                        System.out.println("Type 'OK' if you want to buy books.");
                        System.out.println("Type EXIT else to stop.");
                        String choice = scanner.nextLine();

                        if (choice.equalsIgnoreCase("OK")) {
                            System.out.println("+----------Write OK to confirm purchase or NO to continue shopping-----------+");
                            String accept = scanner.nextLine();

                            if (accept.equalsIgnoreCase("OK")) {
                                System.out.println();
                                System.out.print("Your name: ");
                                String name = scanner.nextLine();

                                System.out.print("Your number: ");
                                String number = scanner.nextLine();

                                System.out.print("Your address: ");
                                String address = scanner.nextLine();

                                System.out.println("---------------------------");
                                System.out.println("Order has been placed. Payment is at the door.");
                                userController.deleteCart(member);
                                break;
                            } else if (accept.equalsIgnoreCase("NO")) {
                                System.out.println("Returning to shopping menu...");
                                break;
                            } else {
                                System.out.println("Invalid response. Returning to menu...");
                                break;
                            }

                        } else if (choice.equalsIgnoreCase("EXIT")) {
                            System.out.println("Exited from cart.");
                            break;
                        } else {

                            try {
                                Integer.parseInt(choice);


                            } catch (Exception e) {
                                System.out.println("Invalid input. Try again.");
                                break;
                            }

                            while (true) {

                                userController.deleteFromCartJson(member, choice);
                                boolean flag = userController.deleteFromCart(member, choice);


                                if (userController.isEmptyCart(member)) {
                                    System.out.println("----------------------");
                                    System.out.println("Cart is empty. Returning to menu...");
                                    break;
                                }
                                if (!flag) {

                                    System.out.println("No such row.");
                                    break;
                                }
                                member.showCart();
                                System.out.println("Book removed from cart.");

                                System.out.println("Enter row to delete book from cart or STOP to return.");
                                choice = scanner.nextLine();
                                if (choice.equalsIgnoreCase("STOP")) {
                                    break;
                                }
                            }

                            break;
                        }


                    case "5":
                        System.out.println("Logging out...");

                        sessionActive = false;
                        break;

                    default:
                        System.out.println("Invalid selection. Please choose 1-6.");
                }
            }
        }
        else{
            while(sessionActive){
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1.Add a New Book.");
                System.out.println("2.Delete a Book.");
                System.out.println("3.Update a Book.");
                System.out.println("4.Search a Book With ID.");
                System.out.println("5.Search a Book With Title.");
                System.out.println("6.Search a Book With Author Name.");
                System.out.println("7.Log Out.");
                scanner = new Scanner(System.in);
                int choice = scanner.nextInt();;
                switch (choice){
                    case 1:
                        scanner.nextLine();
                        System.out.println("Please enter book informations: ");

                        System.out.println("ID: ");
                        String id = scanner.nextLine();

                        System.out.println("Title: ");
                        String title = scanner.nextLine();

                        System.out.println("Author: ");
                        String author = scanner.nextLine();

                        System.out.println("Main Genre: ");
                        String mainGenre = scanner.nextLine();

                        System.out.println("Sub Genre: ");
                        String subGenre = scanner.nextLine();

                        System.out.println("Type: ");
                        String type = scanner.nextLine();

                        System.out.println("Price: ");
                        String price = scanner.nextLine();
                        Book newBook = new Book.BookBuilder()
                                .id(id)
                                        .title(title)
                                                .author(author)
                                                        .mainGenre(mainGenre)
                                                                .subGenre(subGenre)
                                                                        .type(type)
                                                                                .price(price).build();



                        newBook.printBook();
                        bookController.addBook(newBook);
                        break;
                    case 2:
                        scanner=new Scanner(System.in);
                        System.out.println("Please enter the ID number of book: ");
                        String delete = scanner.nextLine();
                        bookController.getBookById(delete);
                        System.out.println("Do you want to delete this book? Enter 1 for 'YES' , Enter 2 for 'NO' ");
                        String decision = scanner.nextLine();
                        if(decision.equals("1")){
                            bookController.deleteBook(delete);
                            break;
                        }
                        else break;

                    case 3:
                        scanner=new Scanner(System.in);
                        System.out.println("Please enter the ID number of book.");
                        String updateId = scanner.nextLine();
                        bookController.getBookById(updateId);
                        System.out.println("Choose the information that you want to update. \n1-Title \n2-Author \n3-Main Genre \n4-Sub Genre \n5-Type  \n6-Price ");
                        int updateLocation= scanner.nextInt();
                        scanner=new Scanner(System.in);
                        if(updateLocation==1 || updateLocation==2 || updateLocation==3 || updateLocation==4|| updateLocation==5 || updateLocation==6) {
                            System.out.println("Enter the updated information:");
                            String newInfo = scanner.nextLine();
                            bookController.updateBook(updateId, updateLocation, newInfo);
                            System.out.println("Updated Version: ");
                            bookController.getBookById(updateId);
                            break;
                        }
                        else {
                            System.out.println("Invalid choice. Try again.");
                            return;
                        }
                    case 4:
                        scanner = new Scanner(System.in);
                        System.out.println("Enter the ID number:");
                        String searchId = scanner.nextLine();
                        bookController.getBookById(searchId);
                        if(bookController.getBookById(searchId)==null){
                            System.out.println("Invalid ID");
                        }
                        break;
                    case 5:
                        scanner = new Scanner(System.in);
                        System.out.println("Enter the name of book:");
                        String name = scanner.nextLine();
                        bookController.nameSearch(name);
                        break;
                    case 6:
                        scanner = new Scanner(System.in);
                        System.out.println("Enter the name of author:");
                        String authorName = scanner.nextLine();
                        bookController.authorSearch(authorName);
                        break;
                    case 7:
                    System.out.println("Logging out...");
                    sessionActive = false;
                    break;

                    default:
                        System.out.println("Invalid selection. Please choose 1-6.");

            }

        }
    }
}}


   