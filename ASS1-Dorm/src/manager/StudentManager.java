/**
 *
 * @author Group 4
 */
package manager;

import core.StudentList;
import dto.Room;
import dto.Student;
import java.util.Scanner;
import util.MyLinkedList;
import util.Node;
public class StudentManager {
    
    static String filename = "resources/students.txt";
    
    private StudentList studentList;

    public StudentManager(StudentList studentList) {
        this.studentList = studentList;
    }
    
    // 2.1
    public void loadData() {
        studentList.loadData(filename);
        System.out.println("aksldjsklj fuck this shjt");
    }
    
    // 2.2
    public void createNewStudentAtEnd() {
         Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter student code: ");
    String scode = scanner.nextLine();
    
    System.out.print("Enter student name: ");
    String name = scanner.nextLine();
    
    System.out.print("Enter birth year: ");
    int byear = scanner.nextInt();
    
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
        System.out.println("do smth pls");
    }
    
    // 2.5
    public void searchStudentByCode() {
          Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter student code: ");
    String scode = scanner.nextLine();
    
    Node<Student> studentNode = studentList.get(new Student(scode, "", 0));
    
    if (studentNode != null) {
        System.out.println("Student found: " + studentNode.getInfo());
    } else {
        System.out.println("Student with code " + scode + " not found.");
    }
    }
    
    // 2.6
    public void deleteStudentByCode() {
          Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter student code to delete: ");
    String scode = scanner.nextLine();
    
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
         Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter student name: ");
    String name = scanner.nextLine();
    
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
    public void searchBookedRoomByStudentCode() {
          Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter student code: ");
    String scode = scanner.nextLine();
    
    Node<Student> studentNode = studentList.get(new Student(scode, "", 0));
    
    if (studentNode != null) {
        Room bookedRoom = studentNode.getInfo().getBookedRoom();
        if (bookedRoom != null) {
            System.out.println("Student with code " + scode + " has booked room: " + bookedRoom.getRcode() + ", " + bookedRoom.getName());
        } else {
            System.out.println("Student with code " + scode + " has not booked any room.");
        }
    } else {
        System.out.println("Student with code " + scode + " not found.");
    }
    }
    
}
