package game.items.weapons;

import game.items.Weapon;

public class Repeater extends Weapon {

    public Repeater() {
        this.level = 1;
        this.damage = 6;
        this.aoeDamage = 0;
        this.accuracy = 0.80;
        this.attacks = 3;
        this.armorPenetration = 0.05;
        this.ranged = true;
        this.type = "Weapon";
        this.name = "Repeating Pistol";
        this.physicalDamage = true;
    }
}
