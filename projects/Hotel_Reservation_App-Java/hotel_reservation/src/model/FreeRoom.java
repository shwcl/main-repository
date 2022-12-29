package model;

public class FreeRoom extends Room{

    // constructor
    public FreeRoom(String roomNumber, RoomType enumeration){
        super(roomNumber, 0.0, enumeration);
    }

    //to string
    @Override
    public String toString(){
        return super.toString() + " Free";
    }
}
