import Items.ItemBase;
import Playable.Base;
import java.util.ArrayList;

public class SetAttributes {
    public static void setItemAttributes(Base Player, ArrayList<ItemBase> selectedItems) {
        for(ItemBase item : selectedItems) {
            item.addStats(Player, item);
        }
        if(Player.getMagicAttackMultiplier() > 0) {
            Player.addMagicAttack((int)(Player.getMagicAttack() * Player.getMagicAttackMultiplier()));
        }
    }
    public static void setBonusMagic(Base Player, int bonus) {
        if(Player.getMagicAttackMultiplier() > 0) {
            Player.addMagicAttack((int)(bonus * (1 + Player.getMagicAttackMultiplier())));
        }
    }
}
