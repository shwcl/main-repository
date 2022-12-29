package main;

import api.UserInputValidation;

public class Tester {

    public static void main(String[] args) {
        HotelApplication hotelApplication = new HotelApplication();
        UserInputValidation userInputValidation = new UserInputValidation();
        boolean keepRunning = true;

        while(keepRunning){
            hotelApplication.displayMainMenu();
            int choice = userInputValidation.validateMenuInput("Select an item 1 - 5:", 5);
            if(choice == 4){
                boolean displayAdminMenu = true;
                while(displayAdminMenu){
                    hotelApplication.displayAdminMenu();
                    choice = userInputValidation.validateMenuInput("Select an item 1 - 6:", 6);
                    if(choice == 6){
                        displayAdminMenu = false;
                    } else {
                        hotelApplication.processAdminMenuInput(choice);
                    }
                }
            } else if (choice == 5) {
                keepRunning = false;
            } else {
                hotelApplication.processMainMenuInput(choice);
            }
        }
    }
}
