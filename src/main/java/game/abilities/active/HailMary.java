package game.abilities.active;

import game.Ability;

//Deals more damage to target the more health attacker is missing
public class HailMary extends Ability {

    public HailMary() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Fireball";
    }

    public void ExtraAttack() {

    }
}
