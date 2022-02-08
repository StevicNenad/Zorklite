package game.characters;

import game.AttackRange;
import game.Character;
import game.DamageType;
import game.Item;

import java.util.ArrayList;

public class Boss extends Character {
    protected DamageType damageType;
    protected boolean   flying;

    public Boss() {
        characterType = CharacterType.BOSS;
    }

    public DamageType getDamageType() {
        return damageType;
    }


}
