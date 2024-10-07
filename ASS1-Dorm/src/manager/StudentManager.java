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

    private final StudentList studentList;

    public StudentManager(StudentList studentList) {
        this.studentList = studentList;
    }

    // 2.1
    public void loadData() {
        int count = studentList.loadData(filename);
        System.out.format("Loaded %d students.\n", count);
    }

    // 2.2
    public void createNewStudentAtEnd() {
        System.out.print("Enter student code: ");
        String scode;
        while (true) {
            scode = Inputter.getString();
            if (studentList.searchByCode(scode) == null) {
                break;
            } else {
                System.out.print("Student with code " + scode + " already exists. Try again: ");
            }
        }

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
    }

    // 2.5
    public void searchStudentByCode() {
        System.out.print("Enter student code: ");
        String scode = Inputter.getString();

        Student student = studentList.searchByCode(scode);

        if (student != null) {
            System.out.println("Student found: ");
            studentList.display(student);
        } else {
            System.out.println("Student with code " + scode + " not found.");
        }
    }

    // 2.6
    public void deleteStudentByCode() {
        System.out.print("Enter student code to delete: ");
        String scode = Inputter.getString();
        studentList.deleteByCode(scode);
    }

    // 2.7
    public void searchStudentByName() {
        System.out.print("Enter student name: ");
        String name = Inputter.getString();
        studentList.searchByName(name);
    }

    // 2.8
    public void searchBookedRoomByStudentCode(BookingList bookingList) {
        System.out.print("Enter student code: ");
        String scode = Inputter.getString();
        studentList.searchStudentRoom(scode, bookingList);
    }
}
