package game.abilities.active;

import game.Ability;

//10% chance to execute random character immidiately (yourself included)
public class RussianRoulette extends Ability {

    public RussianRoulette() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Fireball";
    }

    public void ExtraAttack() {

    }
}