/**
 *
 * @author Group 4
 */
package gui;

import core.BookingList;
import core.RoomList;
import core.StudentList;
import java.util.Arrays;
import manager.BookingManager;
import manager.RoomManager;
import manager.StudentManager;
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
    
    
    private static BookingList bookingList = new BookingList();
    private static StudentList studentList = new StudentList();
    private static RoomList roomList = new RoomList();
    
    
    
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
        RoomManager manager = new RoomManager(roomList);
        Menu menu = new Menu("Room options", "Your choice: ");
        menu.addAll(Arrays.asList(ROOM_OPTIONS));
        
        while (true) {
            menu.display();

            int choice;
            choice = Inputter.getInt(0,OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    manager.loadData();
                    break;
                case 2:
                    manager.createNewRoomAtEnd();
                    break;
                case 3:
                    manager.display();
                    break;
                case 4:
                    manager.saveData();
                    break;
                case 5:
                    manager.searchRoomByCode();
                    break;
                case 6:
                    manager.deleteRoomByCode();
                    break;
                case 7:
                    manager.sort();
                    break;
                case 8:
                    manager.createNewRoomAtBeginning();
                    break;
                case 9:
                    manager.createNewRoomBeforeIndex();
                    break;
                case 10:
                    manager.deleteRoomByIndex();
                    break;
                case 11:
                    manager.searchRoomByName();
                    break;
                case 12:
                    manager.searchBookedRoomByCode();
                    break;
            }
        }
    }
    
    // need implementations
    static void displayMenuStudent() {
        StudentManager manager = new StudentManager(studentList);
        Menu menu = new Menu("Student options", "Your choice: ");
        menu.addAll(Arrays.asList(STUDENT_OPTIONS));
        
        while (true) {
            menu.display();

            int choice;
            choice = Inputter.getInt(0,OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    manager.loadData();
                    break;
                case 2:
                    manager.createNewStudentAtEnd();
                    break;
                case 3:
                    manager.display();
                    break;
                case 4:
                    manager.saveData();
                    break;
                case 5:
                    manager.searchStudentByCode();
                    break;
                case 6:
                    manager.deleteStudentByCode();
                    break;
                case 7:
                    manager.searchStudentByName();
                    break;
                case 8:
                    manager.searchBookedRoomByStudentCode(bookingList);
                    break;
            }
        }
    }
    
    // need implementations
    static void displayMenuBooking() {
        BookingManager manager = new BookingManager(bookingList, roomList, studentList);
        Menu menu = new Menu("Booking options", "Your choice: ");
        menu.addAll(Arrays.asList(BOOKING_OPTIONS));
        
        while (true) {
            menu.display();

            int choice;
            choice = Inputter.getInt(0,OPTIONS.length);
            System.out.println("");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    manager.loadData();
                    break;
                case 2:
                    manager.bookRoom();
                    break;
                case 3:
                    manager.display();
                    break;
                case 4:
                    manager.saveData();
                    break;
                case 5:
                    manager.sort();
                    break;
                case 6:
                    manager.leaveRoom();
                    break;
            }
        }
    }
    
}
