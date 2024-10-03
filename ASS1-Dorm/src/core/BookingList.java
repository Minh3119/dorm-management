/**
 *
 * @author Group 4
 */

package core;

import dto.Booking;
import util.MyLinkedList;

public class BookingList extends MyLinkedList<Booking> {

    public BookingList() {
    }
    
    // 3.1
    public void loadData(String filename) {
        // load data tu file txt
    }
    
    // 3.2
    public void bookRoom() {
        // create new Booking, add it into BookingList
    }
    
    // 3.3
    public void display() {
    }
    
    // 3.4
    public void saveData(String filename) {
    }    
    
    // 3.5
    public void sort(String rcode, String scode) {
        // sort + display result
        // rcode: descending, then scode descending
    }
    
    // 3.6
    public void leaveRoom(String rcode, String scode) {
    }
}
