package Playable;
import Enemy.Martian;
import static java.lang.System.out;

public class BloodHarvester extends Base{
    private boolean activateBloodSurge;

    public BloodHarvester() {
        this.setName("Blood Harvester");
        this.setTags("Sustain");
        this.setMaxHealth(700);
        this.addPhysicalAttack(60);
        this.addPhysicalArmor(40);
        this.addMagicArmor(32);
        this.activateBloodSurge = false;
    }

    public int getBonusBloodSurgeDamage() {
        if(activateBloodSurge) {
            int bonusBloodSurgeDamage = (int)(this.getMaxHealth() * 0.03);
            this.addHealth(-bonusBloodSurgeDamage);
            return bonusBloodSurgeDamage;
        } else return 0;
    }

    public void toggleBloodSurge() {
        activateBloodSurge = !activateBloodSurge; // Reverses the boolean (true => false, vice versa)
    }

    @Override
    public void charDesc() {
        out.printf("Name: %s\n", this.getName());
        out.printf("Tags: %s\n", this.getTags());
        out.println("========================================================================");
        out.println("                               >> STATS <<");
        out.printf("                      Physical Attack: %d\n", this.getPhysicalAttack());
        out.printf("                               Health: %d | %d\n", this.getHealth(), this.getMaxHealth());
        out.printf("                       Physical Armor: %d\n", this.getPhysicalArmor());
        out.printf("                         Magic Resist: %d\n", this.getMagicArmor());
        out.println("========================================================================");
        out.println(" 0. Basic Attack");
        out.printf ("    Performs basic attack dealing %d damage.\n", this.getPhysicalAttack());
        out.println("    Critical strikes deal 200% damage.");
        out.println();
        out.println("    Cooldown: 0 Turns");
        out.println();
        out.println(" 1. Consume");
        out.println("    Harvester extracts blood from the enemy, dealing 50(+65% AP)");
        out.println("    magic damage. Heals for 50% of the damage dealt.");
        out.println();
        out.println("    Cooldown: 3 Turns");
        out.println();
        out.println(" 2. Blood Spike");
        out.println("    Summons blood spikes on the ground, dealing 60(+55% AP)(+10% Current");
        out.println("     HP) magic damage.");
        out.println();
        out.println("    Cooldown: 2 Turns");
        out.println();
        out.println(" 3. Blood Shield");
        out.println("    Creates a shield based on his max HP, absorbing incoming damage. ");
        out.println("    Receive 50% max HP shield for 2 turns. Remaining shield will be");
        out.println("    converted into health.");
        out.println();
        out.println("    Cooldown: 5 Turns");
        out.println();
        out.println(" 4. Blood Surge");
        out.println("    When active, consume 3% of your max HP as bonus damage to your");
        out.println("    attacks. Cast again to disable.");
        out.println();
        out.println("    After using, you can still cast another ability.");
        out.println();
        out.println("    Cooldown: 0 Turns");
        out.println("========================================================================");
        out.println(" Select this fighter?");
        out.println(" 1 - Yes, 0 - No");
    }

    public void abilityList(Martian NPC) {
        String toggle = this.activateBloodSurge ? "On" : "Off";
        out.println(" Select your move:");
        out.printf(" 0. Basic Attack | CD: %s\n", this.ability0RemainingCD());
        out.printf(" 1. Consume      | CD: %s\n", this.ability1RemainingCD());
        out.printf(" 2. Blood Spike  | CD: %s\n", this.ability2RemainingCD());
        out.printf(" 3. Blood Shield | CD: %s\n", this.ability3RemainingCD());
        out.printf(" 4. Blood Surge  | CD: %s | Bonus Damage: %s | Toggle: %s\n", this.ability4RemainingCD(), (int)(this.getMaxHealth() * 0.03), toggle);
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

    public boolean basicAttack(Base BloodHarvester, Martian NPC) {
        if(abilityCD0 == -1) {
            int chance = random.nextInt(0, 101);
            String abilityName = "Slash";
            String type = "Physical";

            int damage = getPhysicalAttack();
            if (chance < this.getCritChance()) {
                damage *= 2;
            }
            if(this.activateBloodSurge) { damage += this.getBonusBloodSurgeDamage(); }
            damage = calculateDamage(damage, NPC, type);
            this.dealDamage(damage, NPC);

            if (chance < this.getCritChance()) out.println(" CRITICAL!!!");
            out.printf(" %s used %s dealing %d physical damage!%n", this.getName(), abilityName, damage);
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

    public boolean ability1(Base BloodHarvester, Martian NPC) {
        if(abilityCD1 == -1) {
            String abilityName = "Consume";
            String type = "Magic";

            int damage = 50 + (int)(this.getMagicAttack() * 0.65);
            if(this.activateBloodSurge) { damage += this.getBonusBloodSurgeDamage(); }
            damage = calculateDamage(damage, NPC, type);
            int heal = (int)(damage * 0.5);
            NPC.addHealth(-damage);
            this.addHealth(heal);
            this.abilityCD1 = 3;

            out.printf(" %s used %s dealing %d magic damage!\n", this.getName(), abilityName, damage);
            out.printf(" It restored %d health.\n", heal);
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

    public boolean ability2(Base BloodHarvester, Martian NPC) {
        if(abilityCD2 == -1) {
            String abilityName = "Blood Spike";
            String type = "Magic";

            int damage = 60 + (int)(this.getMagicAttack() * 0.55 + NPC.getHealth() * 0.10);
            if(this.activateBloodSurge) { damage += this.getBonusBloodSurgeDamage(); }
            damage = calculateDamage(damage, NPC, type);
            NPC.addHealth(-damage);
            this.abilityCD2 = 2;

            out.printf(" %s used %s dealing %d magic damage!\n", this.getName(), abilityName, damage);
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

    public boolean ability3(Base BloodHarvester, Martian NPC) {
        if(abilityCD3 == -1) {
            String abilityName = "Blood Shield";

            int block = (int)(this.getMaxHealth() * 0.5);
            this.addShield(block);
            this.abilityCD3 = 5;

            out.printf(" %s used %s blocking up to %d damage!\n", this.getName(), abilityName, block);
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

    public boolean ability4(Base BloodHarvester, Martian NPC) {
        if(abilityCD4 == -1) {
            String abilityName = "Blood Surge";

            this.toggleBloodSurge();
            if(this.activateBloodSurge) { out.printf(" %s used %s increasing his next attacks!\n", this.getName(), abilityName); }
            else { out.printf(" %s stopped his %s.\n", this.getName(), abilityName); }
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
            out.println(" You can still cast another ability.");
        } else {
            out.println(" Ability in cooldown!");
            out.print(" Press Enter key to continue.");
            prompt.readPassword();
        }
        return false;
    }
}
