package game.abilities.passive;

import game.Ability;
import game.Character;

//each consecutive on the same target adds 25 bonus damage
public class EscalatingViolence extends Ability {

    private Character target;
    private int bonusDamage;

    public EscalatingViolence() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Escalating Violence";
        description = "Every consecutive hit on the same target deals 20 bonus damage. The moment you switch targets the bonus drops to 0.";
        target = null;
        bonusDamage = 0;
        targetedAbility = false;
        manaReq = 0;
    }

    @Override
    public boolean calculateBonusDamage(Character target) {
        if(this.target == target) {
            bonusDamage += 20;
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
