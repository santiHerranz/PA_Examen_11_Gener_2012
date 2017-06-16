// Our version of Llibreria.Keyboard.java
// DrJava-friendly: uses Dialog Boxes (also solves focus problem)

// Place Llibreria.Keyboard.class in same directory as your programs,
// and DO NOT "import cs1" (or else you'll get LLC's Llibreria.Keyboard class)

// Use:
// Llibreria.Keyboard.readInt()    or  Llibreria.Keyboard.readInt(prompt)
// Llibreria.Keyboard.readDouble() or  Llibreria.Keyboard.readDouble(prompt)
// Llibreria.Keyboard.readString() or  Llibreria.Keyboard.readString(prompt)

import javax.swing.*;
import java.awt.*;

public class Keyboard {
    public static String readString() { return readString("Enter a string"); }

    public static String readString(String prompt) {
        System.out.print(prompt + " --> ");
        String result = JOptionPane.showInputDialog(null,prompt);
        System.out.println("[ " + result + " ]");
        return result;
    }

    public static int readInt() { return readInt("Enter an integer"); }

    public static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readString(prompt).trim()); }
            catch (Exception e) { oops(); }
        }
    }

    public static double readDouble() { return readDouble("Enter a floating-point number"); }

    public static double readDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(readString(prompt).trim()); }
            catch (Exception e) { oops(); }
        }
    }

    private static void oops() {
        Toolkit.getDefaultToolkit().beep();
        String errMessage = "** There was an error in your input.  Please try again. **";
        System.out.println(errMessage);
        JOptionPane.showMessageDialog(null,errMessage);
    }

    public static void test() {
        int i = Keyboard.readInt("Please enter an integer");
        System.out.println("You entered: " + i);
        double d = Keyboard.readDouble("Now enter a double");
        System.out.println("You entered: " + d);
        String s = Keyboard.readString("And enter a String");
        System.out.println("You entered: " + s);
    }

    public static void main(String[] args) { Keyboard.test(); }
}