package Items;
import Playable.Base;
import static java.lang.System.*;

public class ShadowBlade extends ItemBase{
    public ShadowBlade() {
        this.setName("Shadow Blade");
        this.setTags("Armor PEN, Physical Attack");
        this.addPhysicalPEN(15);
        this.addPhysicalAttack(30);
    }
    public void addStats(Base Player, ItemBase shadowBlade) {
        Player.addPhysicalPEN(shadowBlade.getPhysicalPEN());
        Player.addPhysicalAttack(shadowBlade.getPhysicalAttack());
    }

    public void itemDesc() {
        out.printf(" Name: %s\n", this.getName());
        out.printf(" Tags: %s\n", this.getTags());
        out.println("===============================================================");
        out.println(" Description:");
        out.println(" Gives +15 Armor PEN and +30 Physical Attack to the holder.");
        out.println("===============================================================");
        out.println(" Do you like to equip this item?");
        out.println(" 1 - Yes, 0 - No");
        out.print(" Enter selection: ");
    }
}
