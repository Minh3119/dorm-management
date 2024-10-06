/**
 *
 * @author Group 4
 */

package manager;

import core.BookingList;
import core.RoomList;
import core.StudentList;
import dto.Booking;
import dto.Room;
import dto.Student;
import java.util.Date;
import util.Inputter;

public class BookingManager {
    
    static String filename = "src/resources/bookings.txt";
    public static String dateFormat = "dd/MM/yyyy";
    
    private BookingList bookingList;
    private RoomList roomList;
    private StudentList studentList;

    public BookingManager(BookingList bookingList, RoomList roomList, StudentList studentList) {
        this.bookingList = bookingList;
        this.roomList = roomList;
        this.studentList = studentList;
    }
    
    // 3.1
    public void loadData() {
        bookingList.loadData(filename);
        System.out.format("Loaded bookings from %s.\n", filename);
    }
    
    // 3.2
    public void bookRoom() {
        System.out.println("Please insert infomation for the new booking:");

        String rcode = null;
        String scode = null;
        Date bdate = new Date();
        
        Room room = null;
        Student student = null;
        
        // get rcode
        System.out.print("Room Code: ");
        while (room == null) {
            rcode = Inputter.getString();
            room = roomList.searchByCode(rcode);
            if (room != null) {
                break;
            } else {
                System.out.print("Room not found, try again: ");
            }
        }

        // get rname
        System.out.print("Student Code: ");
        while (student == null) {
            scode = Inputter.getString();
            student = studentList.searchByCode(scode);
            if (student != null) {
                break;
            } else {
                System.out.print("Student not found, try again: ");
            }
        }

        Booking booking = new Booking(rcode, scode, bdate, null, 1);
        bookingList.addToEnd(booking);
        System.out.format("Created new booking.\n");
    }
    
    // 3.3
    public void display() {
        if (bookingList.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("List of bookings:");
            bookingList.display();
        }
    }
    
    // 3.4
    public void saveData() {
        bookingList.saveData(filename);
        System.out.format("Saved bookings to %s \n", filename);
    }
    
    // 3.5
    public void sort() {
        
    }
    
    // 3.6
    public void leaveRoom() {
        
    }
    
}
