package game.items;

import game.Item;

public class DemonEssences extends Item {

    public DemonEssences(){
        name = "Demon Essence";
        description = "The raw power of a malevolent entitiy. You can feel your armor and equipment automatically get stronger.";
        itemType = ItemType.ESSENCE;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
