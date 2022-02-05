package game.abilities.passive;

import game.Ability;

//Causes a 20 damage explosion when an attack lands that deals 100% aoe damage
public class DivineCombust extends Ability {

    public DivineCombust() {
        abilityType = AbilityType.PASSIVE;
        abilityName = "Divine Combustion";
        description = "Adds a 35 damage AoE explosion to every attack and projectile. Deals magic damage";
        targetedAbility = false;
        manaReq = 0;
    }
}
