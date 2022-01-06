package game.items.weapons;

import game.items.Weapon;

public class Greatsword extends Weapon {

    public Greatsword() {
        this.level = 1;
        this.damage = 45;
        this.aoeDamage = 0.30;
        this.accuracy = 0.65;
        this.attacks = 1;
        this.armorPenetration = 0.20;
        this.ranged = false;
        this.type = "Weapon";
        this.name = "Greatsword";
        this.physicalDamage = true;
    }
}
