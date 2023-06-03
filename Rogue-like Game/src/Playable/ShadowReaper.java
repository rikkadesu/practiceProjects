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
        out.println("========================================================================");
        out.println("                               >> STATS <<");
        out.printf("                      Physical Attack: %d\n", this.getPhysicalAttack());
        out.printf("                               Health: %d | %d\n", this.getHealth(), this.getMaxHealth());
        out.printf("                       Physical Armor: %d\n", this.getPhysicalArmor());
        out.printf("                         Magic Resist: %d\n", this.getMagicArmor());
        out.println("========================================================================");
        out.println(" Abilities:");
        out.println(" 0. Basic Attack");
        out.println("    Performs basic attack dealing 65 damage.");
        out.println("    Critical strikes deal 200% damage.");
        out.println();
        out.println("    Cooldown: 0 Turns");
        out.println();
        out.println(" 1. Dark Pact");
        out.println("    Fuses his hands with shadow power and then strike dealing");
        out.println("    80 (+130% PA) physical damage.");
        out.println("    Can critically strike to deal 1.5x damage.");
        out.println();
        out.println("    Cooldown: 3 Turns");
        out.println();
        out.println(" 2. Evade and Nullify");
        out.println("    Covering himself from dark mist, taking 25% less damage from");
        out.println("    next attack, while nullifying enemy's defenses,  making them");
        out.println("    receive 35% more damage on next attack.");
        out.println();
        out.println("    Cooldown: 3 Turns");
        out.println();
        out.println(" 3. Silent Slaughter");
        out.println("    Sneakily attacks his target dealing 60 (135% PA + 150%");
        out.println("    physical PEN) physical damage.");
        out.println();
        out.println("    Cooldown: 3 Turns");
        out.println();
        out.println(" 4. Shadow Execute");
        out.println("    Consumes his inner life energy to deal massive amounts of damage.");
        out.println("    Consume 15% of max HP. If the enemy is below 25% HP, instantly kill");
        out.println("    and restore consumed health, else deal 200 (+85% PA) physical damage");
        out.println("    to it.");
        out.println();
        out.println("    Cooldown: 4 Turns");
        out.println("========================================================================");
        out.println(" Select this guardian?");
        out.println(" 1 - Yes, 0 - No");
    }

    public void abilityList(Martian NPC) {
        out.println(" Select your move:");
        out.printf(" 0. Basic Attack     | CD: %s\n", this.ability0RemainingCD());
        out.printf(" 1. Dark Pact        | CD: %s\n", this.ability1RemainingCD());
        out.printf(" 2. Nullify          | CD: %s\n", this.ability2RemainingCD());
        out.printf(" 3. Silent Slaughter | CD: %s\n", this.ability3RemainingCD());
        out.printf(" 4. Shadow Execute   | CD: %s |  %%HP Threshold: [%d]\n", this.ability4RemainingCD(), (int)(NPC.getMaxHealth() * 0.25));
    }

    public String ability0RemainingCD() {
        if(this.abilityCD0 == -1) return "Ready";
        else return this.abilityCD0 + 1 + " turns";
    }
    public String ability1RemainingCD() {
        if(this.abilityCD1 == -1) return "Ready";
        else return this.abilityCD1 + 1 + " turns";
    }
    public String ability2RemainingCD() {
        if(this.abilityCD2 == -1) return "Ready";
        else return this.abilityCD2 + 1 + " turns";
    }
    public String ability3RemainingCD() {
        if(this.abilityCD3 == -1) return "Ready";
        else return this.abilityCD3 + 1 + " turns";
    }
    public String ability4RemainingCD() {
        if(this.abilityCD4 == -1) return "Ready";
        else return this.abilityCD4 + 1 + " turns";
    }

    public boolean useAbility(int choice, Base Player, Martian NPC) {
        boolean endTurn;
        switch (choice) {
            case 0 -> {
                endTurn = this.basicAttack(Player, NPC);
                return endTurn;
            }
            case 1 -> {
                endTurn = this.ability1(Player, NPC);
                return endTurn;
            }
            case 2 -> {
                endTurn = this.ability2(Player, NPC);
                return endTurn;
            }
            case 3 -> {
                endTurn = this.ability3(Player, NPC);
                return endTurn;
            }
            case 4 -> {
                endTurn = this.ability4(Player, NPC);
                return endTurn;
            }
            default -> {
                out.println("Invalid choice!");
                return false;
            }
        }
    }

    public boolean basicAttack(Base ShadowReaper, Martian NPC) {
        // (100% AD) physical damage, 200% crit damage
        if(abilityCD0 == -1) {
            int chance = random.nextInt(0, 101);
            String abilityName = "Stab";
            String type = "Physical";

            int damage = getPhysicalAttack();
            if (chance < this.getCritChance()) {
                damage *= 2;
            }
            damage = calculateDamage(damage, NPC, type);
            this.dealDamage(damage, NPC);

            if (chance < this.getCritChance()) out.println(" CRITICAL!!!");
            out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else {
            out.println(" Ability in cooldown!");
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return false;
        }
    }
    public boolean ability1(Base ShadowReaper, Martian NPC) {
        // 80 (+130% AD) physical damage, 150% crit damage
        if(abilityCD1 == -1) {
            int chance = random.nextInt(0, 101);
            String abilityName = "Dark Pact";
            String type = "Physical";

            int damage = 80 + (int) (this.getPhysicalAttack() * 1.3);
            if (chance < this.getCritChance()) { damage *= 1.5; }

            damage = this.calculateDamage(damage, NPC, type);
            this.dealDamage(damage, NPC);
            abilityCD1 = 3;

            if (chance < this.getCritChance()) out.println(" CRITICAL!!!");
            out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else {
            out.println(" Ability in cooldown!");
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return false;
        }
    }
    public boolean ability2(Base ShadowReaper, Martian NPC) {
        // +35% increased damage, receive 25% less damage
        if(abilityCD2 == -1) {
            String abilityName = "Evade and Nullify";

            this.addIncreasedDamage(0.35);
            this.addDecreaseIncomingDamage(0.25);
            abilityCD2 = 3;

            out.printf(" Shadow Reaper used %s increasing his next damage\n and receiving less damage from next attack.%n", abilityName);
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else {
            out.println(" Ability in cooldown!");
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return false;
        }
    }
    public boolean ability3(Base ShadowReaper, Martian NPC) {
        // 60 (140% AD + 180% physical PEN) physical damage
        if(abilityCD3 == -1) {
            String abilityName = "Silent Slaughter";
            String type = "Physical";

            int damage = 60 + ((int) (this.getPhysicalAttack() * 1.40) + (int) (this.getPhysicalPEN() * 1.80));
            damage = this.calculateDamage(damage, NPC, type);
            this.dealDamage(damage, NPC);
            abilityCD3 = 3;

            out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else {
            out.println(" Ability in cooldown!");
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return false;
        }
    }
    public boolean ability4(Base ShadowReaper, Martian NPC) {
        // 25% max HP execute, else 200 (+85% AD) true damage
        if(abilityCD4 == -1) {
            String abilityName = "Shadow Execute";
            String type = "Physical";

            if (NPC.getHealth() < (NPC.getMaxHealth() * 0.25)) {
                NPC.addHealth(-NPC.getHealth());
                out.printf(" Shadow Reaper used %s, successfully dealing the killing blow!%n", abilityName);
            } else {
                int damage = 200 + (int) (this.getPhysicalAttack() * 0.85);
                damage = this.calculateDamage(damage, NPC, type);
                this.dealDamage(damage, NPC);
                this.addHealth(-(int) (this.getMaxHealth() * 0.15));
                out.printf(" Shadow Reaper used %s dealing %d damage!%n", abilityName, damage);
                out.printf(" He lost %d HP in the process.%n", (int) (this.getMaxHealth() * 0.15));
            }
            abilityCD4 = 4;

            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else {
            out.println(" Ability in cooldown!");
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return false;
        }
    }
}
