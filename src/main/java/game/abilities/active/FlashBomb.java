package game.abilities.active;

import game.Ability;

//Permanent reduction of target stats (accuracy, damage reduction etc)
public class FlashBomb extends Ability {

    public FlashBomb() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Flash Bomb";
        description = "Throw a flash bomb at the ground, blinding and weakening attributes of the enemy.";
        targetedAbility = false;
        manaReq = 40;
    }
}
