package game.rooms;

import game.Character;
import game.Item;
import game.Room;
import game.characters.Player;
import game.items.Armor;
import game.items.Armory;
import game.items.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StartRoom extends Room {
    private ArrayList<Item> setOne;
    private ArrayList<Item> setTwo;
    private ArrayList<Item> setThree;

    public StartRoom(){
        this.roomType = RoomType.START;

        this.bonus = false;
        this.treasury = false;
        this.explored = true;

        this.exits = new HashMap<String, Room>();
        this.loot = new ArrayList<Item>();
        this.monsters = new ArrayList<Character>();

        this.boss = null;

        setOne = new ArrayList<Item>();
        setTwo = new ArrayList<Item>();
        setThree = new ArrayList<Item>();

        Armory armory = new Armory();
        setOne.add(armory.getRandomWeapon());
        setOne.add(armory.getRandomArmor());

        armory = new Armory();
        setTwo.add(armory.getRandomWeapon());
        setTwo.add(armory.getRandomArmor());

        armory = new Armory();
        setThree.add(armory.getRandomWeapon());
        setThree.add(armory.getRandomArmor());

        generateFirstDescription();
    }

    @Override
    //Function that makes player choose loadout for the round
    public void chooseLoadout(Player player) {
        String user_pick = "0";
        Scanner sc = new Scanner(System.in);

        //levels starting weapons according to demonicEssences player possesses
        if(player.getDemonicEssence() > 0) {
            for(Item equipment : setOne) {
                equipment.upgradeStats(player.getDemonicEssence());
            }

            for(Item equipment : setTwo) {
                equipment.upgradeStats(player.getDemonicEssence());
            }

            for(Item equipment : setThree) {
                equipment.upgradeStats(player.getDemonicEssence());
            }

            generateFirstDescription();
        }


        do{
            printDescription();
            user_pick = sc.nextLine();

            try{
                int number = Integer.parseInt(user_pick);
                Weapon wpn;
                Armor arm;

                switch(number) {
                    case 1:
                        wpn = (Weapon) getSetOne().get(0);
                        arm = (Armor) getSetOne().get(1);

                        if(confirmLoadout(player, wpn, arm)) return;

                        break;
                    case 2:
                        wpn = (Weapon) getSetTwo().get(0);
                        arm = (Armor) getSetTwo().get(1);

                        if(confirmLoadout(player, wpn, arm)) return;

                        break;
                    case 3:
                        wpn = (Weapon) getSetThree().get(0);
                        arm = (Armor) getSetThree().get(1);

                        if(confirmLoadout(player, wpn, arm)) return;

                        break;
                    default:
                        System.out.println("Please use only numbers between 1 and 3. Press enter to continue...");
                        sc.nextLine();
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Please use only valid inputs (numbers). Press enter to continue...");
                sc.nextLine();
            }
        }while(true);
    }

    //Function that confirms user Loadout
    private boolean confirmLoadout(Player player, Weapon weapon, Armor armor) {
        player.setWeapon(weapon);
        player.setArmor(armor);

        Scanner sc = new Scanner(System.in);
        String user_confirm;
        System.out.println( "You have chosen the " + weapon.getName() + " with a " + armor.getName() +"\n" +
                "You can't change your loadout during the run. Are you ready to start your journey? Y/N: ");

        user_confirm = sc.nextLine();

        if (user_confirm.toLowerCase().equals("y")) {
            generateDescription();
            printDescription();
            return true;
        }
        else {
            return false;
        }
    }

    private void generateFirstDescription() {
        String description =    "You are in a dark dungeon. You have no idea how you came to this place,\n" +
                                "but you feel the only way to get out is to push forward. In front of you\n" +
                                "there are three chests. You have to choose one. Inside of them you find:\n\n" +

                String.format("%-30s","1st Chest") + String.format("%-30s", "2nd Chest") + String.format("%-30s", "3rd Chest")  +
                String.format("\n%-30s","Weapon") + String.format("%-30s", "Weapon") + String.format("%-30s", "Weapon")  +
                String.format("\n%-30s", setOne.get(0).getName() + ", lvl " + setOne.get(0).getAttributes().getLevel()) +
                String.format("%-30s", setTwo.get(0).getName() + ", lvl " + setTwo.get(0).getAttributes().getLevel())  +
                String.format("%-30s", setThree.get(0).getName() + ", lvl " + setThree.get(0).getAttributes().getLevel())  +
                String.format("\n%-30s","Armor") + String.format("%-30s", "Armor") + String.format("%-30s", "Armor")  +
                String.format("\n%-30s", setOne.get(1).getName() + ", lvl " + setOne.get(1).getAttributes().getLevel())  +
                String.format("%-30s", setTwo.get(1).getName() + " lvl " + setTwo.get(1).getAttributes().getLevel())  +
                String.format("%-30s", setThree.get(1).getName() + " lvl " + setThree.get(1).getAttributes().getLevel()) +
                        "\n\nChoose (1-3): ";

        this.description = description;
    }

    @Override
    public void generateDescription() {
        String description =    "As you pick up your Equipment, the other chests vanish. You look around, but you can't remember how\n" +
                                "you got into this place or where you were before. You spot a huge red door in the east of the room, it seems\n" +
                                "that is the only way forward... What is your next move? (type help or tutorial)\n";

        this.description = description;
    }

    @Override
    public void printMap() {
        super.printMap();
    }

    public ArrayList<Item> getSetOne() {
        return setOne;
    }

    public ArrayList<Item> getSetTwo() {
        return setTwo;
    }

    public ArrayList<Item> getSetThree() {
        return setThree;
    }
}
