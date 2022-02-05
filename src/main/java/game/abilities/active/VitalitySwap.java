package game.abilities.active;

import game.Ability;

//Swap health with target (percentage)
public class VitalitySwap extends Ability {

    public VitalitySwap() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Vitality Swap";
        description = "Swap the health of a target with yours. (Percentages)";
        targetedAbility = true;
        manaReq = 100;
    }

    public void ExtraAttack() {

    }
}
