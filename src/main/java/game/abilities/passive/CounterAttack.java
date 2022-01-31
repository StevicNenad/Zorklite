package game.abilities.passive;

import game.Ability;

//Additionally to evasion, successfully evading an attack causes attacker to receive a counter attack that is always a critical hit
public class CounterAttack extends Ability {

    public CounterAttack() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Counter Attack";
    }
}
