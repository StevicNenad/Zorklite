package game.abilities.active;

import game.Ability;

//Charge the enemy and deal absurd damage, suffering some blowback in return (25%)
public class RecklessCharge extends Ability {

    public RecklessCharge() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Reckless Charge";
        this.targetedAbility = true;
    }

    public void ExtraAttack() {

    }
}
