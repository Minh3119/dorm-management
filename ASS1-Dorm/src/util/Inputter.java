/**
 *
 * @author Group 4
 */

package util;

import java.util.Scanner;
import util.Menu;
import util.RoomType;

public class Inputter {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static RoomType convertsToRoomType(String s) {
        if (s.compareTo("DOUBLE") == 0) {
            return RoomType.DOUBLE;
        } else if (s.compareTo("TRIPLE") == 0) {
            return RoomType.TRIPLE;
        }
        return null;
    }
    
    public static float getPrice() {
        while (true) {
            String s = scanner.nextLine();
            try {
                float f = Float.parseFloat(s);
                if (f >= 0) {
                    return f;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Try again:");
            }
        }
    }
    
//    public static int getBookedBeds(RoomType roomType) {
//        
//        int maxBeds = roomType.getBeds();
//        return getInt(0, maxBeds);
//    }
    
    public static String getString() {
        return scanner.nextLine();
    }
    
    public static RoomType getRoomType() {
        Menu menu = new Menu("There are 2 types of room:", "Your choice: ");
        menu.add("1. Double room (" +RoomType.DOUBLE.getBeds()+ " beds)");
        menu.add("2. Triple room (" +RoomType.TRIPLE.getBeds()+ " beds)");
        menu.display();

        int choice = getInt(1,2);
        switch (choice) {
            case 1:
                return RoomType.DOUBLE;                
            case 2:
                return RoomType.TRIPLE;
        }
        return null;
    }
    
    public static int getInt(int min, int max) {
        while (true) {
            String s = scanner.nextLine();
            try {
                int d = Integer.parseInt(s);
                if (d >= min && d <= max) {
                    return d;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Try again:");
            }
        }
    }
    
    private boolean isValidBeds(int beds, RoomType roomType) {
        return beds <= roomType.getBeds();
    }
}
