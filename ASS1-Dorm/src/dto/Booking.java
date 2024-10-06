/**
 *
 * @author Group 4
 */

package dto;

import java.util.Date;

public class Booking {
    
    private String rcode;
    private String scode;
    private Date bookDate;
    private Date leaveDate;
    private int state;      // 0 or 1 only

    public Booking(String rcode, String scode, Date bookDate, Date leaveDate, int state) {
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

    public Date getBookDate() {
        return bookDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public int getState() {
        return state;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
}
