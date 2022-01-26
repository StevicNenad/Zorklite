package game.abilities.passive;

import game.Ability;

//Causes a 20 damage explosion when an attack lands that deals 100% aoe damage
public class DivineCombust extends Ability {

    public DivineCombust() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Split shot";
    }

    public void ExtraAttack() {

    }
}
