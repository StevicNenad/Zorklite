package game.abilities.passive;

import game.Ability;

//Makes Weapon deal magic damage. 20 dmg attack bonus
public class EmberInfusion extends Ability {

    public EmberInfusion() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Split shot";
    }

    public void ExtraAttack() {

    }
}
