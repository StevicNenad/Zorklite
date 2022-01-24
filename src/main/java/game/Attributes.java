package game;

public class Attributes {

    private int     damage,
                    attacks,        //how many consecutive attacks can be performed (double-attack, triple-attack etc)
                    projectiles,    //how many projectiles are shot at once (this way bows can shoot 3 arrows at once etc)
                    strength,
                    intelligence,
                    agility,
                    speed,          //determines who goes first in battle
                    stealth,        //determines how easy one is to perform sneak actions
                    perception,     //determines how likely one is to find secrets or spot hidden enemies
                    level;

    private double  accuracy,
                    critChance,
                    critPercentage,
                    aoeDamage,      //Area of effect damage
                    damageReduction,
                    evasion;

    public Attributes(int damage, int attacks, int projectiles, int strength, int intelligence, int agility, int speed, int perception, int level,
                      double accuracy, double critChance, double critPercentage, double aoeDamage, double damageReduction, double evasion) {
        double levelModifier = (level - 1) / 10 + 1;//Automatically increases stats based on Level

        this.damage = damage;
        this.attacks = attacks;
        this.projectiles = projectiles;

        this.strength = (int) (strength * levelModifier);
        this.intelligence = (int) (intelligence * levelModifier);
        this.agility = (int) (agility * levelModifier);
        this.speed = (int) (speed * levelModifier);
        this.perception = (int) (perception * levelModifier);
        this.stealth = (int) (stealth * levelModifier);

        this.accuracy = accuracy;
        this.critChance = critChance;
        this.critPercentage = critPercentage;
        this.aoeDamage = aoeDamage;
        this.damageReduction = damageReduction;
        this.evasion = evasion;
        this.level = level;
    }

    public int calculateHealth() {
        return 25 * strength;//25 HP per strength point
    }

    public int calculateArmor() {
        return 5 * agility;//5 Armor per agility point
    }

    public int calculateMana() {
        return 10 * intelligence;//10 Mana per intelligence point
    }

    public int calculateShield() {
        return 5 * intelligence;//5 Magic Shield per intelligence point
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setEvasion(double evasion) {
        this.evasion = evasion;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public double getCritPercentage() {
        return critPercentage;
    }
}
