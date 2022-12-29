package api;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private AdminResource adminResource;
    private List<String> adminMenuItems;
    private UserInputValidation userInputValidation;

    //constructor
    public AdminMenu(){
        adminResource = AdminResource.getInstance();
        userInputValidation = new UserInputValidation();

        adminMenuItems = new ArrayList<>();
        adminMenuItems.add("Admin Menu");
        adminMenuItems.add("----------");
        adminMenuItems.add("1. See all Customers");
        adminMenuItems.add("2. See all Rooms");
        adminMenuItems.add("3. See all Reservations");
        adminMenuItems.add("4. Add a Room");
        adminMenuItems.add("5. Add Test Data");
        adminMenuItems.add("6. Back to Main Menu");
    }

    public void getAllRooms(){
        adminResource.getAllRooms();
    }

    public void AddRoom(){
        Scanner scanner = new Scanner(System.in);
        boolean continueProcess = true;
        while(continueProcess){
            try {
                System.out.println("Enter a selection number: 1 - Paid Room. 2 - Free Room");
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1){
                    addPaidRoom();
                    if(userInputValidation.getAnswer("Add another room? 'y' for Yes. 'n' for No").equals("n")) {
                        continueProcess = false;
                    }
                } else if (input == 2) {
                    addFreeRoom();
                    if(userInputValidation.getAnswer("Add another room? 'y' for Yes. 'n' for No").equals("n")) {
                        continueProcess = false;
                    }
                } else {
                    System.out.println("Error: selected number out of range");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input");
            }
        }
    }


    private void addPaidRoom(){
        String roomNumber = userInputValidation.validateRoomNumberInput();
        Double price = userInputValidation.validatePriceInput();
        RoomType roomType = userInputValidation.validateRoomTypeInput();
        if(adminResource.getRoom(roomNumber) != null){
            System.out.println("Error: Room number already exists");
        } else {
            IRoom room = new Room(roomNumber,price,roomType);
            adminResource.addRoom(room);
            System.out.println("Room added successfully");
        }
    }


    private void addFreeRoom(){
        String roomNumber = userInputValidation.validateRoomNumberInput();
        RoomType roomType = userInputValidation.validateRoomTypeInput();
        if (adminResource.getRoom(roomNumber) != null) {
            System.out.println("Error: Room number already exists");
        } else {
            IRoom room = new FreeRoom(roomNumber, roomType);
            adminResource.addRoom(room);
            System.out.println("Room added successfully");
        }
    }

    public void displayAdminMenu(){
        AdminMenu adminMenu = new AdminMenu();
        for(String item: adminMenuItems){
            System.out.println(item);
        }
    }

    public void displayAllReservation(){
        adminResource.displayAllReservation();
    }

    public void generateTestData(){
        adminResource.generateTestData();
    }

}