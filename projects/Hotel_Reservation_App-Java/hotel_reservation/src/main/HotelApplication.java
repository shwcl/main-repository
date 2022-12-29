package main;

import api.AdminMenu;
import api.MainMenu;

public class HotelApplication {
    private MainMenu mainMenu;
    private AdminMenu adminMenu;

    // constructor
    public HotelApplication (){
        mainMenu = new MainMenu();
        adminMenu = new AdminMenu();
    }

    public void addRoom(){
        adminMenu.AddRoom();
    }

    public void getAllRooms(){
        adminMenu.getAllRooms();
    }

    public void createCustomer(){
        mainMenu.createCustomer();
    }

    public void getAllCustomers(){
        mainMenu.getAllCustomers();
    }

    public void displayAvailableRooms(){
        mainMenu.displayAvailableRooms();
    }

    public void displayAllReservation(){
        adminMenu.displayAllReservation();
    }

    public void displayCustomersReservation(){
        mainMenu.getCustomersReservation();
    }

    //method to display main Menu
    public void displayMainMenu(){
        mainMenu.displayMainMenu();
    }

     //method to display admin menu
    public void displayAdminMenu(){
        adminMenu.displayAdminMenu();
    }


    public void processMainMenuInput(int choice){

        switch ((choice)){
            case 1: displayAvailableRooms();
                break;
            case 2: displayCustomersReservation();
                break;
            case 3: createCustomer();
                break;
            case 4: displayAdminMenu();
                break;
            case 5: System.out.println("exit..");
                break;
        }
    }

    public void processAdminMenuInput(int choice){

        switch ((choice)){
            case 1: getAllCustomers();
                break;
            case 2: getAllRooms();
                break;
            case 3: displayAllReservation();
                break;
            case 4: addRoom();
                break;
            case 5: generateTestData();
                break;
            case 6:
                System.out.println("Back to Main Menu..");
                break;
            default: System.out.println("Not a valid selection");
        }
    }


    public void generateTestData(){
        adminMenu.generateTestData();
    }

}
