package game.abilities.active;

import game.Ability;

//Restores Shield and Armor. Both scale with Agility and Strength
public class BostonShell extends Ability {

    public BostonShell() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Boston Shell";
        description = "Restore Armor (scales with agility) and Shield (scales with intelligence).";
        targetedAbility = false;
        manaReq = 15;
    }
}
