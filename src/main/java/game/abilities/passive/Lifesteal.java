package game.abilities.passive;

import game.Ability;

//25% lifesteal per hit
public class Lifesteal extends Ability {

    public Lifesteal() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Lifesteal";
        description = "You restore health for a portion of damage dealt with your weapon";
        targetedAbility = false;
        manaReq = 0;
    }
}
