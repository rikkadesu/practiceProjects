package Playable;
import Enemy.Martian;
import java.util.Scanner;

public class ShadowReaper extends Base {
    static Scanner scan = new Scanner(System.in);

    public ShadowReaper() {
        this.setName("Shadow Reaper");
        this.setTags("Burst");
        this.addPhysicalAttack(65);
        this.addHealth(600);
        this.setMaxHealth(this.getHealth());
        this.addPhysicalArmor(30);
        this.addMagicArmor(27);
    }

    @Override
    public void charDesc() {

        System.out.println("Name: Shadow Reaper");
        System.out.println("Tags: Burst");
        System.out.println("===============================================================");
        System.out.println("Stats:");
        System.out.println("PA: 65 | MA: 0");
        System.out.println("HP: 600 | Physical Armor: 30");
        System.out.println("Magic Resist: 27");
        System.out.println("===============================================================");
        System.out.println("Abilities:");
        System.out.println("0. Basic Attack");
        System.out.println("   Performs basic attack dealing 65 damage.");
        System.out.println("   Critical strikes deal 200% damage.");
        System.out.println();
        System.out.println("   Cooldown: 0 Turns");
        System.out.println();
        System.out.println("1. Dark Pact");
        System.out.println("   Fuses his hands with shadow power and then strike dealing");
        System.out.println("   50 (+120% PA) physical damage.");
        System.out.println("   Can critically strike to deal 1.5x damage.");
        System.out.println();
        System.out.println("   Cooldown: 2 Turns");
        System.out.println();
        System.out.println("2. Nullify");
        System.out.println("   Nullifies enemy's defenses, making them receive 35% more");
        System.out.println("   damage on next attack.");
        System.out.println();
        System.out.println("   Cooldown: 3 Turns");
        System.out.println();
        System.out.println("3. Silent Slaughter");
        System.out.println("   Sneakily attacks his target dealing 60 (135% PA + 115% physical PEN)");
        System.out.println("   physical damage.");
        System.out.println();
        System.out.println("   Cooldown: 2 Turns");
        System.out.println();
        System.out.println("4. Shadow Execute");
        System.out.println("   Consumes his inner life energy to deal massive amounts of");
        System.out.println("   damage. Consume 15% of max HP. If the enemy is below 25% HP,");
        System.out.println("   instantly kill it, else deal 200 (+85%PA) physical damage to");
        System.out.println("   it.");
        System.out.println();
        System.out.println("   Cooldown: 4 Turns");
        System.out.println("===============================================================");
        System.out.println("Select this guardian?");
        System.out.println("1 - Yes, 0 - No");
        System.out.print("Enter selection: ");
    }

    public void basicAttack(Base ShadowReaper, Martian NPC) {
        // (100% AD) physical damage, 200% crit damage
        int chance = random.nextInt(0, 101);
        String abilityName = "Stab";
        int damage = getPhysicalAttack();
        if(chance < this.getCritChance()) { damage *= 2; }
        damage = checkIncreasedDamage(damage);
        damage -= ((NPC.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = checkEnemyReduction(damage, NPC);
        NPC.addHealth(-damage);

        if(chance < this.getCritChance()) { System.out.println("CRITICAL!!!"); }
        System.out.printf("Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability1(Base ShadowReaper, Martian NPC) {
        // 50 (+150% AD) physical damage, 180% crit damage
        int chance = random.nextInt(0, 101);
        String abilityName = "Dark Pact";
        int damage = 50 + (int)(this.getPhysicalAttack() * 1.5);
        if(chance < this.getCritChance()) { damage *= 1.8; }
        damage = checkIncreasedDamage(damage);
        damage -= ((NPC.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = checkEnemyReduction(damage, NPC);
        NPC.addHealth(-damage);

        System.out.printf("Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability2(Base ShadowReaper, Martian NPC) {
        // +35% increased damage, receive 50% less damage
        String abilityName = "Evade and Nullify";
        this.addIncreasedDamage(0.35);
        this.addDecreaseIncomingDamage(0.5);

        System.out.printf("Shadow Reaper used %s increasing his next damage and receiving less damage for next attack.%n", abilityName);
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability3(Base ShadowReaper, Martian NPC) {
        // 60 (135% AD + 115% physical PEN) physical damage
        String abilityName = "Silent Slaughter";
        int damage = 60 + ((int)(this.getPhysicalAttack() * 1.35) + (int)(this.getPhysicalPEN() * 1.15));
        damage = checkIncreasedDamage(damage);
        damage -= ((NPC.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = checkEnemyReduction(damage, NPC);
        NPC.addHealth(-damage);

        System.out.printf("Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability4(Base ShadowReaper, Martian NPC) {
        // 25% max HP execute, else 200 (+85% AD) true damage
        String abilityName = "Shadow Execute";
        int damage = 200 + (int)(this.getPhysicalAttack() * 0.85);
        if(NPC.getHealth() < (NPC.getMaxHealth() * 0.25)) {
            NPC.addHealth(-NPC.getHealth());
            System.out.printf("Shadow Reaper used %s, successfully dealing the killing blow!%n", abilityName);
        } else {
            NPC.addHealth(-damage);
            this.addHealth(-(int)(this.getMaxHealth() * 0.15));
            System.out.printf("Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
            System.out.printf("He lost %d HP in the process.%n", (int)(this.getMaxHealth() * 0.15));
        }
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
}
