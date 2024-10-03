/**
 *
 * @author Group 4
 */

package dto;

import model.Room;
import model.Student;
import util.MyLinkedList;

public class StudentList extends MyLinkedList<Student> {

    public StudentList() {
    }
    
    public void loadData(String filename) {
        
    }
    
    public void saveData(String filename) {
        
    }
    
    public Student searchByCode(String scode) {
        return null;
    }
    
    public Student searchByName(String sname) {
        return null;
    }
    
    public void deleteByCode(String scode) {
        
    }
    
    public Room searchStudentRoom(String scode) {
        // search the room that's booked by student with scode
        return null;
    }
    
}
