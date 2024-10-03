/**
 *
 * @author Group 4
 */
package manager;

import core.RoomList;
import dto.Room;
import util.Inputter;
import util.RoomType;

/*
Program flow:
Main -> manager.RoomManager -> core.RoomList -> dto.Room
Main -> manager.StudentManager -> core.StudentList -> dto.Student
Main -> manager.BookingManager -> core.BookingList -> dto.Booking
*/

public class RoomManager {
    
    private RoomList roomList;

    public RoomManager(RoomList roomList) {
        this.roomList = roomList;
    }
    
    public void loadData(String filename) {
        roomList.loadData(filename);
        System.out.format("Loaded rooms from %s.\n", filename);
    }
    
    public void createNewRoomAtEnd() {
        System.out.println("\nPlease insert infomation for the new room:");
        
        String rcode, rname, dom, floor;
        RoomType roomType;
        double price;
        int booked;
        
        // get rcode
        System.out.print("Room ID: ");
        rcode = Inputter.getString();
        
        // get rname
        System.out.print("Room Name: ");
        rname = Inputter.getString();
        
        // get dom
        System.out.print("Room's dorm name: ");
        dom = Inputter.getString();
        
        // get floor
        System.out.print("Room's at which floor: ");
        floor = Inputter.getString();
        
        // get room type DOUBLE or TRIPLE, Inputter can handle this
        roomType = Inputter.getRoomType();
        
        // get amount of booked beds in the room
        booked = Inputter.getBookedBeds(roomType);
        
        // get room's price
        price = Inputter.getPrice();
        
        Room room = new Room(rcode, rname, dom, floor, roomType, booked, price);        
        roomList.addToEnd(room);
        System.out.format("Created new room.\n");
    }
    
}
