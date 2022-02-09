package game.abilities.active;

import game.Ability;

//10% chance to execute random character immidiately (yourself included)
public class RussianRoulette extends Ability {

    public RussianRoulette() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Russian Roulette";
        description = "10% chance to execute a random character in battle instantly. Including yourself.";
        targetedAbility = false;
        manaReq = 100;
    }

}
