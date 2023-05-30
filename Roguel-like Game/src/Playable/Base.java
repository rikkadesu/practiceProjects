package Playable;
import Enemy.Martian;
import java.io.Console;
import java.util.Random;

public class Base {
    public int checkIncreasedDamage(int damage) {
        if(this.getIncreasedDamage() > 0) {
            int newDamage = (int)(damage * (this.getIncreasedDamage() + 1));
            this.addIncreasedDamage(-this.getIncreasedDamage());
            return newDamage;
        }
        return damage;
    }
    public int checkEnemyReduction(int damage, Martian NPC) {
        if(NPC.getDecreaseIncomingDamage() > 0) {
            int newDamage = (int)(damage - (damage * NPC.getDecreaseIncomingDamage()));
            NPC.addDecreaseIncomingDamage(-NPC.getDecreaseIncomingDamage());
            return newDamage;
        }
        return damage;
    }

    static Console prompt = System.console();
    static Random random = new Random();

    private String name;
    private String tags;
    private int physicalAttack;
    private int magicAttack;
    private int maxHealth;
    private int health;
    private int physicalArmor;
    private int magicResist;
    private int physicalLifeSteal;
    private int magicLifeSteal;
    private int critChance;
    private int physicalPEN;
    private int magicPEN;
    private double increasedDamage;
    private double decreaseIncomingDamage;

    public Base() {
        physicalAttack = 0;
        magicAttack = 0;
        maxHealth = 0;
        health = 0;
        physicalArmor = 0;
        magicResist = 0;
        physicalLifeSteal = 0;
        magicLifeSteal = 0;
        critChance = 0;
        physicalPEN = 0;
        magicPEN = 0;
        increasedDamage = 0;
        decreaseIncomingDamage = 0;
    }
    public void setName(String charName) { name = charName; }
    public void setTags(String charTags) { tags = charTags; }
    public void addPhysicalAttack(int AD) { physicalAttack += AD; }
    public void addMagicAttack(int AP) { magicAttack += AP; }
    public void setMaxHealth(int HP) { maxHealth += HP; }
    public void addHealth(int HP) { health += HP; }
    public void addPhysicalArmor(int AR) { physicalArmor += AR; }
    public void addMagicArmor(int MR) { magicResist += MR; }
    public void addPhysicalLifeSteal(int physicalHeal) { physicalLifeSteal += physicalHeal; }
    public void addMagicLifeSteal(int magicHeal) { magicLifeSteal += magicHeal; }
    public void addCritChance(int crit) { critChance += crit; }
    public void addPhysicalPEN(int PEN) { physicalPEN += PEN; }
    public void addMagicPEN(int PEN) { magicPEN += PEN; }
    public void addIncreasedDamage(double increase) { increasedDamage += increase; }
    public void addDecreaseIncomingDamage(double decrease) { decreaseIncomingDamage += decrease; }

    public String getName() { return name; }
    public String getTags() { return tags; }
    public int getPhysicalAttack() { return physicalAttack; }
    public int getMagicAttack() { return magicAttack; }
    public int getMaxHealth() { return maxHealth; }
    public int getHealth() { return health; }
    public int getPhysicalArmor() { return physicalArmor; }
    public int getMagicArmor() { return magicResist; }
    public int getPhysicalLifeSteal() { return physicalLifeSteal; }
    public int getMagicLifeSteal() { return magicLifeSteal; }
    public int getCritChance() { return critChance; }
    public int getPhysicalPEN() { return physicalPEN; }
    public int getMagicPEN() { return magicPEN; }
    public double getIncreasedDamage() { return increasedDamage; }
    public double getDecreaseIncomingDamage() { return decreaseIncomingDamage; }

    public void charDesc() {
    }
    public void basicAttack(Base Player, Martian NPC) {
    }
    public void ability1(Base Player, Martian NPC) {
    }
    public void ability2(Base Player, Martian NPC) {
    }
    public void ability3(Base Player, Martian NPC) {
    }
    public void ability4(Base Player, Martian NPC) {
    }
}
