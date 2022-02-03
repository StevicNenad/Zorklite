package game.abilities.active;

import game.Ability;

//Heal based on intelligence attribute
public class DivineLight extends Ability {

    public DivineLight() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Divine Light";
        this.targetedAbility = true;
    }

    public void ExtraAttack() {

    }
}
