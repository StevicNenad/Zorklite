package game.abilities.passive;

import game.Ability;

//Steals a random attribute on hit. Armor Protects target from ability
public class HydroTouch extends Ability {

    public HydroTouch() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Hydro Touch";
        description = "If enemy has no Armor, steal one random attribute with every hit.";
        targetedAbility = false;
        manaReq = 0;
    }
}
