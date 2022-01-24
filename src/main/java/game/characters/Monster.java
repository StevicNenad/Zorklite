package game.characters;

import game.AttackRange;
import game.Character;
import game.DamageType;

public class Monster extends Character {
    protected DamageType damageType;
    protected AttackRange attackRange;

    public Monster() {
        characterType = CharacterType.MONSTER;
    }
}
