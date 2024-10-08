/**
 *
 * @author Group 4
 */
package core;

import dto.Room;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import util.MyLinkedList;
import java.io.FileWriter;
import java.io.IOException;
import util.Inputter;
import util.Node;
import util.RoomType;
import core.BookingList;
import java.util.List;

public class RoomList extends MyLinkedList<Room> {

    public RoomList() {
    }

    // 1.1
    public int loadData(String filename) {
        // data = rcode, name, dom, floor, type, booked, price
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    RoomType type = Inputter.convertsToRoomType(data[4]);
                    int booked = Integer.parseInt(data[5]);
                    double price = Double.parseDouble(data[6]);

                    Room room = new Room(data[0], data[1], data[2], data[3], type, booked, price);
                    if (searchByCode(data[0]) == null) {
                        this.addLast(room);
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 1.2
    public void addToEnd(Room room) {
        addLast(room);
    }

    // 1.3
    public void display() {
        // display all rooms in the list
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.format("%-10s | %-20s | %-5s | %-5s | %-7s | %4s | %-6s | %s\n",
                "rcode", "name", "dom", "floor", "type", "beds", "booked", "price");
        System.out.println("-------------------------------------------------------------------------------------------------");
        traverse();
    }

    public void display(Room room) {
        // display all rooms in the list
//        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.format("%-10s | %-20s | %-5s | %-5s | %-7s | %4s | %-6s | %s\n",
                "rcode", "name", "dom", "floor", "type", "beds", "booked", "price");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(room);
    }

    // 1.4
    public void saveData(String filename) {
        // data = rcode, name, dom, floor, type, booked, price
        loadData(filename);

        if (this.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            Node<Room> p = head;
            String[] lineComponents = new String[7];
            while (p != null) {
                Room room = p.getInfo();
                lineComponents[0] = room.getRcode();
                lineComponents[1] = room.getName();
                lineComponents[2] = room.getDom();
                lineComponents[3] = room.getFloor();
                lineComponents[4] = room.getRoomType().toString();
                lineComponents[5] = String.valueOf(room.getBooked());
                lineComponents[6] = String.valueOf(room.getPrice());
                String line = String.join(",", lineComponents);
                writer.write(line);
                writer.newLine();
                p = p.getNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.format("Saved rooms to %s \n", filename);
    }

    // 1.5
    public Room searchByCode(String rcode) {
        Node<Room> p = head;
        while (p != null) {
            if (p.getInfo().getRcode().equals(rcode)) {
                return p.getInfo();
            }
            p = p.getNext();
        }
        return null;
    }

    private Node searchNodeByCode(String rcode) {
        Node<Room> p = head;
        while (p != null) {
            if (p.getInfo().getRcode().equals(rcode)) {
                return p;
            }
            p = p.getNext();
        }
        return null;
    }

    // 1.6
    public void deleteByCode(String rcode) {
        Node<Room> p = searchNodeByCode(rcode);
        if (p != null) {
            this.remove(p);
            System.out.println("Room with code " + rcode + " has been deleted.");
        } else {
            System.out.println("Room with code " + rcode + " not found.");
        }
    }

    // 1.7
    public void sortByCode() {
        // sort the list based on rcode ASCENDING
        Node<Room> p = head;
        Node<Room> q;
        while (p != null) {
            q = p.getNext();
            while (q != null) {
                if (p.getInfo().getRcode().compareTo(q.getInfo().getRcode()) > 0) {
                    swap(p, q);
                }
                q = q.getNext();
            }
            p = p.getNext();
        }
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
        this.remove(index);
    }

    // 1.11
    public void searchByName(String name) {
        Node<Room> current = head;
        Node<Room> firstMatch = null;  // Lưu node đầu tiên tìm thấy

        while (current != null) {   //pressing nguyên sàn 
            if (current.getInfo().getName().equals(name)) {
                if (firstMatch == null) {
                    display(current.getInfo());
                    firstMatch = current;
                } else {
                    System.out.println(current.getInfo());
                }
            }
            current = current.getNext();
        }

        if (firstMatch == null) {
            System.out.println("No rooms found with name: " + name);
        }

    }

//    // 1.12
//    public Room searchBookedRoomByCode(String rcode, BookingList bookingList) {
//        
//        Node<Booking> p = bookingList.head;
//        while(p != null) {
//            if (p.getInfo().getRCode)
//            p = p.getNext();
//        }
//        
//        // Tìm kiếm phòng phương thức searchNodeByCode
//        Node<Room> roomNode = searchNodeByCode(rcode);
//
//        if (roomNode != null) {
//            Room room = roomNode.getInfo();
//            if (room.getBooked() == 0) {
//                return room;
//            } else {
//                System.out.println("Room is fully booked");
//            }
//        } else {
//            System.out.println("Room with code " + rcode + " not found.");
//        }
//
//        return null;  // Trả về null
//    }

}
