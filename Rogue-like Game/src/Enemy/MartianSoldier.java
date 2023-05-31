package Enemy;
import Playable.Base;
import static java.lang.System.out;

public class MartianSoldier extends Martian{
    public MartianSoldier() {
        this.setName("Martian Soldier");
        this.setTags("Basic Martian Troop");
        this.addPhysicalAttack(random.nextInt(40, 71));
        this.addCritChance(random.nextInt(0, 26));
        this.setMaxHealth(random.nextInt(500, 1001));
        this.addPhysicalArmor(random.nextInt(30, 41));
        this.addMagicArmor(random.nextInt(30, 41));
        this.addPhysicalPEN(random.nextInt(0, 21));
    }

    public void useAbility(Base Player, Martian NPC) {
        int choice = random.nextInt(0, 5);
        switch (choice) {
            case 0 -> {
                this.basicAttack(Player, NPC);
            }
            case 1 -> {
                this.ability1(Player, NPC);
            }
            case 2 -> {
                this.ability2(Player, NPC);
            }
            case 3 -> {
                this.ability3(Player, NPC);
            }
            case 4 -> {
                this.ability4(Player, NPC);
            }
            default -> {
                out.println("Invalid choice!");
            }
        }
    }

    @Override
    public void basicAttack(Base Player, Martian NPC) {
        // (100% AD) physical damage, 200% crit damage
        int chance = random.nextInt(0, 101);
        String abilityName = "Swing";
        int damage = getPhysicalAttack();
        if(chance < this.getCritChance()) { damage *= 2; }
        damage = this.checkIncreasedDamage(damage);
        damage -= ((Player.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = this.checkEnemyReduction(damage, Player);
        Player.addHealth(-damage);

        if(chance < this.getCritChance()) { System.out.println(" CRITICAL!!!"); }
        System.out.printf(" Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability1(Base Player, Martian NPC) {
        // 30 (+65% AD) physical damage
        String abilityName = "Power Strike";
        int damage = (30 + (int)(this.getPhysicalAttack() * 0.65));
        damage = this.checkIncreasedDamage(damage);
        damage -= ((Player.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = this.checkEnemyReduction(damage, Player);
        Player.addHealth(-damage);

        System.out.printf(" Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability2(Base Player, Martian NPC) {
        // (100% physical armor + 100% magic resist) physical damage
        String abilityName = "Shield Bash";
        int damage = (this.getPhysicalArmor() + this.getMagicArmor());
        damage = this.checkIncreasedDamage(damage);
        damage -= ((Player.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = this.checkEnemyReduction(damage, Player);
        Player.addHealth(-damage);

        System.out.printf(" Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability3(Base Player, Martian NPC) {
        // (15% lost HP) or (100% AD) physical damage (whichever is higher)
        String abilityName = "Energy Blast";
        int damage;
        int damage1 = (int)((this.getMaxHealth() - this.getHealth()) * 0.15);
        int damage2 = this.getPhysicalAttack();
        damage = Math.max(damage1, damage2);
        damage = this.checkIncreasedDamage(damage);
        damage -= ((Player.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = this.checkEnemyReduction(damage, Player);
        Player.addHealth(-damage);

        System.out.printf(" Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print(" Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability4(Base Player, Martian NPC) {
        // (200% AD) physical damage
        String abilityName = "Meteor Strike";
        int damage = this.getPhysicalAttack() * 2;
        if(this.getHealth() < (this.getMaxHealth() * 0.5)) {
            Player.addHealth(-damage);
            System.out.printf(" Martian Soldier used %s dealing %d true damage!%n", abilityName, damage);
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
        } else {
            damage = this.checkIncreasedDamage(damage);
            damage -= ((Player.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
            damage = this.checkEnemyReduction(damage, Player);
            Player.addHealth(-damage);
            System.out.printf(" Martian Soldier used %s dealing %d physical damage!%n", abilityName, damage);
            System.out.print(" Press Enter key to continue.");
            prompt.readPassword();
        }


    }
}
