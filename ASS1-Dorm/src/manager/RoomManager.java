/**
 *
 * @author Group 4
 */
package manager;

import core.RoomList;
import dto.Room;
import util.Inputter;
import util.Node;
import util.RoomType;

/*
Program flow:
Main -> manager.RoomManager -> core.RoomList -> dto.Room
Main -> manager.StudentManager -> core.StudentList -> dto.Student
Main -> manager.BookingManager -> core.BookingList -> dto.Booking
 */
public class RoomManager {

    static String filename = "src/resources/rooms.txt";

    private final RoomList roomList;

    public RoomManager(RoomList roomList) {
        this.roomList = roomList;
    }

    // 1.1
    public void loadData() {
        int count = roomList.loadData(filename);
        System.out.format("Loaded %d rooms.\n", count);
    }

    // 1.2
    public void createNewRoomAtEnd() {
        System.out.println("Please insert infomation for the new room:");

        String rcode, rname, dom, floor;
        RoomType roomType;
        double price;
//        int booked;

        // get rcode
        System.out.print("Room Code: ");
        while (true) {
            rcode = Inputter.getString();
            if (roomList.searchByCode(rcode) == null) {
                break;
            } else {
                System.out.print("Room with code " + rcode + " already exists. Try again: ");
            }
        }

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
//        System.out.println("Number of booked beds: ");
//        booked = Inputter.getInt(0, roomType.getBeds());

        // get room's price
        System.out.println("Room's price: ");
        price = Inputter.getPrice();

        Room room = new Room(rcode, rname, dom, floor, roomType, 0, price);
        roomList.addToEnd(room);
        System.out.format("Created new room.\n");
    }

    // 1.3
    public void display() {
        if (roomList.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            System.out.println("List of rooms:");
            roomList.display();
        }
    }

    // 1.4
    public void saveData() {
        roomList.saveData(filename);
    }

    // 1.5
    public void searchRoomByCode() {
        System.out.print("Input room code: ");
        String rcode = Inputter.getString();
        Room room = roomList.searchByCode(rcode);

        if (room != null) {
            System.out.println("Room found: ");
            roomList.display(room);
        } else {
            System.out.println("Room with code " + rcode + " not found.");
        }
    }

    // 1.6
    public void deleteRoomByCode() {
        System.out.print("Input room code: ");
        String input = Inputter.getString();
        roomList.deleteByCode(input);
    }

    // 1.7
    public void sort() {
        roomList.sortByCode();
        roomList.display();
    }

    // 1.8
    public void createNewRoomAtBeginning() {
        System.out.println("Please insert infomation for the new room:");

        String rcode, rname, dom, floor;
        RoomType roomType;
        double price;

        // get rcode
        System.out.print("Room Code: ");
        while (true) {
            rcode = Inputter.getString();
            if (roomList.searchByCode(rcode) == null) {
                break;
            } else {
                System.out.print("Room with code " + rcode + " already exists. Try again: ");
            }
        }

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

        // get room's price
        System.out.println("Room's price: ");
        price = Inputter.getPrice();

        Room room = new Room(rcode, rname, dom, floor, roomType, 0, price);
        roomList.addToBeginning(room);
    }

    // 1.9
    public void createNewRoomBeforeIndex() {
        System.out.println("Please insert infomation for the new room:");

        String rcode, rname, dom, floor;
        RoomType roomType;
        double price;

        // get rcode
        System.out.print("Room Code: ");
        while (true) {
            rcode = Inputter.getString();
            if (roomList.searchByCode(rcode) == null) {
                break;
            } else {
                System.out.print("Room with code " + rcode + " already exists. Try again: ");
            }
        }

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

        // get room's price
        System.out.println("Room's price: ");
        price = Inputter.getPrice();

        Room room = new Room(rcode, rname, dom, floor, roomType, 0, price);
        System.out.print("Input room index number: ");
        int index = Inputter.getInt(0, roomList.size()-1);
        roomList.addBeforeIndex(index, room);
    }

    // 1.10
    public void deleteRoomByIndex() {
        System.out.print("Input room index number: ");
        int index = Inputter.getInt(0, roomList.size()-1);
        roomList.deleteByIndex(index);
        System.out.println("Deleted.");
    }

    // 1.11
    public void searchRoomByName() {
        System.out.print("Input room name: ");
        String name = Inputter.getString();
        roomList.searchByName(name);
    }

}
