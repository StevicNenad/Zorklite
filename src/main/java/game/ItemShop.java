package game;

import game.characters.Player;
import game.items.Accessories;
import game.items.Gems;
import game.items.ItemFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemShop {
    private ArrayList<Item> inventory;

    public ItemShop() {
        inventory = new ArrayList<Item>();
        generateInventory();
    }

    private void generateInventory(){
        ItemFactory iF = new ItemFactory();
        inventory.add(iF.getRandomAccessory());
        inventory.add(iF.getRandomGem());
        inventory.add(iF.getPotion());
        inventory.add(iF.getPotion());
        inventory.add(iF.getPotion());
    }

    public void openShop(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.println( "As you enter the room, a strange merchant appears before you. He utters not a word,\n" +
                            "but merely points to his stand with the following items: ");
        int index = 0;
        String userInput;

        do {
            for (Item item : inventory) {
                System.out.println(index + item.getName() + " | PRICE: " + item.getValue() + " death tokens");
            }
            System.out.println("Q - Exit");
            System.out.print(">");

            userInput = sc.nextLine();

            if(userInput.toLowerCase().equals("q")) {
                break;
            }

            try {
                int itemIndex = Integer.parseInt(userInput);

                if(itemIndex < 0 || itemIndex >= inventory.size()) {
                    System.out.println("No such item exists...");
                }
                else {
                    if(player.getDeathTokens() >= inventory.get(itemIndex).getValue()) {
                        player.updateTokens(-inventory.get(itemIndex).getValue());

                        if(inventory.get(itemIndex).getitemType() == Item.ItemType.GEM) {
                            Ability ability = ((Gems)inventory.get(itemIndex)).getAbility();
                            if(ability.getAbilityType() == Ability.AbilityType.ACTIVE) {
                                player.getActives().add(ability);
                            }
                            else {
                                player.getPassives().add(ability);
                            }
                        }
                        else {
                            Accessories accessories = (Accessories) inventory.get(itemIndex);
                            player.setAccessory(accessories);
                        }

                        inventory.remove(itemIndex);
                    }
                    else {
                        System.out.println("Not enough death tokens...");
                    }
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("Please use only valid inputs (numbers).");
            }


        }while(true);

        System.out.println("As quickly as he appeared, as quickly he again disappeared. You have no way to reach his shop again.");

    }

}
