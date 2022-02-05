package game.abilities.passive;

import game.Ability;

//Makes Weapon deal magic damage. 20 dmg attack bonus
public class EmberInfusion extends Ability {

    public EmberInfusion() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Ember Infusion";
        description = "Physical weapon deals magic damage now. If weapon already magical or universal, adds bonus damage instead.";
        targetedAbility = false;
        manaReq = 0;
    }
}
