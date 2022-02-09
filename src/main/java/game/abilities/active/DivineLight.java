package game.abilities.active;

import game.Ability;

//Heal based on intelligence attribute
public class DivineLight extends Ability {

    public DivineLight() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Divine Light";
        description = "Heal yourself with a divine power. Scales with intelligence.";
        targetedAbility = true;
        manaReq = 35;
    }
}
