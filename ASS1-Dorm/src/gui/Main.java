/**
 *
 * @author Group 4
 */
package gui;

import core.BookingList;
import core.RoomTree;
import dto.Room;
import dto.RoomType;
import java.util.Arrays;
import util.Inputter;
import util.Menu;

public class Main {
    
    private static String[] OPTIONS = {
        "1. Room management",
        "2. Student management",
        "3. Booking management",
        "0. Exit"
    };
    
    private static String[] ROOM_OPTIONS = {
        "1. Load data from file",
        "2. Input & add to the end",
        "3. Display room data",
        "4. Save room list to file",
        "5. Search by room code",
        "6. Delete by room code",
        "7. Sort by room code",
        "8. Input & add to beginning",
        "9. Add before position k",
        "10. Delete position k",
        "11. Search by room name",
        "12. Search booked room by room code",
        "0. Go back"
    };
    
    private static String[] STUDENT_OPTIONS = {
        "1. Load data from file",
        "2. Input & add to the end",
        "3. Display student data",
        "4. Save student list to file",
        "5. Search by student code",
        "6. Delete by student code",
        "7. Search by student name",
        "8. Search booked room by student code",
        "0. Go back"
    };
    
    private static String[] BOOKING_OPTIONS = {
        "1. Load data from file",
        "2. Book a room",
        "3. Display booking data",
        "4. Save booking list to file",
        "5. Sort",
        "6. Leave a room",
        "0. Go back"
    };
    
    
    private final static BookingList bookingList = new BookingList();
//    private final static StudentList studentList = new StudentList();
    private final static RoomTree roomTree = new RoomTree();

    
    public static void main(String[] args) {
        displayMainMenu();
    }
    
    // Works fine for now
    static void displayMainMenu() {
        Menu menu = new Menu("Dorm Management Program", "Your choice: ");
        menu.addAll(Arrays.asList(OPTIONS));
        
        while (true) {
            menu.display();
            
            int choice;
            choice = Inputter.getInt(0,OPTIONS.length);
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displayMenuRoom();
                    break;
                case 2:
                    displayMenuStudent();
                    break;
                case 3:
                    displayMenuBooking();
                    break;
            }            
        }
    }
    
    // need implementations
    static void displayMenuRoom() {
        Menu menu = new Menu("Room options", "Your choice: ");
        menu.addAll(Arrays.asList(ROOM_OPTIONS));
        
        while (true) {
            menu.display();

            int choice;
            choice = Inputter.getInt(0,ROOM_OPTIONS.length);
            System.out.println("");
            String rcode, rname, dom, floor;
            RoomType roomType;
            double price;
            Room room;

            switch (choice) {
                case 0:
                    return;
                case 1:
                    roomTree.loadData();
                    break;
                case 2:
                    // ask for room information here, then .insert()
                    System.out.println("Please insert infomation for the new room:");

                    // get rcode
                    System.out.print("Room Code: ");
                    while (true) {
                        rcode = Inputter.getString();
                        if (roomTree.searchByCode(rcode) == null) {
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

                    Room newRoom = new Room(rcode, rname, dom, floor, roomType, 0, price);
                    roomTree.insert(newRoom);
                    break;
                case 3:
                    if (roomTree.isEmpty()) {
                        System.out.println("No rooms found.");
                    } else {
                        System.out.println("Rooms:");
                        roomTree.display();
                    }
                    break;
                case 4:
                    roomTree.saveData();
                    break;
                case 5:
                    System.out.print("Input room code: ");
                    rcode = Inputter.getString();
                    room = roomTree.searchByCode(rcode);

                    if (room != null) {
                        System.out.println("Room found: ");
                        roomTree.display(room);
                    } else {
                        System.out.println("Room with code " + rcode + " not found.");
                    }
                    
                    break;
                case 6:
                    System.out.print("Input room code: ");
                    rcode = Inputter.getString();
                    roomTree.deleteByCopying(roomTree.searchByCode(rcode));
                    break;
                case 7:
                    System.out.print("Input room code: ");
                    rcode = Inputter.getString();
                    roomTree.deleteByMerging(roomTree.searchByCode(rcode));
                    break;
                case 8:
                    roomTree.balance();
                    System.out.println("Tree is now balanced.");
                    break;
                case 9:
                    roomTree.breadth();
                    break;
                case 10:
                    int count = roomTree.count();
                    System.out.println("Found " + count + " rooms.");
                    break;
                case 11:
                    System.out.print("Input room code: ");
                    rname = Inputter.getString();
                    room = roomTree.searchByName(rname);

                    if (room != null) {
                        System.out.println("Room found: ");
                        roomTree.display(room);
                    } else {
                        System.out.println("Room with name " + rname + " not found.");
                    }
                    break;
                case 12:
                    System.out.print("Input room code: ");
                    rcode = Inputter.getString();
                    roomTree.searchBookedByRcode(rcode);
                    break;
            }
        }
    }
    
    // need implementations
    static void displayMenuStudent() {
        Menu menu = new Menu("Student options", "Your choice: ");
        menu.addAll(Arrays.asList(STUDENT_OPTIONS));
        
        while (true) {
            menu.display();

            int choice;
            choice = Inputter.getInt(0,STUDENT_OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    studentManager.loadData();
                    break;
                case 2:
                    studentManager.createNewStudentAtEnd();
                    break;
                case 3:
                    studentManager.display();
                    break;
                case 4:
                    studentManager.saveData();
                    break;
                case 5:
                    studentManager.searchStudentByCode();
                    break;
                case 6:
                    studentManager.deleteStudentByCode();
                    break;
                case 7:
                    studentManager.searchStudentByName();
                    break;
                case 8:
                    studentManager.searchBookedRoomByStudentCode(bookingList);
                    break;
            }
        }
    }
    
    // need implementations
    static void displayMenuBooking() {
        Menu menu = new Menu("Booking options", "Your choice: ");
        menu.addAll(Arrays.asList(BOOKING_OPTIONS));
        
        while (true) {
            menu.display();

            int choice;
            choice = Inputter.getInt(0,BOOKING_OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    bookingManager.loadData();
                    break;
                case 2:
                    bookingManager.bookRoom();
                    break;
                case 3:
                    bookingManager.display();
                    break;
                case 4:
                    bookingManager.saveData();
                    break;
                case 5:
                    bookingManager.sort();
                    break;
                case 6:
                    bookingManager.leaveRoom();
                    break;
            }
        }
    }
    
}
