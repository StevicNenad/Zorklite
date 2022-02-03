package game;

public class Ability {
    protected enum AbilityType{
        ACTIVE,
        PASSIVE
    }
    protected boolean       targetedAbility;
    protected String        abilityName;
    protected AbilityType   abilityType;

    public String getAbilityName() {
        return abilityName;
    }

    public boolean isTargetedAbility() {
        return targetedAbility;
    }

    public boolean calculateBonusDamage(Character target){
        return true;
    }

    public int getBonusDamage(){
        return 0;
    }
}
