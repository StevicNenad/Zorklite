package game.characters.monsters;

import game.Attributes;
import game.characters.Monster;

public class FireLizard extends Monster {

    public FireLizard() {
        attributes = new Attributes(12, 5, 6, 8, 1, 0, 1, 0);

        health = attributes.calculateHealth();
        armorPoints = attributes.calculateArmor();
        mana = attributes.calculateMana();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public CharacterType getType() {
        return super.getType();
    }
}
