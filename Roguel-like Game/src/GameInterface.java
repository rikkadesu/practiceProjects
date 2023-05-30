import static java.lang.System.*;
import java.util.ArrayList;
import Misc.CustomMethods;
import Playable.*;
import Items.*;
import Enemy.*;

public class GameInterface {
    static Base lockedChar; // Stores the character to be played (index form)
    static ArrayList<ItemBase> equippedItems = new ArrayList<>();

    public static void main(String[] args) {
        boolean stillPlay = true;

        while(stillPlay) {
            CustomMethods.clear_screen();
            out.println("Galactic Grudge Match: Martian Melee Madness!");
            out.print("Press Enter key to continue.");
            CustomMethods.prompt.readPassword();

            lockedChar = CharacterPicking.picking();
            equippedItems = ItemPicking.picking();
        }
    }
}

// To-do
// Handle the 40% more Magic Attack
// Giving item attributes to character
// Generate enemy attributes
// Code the actual combat
// Try to add the cooldown feature
// Add progression (increase character stats, increase difficulty)


// FOR TESTING
//            CustomMethods.clear_screen();
//            out.printf("Selected: %s\n", lockedChar.getName());
//            int i = 1;
//            out.println("Equipped Items:");
//            for(ItemBase name : equippedItems) {
//                out.printf("%d. %s\n", i, name.getName());
//                i++;
//            }
//            out.println("Success equip");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            CustomMethods.clear_screen();
//            out.println("Name: " + lockedChar.getName());
//            out.println("PA: " + lockedChar.getPhysicalAttack());
//            out.printf("Health: %d | %d\n", lockedChar.getHealth(), lockedChar.getMaxHealth());
//            out.println("Physical Armor: " + lockedChar.getPhysicalArmor());
//            out.println("Magic Armor: " + lockedChar.getMagicArmor());
//            out.println("Success equip");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            out.println("After damaged:");
//            lockedChar.addHealth(-200);
//            out.printf("Health: %d | %d\n", lockedChar.getHealth(), lockedChar.getMaxHealth());
//            out.println("Success damage");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();