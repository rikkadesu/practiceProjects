package Misc;
import java.io.IOException;
import java.io.Console;

public class CustomMethods {
    public static Console prompt = System.console(); // Global declaration for console class

    public static void clear_screen() { // Clears the whole terminal
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
