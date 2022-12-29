package api;

import model.IRoom;
import service.AdminService;
import service.ReservationService;

public class AdminResource {
    private static AdminResource adminResource = new AdminResource();
    private ReservationService reservationService;
    private AdminService adminService;

    //constructor
    private AdminResource(){
        reservationService = ReservationService.getInstance();
        adminService = AdminService.getInstance();
    }

    public static AdminResource getInstance(){
        return adminResource;
    }

    // add a room to the rooms list in the service class
    public void addRoom(IRoom room){
        reservationService.addRoom(room);
    }

    public void getAllRooms(){
        reservationService.getAllRooms();
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public void displayAllReservation(){
        reservationService.printAllReservation();
    }

    public void generateTestData(){
        adminService.generateTestData();
    }


}

