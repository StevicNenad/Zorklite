package game.characters;

import game.AttackRange;
import game.Character;
import game.DamageType;
import game.Item;

import java.util.ArrayList;

public class Boss extends Character {
    protected DamageType damageType;
    protected AttackRange attackRange;
    protected ArrayList<Item> loot;

    public Boss() {
        characterType = CharacterType.BOSS;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public AttackRange getAttackRange() {
        return attackRange;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }
}
