package game.items.weapons;

import game.items.Weapon;

public class Sword extends Weapon {

    public Sword() {
        this.level = 1;
        this.damage = 25;
        this.aoeDamage = 0;
        this.accuracy = 0.9;
        this.attacks = 1;
        this.armorPenetration = 0;
        this.ranged = false;
        this.type = "Weapon";
        this.name = "Broadsword";
        this.physicalDamage = true;
    }
}
