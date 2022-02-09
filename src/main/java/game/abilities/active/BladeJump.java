package game.abilities.active;

import game.Ability;

//Critical attack targets as you jump between them. Number of jumps scales with agility attribute.
public class BladeJump extends Ability {

    public BladeJump() {
        abilityType = AbilityType.ACTIVE;
        abilityName = "Blade Jump";
        description = "Jump between enemies while critically hitting them. Jumps scale with agility.";
        targetedAbility = false;
        manaReq = 75;
    }
}
