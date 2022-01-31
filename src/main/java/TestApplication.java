import game.Battle;
import game.Character;
import game.Game;
import game.abilities.passive.CounterAttack;
import game.abilities.passive.Lifesteal;
import game.characters.Monster;
import game.characters.MonsterGenerator;
import game.characters.Player;
import game.items.weapons.Greatsword;

import java.util.ArrayList;
import java.util.Random;

public class TestApplication {

    public static void main(String[] args) {


        Game game = new Game();

        game.mainGame();


        /*
        Player player = new Player();
        ArrayList<Character> monsterList = new ArrayList<Character>();
        Greatsword greatsword = new Greatsword();
        Lifesteal lifesteal = new Lifesteal();
        CounterAttack counterAttack = new CounterAttack();
        player.getPassives().add(counterAttack);
        player.getPassives().add(lifesteal);
        player.setWeapon(greatsword);
        player.getAttributes().setEvasion(1);

        for(int i = 0; i < 3; i++) {
            MonsterGenerator mongen = new MonsterGenerator();
            Monster monster = mongen.getRandomMonster();
            monsterList.add(monster);
        }

        Battle battle = new Battle();
        battle.startEncounter(player, monsterList);
         */
    }
}
