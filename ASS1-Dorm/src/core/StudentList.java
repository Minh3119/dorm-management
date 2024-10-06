/**
 *
 * @author Group 4
 */
package core;

import dto.Booking;
import dto.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import util.MyLinkedList;
import util.Node;

public class StudentList extends MyLinkedList<Student> {
    
    public StudentList() {
    }

    // 2.1
    public int loadData(String filename) {
        // data = scode, name, byear
//        if (dataLoadedFromFile) {
//            System.out.println("Loaded 0 students.");
//            return;
//        }
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Student s = new Student(data[0], data[1], Integer.parseInt(data[2]));
                    if (searchByCode(data[0]) == null) {
                        this.addLast(s);
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 2.2
    public void addToEnd(Student student) {
        addLast(student);
    }

    // 2.3
    public void display() {
        System.out.println("-------------------------------------------------");
        System.out.format("%-10s | %-20s | %-8s\n", "scode", "name", "byear");
        System.out.println("-------------------------------------------------");
        this.traverse();
    }
    
    public void display(Student student) {
//        System.out.println("-------------------------------------------------");
        System.out.format("%-10s | %-20s | %-8s\n", "scode", "name", "byear");
        System.out.println("-------------------------------------------------");
        System.out.println(student);
    }

    // 2.4
    public void saveData(String filename) {
        // data = rcode, name, dom, floor, type, booked, price
        loadData(filename);
        
        if (this.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            Node<Student> p = head;
            String[] lineComponents = new String[3];
            while (p != null) {
                Student s = p.getInfo();
                lineComponents[0] = s.getScode();
                lineComponents[1] = s.getName();
                lineComponents[2] = String.valueOf(s.getByear());
                
                String line = String.join(",", lineComponents);
                writer.write(line);
                writer.newLine();
                p = p.getNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.format("Saved students to %s \n", filename);
    }

    // 2.5
    public Student searchByCode(String scode) {
        Node<Student> p = head;
        while (p != null) {
            if (p.getInfo().getScode().equals(scode)) {
                return p.getInfo();
            }
            p = p.getNext();
        }
        return null;
    }
    
    private Node searchNodeByCode(String scode) {
        Node<Student> p = head;
        while (p != null) {
            if (p.getInfo().getScode().equals(scode)) {
                return p;
            }
            p = p.getNext();
        }
        return null;
    }

    // 2.6
    public void deleteByCode(String scode) {
        Node<Student> p = searchNodeByCode(scode);
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
        Node<Student> studentNode = searchNodeByCode(scode);

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
