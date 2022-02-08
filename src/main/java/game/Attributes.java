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
                    magicResistance,
                    evasion;

    public Attributes(int damage, int attacks, int projectiles, int strength, int intelligence, int agility, int speed, int perception, int stealth, int level,
                      double accuracy, double critChance, double critPercentage, double aoeDamage, double evasion) {

        this.damage = damage;
        this.attacks = attacks;
        this.projectiles = projectiles;

        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.speed = speed;
        this.perception = perception;
        this.stealth = stealth;

        this.accuracy = accuracy;
        this.critChance = critChance;
        this.critPercentage = critPercentage;
        this.aoeDamage = aoeDamage;
        this.damageReduction = calculateArmor() / 200;
        this.magicResistance = calculateShield() / 200;
        this.evasion = evasion;
        this.level = level;
    }

    public int calculateHealth() {
        return 100 + (20 * strength);//20 HP per strength point
    }

    public int calculatePlayerHealth(int bonusStrength) {
        return 100 + (10 * (strength + bonusStrength));
    }

    public int calculateArmor() {
        return 10 * agility;//5 Armor per agility point
    }

    public int calculatePlayerArmor(int bonusAgility) {
        return 5 * (agility + bonusAgility);
    }

    public int calculateMana() {
        return 100 + (5 * intelligence);//5 Mana per intelligence point
    }

    public int calculatePlayerMana(int bonusIntelligence) {
        return 100 + (2 * (intelligence + bonusIntelligence));
    }

    public int calculateShield() {
        return 10 * intelligence;//10 Magic Shield per intelligence point
    }

    public int calculatePlayerShield(int bonusIntelligence) {
        return 5 * (bonusIntelligence + intelligence);
    }

    public void levelScaleMonsters(int level) {

        if(level == 1) {
            return;
        }

        double scale = (double) level / 5;

        this.level = level;
        damage += (damage * scale);
        strength += (strength * scale);
        intelligence += (intelligence * scale);
        agility += (agility * scale);
        speed += (speed * scale);
        stealth += (stealth * scale);
        perception += (perception * scale);

        damageReduction = (double) calculateArmor() / 300;
        magicResistance = (double) calculateShield() / 300;
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

    public void setDamageReduction(double damageReduction) {
        this.damageReduction = damageReduction;
    }

    public void setMagicResistance(double magicResistance) {
        this.magicResistance = magicResistance;
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

    public int getSpeed() {
        return speed;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getProjectiles() {
        return projectiles;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public int getPerception() {
        return perception;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getCritChance() {
        return critChance;
    }

    public double getAoeDamage() {
        return aoeDamage;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    public double getMagicResistance() {
        return magicResistance;
    }

    public double getEvasion() {
        return evasion;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public int getStealth() {
        return stealth;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public void setAttacks(int attacks) {
        this.attacks = attacks;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public void setCritPercentage(double critPercentage) {
        this.critPercentage = critPercentage;
    }

    public void setAoeDamage(double aoeDamage) {
        this.aoeDamage = aoeDamage;
    }

    public void setProjectiles(int projectiles) {
        this.projectiles = projectiles;
    }

    public void addLevel(int level) {
        this.level += level;
    }
}
