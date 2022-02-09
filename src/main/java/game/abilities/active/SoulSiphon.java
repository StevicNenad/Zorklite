package game.abilities.active;

import game.Ability;

//Drain enemies HP, damage and heal amount scales with intelligence
public class SoulSiphon extends Ability {

    public SoulSiphon() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Soul Siphon";
        description = "Drain the HP of the target and add them to yourself";
        targetedAbility = true;
        manaReq = 55;
    }
}
