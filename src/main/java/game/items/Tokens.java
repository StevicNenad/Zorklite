package game.items;

import game.Item;

public class Tokens extends Item {
    int value;

    public Tokens(){
        value = 0;
        name = "Death Tokens";
        description = "Can be used to level up";
        itemType = ItemType.TOKENS;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
