/**
 *
 * @author Group 4
 */

package model;

import java.time.LocalDate;

public class Booking {
    
    private String rcode;
    private String scode;
    private LocalDate bookDate;
    private LocalDate leaveDate;
    private int state;      // 0 or 1 only

    public Booking(String rcode, String scode, LocalDate bookDate, LocalDate leaveDate, int state) {
        this.rcode = rcode;
        this.scode = scode;
        this.bookDate = bookDate;
        this.leaveDate = leaveDate;
        this.state = state;
    }

    public String getRcode() {
        return rcode;
    }

    public String getScode() {
        return scode;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public int getState() {
        return state;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
}
