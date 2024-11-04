/**
 *
 * @author Group 4
 */
package gui;

import core.BookingList;
import core.RoomTree;
import core.StudentTree;
import dto.Room;
import dto.Student;
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
        "2. Input & add to the tree",
        "3. Display data by in-order traversal",
        "4. Save room tree to file by post-order traversal",
        "5. Search room by rcode",
        "6. Delete by bcode by copying",
        "7. Delete by bcode by merging",
        "8. Simply balancing",
        "9. Display data by breadth-first traversal",
        "10. Count the number of rooms",
        "11. Search by name",
        "12. Search booked by rcode",
        "0. Go back"
    };

    private static String[] STUDENT_OPTIONS = {
        "1. Load data from file",
        "2. Input & add to the tree",
        "3. Display data by pre-order traversal",
        "4. Save student tree to file by in-order traversal",
        "5. Search by scode",
        "6. Delete by scode by copying",
        "7. Search by student name",
        "8. Search booking room by scode",
        "0. Go back"
    };

    private static String[] BOOKING_OPTIONS = {
        "1. Load data from file",
        "2. Book a room",
        "3. Display booking data",
        "4. Save booking list to file",
        "5. Sort by rcode + scode",
        "6. Leave a room by rcode + scode",
        "0. Go back"
    };

    private final static BookingList bookingList = new BookingList();
    private final static StudentTree studentTree = new StudentTree();
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
            choice = Inputter.getInt(0, OPTIONS.length);
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
            choice = Inputter.getInt(0, ROOM_OPTIONS.length);
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
                    System.out.print("Input room name: ");
                    rname = Inputter.getString();
                    room = roomTree.searchByName(rname);

                    if (room != null) {
                        System.out.println("Room found: ");
                        roomTree.display();
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
            choice = Inputter.getInt(0, STUDENT_OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    studentTree.loadData("students.txt");
                    break;
                case 2:
                    System.out.println("Please insert infomation for the new room:");

                    // get scode
                    System.out.print("Student's Code: ");
                    while (true) {
                        String scode = Inputter.getString();
                        if (studentTree.searchByCode(scode) == null) {
                            break;
                        } else {
                            System.out.print("Student with code " + scode + " already exists. Try again: ");
                        }
                    }

                    // get sname
                    String sname = Inputter.getName("Student's Name: ");

                    // get bYear
                    System.out.print("Student's BirthYear: ");
                    int bYear = Inputter.getInt(1950, 2005);

                    // get booked Room
                    //....
                    Student newStudent = new Student(sname, sname, bYear);
                    studentTree.addToEnd(newStudent);
                    break;
                case 3: //Display data by pre-order traversal
                    studentTree.displayByPreOrder();
                    break;
                case 4: // Save Data in-order
                    studentTree.saveData("students.txt");
                    break;
                case 5: // search by scode
                    System.out.print("Input Student code: ");
                    String scode = Inputter.getString();
                    Student s = studentTree.searchByCode(scode);

                    if (s != null) {
                        System.out.println("Student found: ");
                        studentTree.searchByCode(scode);
                    } else {
                        System.out.println("Student with code " + scode + " not found.");
                    }
                    break;
                case 6:
                    System.out.print("Input Student code: ");
                    scode = Inputter.getString();
                    studentTree.deleteByCopying(studentTree.searchByCode(scode));
                    break;
                case 7:
                    System.out.print("Input student name: ");
                    sname = Inputter.getString();
                    studentTree.searchByName(sname);

                    break;
                case 8:
                    System.out.print("Input Student code: ");
                    scode = Inputter.getString();
                    studentTree.searchStudentRoom(scode);
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
            choice = Inputter.getInt(0, BOOKING_OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    bookingList.loadData("booking.txt");
                    break;
                case 2:
                    System.out.println("Please insert infomation for the new booking: ");

                    // get rcode
                    System.out.print("Room Code: ");
                    String rcode = Inputter.getString();
                    //.....

                    // get sname
                    String scode = Inputter.getName("Student Code ");

                    //....
                case 3:
                    bookingList.display();
                    break;
                case 4:
                    bookingList.saveData("booking.txt");
                    break;
                case 5: // HÀM SAI
                    //bookingList.sort();
                    break;
                case 6:
                    // get rcode
                    System.out.print("Room Code: ");
                    rcode = Inputter.getString();
                    //.....

                    // get sname
                    scode = Inputter.getName("Student Code ");
                    
                    
                    bookingList.leaveRoom(rcode, scode, roomTree);
                    break;
            }
        }
    }

}
