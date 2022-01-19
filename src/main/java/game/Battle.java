package game;

import game.characters.Player;

import java.util.ArrayList;

public class Battle {
    private Character pc;
    private ArrayList<Character> enemy;
    private boolean battle_over;

    public Battle(Player pc, ArrayList<Character> foes) {
        this.pc = pc;
        this.enemy = foes;
        battle_over = false;
    }

    public void encounter() {
        int playerHealth = pc.getHealth();
        int enemyHealth = enemy.getHealth();

        while(playerHealth != 0 && enemyHealth != 0) {

        }
    }

    public void turn(Character current) {
        if(current.getType() == Character.CharacterType.MONSTER){

        }
    }
}
