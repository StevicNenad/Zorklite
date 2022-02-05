package game.abilities.passive;

import game.Ability;

//Doubles amount of projectiles, but damage reduced by 60%. On melee weapons, adds deadly area of effect damage (35%)
public class Blitz extends Ability {

    public Blitz() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Blitz";
        description = "For ranged: Double amount of projectiles, but damage reduced to 70%. For melee: add 35% cleave damage";
        targetedAbility = false;
        manaReq = 0;
    }
}
