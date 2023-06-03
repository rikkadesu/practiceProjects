package Items;
import Playable.Base;
import static java.lang.System.*;

public class EnchantedAmulet extends ItemBase {
    public EnchantedAmulet() {
        this.setName("Enchanted Amulet");
        this.setTags("Massive Magic Power");
        this.addMagicAttack(70);
    }
    public void addStats(Base Player, ItemBase enchantAmulet) {
        Player.addMagicAttack(enchantAmulet.getMagicAttack());
        if(Player.getMagicAttackMultiplier() == 0) {
            Player.addMagicAttackMultiplier(0.40);
        }
    }

    public void itemDesc() {
        out.printf(" Name: %s\n", this.getName());
        out.printf(" Tags: %s\n", this.getTags());
        out.println("===============================================================");
        out.println(" Description:");
        out.println(" Gives +70 Magic Attack to the holder.");
        out.println(" Unique - Gives 40% more Magic Attack.");
        out.println("===============================================================");
        out.println(" Do you like to equip this item?");
        out.println(" 1 - Yes, 0 - No");
    }
}
