package game;

import game.characters.Player;

public class Battle {
    private Character pc;
    private Character enemy;
    private boolean battle_over;

    public Battle(Player pc, Character enemy) {
        this.pc = pc;
        this.enemy = enemy;
        battle_over = false;
    }

    public void encounter() {
        int playerHealth = pc.getHealth();
        int enemyHealth = enemy.getHealth();

        while(playerHealth != 0 && enemyHealth != 0) {

        }
    }

    public void turn(Character current) {
        if(current.getType() == "Enemy"){

        }
    }
}
