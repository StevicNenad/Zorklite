package game.characters;

import game.Ability;
import game.Attributes;
import game.Character;
import game.Item;
import game.items.Accessories;
import game.items.Armor;
import game.items.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {
    private Weapon weapon;
    private Armor armor;
    private Accessories firstAcc, secondAcc;
    private ArrayList<Ability> active;
    private ArrayList<Ability> passive;

    private int bonusStrength,
                bonusIntelligence,
                bonusAgility,
                bonusPerception,
                bonusSpeed,
                bonusStealth;

    private int deaths,
                demonicEssence;


    public Player() {
        name = "Player";
        shortName = "YOU";

        attributes = new Attributes(
                5,
                0,
                0,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                0,
                0,
                0,
                0,
                0
        );

        bonusStrength = 0;
        bonusAgility = 0;
        bonusIntelligence = 0;
        bonusPerception = 0;
        bonusSpeed = 0;
        bonusStealth = 0;

        characterType = CharacterType.PLAYER;
        maxHealth = attributes.calculatePlayerHealth(bonusStrength);
        currentHealth = maxHealth;
        armorPoints = 0;
        shieldPoints = 0;
        currentShield = shieldPoints;
        currentArmor = armorPoints;
        maxMana = attributes.calculatePlayerMana(bonusAgility);
        currentMana = maxMana;
        deaths = 0;
        potions = 0;
        deathTokens = 0;
        demonicEssence = 0;
        attributePoints = 0;

        calculateResistances();

        weapon = null;
        armor = null;
        firstAcc = null;
        secondAcc = null;
        passives = new ArrayList<Ability>();
        actives = new ArrayList<Ability>();
    }

    private void addStats(Item item) {
        int damage = attributes.getDamage() + item.getAttributes().getDamage(),
            attacks = attributes.getAttacks() + item.getAttributes().getAttacks(),
            projectiles = attributes.getProjectiles() + item.getAttributes().getProjectiles();

        double  accuracy = attributes.getAccuracy() + item.getAttributes().getAccuracy(),
                critChance = attributes.getCritChance() + item.getAttributes().getCritChance(),
                critPercentage = attributes.getCritPercentage() + item.getAttributes().getCritPercentage(),
                aoeDamage = attributes.getAoeDamage() + item.getAttributes().getAoeDamage(),
                damageReduction = attributes.getDamageReduction() + item.getAttributes().getDamageReduction(),
                magicResistance = attributes.getMagicResistance() + item.getAttributes().getMagicResistance(),
                evasion = attributes.getEvasion() + item.getAttributes().getEvasion();

        attributes.setDamage(damage);
        attributes.setAttacks(attacks);
        attributes.setProjectiles(projectiles);
        attributes.setAccuracy(accuracy);
        attributes.setCritChance(critChance);
        attributes.setCritPercentage(critPercentage);
        attributes.setAoeDamage(aoeDamage);
        attributes.setDamageReduction(damageReduction);
        attributes.setMagicResistance(magicResistance);
        attributes.setEvasion(evasion);

        bonusStrength += item.getAttributes().getStrength();
        bonusIntelligence += item.getAttributes().getIntelligence();
        bonusAgility += item.getAttributes().getAgility();
        bonusSpeed += item.getAttributes().getSpeed();
        bonusPerception += item.getAttributes().getPerception();
        bonusStealth += item.getAttributes().getStealth();
    }

    private void removeStats(Item item) {
        int     damage = attributes.getDamage() - item.getAttributes().getDamage(),
                attacks = attributes.getAttacks() - item.getAttributes().getAttacks(),
                projectiles = attributes.getProjectiles() - item.getAttributes().getProjectiles();

        double  accuracy = attributes.getAccuracy() - item.getAttributes().getAccuracy(),
                critChance = attributes.getCritChance() - item.getAttributes().getCritChance(),
                critPercentage = attributes.getCritPercentage() - item.getAttributes().getCritPercentage(),
                aoeDamage = attributes.getAoeDamage() - item.getAttributes().getAoeDamage(),
                damageReduction = attributes.getDamageReduction() - item.getAttributes().getDamageReduction(),
                magicResistance = attributes.getMagicResistance() - item.getAttributes().getMagicResistance(),
                evasion = attributes.getEvasion() - item.getAttributes().getEvasion();

        attributes.setDamage(damage);
        attributes.setAttacks(attacks);
        attributes.setProjectiles(projectiles);
        attributes.setAccuracy(accuracy);
        attributes.setCritChance(critChance);
        attributes.setCritPercentage(critPercentage);
        attributes.setAoeDamage(aoeDamage);
        attributes.setDamageReduction(damageReduction);
        attributes.setMagicResistance(magicResistance);
        attributes.setEvasion(evasion);

        bonusStrength -= item.getAttributes().getStrength();
        bonusIntelligence -= item.getAttributes().getIntelligence();
        bonusAgility -= item.getAttributes().getAgility();
        bonusSpeed -= item.getAttributes().getSpeed();
        bonusPerception -= item.getAttributes().getPerception();
        bonusStealth -= item.getAttributes().getStealth();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        addStats(weapon);
        recalculatePlayerStats(bonusStrength, bonusAgility, bonusIntelligence);
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        addStats(armor);

        this.armorPoints = armor.getArmorPoints();
        currentArmor = armorPoints;

        this.shieldPoints = armor.getShieldPoints();
        currentShield = shieldPoints;

        attributes.setSpeed((int)(attributes.getSpeed() * armor.getSpeedModifier()));

        if(attributes.getSpeed() == 0) {
            attributes.setSpeed(1);
        }
    }

    public void resetCharacter() {
        removeStats(weapon);
        weapon = null;

        removeStats(armor);
        armor = null;

        if(firstAcc != null) {
            removeStats(firstAcc);
            firstAcc = null;
        }
        if(secondAcc != null) {
            removeStats(secondAcc);
            secondAcc = null;
        }

        passives.clear();
        actives.clear();

        currentMana = maxMana;
        currentHealth = maxHealth;
        currentArmor = armorPoints;
        currentShield = shieldPoints;
    }

    public void setAccessory(Accessories accessory) {
        if(firstAcc == null) {
            firstAcc = accessory;
            addStats(firstAcc);
        }
        else if(secondAcc == null) {
            secondAcc = accessory;
            addStats(secondAcc);
        }
        else {
            String user_choice = null;
            Scanner sc = new Scanner(System.in);

            System.out.println( "Accessory slots full. Which accessory do you wish to swap out?\n" +
                                "1 - " + firstAcc.getName() + "\n" +
                                "2 - " + secondAcc.getName() + "\n" +
                                "Any - Cancel");

            user_choice = sc.nextLine();

            switch(user_choice) {
                case "1":
                    System.out.println("You have swapped out " + firstAcc.getName() + " for " + accessory.getName());
                    removeStats(firstAcc);
                    firstAcc = accessory;
                    addStats(firstAcc);
                    break;
                case "2":
                    System.out.println("You have swapped out " + secondAcc.getName() + " for " + accessory.getName());
                    removeStats(secondAcc);
                    secondAcc = accessory;
                    addStats(secondAcc);
                    break;
            }
        }
    }

    public void saveToFile() {
        try {
            File dir = new File("saves");
            dir.mkdirs();
            File saveData = new File(dir,"save.dat");

            if(saveData.createNewFile() || saveData.canWrite()){
                FileWriter writer = new FileWriter("saves/save.dat");

                writer.write(
                        attributes.getStrength() + ";" +
                        attributes.getIntelligence() + ";" +
                        attributes.getAgility() + ";" +
                        attributes.getStealth() + ";" +
                        attributes.getSpeed() + ";" +
                        attributes.getPerception() + ";" +
                        attributes.getLevel() + ";" +
                        potions + ";" +
                        deathTokens + ";" +
                        demonicEssence + ";" +
                        attributePoints + ";" +
                        deaths
                );
                writer.close();
            }
        }catch (IOException ex) {
            System.out.println("Couldn't create save file...");
        }
    }

    public void loadSaveFile() {
        try {
            File saveData = new File("saves", "save.dat");
            Scanner sc = new Scanner(saveData);
            String line = sc.nextLine();
            String[] sections = line.split(";");

            attributes.setStrength(Integer.parseInt(sections[0]));
            attributes.setIntelligence(Integer.parseInt(sections[1]));
            attributes.setAgility(Integer.parseInt(sections[2]));
            attributes.setStealth(Integer.parseInt(sections[3]));
            attributes.setSpeed(Integer.parseInt(sections[4]));
            attributes.setPerception(Integer.parseInt(sections[5]));
            attributes.setLevel(Integer.parseInt(sections[6]));
            potions = Integer.parseInt(sections[7]);
            deathTokens = Integer.parseInt(sections[8]);
            demonicEssence = Integer.parseInt(sections[9]);
            attributePoints = Integer.parseInt(sections[10]);
            deaths = Integer.parseInt(sections[11]);

        }catch (FileNotFoundException ex) {
            System.out.println("Couldn't find save file");
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Accessories getFirstAcc() {
        return firstAcc;
    }

    public Accessories getSecondAcc() {
        return secondAcc;
    }

    public int getDemonicEssence() {
        return demonicEssence;
    }

    @Override
    public CharacterType getType() {
        return super.getType();
    }

    public void updateDemonicEssence(int demonicEssence) {
        this.demonicEssence += demonicEssence;
    }

    public void updateAttributePoints(int attPoints) {
        attributePoints += attPoints;
    }

    public void updateDeaths(int deaths) {
        this.deaths += deaths;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getBonusAgility() {
        return bonusAgility;
    }

    public int getBonusIntelligence() {
        return bonusIntelligence;
    }

    public int getBonusPerception() {
        return bonusPerception;
    }

    public int getBonusSpeed() {
        return bonusSpeed;
    }

    public int getBonusStealth() {
        return bonusStealth;
    }

    public int getBonusStrength() {
        return bonusStrength;
    }

    public void setDemonicEssence(int demonicEssence) {
        this.demonicEssence = demonicEssence;
    }
}
