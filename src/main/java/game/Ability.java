package game;

public class Ability {
    protected enum AbilityType{
        ACTIVE,
        PASSIVE
    }
    protected boolean       targetedAbility;
    protected String        abilityName,
                            description;
    protected AbilityType   abilityType;
    protected int           manaReq;

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

    public int getManaReq() {
        return manaReq;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public String getDescription() {
        return description;
    }
}
