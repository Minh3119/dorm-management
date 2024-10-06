/**
 *
 * @author Group 4
 */
package core;

import dto.Room;
import util.MyLinkedList;
import java.io.FileWriter;
import java.io.IOException;
import util.Node;

public class RoomList extends MyLinkedList<Room> {

    public RoomList() {
    }

    // 1.1
    public void loadData(String filename) {
//        String fileName = "RoomList.txt";
//
//        try {
//            File file = new File(fileName);
//            
//
//            try (
//                    Scanner scanner = new Scanner(file)) {
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine();
//                    System.out.println(line);
//                }
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred: File not found.");
//        }
    }

    // 1.2
    public void addToEnd(Room room) {
        addLast(room);
    }

    // 1.3
    public void display() {
        // display all rooms in the list
        System.out.println("");

    }

    // 1.4
    public void saveData(String filename) {
        String data = ".";

        String fileName = "resources/room.txt";

        try {
            try ( // Create a FileWriter object
                    FileWriter writer = new FileWriter(fileName)) {
                // Write data to the file
                writer.write(data);
            }

            System.out.println("Data saved " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving data.");
        }
    }

    // 1.5
    public Room searchByCode(String rcode) {
        // search the room by rcode, return the found room, else return null
//        get all the rooms.
//        loop through using size() to getRcode and compare the .getRcode() ===rcode
        MyLinkedList<Room> room = new MyLinkedList<>();
        Room rooms = new Room();
        for (int i = 0; i < room.size(); i++) {
            String code = rooms.getRcode();
            if (code == null ? rcode == null : code.equals(rcode)) {
                return rooms;
            }
            else{
                System.out.println("Data not found");
            }
        }
        return null;
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
        MyLinkedList<Room> room = new MyLinkedList<>();
        Room rooms = new Room();
        int count = 0;
        for (int i = 0; i < room.size(); i++) {
            count++;
            if (index == count) {
                room.remove(index);
            }
            else{
                System.out.println("Data not found");
            }
        }
        return;
    }

    // 1.11
    public void searchByName(String name) {
        // search the room by name
        MyLinkedList<Room> room = new MyLinkedList<>();
        Room rooms = new Room();
        for (int i = 0; i < room.size(); i++) {
            String n = rooms.getName();
            if (n == null ? name == null : n.equals(name)) {
                System.out.println(rooms);
            }
            else{
                System.out.println("Data not found");
            }
        }
        return;
    }

    // 1.12
    public Room searchBookedRoomByCode(String rcode) {
        // search the BOOKED room
        MyLinkedList<Room> room = new MyLinkedList<>();
        Room rooms = new Room();
        for (int i = 0; i < room.size(); i++) {
            String code = rooms.getRcode();
            if (rooms.getBooked() == 0) {
                if (code == null ? rcode == null : code.equals(rcode)) {
                    return rooms;
                }
            }
            else{
                System.out.println("Room is fully booked");
            }
        }
        return null;
    }

}
