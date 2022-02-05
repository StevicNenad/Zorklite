package game.items;

import game.Ability;
import game.Item;

public class Potion extends Item {

    public Potion(){
        name = "Health Potion";
        description = "Heals user for 33% of their HP.";
        itemType = ItemType.POTION;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
