import static java.lang.System.*;
import java.util.Scanner;
import Misc.CustomMethods;
import Playable.*;
import Enemy.*;

public class Combat {
    public static void playerStats(Base Player) {
        out.println(" YOUR STATS");
        out.printf(" Health: %d | %d\n", Player.getHealth(), Player.getMaxHealth());
        out.printf(" PA: %d | MA: %d\n", Player.getPhysicalAttack(), Player.getMagicAttack());
        out.printf(" AR: %d | MR: %d\n", Player.getPhysicalArmor(), Player.getMagicArmor());
        out.printf(" Physical PEN: %d | Magic PEN: %s\n", Player.getPhysicalPEN(), Player.getMagicPEN());
        out.printf(" Physical Lifesteal: %d | Magic Lifesteal: %s\n", Player.getPhysicalLifeSteal(), Player.getMagicLifeSteal());
        out.printf(" Crit: %d%% | Damage Amplifier: %.2f\n", Player.getCritChance(), Player.getIncreasedDamage());
        out.printf(" Damage Reduction: %.2f\n", Player.getDecreaseIncomingDamage());
    }
    public static void enemyStats(Martian NPC) {
        out.println(" ENEMY STATS");
        out.printf(" Health: %d | %d\n", NPC.getHealth(), NPC.getMaxHealth());
        out.printf(" PA: %d | MA: %d\n", NPC.getPhysicalAttack(), NPC.getMagicAttack());
        out.printf(" AR: %d | MR: %d\n", NPC.getPhysicalArmor(), NPC.getMagicArmor());
        out.printf(" Physical PEN: %d | Magic PEN: %s\n", NPC.getPhysicalPEN(), NPC.getMagicPEN());
        out.printf(" Physical Lifesteal: %d | Magic Lifesteal: %s\n", NPC.getPhysicalLifeSteal(), NPC.getMagicLifeSteal());
        out.printf(" Crit: %d%% | Damage Amplifier: %.2f\n", NPC.getCritChance(), NPC.getIncreasedDamage());
        out.printf(" Damage Reduction: %.2f\n", NPC.getDecreaseIncomingDamage());
    }

    public static boolean combat(Base Player, Martian NPC) {
        Scanner scan = new Scanner(System.in);
        boolean endTurn1 = false;
        boolean endTurn2 = false;
        int attack;
        while(Player.getHealth() > 0 && NPC.getHealth() > 0) { // Checks if both still have health, if either of the two reached 0 or below, game will end
            CustomMethods.clear_screen();
            enemyStats(NPC);
            out.println("===============================================================");
            playerStats(Player);
            out.println("===============================================================");
            Player.abilityList(NPC);
            while(!endTurn1) {
                out.print(" Enter selection: ");
                attack = scan.nextInt();
                endTurn1 = Player.useAbility(attack, Player, NPC); // Player Attack
                }
            while(!endTurn2) {
                if(NPC.getHealth() > 0) { // Checks if enemy still has health left, if none left, enemy will not attack anymore
                    endTurn2 = NPC.useAbility(Player, NPC); // Enemy AI Attack
                    }
            }
            Player.updateCooldowns();
            NPC.updateCooldowns();
            endTurn1 = false;
            endTurn2 = false;
        }
        return NPC.getHealth() <= 0;
    }
}
