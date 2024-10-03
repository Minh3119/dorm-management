/**
 *
 * @author Group 4
 */
package manager;

import core.StudentList;

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
        
    }
    
    // 2.3
    public void display() {
        
    }
    
    // 2.4
    public void saveData() {
        studentList.saveData(filename);
        System.out.println("do smth pls");
    }
    
    // 2.5
    public void searchStudentByCode() {
        
    }
    
    // 2.6
    public void deleteStudentByCode() {
        
    }
    
    // 2.7
    public void searchStudentByName() {
        
    }
    
    // 2.8
    public void searchBookedRoomByStudentCode() {
        
    }
    
}
