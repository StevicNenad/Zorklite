package game;

public class Ability {
    protected enum AbilityType{
        ACTIVE,
        PASSIVE
    }
    protected String        abilityName;
    protected AbilityType   abilityType;
    protected DamageType    damageType;

}
