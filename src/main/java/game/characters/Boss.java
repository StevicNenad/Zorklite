package game.characters;

import game.AttackRange;
import game.Character;
import game.DamageType;

public class Boss extends Character {
    protected DamageType damageType;
    protected AttackRange attackRange;

    public Boss() {
        characterType = CharacterType.BOSS;
    }
}
