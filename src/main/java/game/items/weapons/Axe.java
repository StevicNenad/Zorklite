package game.items.weapons;

import game.items.Weapon;

public class Axe extends Weapon {

    public Axe() {
        this.level = 1;
        this.damage = 30;
        this.aoeDamage = 0.10;
        this.accuracy = 0.8;
        this.attacks = 1;
        this.armorPenetration = 0.10;
        this.ranged = false;
        this.type = "Weapon";
        this.name = "Battleaxe";
        this.physicalDamage = true;
    }
}
