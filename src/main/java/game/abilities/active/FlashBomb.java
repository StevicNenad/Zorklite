package game.abilities.active;

import game.Ability;

//Permanent reduction of target stats (accuracy, damage reduction etc)
public class FlashBomb extends Ability {

    public FlashBomb() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Flash Bomb";
        this.targetedAbility = false;
    }

    public void ExtraAttack() {

    }
}
