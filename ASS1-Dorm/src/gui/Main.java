/**
 *
 * @author Group 4
 */
package gui;

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

    public static void main(String[] args) {
        displayMainMenu();
    }
    
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
    
    static void displayMenuRoom() {
        Menu menu = new Menu("Room options", "Your choice: ");
        menu.addAll(Arrays.asList(ROOM_OPTIONS));
        menu.display();
        
        int choice;
        choice = Inputter.getInt(0,OPTIONS.length);

        switch (choice) {
            case 0:
                return;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    
    static void displayMenuStudent() {
        Menu menu = new Menu("Student options", "Your choice: ");
        menu.addAll(Arrays.asList(STUDENT_OPTIONS));
        menu.display();
        
        int choice;
        choice = Inputter.getInt(0,OPTIONS.length);

        switch (choice) {
            case 0:
                return;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    
    static void displayMenuBooking() {
        Menu menu = new Menu("Booking options", "Your choice: ");
        menu.addAll(Arrays.asList(BOOKING_OPTIONS));
        menu.display();
        
        int choice;
        choice = Inputter.getInt(0,OPTIONS.length);

        switch (choice) {
            case 0:
                return;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    
}
