package game.abilities.passive;

import game.Ability;

//Shoots 2 additional projectiles from ranged weapons, but damage reduced by 30%. On melee weapons, adds deadly area of effect damage (35%)
public class SplitShot extends Ability {

    public SplitShot() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Split shot";
    }

    public void ExtraAttack() {

    }
}
