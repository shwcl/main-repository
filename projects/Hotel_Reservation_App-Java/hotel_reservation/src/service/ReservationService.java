package service;
import model.*;
import java.util.*;

public class ReservationService {
    private Collection<IRoom> rooms;
    private Collection<Reservation> reservations;
    private static ReservationService reservationService = new ReservationService();

    // constructor
    private ReservationService(){
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public static ReservationService getInstance(){
        return reservationService;
    }

    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public void getAllRooms(){
        for(IRoom r: rooms){
            System.out.println(r.toString());
        }
    }

    public IRoom getARoom(String roomId){
        for(IRoom r: rooms){
            if(r.getRoomNumber().equals(roomId)){
                return r;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<Reservation>getCustomersReservation(Customer customer){
        Collection<Reservation>customersReservation = new ArrayList<>();
        for(Reservation res: reservations){
            if(res.getCustomer().equals(customer)){
                customersReservation.add(res);
            }
        }
        return customersReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom>availableRooms = new HashSet<>();
        Collection<IRoom>conflictRooms = new HashSet<>();
        // 1. traverse resv list and run algo to identify/store conflicting rooms
        for(Reservation res: reservations){
            if(  // requested check-in date <= resv check-in date AND requested check-out date >= resv check-in date
               (checkInDate.before(res.getCheckInDate()) || checkInDate.equals(res.getCheckInDate())) &&
                       (checkOutDate.after(res.getCheckInDate()) || checkOutDate.equals(res.getCheckInDate()))
                ||
                // OR requested check-in date <= resv check-out date AND requested checkout >= resv check-out date
               (checkInDate.before(res.getCheckOutDate()) || checkInDate.equals(res.getCheckOutDate())) &&
                       (checkOutDate.after(res.getCheckOutDate()) || checkOutDate.equals(res.getCheckOutDate()))
              ){
                    conflictRooms.add(res.getRoom());
            }
        }
        // 2. Run the rev list again and identify all rooms not part of "conflict rooms" list
        for(Reservation res: reservations){
            if(!(conflictRooms.contains(res.getRoom()))){
                availableRooms.add(res.getRoom());
            }
        }
        // 3. Add all unreserved rooms to the Available rooms list:
        for(IRoom r: this.rooms){
            int count = 0;
            for(Reservation rev : reservations){
                if(r.equals(rev.getRoom())){
                    count++;
                }
            }
            if (count == 0){
                availableRooms.add(r);
            }
        }
        return availableRooms;
    }

    public void printAllReservation(){
        for(Reservation res: reservations){
            System.out.println(res.toString());
        }
    }
}
