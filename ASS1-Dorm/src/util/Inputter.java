/**
 *
 * @author Group 4
 */

package util;

import java.util.Scanner;

public class Inputter {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static RoomType getRoomType() {
        Menu menu = new Menu("There are 2 types of room:", "Your choice: ");
        menu.add("1. Double room (4 beds)");
        menu.add("2. Triple room (6 beds)");
        System.out.print("Please choose an option:");
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
}
