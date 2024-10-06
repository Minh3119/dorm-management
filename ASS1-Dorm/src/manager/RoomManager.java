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

    static String filename = "resources/rooms.txt";

    private RoomList roomList;

    public RoomManager(RoomList roomList) {
        this.roomList = roomList;
    }

    // 1.1
    public void loadData() {
        roomList.loadData(filename);
        System.out.format("Loaded rooms from %s.\n", filename);
    }

    // 1.2
    public void createNewRoomAtEnd() {
        System.out.println("Please insert infomation for the new room:");

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
        System.out.println("Number of booked beds: ");
        booked = Inputter.getInt(0, roomType.getBeds());

        // get room's price
        price = Inputter.getPrice();

        Room room = new Room(rcode, rname, dom, floor, roomType, booked, price);
        roomList.addToEnd(room);
        System.out.format("Created new room.\n");
    }

    // 1.3
    public void display() {
        roomList.display();
    }

    // 1.4
    public void saveData() {
        roomList.saveData(filename);
    }

    // 1.5
    public void searchRoomByCode() {
        String input = Inputter.getString();
        roomList.searchBookedRoomByCode(input);
    }

    // 1.6
    public void deleteRoomByCode() {
        String input = Inputter.getString();
        roomList.deleteByCode(input);
    }

    // 1.7
    public void sort() {
    }

    // 1.8
    public void createNewRoomAtBeginning() {
        Room room = new Room();
        roomList.addToBeginning(room);
    }

    // 1.9
    public void createNewRoomBeforeIndex() {
        Room room = new Room();
        int index = Inputter.getInt(0, 0);
        roomList.addBeforeIndex(index, room);
        //roomList.addBeforeIndex(index, room);
    }

    // 1.10
    public void deleteRoomByIndex() {
        int index=Inputter.getInt(0, 0);
        roomList.deleteByIndex(index);
        System.out.println("Deleted.");
    }

    // 1.11
    public void searchRoomByName() {
        // ask user for room name and search and print room info     
        String input = Inputter.getString();
        roomList.searchByName(input);
    }

    // 1.12
    public void searchBookedRoomByCode() {
        String input = Inputter.getString();
        roomList.searchBookedRoomByCode(input);
    }

}
