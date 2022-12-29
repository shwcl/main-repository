package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private Collection<String> mainMenuItems;
    private HotelResource hotelResource;
    private Collection<IRoom> availableRooms;
    private UserInputValidation userInputValidation;

    //constructor
    public MainMenu(){
        hotelResource = HotelResource.getInstance();

        availableRooms = new ArrayList<>();
        userInputValidation = new UserInputValidation();

        // initialize menu list
        mainMenuItems = new ArrayList<>();

        // add Menu items
        mainMenuItems.add("--- Hotel Reservation App ---");
        mainMenuItems.add("1. Find and reserve a room");
        mainMenuItems.add("2. See my reservations");
        mainMenuItems.add("3. Create an account");
        mainMenuItems.add("4. Admin");
        mainMenuItems.add("5. Exit");
    }

    // Method to display Main Menu
    public void displayMainMenu(){
        for(String menuItem: mainMenuItems){
            System.out.println(menuItem);
        }
    }

    public void createCustomer() {
        boolean addCustomer = true;

        while(addCustomer) {
            String firstName = userInputValidation.validateFirstNameInput();
            String lastName = userInputValidation.validateLastNameInput();
            String email = userInputValidation.validateEmailLength();
            try {
                if(hotelResource.getCustomer(email) != null) {
                    System.out.println("Error: Customer account already exist.");
                } else {
                    hotelResource.createACustomer(firstName,lastName,email);
                    System.out.println("Customer account added successfully");
                    addCustomer = false;
                }
            } catch (IllegalArgumentException e){
                e.getLocalizedMessage();
                System.out.println("Error: invalid email");
            }
        }
    }

    public void getAllCustomers(){
        hotelResource.getAllCustomers();
    }

    public void getCustomersReservation(){
        Collection<Reservation>customerReservation;
        String email = userInputValidation.validateEmailLength();

        try {
            Customer customer = hotelResource.getCustomer(email);
            if (customer == null){
                System.out.println("Error: Email address does not exist");
            } else{
                customerReservation = hotelResource.getCustomersReservation(customer);
                if(customerReservation.size() == 0){
                    System.out.println("No reservation found for customer with email address " + email);
                } else {
                    for(Reservation res: customerReservation) {
                        System.out.println(res.toString());
                    }
                }
            }
        } catch (IllegalArgumentException e){
            e.getLocalizedMessage();
            System.out.println("Error: invalid email");
        }
    }

    public void displayAvailableRooms() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        Date checkInDate = userInputValidation.validateDateInput("Enter check-in date in format dd/mm/yyyy: ");
        Date checkOutDate = userInputValidation.validateDateInput("Enter check-out date in format dd/mm/yyyy: ");
        if (checkInDate.after(currentDate) && checkInDate.before(checkOutDate)) {
            availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
            if (availableRooms.size() == 0) {
                if (getSuggestedRooms(checkInDate, checkOutDate).size() > 0) {
                    availableRooms = getSuggestedRooms(checkInDate, checkOutDate);
                    System.out.println("--- Available rooms from " + simpleDateFormat.format(addDaysToDate(checkInDate))
                            + " to " + simpleDateFormat.format(addDaysToDate(checkOutDate)));
                    for (IRoom r : availableRooms) {
                        System.out.println(r.toString());
                    }
                } else {
                    System.out.println("No available rooms");
                }
            } else {
                System.out.println("--- Available Rooms ---");
                for (IRoom r : availableRooms) {
                    System.out.println(r.toString());
                }
                if (userInputValidation.getAnswer("Do you want to book a room? y/n").equals("y"))
                    reserveRoom(checkInDate, checkOutDate);
            }
        } else {
            System.out.println("Error: check-in date is greater or equal to check-out date or less than or equal to current date");
        }
    }

    // Book a room
    public void reserveRoom(Date checkInDate, Date checkOutDate) {
        if (userInputValidation.getAnswer("Do you have an account? y/n").equals("y")) {
            String email = userInputValidation.validateEmailLength();
            Customer customer = hotelResource.getCustomer(email);
            if (customer == null) {
                System.out.println("Error: email address does not exist");
            } else {
                String roomNumber = userInputValidation.validateRoomNumberInput();
                IRoom room = hotelResource.getRoom(roomNumber);
                if (room == null) {
                    System.out.println("Error: Room number does not exist");
                } else {  // check that room exist in available rooms list before booking room
                    if (availableRooms.contains(room)) {
                        hotelResource.bookAroom(customer, room, checkInDate, checkOutDate);
                        System.out.println("Room successfully reserved by customer with email address " + email);
                    } else {
                        System.out.println("Room number not available");
                    }
                }
            }
        } else {
            System.out.println("You must first create an account to book a room. " +
                    "To create an account, select Create an account from the Main Menu");
        }
    }

    private Collection<IRoom> getSuggestedRooms(Date checkInDate, Date checkOutDate) {
        availableRooms = hotelResource.findARoom(addDaysToDate(checkInDate), addDaysToDate(checkOutDate));
        return availableRooms;
    }

    private Date addDaysToDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,7);
        return calendar.getTime();
    }
}
