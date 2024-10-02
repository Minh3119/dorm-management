/**
 *
 * @author Group 4
 */
package dto;

public class Student {
    
    private String scode;
    private String name;
    private int byear;      // student's birth year, used to check age >= 18

    public Student(String scode, String name, int byear) {
        this.scode = scode;
        this.name = name;
        this.byear = byear;
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
    
    
}
