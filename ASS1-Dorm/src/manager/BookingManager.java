/**
 *
 * @author Group 4
 */

package manager;

import core.BookingList;

public class BookingManager {
    
    static String filename = "resources/bookings.txt";
    
    private BookingList bookingList;

    public BookingManager(BookingList bookingList) {
        this.bookingList = bookingList;
    }
    
    // 3.1
    public void loadData() {
        bookingList.loadData(filename);
        System.out.println("im dying in this ASS");
    }
    
    // 3.2
    public void bookRoom() {
        
    }
    
    // 3.3
    public void display() {
        
    }
    
    // 3.4
    public void saveData() {
        
    }
    
    // 3.5
    public void sort() {
        
    }
    
    // 3.6
    public void leaveRoom() {
        
    }
    
}
