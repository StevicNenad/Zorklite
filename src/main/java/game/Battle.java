package game;

import game.characters.Player;

import java.util.ArrayList;

public class Battle {
    private Player pc;
    private ArrayList<Character> enemies;
    private boolean battle_over;

    public Battle(Player pc, ArrayList<Character> foes) {
        this.pc = pc;
        this.enemies = foes;
        battle_over = false;
    }

    public void encounter(Character attacker) {
        int playerHealth = pc.getCurrenthealth();

        if(attacker.getType() == Character.CharacterType.PLAYER) {
            for(Character enemy : enemies) {
                enemy.updateHealth((int) (pc.getWeapon().getAttributes().getDamage() * pc.getWeapon().getAttributes().getCritPercentage()));
            }
        }
        else {

        }
        while(playerHealth != 0 && !enemies.isEmpty()) {

        }
    }

    public void turn(Character current) {
        if(current.getType() == Character.CharacterType.MONSTER){

        }
    }
}
