package service;

import model.FreeRoom;
import model.Room;
import model.RoomType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdminService {
    private CustomerService customerService;
    private ReservationService reservationService;
    private static AdminService adminService = new AdminService();

    // constructor
    private AdminService(){
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
    }

    public static AdminService getInstance(){
        return adminService;
    }

    public void generateTestData() {
        Scanner scanner = new Scanner(System.in);
        boolean continueProcess = true;
        while (continueProcess) {
            System.out.println("Warning: all previously stored data will be removed and new data added. Proceed? y/n");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                reservationService.addRoom(new Room("100", 100.00, RoomType.SINGLE));
                reservationService.addRoom(new Room("101", 100.00, RoomType.SINGLE));
                reservationService.addRoom(new Room("102", 120.00, RoomType.DOUBLE));
                reservationService.addRoom(new Room("103", 120.00, RoomType.DOUBLE));
                reservationService.addRoom(new Room("104", 120.00, RoomType.DOUBLE));
                reservationService.addRoom(new Room("105", 120.00, RoomType.DOUBLE));
                reservationService.addRoom(new Room("106", 90.00, RoomType.SINGLE));
                reservationService.addRoom(new Room("107", 90.00, RoomType.SINGLE));
                reservationService.addRoom(new FreeRoom("108", RoomType.SINGLE));
                reservationService.addRoom(new FreeRoom("109", RoomType.SINGLE));
                reservationService.addRoom(new FreeRoom("110", RoomType.SINGLE));

                customerService.addCustomer("Skarr", "Skarabi", "skarr@scarabi.com");
                customerService.addCustomer("Zazuu", "Skarabi", "zazuu@skarabi.com");
                customerService.addCustomer("Simba", "Priderock", "simba@africo.com");
                customerService.addCustomer("Nalah", "Priderock", "nalah@africo.com");
                customerService.addCustomer("Mufasah", "Priderock", "mufasah@africo.com");

                reservationService.reserveARoom(customerService.getCustomer("skarr@scarabi.com"), reservationService.getARoom("100"),
                        this.convertToDate("01/12/2022"), this.convertToDate("05/12/2022"));
                reservationService.reserveARoom(customerService.getCustomer("skarr@scarabi.com"), reservationService.getARoom("100"),
                        convertToDate("10/12/2022"), convertToDate("15/12/2022"));
                reservationService.reserveARoom(customerService.getCustomer("skarr@scarabi.com"), reservationService.getARoom("100"),
                        this.convertToDate("17/12/2022"), this.convertToDate("20/12/2022"));
                reservationService.reserveARoom(customerService.getCustomer("simba@africo.com"), reservationService.getARoom("101"),
                        this.convertToDate("02/12/2022"), this.convertToDate("10/12/2022"));
                reservationService.reserveARoom(customerService.getCustomer("simba@africo.com"), reservationService.getARoom("107"),
                        this.convertToDate("15/12/2022"), this.convertToDate("20/12/2022"));
                reservationService.reserveARoom(customerService.getCustomer("nalah@africo.com"), reservationService.getARoom("110"),
                        this.convertToDate("20/12/2022"), this.convertToDate("25/12/2022"));
                System.out.println("Test data added to system");
                continueProcess = false;
            } else if (answer.equals("n")) {
                continueProcess = false;
            } else {
                System.out.println("Error: Invalid input. Try again");
            }
        }
    }

    // helper method to convert inputs to date
    private Date convertToDate (String dateString) {
        Date date = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println("Error: invalid date format.");
        }
        return  date;
    }


}