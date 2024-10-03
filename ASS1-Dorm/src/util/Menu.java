/**
 *
 * @author Group 4
 */
package util;

import java.util.ArrayList;

public class Menu extends ArrayList<String> {

    private String title;
    private String prompt;

    public Menu(String title, String prompt) {
        this.title = title;
        this.prompt = prompt; 
   }

    public void display() {
        System.out.println("\n" + title);
        for (String s : this) {
            System.out.println(s);
        }
        System.out.print(prompt);
    }
    
}
