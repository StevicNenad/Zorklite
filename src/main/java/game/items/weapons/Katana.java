package game.items.weapons;

import game.items.Weapon;

public class Katana extends Weapon {

    public Katana() {
        this.level = 1;
        this.damage = 18;
        this.aoeDamage = 0;
        this.accuracy = 0.9;
        this.attacks = 1;
        this.armorPenetration = 1.0;
        this.ranged = false;
        this.type = "Weapon";
        this.name = "Dark Katana";
        this.physicalDamage = false;
    }
}
