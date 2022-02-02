package game.abilities.passive;

import game.Ability;

//Steals a random attribute on hit. Armor Protects target from ability
public class HydroTouch extends Ability {

    public HydroTouch() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Hydro Touch";
    }
}
