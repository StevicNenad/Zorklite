package game.characters;

import game.Character;
import game.items.Accessories;
import game.items.Armor;
import game.items.Weapon;

import java.util.ArrayList;

public class Monster extends Character {
    protected int       health,
                        speed,
                        armor;
    protected double    accuracy;
    protected String    monsterName;
}
