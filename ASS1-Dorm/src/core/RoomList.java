/**
 *
 * @author Group 4
 */

package core;

import dto.Room;
import util.MyLinkedList;

public class RoomList extends MyLinkedList<Room> {

    public RoomList() {
    }
    
    // 1.1
    public void loadData(String filename) {
        // load data from file txt
    }
    
    // 1.2
    public void addToEnd(Room room) {
        addLast(room);
    }
    
    // 1.3
    public void display() {
        // display all rooms in the list
    }
    
    // 1.4
    public void saveData(String filename) {
        // save data to file txt
    }
    
    // 1.5
    public void searchByCode(String rcode) {
        // search the room by rcode, return the found room, else return null
    }
    
    // 1.6
    public void deleteByCode(String rcode) {
        // delete a room by code
    }
    
    // 1.7
    public void sortByCode() {
        // sort the list based on rcode
    }
    
    // 1.8
    public void addToBeginning(Room room) {
        addFirst(room);
    }
    
    // 1.9
    public void addBeforeIndex(int k, Room room) {
        insert(k, room);
    }
    
    // 1.10 
    public void deleteByIndex(int index) {
        // delete a room by index
    }
    
    // 1.11
    public void searchByName(String name) {
        // search the room by name
    }
    
    // 1.12
    public void searchBookedRoomByCode(String rcode) {
        // search the BOOKED room
    }
    
}
