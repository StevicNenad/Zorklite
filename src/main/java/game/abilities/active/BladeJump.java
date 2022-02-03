package game.abilities.active;

import game.Ability;

//Critical attack targets as you jump between them. Number of jumps scales with agility attribute.
public class BladeJump extends Ability {

    public BladeJump() {
        this.abilityType = AbilityType.ACTIVE;
        this.abilityName = "Blade Jump";
        this.targetedAbility = false;
    }

    public void ExtraAttack() {

    }
}
