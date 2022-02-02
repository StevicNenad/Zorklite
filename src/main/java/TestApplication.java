import game.Battle;
import game.Character;
import game.Game;
import game.abilities.passive.*;
import game.characters.Monster;
import game.characters.MonsterGenerator;
import game.characters.Player;
import game.items.weapons.Greatsword;
import game.items.weapons.Repeater;
import game.rooms.StartRoom;

import java.util.ArrayList;

public class TestApplication {

    public static void main(String[] args) {


        /*
        Game game = new Game();

        game.mainGame();
        */

        Player player = new Player();
        ArrayList<Character> monsterList = new ArrayList<Character>();
        Greatsword greatsword = new Greatsword();
        Repeater repeater = new Repeater();
        Lifesteal lifesteal = new Lifesteal();
        HydroTouch hydroTouch = new HydroTouch();
        CounterAttack counterAttack = new CounterAttack();
        ExtraAttack extraAttack = new ExtraAttack();
        EscalatingViolence escalatingViolence = new EscalatingViolence();
        player.getPassives().add(escalatingViolence);
        player.getPassives().add(extraAttack);
        player.getPassives().add(lifesteal);
        player.getPassives().add(hydroTouch);
        player.setDemonicEssence(10);
        player.setWeapon(repeater);
        player.getAttributes().setEvasion(1);

        for(int i = 0; i < 3; i++) {
            MonsterGenerator mongen = new MonsterGenerator();
            Monster monster = mongen.getRandomMonster();
            monsterList.add(monster);
        }

        Battle battle = new Battle();
        battle.startEncounter(player, monsterList);

    }

}
