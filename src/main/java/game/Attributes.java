package game;

public class Attributes {
    private int strength,
                intelligence,
                agility,
                speed,
                perception,
                level,
                experience,
                lvlUpExperience;//Experience required for level up.

    private double  accuracy,
                    evasion;

    public Attributes(int strength, int intelligence, int agility, int speed, int perception, int level,  double accuracy, double evasion) {
        double levelModifier = (level - 1) / 10 + 1;//Automatically increases stats based on Level

        this.strength = (int) (strength * levelModifier);
        this.intelligence = (int) (intelligence * levelModifier);
        this.agility = (int) (agility * levelModifier);
        this.speed = (int) (speed * levelModifier);
        this.perception = (int) (perception * levelModifier);

        this.level = level;
        this.experience = 0;
        this.lvlUpExperience = 100;

        this.accuracy = accuracy;
        this.evasion = evasion;
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

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLvlUpExperience(int lvlUpExperience) {
        this.lvlUpExperience = lvlUpExperience;
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
}
