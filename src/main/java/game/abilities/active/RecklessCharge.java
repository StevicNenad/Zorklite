package game.abilities.active;

import game.Ability;

//Charge the enemy and deal absurd damage, suffering some blowback in return (25%)
public class RecklessCharge extends Ability {

    public RecklessCharge() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Reckless Charge";
        description = "A reckless attack that causes severe damage to the target as well as the attacker.";
        targetedAbility = true;
        manaReq = 0;
    }

    public void ExtraAttack() {

    }
}
