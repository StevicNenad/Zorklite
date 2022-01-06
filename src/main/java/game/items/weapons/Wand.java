package game.items.weapons;

import game.items.Weapon;

public class Wand extends Weapon {

    public Wand() {
        this.level = 1;
        this.damage = 8;
        this.aoeDamage = 0;
        this.accuracy = 0.8;
        this.attacks = 1;
        this.armorPenetration = 1.0;
        this.ranged = true;
        this.type = "Weapon";
        this.name = "Magic Wand";
        this.physicalDamage = false;
    }
}
