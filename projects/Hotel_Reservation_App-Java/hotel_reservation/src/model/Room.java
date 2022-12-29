package model;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    // constructor
    public Room(String roomNumber, Double price, RoomType enumeration){
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public final boolean isFree(){
        if(this.price==0){
            return true;
        }
        return false;
    }

    //Create getters
    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    // create toString()
    @Override
    public String toString(){
        return "Room Number: " + roomNumber + " Price: "
                + price + " Room Type: " + enumeration;
    }








}
