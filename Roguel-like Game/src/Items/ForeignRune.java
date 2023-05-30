package Items;
import Playable.Base;
import static java.lang.System.*;

public class ForeignRune extends ItemBase {
    public ForeignRune() {
        this.setName("Foreign Rune");
        this.setTags("Health, Defense");
        this.addHealth(200);
        this.addPhysicalArmor(20);
        this.addMagicArmor(20);
    }
    public void addStats(Base Player) {
        Player.addHealth(this.getHealth());
        Player.addPhysicalArmor(this.getPhysicalArmor());
        Player.addMagicArmor(this.getMagicArmor());
    }

    public void itemDesc() {
        out.printf("Name: %s\n", this.getName());
        out.printf("Tags: %s\n", this.getTags());
        out.println("===============================================================");
        out.println("Description:");
        out.println("Gives +200 Health and +20 Physical Armor and Magic Resist to");
        out.println("the holder.");
        out.println("===============================================================");
        out.println("Do you like to equip this item?");
        out.println("1 - Yes, 0 - No");
        out.print("Enter selection: ");
    }
}
