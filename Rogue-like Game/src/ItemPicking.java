import Items.*;
import Misc.CustomMethods;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.console;
import static java.lang.System.out;

public class ItemPicking {
    public static ArrayList<ItemBase> picking() {
        ArrayList<ItemBase> equippedItem = new ArrayList<>();
        int equippedCount = 0;
        while(equippedCount < 3) {
            CustomMethods.clear_screen();
            out.println("                    CHOOSE AN ITEM TO USE");
            out.println("                   Select Up To Three Items");
            out.printf("                        Item count: %d\n", equippedCount);
            out.println("===============================================================");
            int itemEquipped = ShowItems.listItem();
            if (itemEquipped != 0) {
                equippedItem.add(ShowItems.itemList.get(itemEquipped-1));
                equippedCount++;
            }
        }
        return equippedItem;
    }
}

class ShowItems {
    static ArrayList<ItemBase> itemList = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static int listItem() {
        // Setting the list of items in the Array List
        itemList.clear();
        itemList.add(new CelestialOrb());
        itemList.add(new DragonsFang());
        itemList.add(new EnchantedAmulet());
        itemList.add(new ForeignRune());
        itemList.add(new ShadowBlade());

        int i = 1;
        for(ItemBase name : itemList) {
            out.printf(" %d. %s\n", i, name.getName());
            i++;
        }
        int selected = CustomMethods.tryCatchInt(scan, "Enter selection: ");
        boolean equipped = showItemDesc(selected);
        if(equipped) return selected;
        return 0; // Default return value
    }

    public static boolean showItemDesc(int selected) {
        try {
            ItemBase currentPick = itemList.get(selected - 1);
            CustomMethods.clear_screen();
            currentPick.itemDesc();
            int equip = CustomMethods.tryCatchInt(scan, "Enter selection: ");
            return equip == 1;
        } catch (Exception e) {
            out.print(" Invalid input. Press Enter key to continue.");
            console().readPassword();
            return false;
        }
    }
}