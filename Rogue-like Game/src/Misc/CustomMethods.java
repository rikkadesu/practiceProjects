package Misc;
import java.util.Scanner;
import java.io.IOException;
import java.io.Console;

import static java.lang.System.out;

public class CustomMethods {
    public static Console console = System.console();
    public static Console prompt = console;

    public static void clear_screen() { // Clears the whole terminal
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int tryCatchInt(Scanner scan, String prompt) {
        int value = 0;
        boolean valid = false;
        while(!valid) {
            try {
                out.print(" " + prompt);
                value = scan.nextInt();
                valid = true;
            } catch (Exception e) {
                scan.nextLine(); // Flushes input buffer
                out.print(" Invalid input. Press Enter key to continue.");
                console.readPassword();
            }
        }
        return value;
    }
}
