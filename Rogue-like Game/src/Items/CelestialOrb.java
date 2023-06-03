package Items;
import Playable.Base;
import static java.lang.System.out;

public class CelestialOrb extends ItemBase {
    public CelestialOrb() {
        this.setName("Celestial Orb");
        this.setTags("Magic PEN, Magic Power");
        this.addMagicPEN(8);
        this.addMagicAttack(40);
    }

    public void addStats(Base Player, ItemBase CelOrb) {
        Player.addMagicPEN(CelOrb.getMagicPEN());
        Player.addMagicAttack(CelOrb.getMagicAttack());
    }

    public void itemDesc() {
        out.printf(" Name: %s\n", this.getName());
        out.printf(" Tags: %s\n", this.getTags());
        out.println("===============================================================");
        out.println(" Description:");
        out.println(" Gives +8 Magic PEN and +40 Magic Attack to the holder.");
        out.println("===============================================================");
        out.println(" Do you like to equip this item?");
        out.println(" 1 - Yes, 0 - No");
    }
}
