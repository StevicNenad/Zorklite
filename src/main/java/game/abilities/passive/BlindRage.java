package game.abilities.passive;

import game.Ability;

//Takes more dmg/higher miss chance but big attack bonus.
public class BlindRage extends Ability {

    public BlindRage() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Blind Rage";
        description = "Double your damage, accuracy reduced to 50%";
        targetedAbility = false;
        manaReq = 0;
    }
}
