package game.abilities.passive;

import game.Ability;

//every two turns, gain additional attack
public class ExtraAttack extends Ability {

    public ExtraAttack() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Extra Attack";
        description = "Attack an additional time every 2nd turn.";
        targetedAbility = false;
        manaReq = 0;
    }
}
