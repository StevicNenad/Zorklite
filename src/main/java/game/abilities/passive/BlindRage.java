package game.abilities.passive;

import game.Ability;

//Takes more dmg/higher miss chance but big attack bonus. Or increase in attack count, i've yet to decide
public class BlindRage extends Ability {

    public BlindRage() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Split shot";
    }

    public void ExtraAttack() {

    }
}