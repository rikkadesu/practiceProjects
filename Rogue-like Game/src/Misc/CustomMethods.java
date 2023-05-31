package Misc;
import java.io.IOException;
import java.io.Console;

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
}
