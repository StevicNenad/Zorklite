package game.abilities.passive;

import game.Ability;

//Makes weapon deal universal damage
public class LightningSparks extends Ability {

    public LightningSparks() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Lightning Sparks";
        description = "Weapon damage becomes universal";
        targetedAbility = false;
        manaReq = 0;
    }
}
