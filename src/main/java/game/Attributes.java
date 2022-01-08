package game;

public class Attributes {
    private int strength,
                intelligence,
                agility,
                speed,
                level,
                experience,
                lvlUpExperience;//Experience required for level up.

    private double  accuracy,
                    evasion;

    public Attributes(int strength, int intelligence, int agility, int speed, int level, int experience, double accuracy, double evasion) {
        double levelModifier = (level - 1) / 10 + 1;//Automatically increases stats based on Level

        this.strength = (int) (strength * levelModifier);
        this.intelligence = (int) (intelligence * levelModifier);
        this.agility = (int) (agility * levelModifier);
        this.speed = (int) (speed * levelModifier);

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

}
