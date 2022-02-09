package game.characters;

import game.characters.monsters.*;
import game.items.ItemFactory;

import java.util.ArrayList;
import java.util.Random;


//Class that initializes all Monsters and adds them to ArrayList. Can generate a Random monster and create loot.
public class MonsterGenerator {
    ArrayList<Monster> monsterList;

    public MonsterGenerator() {
        monsterList = new ArrayList<Monster>();

        //Initialize all Monsters and add them to List
        Arachnid arachnid = new Arachnid();
        monsterList.add(arachnid);

        Banshee banshee = new Banshee();
        monsterList.add(banshee);

        Bat bat = new Bat();
        monsterList.add(bat);

        Behemoth behemoth = new Behemoth();
        monsterList.add(behemoth);

        FireLizard fireLizard = new FireLizard();
        monsterList.add(fireLizard);

        Imp imp = new Imp();
        monsterList.add(imp);

        Pangolin pangolin = new Pangolin();
        monsterList.add(pangolin);

        Sentry sentry = new Sentry();
        monsterList.add(sentry);

        Slime slime = new Slime();
        monsterList.add(slime);

        Spectre spectre = new Spectre();
        monsterList.add(spectre);
    }

    //Gets a random Monster from ArrayList
    public Monster getRandomMonster(){
        Random rn = new Random();
        int index = rn.nextInt(monsterList.size());

        return monsterList.get(index);
    }

    //Generates loot based on monster type. Every Monster has own drop chance for items
    public void generateLoot(Monster monster) {
        ItemFactory iF = new ItemFactory();
        Random rn = new Random();
        double dropChance = rn.nextDouble();
        double  gemChance,
                accChance,
                potChance;

        switch (monster.getShortName()) {
            case "Spr":
                gemChance = 0.02;
                accChance = 0.10;
                potChance = 0.55;
                break;
            case "Ban":
                gemChance = 0.05;
                accChance = 0.15;
                potChance = 0.45;
                break;
            case "Bat":
                gemChance = 0.01;
                accChance = 0.08;
                potChance = 0.50;
                break;
            case "Beh":
                gemChance = 0.10;
                accChance = 0.25;
                potChance = 0.60;
                break;
            case "Liz":
                gemChance = 0.05;
                accChance = 0.15;
                potChance = 0.50;
                break;
            case "Imp":
                gemChance = 0.15;
                accChance = 0.35;
                potChance = 0.50;
                break;
            case "Pan":
                gemChance = 0.10;
                accChance = 0.20;
                potChance = 0.50;
                break;
            case "Sen":
                gemChance = 0.07;
                accChance = 0.17;
                potChance = 0.50;
                break;
            case "Sli":
                gemChance = 0.02;
                accChance = 0.10;
                potChance = 1;
                break;
            case "Spc":
                gemChance = 0.12;
                accChance = 0.22;
                potChance = 0.50;
                break;
            default:
                gemChance = 0;
                accChance = 0;
                potChance = 0;
                break;
        }

        gemChance *= 1 + (monster.getAttributes().getLevel() * 0.05);
        accChance *= 1 + (monster.getAttributes().getLevel() * 0.05);
        potChance *= 1 + (monster.getAttributes().getLevel() * 0.05);

        if(dropChance <= gemChance) {
            monster.getLoot().add(iF.getRandomGem());
        }
        else if(dropChance <= accChance) {
            monster.getLoot().add(iF.getRandomAccessory());
        }
        else if(dropChance <= potChance) {
            monster.getLoot().add(iF.getPotion());
        }
    }
}
