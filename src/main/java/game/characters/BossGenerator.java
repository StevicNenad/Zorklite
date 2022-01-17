package game.characters;

import game.characters.monsters.*;

import java.util.ArrayList;
import java.util.Random;

public class BossGenerator {
    ArrayList<Monster> monsterList;

    public BossGenerator() {
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

    public Monster getRandomMonster(){
        Random rn = new Random();
        int index = rn.nextInt(monsterList.size());

        return monsterList.get(index);
    }
}
