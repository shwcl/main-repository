package api;

import model.RoomType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class UserInputValidation {
    private Scanner scanner;

    // constructor
    public UserInputValidation(){
        scanner = new Scanner(System.in);
    }

    public String validateFirstNameInput(){
        String firstName = null;
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter first name: ");
            firstName = scanner.nextLine();
            if (firstName.length() > 0) {
                valid = true;
            }
        }
        return firstName;
    }

    public String validateLastNameInput(){
        String lastName = null;
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter last name: ");
            lastName = scanner.nextLine();
            if (lastName.length() > 0) {
                valid = true;
            }
        }
        return  lastName;
    }


    public String validateEmailLength(){
        String email = null;
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter your email address in the format name@domain.com: ");
            email = scanner.nextLine();
            if (email.length() > 0) {
                valid = true;
            }
        }
        return email;
    }

    public String validateRoomNumberInput(){
        String roomNumber = null;
        boolean valid = false;
        Pattern pattern = Pattern.compile("[0-9]+");

        while(!valid) {
            System.out.println("Enter room number: ");
            roomNumber = scanner.nextLine();
            if (pattern.matcher(roomNumber).matches()) {
                valid = true;
            } else {
                System.out.println("Error: invalid input");
            }
        }
        return roomNumber;
    }

    public Double validatePriceInput() {
        double price = 0.0;
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input");
            }
        }
        return price;
    }

    public RoomType validateRoomTypeInput() {
        String roomTypeSelection = null;
        RoomType roomType = null;
        boolean valid = false;

        while(!valid){
            System.out.println("Enter room type: 's' for Single bed. 'd' for Double bed");
            roomTypeSelection = scanner.nextLine();
            if (roomTypeSelection.equals("s")){
                roomType = RoomType.SINGLE;
                valid = true;
            } else if (roomTypeSelection.equals("d")) {
                roomType = RoomType.DOUBLE;
                valid = true;
            } else {
                System.out.println("Error: invalid input");
            }
        }
        return roomType;
    }

    public String getAnswer(String question){
        String result = null;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(question);
            String input = scanner.nextLine();
            if(input.equals("y") || input.equals("n")) {
               result = input.equals("y")? "y" : "n";
               keepRunning = false;
            } else {
                System.out.println("Error: Invalid input");
            }
        }
        return result;
    }

    // helper method to convert date string input to date
    public Date validateDateInput (String promptMsg) {
        Scanner scanner = new Scanner(System.in);
        Date date = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);

        boolean valid = false;
        while(!valid){
            try {
                System.out.println(promptMsg);
                String input = scanner.nextLine();
                date = simpleDateFormat.parse(input);
                valid = true;

            } catch (ParseException e){
                System.out.println(e.getLocalizedMessage());
                System.out.println("Error: invalid date format.");
            }
        }
        return date;
    }

    public int validateMenuInput(String promptMsg, int numberOfMenuItems){
        scanner = new Scanner(System.in);
        boolean valid = false;
        int numberSelected = 0;
        while(!valid){
            try {
                System.out.println(promptMsg);
                String inputStr = scanner.nextLine();
                int inputInt = parseInt(inputStr);
                if(inputInt >=1 && inputInt <= numberOfMenuItems){
                    numberSelected = inputInt;
                    valid = true;
                } else {
                    System.out.println("Error: not a valid selection");
                }
            } catch (NumberFormatException e){
                System.out.println(e.getLocalizedMessage());
                System.out.println("Error: invalid number format");
            }
        }
        return numberSelected;
    }
}