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
    
    
    private final static BookingList bookingList = new BookingList();
    private final static StudentList studentList = new StudentList();
    private final static RoomList roomList = new RoomList();
    
    private final static RoomManager roomManager = new RoomManager(roomList);
    private final static StudentManager studentManager = new StudentManager(studentList);
    private final static BookingManager bookingManager = new BookingManager(bookingList, roomList, studentList);
    
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
            switch (choice) {
                case 0:
                    return;
                case 1:
                    roomManager.loadData();
                    break;
                case 2:
                    roomManager.createNewRoomAtEnd();
                    break;
                case 3:
                    roomManager.display();
                    break;
                case 4:
                    roomManager.saveData();
                    break;
                case 5:
                    roomManager.searchRoomByCode();
                    break;
                case 6:
                    roomManager.deleteRoomByCode();
                    break;
                case 7:
                    roomManager.sort();
                    break;
                case 8:
                    roomManager.createNewRoomAtBeginning();
                    break;
                case 9:
                    roomManager.createNewRoomBeforeIndex();
                    break;
                case 10:
                    roomManager.deleteRoomByIndex();
                    break;
                case 11:
                    roomManager.searchRoomByName();
                    break;
                case 12:
                    bookingManager.searchBookedRoomByCode();
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
