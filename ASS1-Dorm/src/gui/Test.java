package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        // Get today's date
        Date today = new Date();
        
        // Format today's date as dd/MM/yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(today);
        
        // Print formatted date
        System.out.println("Today's date: " + formattedDate);
    }
}
