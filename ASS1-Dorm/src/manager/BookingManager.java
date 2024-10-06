/**
 *
 * @author Group 4
 */

package manager;

import core.BookingList;
import dto.Booking;
import java.util.Date;
import util.Inputter;

public class BookingManager {
    
    static String filename = "src/resources/bookings.txt";
    static String dateFormat = "dd/MM/yyyy";
    
    private BookingList bookingList;

    public BookingManager(BookingList bookingList) {
        this.bookingList = bookingList;
    }
    
    // 3.1
    public void loadData() {
        bookingList.loadData(filename);
        System.out.format("Loaded bookings from %s.\n", filename);
    }
    
    // 3.2
    public void bookRoom() {
        System.out.println("Please insert infomation for the new booking:");

        String rcode, scode;
        Date bdate = new Date();

        // get rcode
        System.out.print("Room Code: ");
        rcode = Inputter.getString();

        // get rname
        System.out.print("Student Code: ");
        scode = Inputter.getString();

        Booking booking = new Booking(rcode, scode, bdate, null, 1);
        bookingList.addToEnd(booking);
        System.out.format("Created new booking.\n");
    }
    
    // 3.3
    public void display() {
        
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
