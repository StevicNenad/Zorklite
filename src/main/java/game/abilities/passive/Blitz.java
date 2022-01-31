package game.abilities.passive;

import game.Ability;

//Doubles amount of projectiles, but damage reduced by 60%. On melee weapons, adds deadly area of effect damage (35%)
public class Blitz extends Ability {

    public Blitz() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Blitz";
    }
}
