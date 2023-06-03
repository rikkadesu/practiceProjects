import Misc.CustomMethods;
import Playable.Base;
import Playable.ShadowReaper;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;

public class CharacterPicking {
    public static Base picking() {
        while(true) {
            CustomMethods.clear_screen();
            out.println("                     CHOOSE YOUR GUARDIAN");
            out.println("===============================================================");
            int lockedChar = AvailablePlayable.showList();           // Receives an integer value from specified method from specified class
            if(lockedChar != 0) {                                    // If it received a 0, then the user must proceed to pick again until they lock in
                return AvailablePlayable.charList.get(lockedChar-1); // Returns the selected character in form of integer (index)
            }
        }
    }
}

class AvailablePlayable {
    static ArrayList<Base> charList = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static int showList() {
        //Adding new character starts here
        charList.clear();
        charList.add(new ShadowReaper());

        int i = 1;
        for(Base name : charList) {                // Loops through all available characters
            out.printf(" %d. %s%n", i, name.getName());
            i++;
        }
        int selected = CustomMethods.tryCatchInt(scan, "Enter selection: ");  // Stores currently selected character temporarily
        boolean lockedIn = showCharDesc(selected); // Calls showCharDesc and receive a boolean value (Line 47)
        if(lockedIn) {
            return selected;                       // Depending on the boolean received by lockedIn,
                                                   // the selected character (Line 37)
        }                                          // will be passed to the CharacterPicking class
        return 0;                                  // Default return value
    }

    public static boolean showCharDesc(int selected) {
        try {
            Base currentPick = charList.get(selected - 1); // Takes the object specified by the selected character of user
            CustomMethods.clear_screen();
            currentPick.charDesc();                      // Takes their description
            int lockIn = CustomMethods.tryCatchInt(scan, "Enter selection: ");// User will input 1 or 0 depending on their choice
            return lockIn == 1;                          // Returns true if they agree, otherwise false
        } catch (Exception e) {
            out.print(" Invalid input. Press Enter key to continue.");
            console().readPassword();
            return false;
        }
    }
}