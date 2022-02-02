package game;

public class Ability {
    protected enum AbilityType{
        ACTIVE,
        PASSIVE
    }
    protected boolean       singleTarget;
    protected String        abilityName;
    protected AbilityType   abilityType;
    protected DamageType    damageType;

    public String getAbilityName() {
        return abilityName;
    }

    public boolean isSingleTarget() {
        return singleTarget;
    }

    public boolean calculateBonusDamage(Character target){
        return true;
    }

    public int getBonusDamage(){
        return 0;
    }
}
