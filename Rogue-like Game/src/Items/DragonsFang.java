package Items;
import Playable.Base;

import static java.lang.System.out;

public class DragonsFang extends ItemBase {
    public DragonsFang() {
        this.setName("Dragon's Fang");
        this.setTags("Physical Attack, Critical Chance");
        this.addCritChance(35);
        this.addPhysicalAttack(30);
    }

    public void addStats(Base Player, ItemBase dragonFang) {
        Player.addCritChance(dragonFang.getCritChance());
        Player.addPhysicalAttack(dragonFang.getPhysicalAttack());
    }

    public void itemDesc() {
        out.printf(" Name: %s\n", this.getName());
        out.printf(" Tags: %s\n", this.getTags());
        out.println("===============================================================");
        out.println(" Description:");
        out.println(" Gives +35% Critical Strike Chance and +30 Physical Attack to ");
        out.println(" the holder.");
        out.println("===============================================================");
        out.println(" Do you like to equip this item?");
        out.println(" 1 - Yes, 0 - No");
        out.print(" Enter selection: ");
    }
}
