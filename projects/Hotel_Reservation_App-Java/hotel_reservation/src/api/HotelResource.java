package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource  hotelResource = new HotelResource();
    private CustomerService customerService;
    private ReservationService reservationService;

    // constructor
    private HotelResource(){
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
    }

    public static HotelResource getInstance(){
        return hotelResource;
    }

    void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void getAllCustomers(){
        customerService.getAllCustomers();
    }

    public Reservation bookAroom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return reservationService.findRooms(checkInDate, checkOutDate);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        return  reservationService.getCustomersReservation(customer);
    }


}
