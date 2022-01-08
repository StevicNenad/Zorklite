package game.characters.monsters;

import game.Attributes;
import game.Character;

public class Bat extends Character {

    public Bat(int level) {
        attributes = new Attributes(12, 5, 6, 8, level, 0, 1, 0);

        health = attributes.calculateHealth();
        armorPoints = attributes.calculateArmor();
        mana = attributes.calculateMana();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
