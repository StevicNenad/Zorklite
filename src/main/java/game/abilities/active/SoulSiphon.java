package game.abilities.active;

import game.Ability;

//Drain enemies HP, damage and heal amount scales with intelligence
public class SoulSiphon extends Ability {

    public SoulSiphon() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Soul Siphon";
        this.targetedAbility = true;
    }

    public void ExtraAttack() {

    }
}
