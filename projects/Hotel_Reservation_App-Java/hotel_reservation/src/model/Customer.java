package model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;


    //constructor 1
    public Customer(String firstName, String lastName, String email){
        this.validateEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    //constructor 2
    public Customer(String email){
        this.validateEmail(email);
        this.firstName = "john";
        this.lastName = "Smith";
        this.email = email;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    public void validateEmail(String email){
        String emailPattern = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailPattern);
        if(!(pattern.matcher(email).matches())){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: "
                + lastName + " Email: " + email;
    }
}
