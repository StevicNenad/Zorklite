package game.characters;

import game.AttackRange;
import game.Character;
import game.DamageType;
import game.Item;

import java.util.ArrayList;

public class Monster extends Character {
    protected DamageType        damageType;
    protected AttackRange       attackRange;
    protected boolean           flying;

    public Monster() {
        characterType = CharacterType.MONSTER;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public AttackRange getAttackRange() {
        return attackRange;
    }

    public boolean isFlying() {
        return flying;
    }
}
