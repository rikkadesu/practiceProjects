package Enemy;
import Playable.Base;

public class MartianSoldier extends Martian{
    public MartianSoldier() {
        this.addPhysicalAttack(random.nextInt(40, 71));
        this.addHealth(random.nextInt(500, 1001));
        this.setMaxHealth(this.getHealth());
        this.addPhysicalArmor(random.nextInt(40, 61));
        this.addMagicArmor(random.nextInt(30, 61));
        this.addPhysicalPEN(random.nextInt(0, 31));
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

        if(chance < this.getCritChance()) { System.out.println("CRITICAL!!!"); }
        System.out.printf("Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
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

        System.out.printf("Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
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

        System.out.printf("Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
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

        System.out.printf("Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
    public void ability4(Base Player, Martian NPC) {
        // (200% AD) physical damage
        String abilityName = "Meteor Strike";
        int damage = 0;
        if(this.getHealth() < (this.getHealth() * 0.5)) { damage = this.getPhysicalAttack() * 2; }
        damage = this.checkIncreasedDamage(damage);
        damage -= ((Player.getPhysicalArmor() - this.getPhysicalPEN()) / 2);
        damage = this.checkEnemyReduction(damage, Player);
        Player.addHealth(-damage);

        System.out.printf("Martian Soldier used %s dealing %d damage!%n", abilityName, damage);
        System.out.print("Press Enter key to continue.");
        prompt.readPassword();
    }
}
