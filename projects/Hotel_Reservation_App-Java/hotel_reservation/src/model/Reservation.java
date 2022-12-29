package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    // constructor
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {

        return "Customer Name: " + customer.getFirstName() + " " + customer.getLastName() + " Room: " +
                room + " Check-in date: " + checkInDate + " Check-out date: " + checkOutDate;
    }


}
