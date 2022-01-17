package game.characters.bosses;

import game.Attributes;
import game.characters.Boss;

public class Underlord extends Boss {

    public Underlord() {
        name = "The demon lord, Reth'tirath";
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
