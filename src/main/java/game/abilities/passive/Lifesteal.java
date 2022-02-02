package game.abilities.passive;

import game.Ability;

//25% lifesteal per hit
public class Lifesteal extends Ability {

    public Lifesteal() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Lifesteal";
    }
}
