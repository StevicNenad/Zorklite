package game.abilities.passive;

import game.Ability;

//lowers attack damage to 1, but makes every consecutive attack on a target twice as strong (damage gets doubled if the same enemy is attacked)
public class EscalatingViolence extends Ability {

    public EscalatingViolence() {
        this.abilityType = AbilityType.PASSIVE;
        this.abilityName = "Extra Attack";
    }
}
