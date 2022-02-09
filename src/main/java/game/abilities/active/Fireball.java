package game.abilities.active;

import game.Ability;

//Damage an enemy with a fireball, damage scale with intelligence
    public class Fireball extends Ability {

    public Fireball() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Fireball";
        description = "Hurl a huge fireball at the enemy, causing massive AoE damage. Scales with intelligence.";
        targetedAbility = false;
        manaReq = 100;
    }
}
