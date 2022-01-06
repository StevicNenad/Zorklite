package game.characters;

import game.Character;
import game.items.Accessories;
import game.items.Armor;
import game.items.Weapon;

import java.util.ArrayList;

public class Player extends Character {
    private int     health = 100,
                    mana = 25,
                    speed = 10,
                    strength = 10,
                    intelligence = 10,
                    stealth = 10,
                    experience = 0,
                    level = 1;

    private Weapon weapon;
    private Armor armor;
    private Accessories first, second;
    private Ability first, second, third;
    private ArrayList<ItemKey> keys;
}
