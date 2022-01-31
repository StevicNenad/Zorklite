package game.abilities.active;

import game.Ability;

//Damage an enemy with a fireball, damage scale with intelligence
    public class Fireball extends Ability {

    public Fireball() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Fireball";
    }

    public void ExtraAttack() {

    }
}
