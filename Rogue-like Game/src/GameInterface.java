import static java.lang.System.*;
import java.io.Console;
import java.util.Scanner;
import java.util.ArrayList;
import Misc.CustomMethods;
import Playable.*;
import Items.*;
import Enemy.*;

public class GameInterface {
    static Base lockedChar; // Stores the character to be played (index form)
    static ArrayList<ItemBase> equippedItems;
    static Martian NPC;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean stillPlay = true;
        String title = "Galactic Grudge Match: Martian Melee Madness!";

        CustomMethods.clear_screen();
        int consoleWidth = getConsoleWidth();
        int spaces = (consoleWidth - title.length()) / 2;
        String padding = String.format("%" + spaces + "s", "");
        out.println(padding + "  " + title);
        out.print(padding + "\t\tPress Enter key to continue.");
        CustomMethods.prompt.readPassword();

        while(stillPlay) {
            NPC = new MartianSoldier();

            lockedChar = CharacterPicking.picking();
            equippedItems = ItemPicking.picking();
            SetAttributes.setItemAttributes(lockedChar, equippedItems);
            boolean winner = Combat.combat(lockedChar, NPC);

            CustomMethods.clear_screen();
            if(winner) {
                out.println(" You have defeated the Martian Invader!");
            } else {
                out.println(" You died. Martian Invaders will conquer your homeland.");
            }

            out.println(" Do you want to play again?");
            out.println(" 1 - Yes, 0 - No");
            stillPlay = CustomMethods.tryCatchInt(scan, "Enter selection: ") == 1;
        }
    }

    private static int getConsoleWidth() {
        Console console = System.console();
        if (console != null) {
            return console.writer().getClass().getDeclaredFields().length;
        }
        return -1; // Unable to determine console width
    }
}

// To-do
// Handle the 40% more Magic Attack - done
// Giving item attributes to character - done
// Generate enemy attributes - done
// Code the actual combat - done
// Try to add the cooldown feature - partially done (implement to enemy) - done
// Implement exception handling - done
// Improve quality of life
// Add more playable characters
// Add more enemies
// Add progression (increase character stats, increase difficulty)
