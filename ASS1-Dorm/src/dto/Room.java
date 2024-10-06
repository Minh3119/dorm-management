/**
 *
 * @author Group 4
 */
package dto;

import util.RoomType;

public class Room {
    
    // room id
    private String rcode;
    
    // name of the room
    private String name;
    
    // room's dorm name
    private String dom;
    
    // room at which floor
    private String floor;
    
    // room type - check out util.RoomType
    private RoomType roomType;
    
    // number of booked beds
    private int booked;
    
    // price for 1 bed
    private double price;
    
    public Room(){
        
    }

    public Room(String rcode, String name, String dom, String floor, RoomType roomType, int booked, double price) {
        this.rcode = rcode;
        this.name = name;
        this.dom = dom;
        this.floor = floor;
        this.roomType = roomType;
        this.booked = booked;
        this.price = price;
    }

    public String getRcode() {
        return rcode;
    }

    public String getName() {
        return name;
    }

    public String getDom() {
        return dom;
    }

    public String getFloor() {
        return floor;
    }
    
    public int getBeds() {
        int beds = 4;
        if (roomType == RoomType.TRIPLE) {
            beds = 6;
        }
        return beds;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getBooked() {
        return booked;
    }

    public double getPrice() {
        return price;
    }
    
    public void setBooked(int booked) {
        this.booked = booked;
    }
    
    public void increaseBooked() {
        this.booked++;
    }
    
    public void decreaseBooked() {
        this.booked--;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        
        return String.format("%-10s | %-20s | %-5s | %-5s | %-7s | %-4s | %-6s | %s",
                rcode, name, dom, floor, roomType.toString(), getBeds(), booked, price);
    }
    
}
