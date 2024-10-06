/**
 *
 * @author Group 4
 */
package manager;

import core.BookingList;
import core.StudentList;
import dto.Booking;
import dto.Student;
import util.Node;
import util.Inputter;

public class StudentManager {

    static String filename = "src/resources/students.txt";

    private StudentList studentList;

    public StudentManager(StudentList studentList) {
        this.studentList = studentList;
    }

    // 2.1
    public void loadData() {
        studentList.loadData(filename);
        System.out.format("Loaded students from %s.\n", filename);
    }

    // 2.2
    public void createNewStudentAtEnd() {
        //Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student code: ");
        String scode = Inputter.getString();

        System.out.print("Enter student name: ");
        String name = Inputter.getString();

        System.out.print("Enter birth year: ");
        int byear = Inputter.getInt(1800, 2024);

        Student student = new Student(scode, name, byear);
        studentList.addToEnd(student);
        System.out.println("Student added successfully.");
    }

    // 2.3
    public void display() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("List of students:");
            studentList.display();
        }
    }

    // 2.4
    public void saveData() {
        studentList.saveData(filename);
        System.out.format("Saved students to %s \n", filename);
    }

    // 2.5
    public void searchStudentByCode() {
        //Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student code: ");
        String scode = Inputter.getString();

        Node<Student> studentNode = studentList.get(new Student(scode, "", 0));

        if (studentNode != null) {
            System.out.println("Student found: " + studentNode.getInfo());
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }

    // 2.6
    public void deleteStudentByCode() {
        //Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student code to delete: ");
        String scode = Inputter.getString();

        Node<Student> studentNode = studentList.get(new Student(scode, "", 0));

        if (studentNode != null) {
            studentList.remove(studentNode);
            System.out.println("Student with code " + scode + " has been deleted.");
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }

    // 2.7
    public void searchStudentByName() {
        //Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = Inputter.getString();

        Node<Student> p = studentList.head;
        boolean found = false;

        while (p != null) {
            if (p.getInfo().getName().equalsIgnoreCase(name)) {
                System.out.println("Student found: " + p.getInfo());
                found = true;
            }
            p = p.getNext();
        }

        if (!found) {
            System.out.println("No students with name " + name + " found.");
        }
    }

    // 2.8
    public void searchBookedRoomByStudentCode(BookingList bookingList) {
        //Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student code: ");
        String scode = Inputter.getString();

        boolean found = false;
        Node<Booking> current = bookingList.head;
        while (current != null) {
            Booking booking = current.getInfo();

            if (booking.getScode().equals(scode) && booking.getState() == 1) {
                System.out.println("Student with code " + scode + " has booked room with room code: " + booking.getRcode());
                found = true;
            }

            current = current.getNext();
        }

        if (!found) {
            System.out.println("Student with code " + scode + " has not booked any room.");
        }
    }
}
