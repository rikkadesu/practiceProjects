package Enemy;
import Playable.Base;
import static java.lang.System.out;

public class MartianSoldier extends Martian{
    public MartianSoldier() {
        this.setName("Martian Soldier");
        this.setTags("Basic Martian Troop");
        this.addPhysicalAttack(random.nextInt(40, 71));
        this.addCritChance(random.nextInt(0, 51));
        this.setMaxHealth(random.nextInt(1000, 2001));
        this.addPhysicalArmor(random.nextInt(41, 61));
        this.addMagicArmor(random.nextInt(30, 41));
        this.addPhysicalPEN(random.nextInt(0, 21));
    }

    public boolean useAbility(Base Player, Martian NPC) {
        int choice = random.nextInt(0, 5);
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

    @Override
    public boolean basicAttack(Base Player, Martian NPC) {
        // (100% AD) physical damage, 200% crit damage
        if(abilityCD0 == -1) {
            int chance = random.nextInt(0, 101);
            String abilityName = "Swing";
            String type = "Physical";

            int damage = getPhysicalAttack();
            if (chance < this.getCritChance()) {
                damage *= 2;
            }
            damage = this.calculateDamage(damage, Player, type);
            this.dealDamage(damage, Player);

            if (chance < this.getCritChance()) {
                System.out.println(" CRITICAL!!!");
            }
            System.out.printf(" Martian Soldier used %s dealing %d physical damage!%n", abilityName, damage);
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else return false;
    }
    public boolean ability1(Base Player, Martian NPC) {
        // 50 (+75% AD) physical damage
        if(abilityCD1 == -1) {
            String abilityName = "Power Strike";
            String type = "Physical";

            int damage = (50 + (int) (this.getPhysicalAttack() * 0.75));
            damage = calculateDamage(damage, Player, type);
            this.dealDamage(damage, Player);
            abilityCD1 = 2;

            System.out.printf(" Martian Soldier used %s dealing %d physical damage!%n", abilityName, damage);
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else return false;
    }
    public boolean ability2(Base Player, Martian NPC) {
        // 50 + (100% physical armor + 100% magic resist) physical damage
        if(abilityCD2 == -1) {
            String abilityName = "Shield Bash";
            String type = "Physical";

            int damage = 50 + (this.getPhysicalArmor() + this.getMagicArmor());
            damage = calculateDamage(damage, Player, type);
            this.dealDamage(damage, Player);
            abilityCD2 = 3;

            System.out.printf(" Martian Soldier used %s dealing %d physical damage!%n", abilityName, damage);
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else return false;
    }
    public boolean ability3(Base Player, Martian NPC) {
        // (30% lost HP) or (150% AD) physical damage (whichever is higher)
        if(abilityCD3 == -1) {
            String abilityName = "Energy Blast";
            String type = "Magic";

            int damage;
            int damage1 = (int) ((this.getMaxHealth() - this.getHealth()) * 0.15);
            int damage2 = this.getPhysicalAttack();
            damage = Math.max(damage1, damage2);
            damage = calculateDamage(damage, Player, type);
            this.dealDamage(damage, Player);
            abilityCD3 = 3;

            System.out.printf(" Martian Soldier used %s dealing %d magic damage!%n", abilityName, damage);
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else return false;
    }
    public boolean ability4(Base Player, Martian NPC) {
        // (150% AD + 10% enemy Max HP) physical damage
        if(abilityCD4 == -1) {
            String abilityName = "Meteor Strike";
            String type = "Physical";

            int damage = (int)(this.getPhysicalAttack() * 1.5) + (int)(Player.getMaxHealth() * 0.10);
            if (this.getHealth() < (this.getMaxHealth() * 0.3)) {
                damage = this.calculateDamage(damage, Player, "True");
                this.dealDamage(damage, Player);
                System.out.printf(" Martian Soldier used %s dealing %d true damage!%n", abilityName, damage);
            } else {
                damage = calculateDamage(damage, Player, type);
                this.dealDamage(damage, Player);
                System.out.printf(" Martian Soldier used %s dealing %d physical damage!%n", abilityName, damage);
            }
            this.abilityCD4 = 4;
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
            return true;
        } else return false;
    }
}
