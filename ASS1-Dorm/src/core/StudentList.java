/**
 *
 * @author Group 4
 */
package core;

import dto.Booking;
import dto.Room;
import dto.Student;
import util.MyLinkedList;
import util.Node;

public class StudentList extends MyLinkedList<Student> {

    public StudentList() {
    }

    // 2.1
    public void loadData(String filename) {

    }

    // 2.2
    public void addToEnd(Student student) {
        addLast(student);
    }

    // 2.3
    public void display() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        this.traverse();
    }

    // 2.4
    public void saveData(String filename) {

    }

    // 2.5
    public Node searchByCode(String scode) {
        Node p = head;
        while (p != null) {
            if (p.getInfo() == scode) {
                return p;
            }
            p = p.getNext();
        }
        return null;
    }

    // 2.6
    public void deleteByCode(String scode) {
        Node<Student> p = searchByCode(scode);
        if (p != null) {
            this.remove(p);
            System.out.println("Student with code " + scode + " has been deleted.");
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }

    // 2.7
    public void searchByName(String sname) {
        Node<Student> p = head;
        boolean found = false;
        while (p != null) {
            if (p.getInfo().getName().equalsIgnoreCase(sname)) {
                System.out.println(p.getInfo());
                found = true;
            }
            p = p.getNext();
        }
        if (!found) {
            System.out.println("No student with name " + sname + " found.");
        }
    }

    // 2.8
    public void searchStudentRoom(String scode, BookingList bookingList) {
        // Search for the student by student code
        Node<Student> studentNode = searchByCode(scode);

        if (studentNode != null) {

            Node<Booking> currentBooking = bookingList.head;
            boolean found = false;

            while (currentBooking != null) {
                Booking booking = currentBooking.getInfo();

                if (booking.getScode().equals(scode) && booking.getState() == 1) {

                    String bookedRoomCode = booking.getRcode();

                    System.out.println("Student with code " + scode + " has booked room: ");
                    System.out.println("Room code: " + bookedRoomCode);
                    found = true;
                    break;
                }
                currentBooking = currentBooking.getNext();
            }
            if (!found) {
                System.out.println("Student with code " + scode + " has not booked any room.");
            }
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }
}
