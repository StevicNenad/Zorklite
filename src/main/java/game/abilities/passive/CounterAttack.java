package game.abilities.passive;

import game.Ability;

//Additionally to evasion, successfully evading an attack causes attacker to receive a counter attack that is always a critical hit
public class CounterAttack extends Ability {

    public CounterAttack() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Counter Attack";
        description = "When you evade an attack, you respond with an immediate counter attack";
        targetedAbility = false;
        manaReq = 0;
    }
}
