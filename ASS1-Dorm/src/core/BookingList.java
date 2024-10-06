/**
 *
 * @author Group 4
 */

package core;

import dto.Booking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.MyLinkedList;
import util.Node;

public class BookingList extends MyLinkedList<Booking> {
    
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public BookingList() {
    }
    
    // 3.1
    public void loadData(String filename) {
        // data = rcode, scode, bdate, ldate, state
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    try {
                        Booking s = new Booking(
                            data[0], 
                            data[1], 
                            data[2].equals("null") ? null : formatter.parse(data[2]),
                            data[3].equals("null") ? null : formatter.parse(data[3]),
                            Integer.parseInt(data[4])
                        );
                        this.addLast(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 3.2
//    public void addToEnd(Booking booking) {
//        this.addLast(booking);
//    }
    
    public void bookRoom(Booking booking) {
        // add booking to the beginning
        this.addFirst(booking);
        System.out.println("Room booked successfully: " + booking);
    }
    
    // 3.3
    public void display() {
        traverse();
    }
    
    // 3.4
    public void saveData(String filename) {
        // data = rcode, scode, bdate, ldate, state
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Node<Booking> p = head;
            String[] lineComponents = new String[5];
            while (p != null) {
                Booking s = p.getInfo();
                lineComponents[0] = s.getRcode();
                lineComponents[1] = s.getScode();
                lineComponents[2] = s.getBookDate()==null ? "null" : formatter.format(s.getBookDate());
                lineComponents[3] = s.getLeaveDate()==null ? "null" : formatter.format(s.getLeaveDate());
                lineComponents[4] = String.valueOf(s.getState());
                
                String line = String.join(",", lineComponents);
                writer.write(line);
                writer.newLine();
                p = p.getNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    // 3.5
    public void sort(String rcode, String scode) {
        // sort + display result
        // rcode: descending, then scode descending
        
    }
    
    // 3.6
    public void leaveRoom(String rcode, String scode) {
        Node<Booking> current = head;
        while (current != null) {
            Booking booking = current.getInfo();
            if (booking.getRcode().equals(rcode) && booking.getScode().equals(scode) && booking.getState() == 1) {
                booking.setState(0);
                booking.setLeaveDate(new Date());
                System.out.println("Booking updated: " + booking);
                return;
            }
            current = current.getNext();
        }
        System.out.println("No booking found for the given rcode and scode.");
    }
}

