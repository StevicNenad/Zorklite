package game.abilities.passive;

import game.Ability;
import game.Character;

//each consecutive on the same target adds 25 bonus damage
public class EscalatingViolence extends Ability {

    private Character target;
    private int bonusDamage;

    public EscalatingViolence() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Escalating Violence";
        target = null;
        bonusDamage = 0;
    }

    @Override
    public boolean calculateBonusDamage(Character target) {
        if(this.target == target) {
            bonusDamage += 15;
            return true;
        }
        else {
            this.target = target;
            bonusDamage = 0;
            return false;
        }
    }

    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }
}
