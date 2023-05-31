package Playable;
import Enemy.Martian;

import static java.lang.System.out;

public class ShadowReaper extends Base {
    public ShadowReaper() {
        this.setName("Shadow Reaper");
        this.setTags("Burst");
        this.addPhysicalAttack(65);
        this.setMaxHealth(600);
        this.addPhysicalArmor(30);
        this.addMagicArmor(27);
    }

    @Override
    public void charDesc() {
        out.println(" Name: Shadow Reaper");
        out.println(" Tags: Burst");
        out.println("===============================================================");
        out.println("                       >> STATS <<");
        out.printf("              Physical Attack: %d\n", this.getPhysicalAttack());
        out.printf("                       Health: %d | %d\n", this.getHealth(), this.getMaxHealth());
        out.printf("               Physical Armor: %d\n", this.getPhysicalArmor());
        out.printf("                 Magic Resist: %d\n", this.getMagicArmor());
        out.println("===============================================================");
        out.println(" Abilities:");
        out.println(" 0. Basic Attack");
        out.println("    Performs basic attack dealing 65 damage.");
        out.println("    Critical strikes deal 200% damage.");
        out.println();
        out.println("    Cooldown: 0 Turns");
        out.println();
        out.println(" 1. Dark Pact");
        out.println("    Fuses his hands with shadow power and then strike dealing");
        out.println("    50 (+120% PA) physical damage.");
        out.println("    Can critically strike to deal 1.5x damage.");
        out.println();
        out.println("    Cooldown: 2 Turns");
        out.println();
        out.println(" 2. Evade and Nullify");
        out.println("    Covering himself from dark mist, taking 50% less damage from");
        out.println("    next attack, while nullifying enemy's defenses,  making them");
        out.println("    receive 35% more damage on next attack.");
        out.println();
        out.println("    Cooldown: 3 Turns");
        out.println();
        out.println(" 3. Silent Slaughter");
        out.println("    Sneakily attacks his target dealing 60 (135% PA + 115%");
        out.println("    physical PEN) physical damage.");
        out.println();
        out.println("    Cooldown: 2 Turns");
        out.println();
        out.println(" 4. Shadow Execute");
        out.println("    Consumes his inner life energy to deal massive amounts of");
        out.println("    damage. Consume 15% of max HP. If the enemy is below 25% HP,");
        out.println("    instantly kill it, else deal 200 (+85% PA) physical damage");
        out.println("    to it.");
        out.println();
        out.println("    Cooldown: 4 Turns");
        out.println("===============================================================");
        out.println(" Select this guardian?");
        out.println(" 1 - Yes, 0 - No");
        out.print(" Enter selection: ");
    }

    public void abilityList(Martian NPC) {
        out.println(" Select your move:");
        out.println(" 0. Basic Attack");
        out.println(" 1. Dark Pact");
        out.println(" 2. Nullify");
        out.println(" 3. Silent Slaughter");
        out.printf(" 4. Shadow Execute | [%d]\n", (int)(NPC.getMaxHealth() * 0.25));
    }

    public void useAbility(int choice, Base Player, Martian NPC) {
        switch (choice) {
            case 0 -> this.basicAttack(Player, NPC);
            case 1 -> this.ability1(Player, NPC);
            case 2 -> this.ability2(Player, NPC);
            case 3 -> this.ability3(Player, NPC);
            case 4 -> this.ability4(Player, NPC);
            default -> out.println("Invalid choice!");
        }
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

        if(chance < this.getCritChance()) out.println(" CRITICAL!!!");
        out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
        out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability1(Base ShadowReaper, Martian NPC) {
        // 50 (+150% AD) physical damage, 180% crit damage
        int chance = random.nextInt(0, 101);
        String abilityName = "Dark Pact";
        int damage = 50 + (int)(this.getPhysicalAttack() * 1.2);
        if(chance < this.getCritChance()) { damage *= 1.5; }
        damage = checkIncreasedDamage(damage);
        damage -= ((NPC.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = checkEnemyReduction(damage, NPC);
        NPC.addHealth(-damage);

        if(chance < this.getCritChance()) out.println(" CRITICAL!!!");
        out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
        out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability2(Base ShadowReaper, Martian NPC) {
        // +35% increased damage, receive 50% less damage
        String abilityName = "Evade and Nullify";
        this.addIncreasedDamage(0.35);
        this.addDecreaseIncomingDamage(0.5);
        out.printf(" Shadow Reaper used %s increasing his next damage\n and receiving less damage for next attack.%n", abilityName);
        out.print(" Press Enter key to continue.");
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

        out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
        out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability4(Base ShadowReaper, Martian NPC) {
        // 25% max HP execute, else 200 (+85% AD) true damage
        String abilityName = "Shadow Execute";
        int damage = 200 + (int)(this.getPhysicalAttack() * 0.85);
        if(NPC.getHealth() < (NPC.getMaxHealth() * 0.25)) {
            NPC.addHealth(-NPC.getHealth());
            out.printf(" Shadow Reaper used %s, successfully dealing the killing blow!%n", abilityName);
        } else {
            NPC.addHealth(-damage);
            this.addHealth(-(int)(this.getMaxHealth() * 0.15));
            out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
            out.printf(" He lost %d HP in the process.%n", (int)(this.getMaxHealth() * 0.15));
        }
        out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
}
