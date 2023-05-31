import static java.lang.System.*;
import java.util.Scanner;
import java.util.ArrayList;
import Misc.CustomMethods;
import Playable.*;
import Items.*;
import Enemy.*;

public class Combat {
    public static void playerStats(Base Player) {
        out.println(" YOUR STATS");
        out.printf(" PA: %d | MA: %d\n", Player.getPhysicalAttack(), Player.getMagicAttack());
        out.printf(" Health: %d | %d\n", Player.getHealth(), Player.getMaxHealth());
        out.printf(" AR: %d | MR: %d\n", Player.getPhysicalArmor(), Player.getMagicArmor());
        out.printf(" Crit: %d%% | Damage Amplifier: %.2f\n", Player.getCritChance(), Player.getIncreasedDamage());
        out.printf(" Damage Reduction: %.2f\n", Player.getDecreaseIncomingDamage());
    }
    public static void enemyStats(Martian NPC) {
        out.println(" ENEMY STATS");
        out.printf(" PA: %d | MA: %d\n", NPC.getPhysicalAttack(), NPC.getMagicAttack());
        out.printf(" Health: %d | %d\n", NPC.getHealth(), NPC.getMaxHealth());
        out.printf(" AR: %d | MR: %d\n", NPC.getPhysicalArmor(), NPC.getMagicArmor());
        out.printf(" Crit: %d%% | Damage Amplifier: %.2f\n", NPC.getCritChance(), NPC.getIncreasedDamage());
        out.printf(" Damage Reduction: %.2f\n", NPC.getDecreaseIncomingDamage());
    }

    public static boolean combat(Base Player, Martian NPC) {
        Scanner scan = new Scanner(System.in);
        int attack = 0;
        while(Player.getHealth() > 0 && NPC.getHealth() > 0) {
            CustomMethods.clear_screen();
            enemyStats(NPC);
            out.println("===============================================================");
            playerStats(Player);
            out.println("===============================================================");
            Player.abilityList(NPC);
            out.print(" Enter selection: ");
            attack = scan.nextInt();
            Player.useAbility(attack, Player, NPC); // Player Attack
            if(NPC.getHealth() > 0) NPC.useAbility(Player, NPC); // Enemy AI Attack
        }
        return NPC.getHealth() <= 0;
    }
}
