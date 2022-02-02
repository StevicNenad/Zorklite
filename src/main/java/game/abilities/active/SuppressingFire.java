package game.abilities.active;

import game.Ability;

//Permanent reduction of target stats (accuracy, damage reduction etc)
public class SuppressingFire extends Ability {

    public SuppressingFire() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Suppressing Fire";
    }

    public void ExtraAttack() {

    }
}
