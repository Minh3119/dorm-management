/**
 *
 * @author Group 4
 */
package dto;

public class Student {
    
    private String scode;
    private String name;
    private int byear;      // student's birth year, used to check age >= 18
    public Room bookedRoom;
    public Student(String scode, String name, int byear) {
        this.scode = scode;
        this.name = name;
        this.byear = byear;
        this.bookedRoom=null;
    }

    public String getScode() {
        return scode;
    }

    public String getName() {
        return name;
    }

    public int getByear() {
        return byear;
    }

    public void setByear(int byear) {
        this.byear = byear;
    }
     public int compareScode(Student s2) {
        // so sanh rcode
        return scode.compareTo(s2.getScode());
    }
    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-8s",scode, name, byear);
    }
    
}
