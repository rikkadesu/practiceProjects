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

        while(stillPlay) {
            NPC = new MartianSoldier();
            String title = "Galactic Grudge Match: Martian Melee Madness!";

            CustomMethods.clear_screen();
            int consoleWidth = getConsoleWidth();
            int spaces = (consoleWidth - title.length()) / 2;
            String padding = String.format("%" + spaces + "s", "");
            out.println(padding + "  " + title);
            out.print(padding + "\t\tPress Enter key to continue.");
            CustomMethods.prompt.readPassword();

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
            out.print(" Enter selection: ");
            stillPlay = scan.nextInt() == 1;
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
// Try to add the cooldown feature
// Add progression (increase character stats, increase difficulty)


// FOR TESTING
//
//            CustomMethods.clear_screen();
//            out.println("Details");
//            out.printf("Selected: %s\n", lockedChar.getName());
//            int i = 1;
//            out.println("Equipped Items:");
//            for(ItemBase name : equippedItems) {
//            out.printf("%d. %s\n", i, name.getName());
//            i++;
//            }
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            out.println("Name: " + lockedChar.getName());
//            out.println("PA: " + lockedChar.getPhysicalAttack());
//            out.println("MA: " + lockedChar.getMagicAttack());
//            out.printf("Health: %d | %d\n", lockedChar.getHealth(), lockedChar.getMaxHealth());
//            out.println("Physical Armor: " + lockedChar.getPhysicalArmor());
//            out.println("Magic Armor: " + lockedChar.getMagicArmor());
//            out.println("Bonus Magic: " + lockedChar.getMagicAttackMultiplier());
//            out.println("Success equip");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            out.println("Added more Magic \"30\"");
//            SetAttributes.setBonusMagic(lockedChar, 30);
//            out.println("Name: " + lockedChar.getName());
//            out.println("PA: " + lockedChar.getPhysicalAttack());
//            out.println("MA: " + lockedChar.getMagicAttack());
//
//            out.println("After damaged:");
//            lockedChar.addHealth(-200);
//            out.printf("Health: %d | %d\n", lockedChar.getHealth(), lockedChar.getMaxHealth());
//            out.println("Success damage");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            out.println("Increased Max Health:");
//            lockedChar.setMaxHealth(1200);
//            out.printf("Health: %d | %d\n", lockedChar.getHealth(), lockedChar.getMaxHealth());
//            out.println("Success added Max Health");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            //
//
//            out.println("Name: " + NPC.getName());
//            out.println("PA: " + NPC.getPhysicalAttack());
//            out.println("MA: " + NPC.getMagicAttack());
//            out.printf("Health: %d | %d\n", NPC.getHealth(), NPC.getMaxHealth());
//            out.println("Physical Armor: " + NPC.getPhysicalArmor());
//            out.println("Magic Armor: " + NPC.getMagicArmor());
//            out.println("Bonus Magic: " + NPC.getMagicAttackMultiplier());
//            out.println("Success equip");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            out.println("After damaged:");
//            NPC.addHealth(-200);
//            out.printf("Health: %d | %d\n", NPC.getHealth(), NPC.getMaxHealth());
//            out.println("Success damage");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();
//
//            out.println("Increased Max Health:");
//            NPC.setMaxHealth(1200);
//            out.printf("Health: %d | %d\n", NPC.getHealth(), NPC.getMaxHealth());
//            out.println("Success added Max Health");
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();

//            out.printf("Enemy Max HP: %d\n", NPC.getMaxHealth());
//            lockedChar.abilityList(NPC);
//            out.println("Enter to continue");
//            CustomMethods.prompt.readPassword();