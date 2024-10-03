/**
 *
 * @author Group 4
 */

package dto;

import model.Room;
import util.MyLinkedList;

public class RoomList extends MyLinkedList<Room> {

    public RoomList() {
    }
    
    public void loadData(String filename) {
        // load data from file txt
    }
    
    public void saveData(String filename) {
        // save data to file txt
    }
    
    public void display() {
        // display all rooms in the list
    }
    
    public Room searchByCode(String rcode) {
        // search the room by rcode, return the found room, else return null
        return null;
    }
    
    public Room searchBookedRoomByCode(String rcode) {
        // search the BOOKED room
        return null;
    }
    
    public Room searchByName(String name) {
        // search the room by name
        return null;
    }
    
    public void deleteByCode(String rcode) {
        // delete a room by code
    }
    
    public void deleteByIndex(int index) {
        // delete a room by index
    }
    
    public void sortByCode() {
        // sort the list based on rcode
    }
    
    
}
