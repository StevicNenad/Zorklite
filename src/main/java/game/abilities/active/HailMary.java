package game.abilities.active;

import game.Ability;

//Deals more damage to target the more health attacker is missing
public class HailMary extends Ability {

    public HailMary() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Hail Mary";
        description = "A last ditch effort, the more HP you miss the harder you hit.";
        targetedAbility = true;
        manaReq = 15;
    }
}
