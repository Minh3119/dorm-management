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
import util.Node;

public class BookingManager {
    
    static String filename = "src/resources/bookings.txt";
    public static String dateFormat = "dd/MM/yyyy";
    
    private final BookingList bookingList;
    private final RoomList roomList;
    private final StudentList studentList;

    public BookingManager(BookingList bookingList, RoomList roomList, StudentList studentList) {
        this.bookingList = bookingList;
        this.roomList = roomList;
        this.studentList = studentList;
    }
    
    // 3.1
    public void loadData() {
        int count = bookingList.loadData(filename);
        System.out.format("Loaded %d bookings.\n", count);
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
        while (true) {
            rcode = Inputter.getString();
            if (rcode.equals("0")) {
                System.out.println("Aborted.");
                return;
            }
            room = roomList.searchByCode(rcode);
            if (room == null) {
                System.out.print("Room not found, try another room: ");
                continue;
            }
            
            // check if available beds > 0
            // available beds = total beds - booked
            int availableBeds = room.getBeds() - room.getBooked();
            if (availableBeds == 0) {
                System.out.print("Room is fully booked (0 beds left), try another room: ");
                continue;
            }
            
            break;
        }
        

        // get rname
        System.out.print("Student Code: ");
        while (true) {
            scode = Inputter.getString();
            if (scode.equals("0")) {
                System.out.println("Aborted.");
                return;
            }
            student = studentList.searchByCode(scode);
            if (student == null) {
                System.out.print("Student not found, try again: ");
                continue;
            }
            
            // if student is not living in any room -> valid
            if (bookingList.isStudentBooked(scode) == true) {
                System.out.print("This student already booked a room, try another student: ");
                continue;
            }
            
            break;
        }
        
        room.increaseBooked();      // increase booked beds by 1
        Booking booking = new Booking(rcode, scode, bdate, null, 1);
        bookingList.bookRoom(booking);
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
    }
    
    // 3.5
    public void sort() {
        bookingList.sortRoomCodeDESC();
        bookingList.display();
        bookingList.sortStudentCodeDESC();
        bookingList.display();
    }
    
    // 3.6
    public void leaveRoom() {
        System.out.print("Enter Room Code: ");
        String rcode = Inputter.getString();
        System.out.print("Enter Student Code: ");
        String scode = Inputter.getString();
        bookingList.leaveRoom(rcode, scode, roomList);
    }
    
    // 1.12
    public void searchBookedRoomByCode() {
        System.out.println("Enter Room Code: ");
        String rcode = Inputter.getString();
        
        Node<Booking> p = bookingList.head;
        boolean firstFind = true;
        while(p != null) {
            Booking booking = p.getInfo();
            if (booking.getRcode().equals(rcode) && booking.getState() == 1) {
                if (firstFind) {
                    System.out.println("Found booked room with code: " + rcode);
                    Student student = studentList.searchByCode(p.getInfo().getScode());
                    studentList.display(student);
                    firstFind = false;
                } else {
                    Student student = studentList.searchByCode(p.getInfo().getScode());
                    System.out.println(student);
                }
            }
            p = p.getNext();
        }
    }
    
}
