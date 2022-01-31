package game.abilities.active;

import game.Ability;

//Restores Shield and Armor. Both scale with Agility and Strength
public class BostonShell extends Ability {

    public BostonShell() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Fireball";
    }

    public void ExtraAttack() {

    }
}
