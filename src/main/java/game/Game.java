package game;

import game.characters.Player;
import game.items.Accessories;
import game.items.Gems;
import game.items.Tokens;
import game.items.Weapon;
import game.rooms.StartRoom;

import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Player player;
    private int roomNumber;
    private boolean gameover;
    private Room currentRoom;
    private ArrayList<Room> rooms;

    public Game() {
        parser = new Parser(System.in);
        roomNumber = 0;
        rooms = new ArrayList<Room>();
        currentRoom = new StartRoom();
        player = new Player();
        gameover = false;
    }

    public void mainGame() {
        while (true) {
            welcome();
            RoomGenerator roomGen = new RoomGenerator();
            rooms = roomGen.getRooms();
            currentRoom = rooms.get(0);
            currentRoom.chooseLoadout(player);

            while (!gameover) {
                Command command = parser.getCommand();
                processCommand(command);
            }

            //Print stats, progress and goodbye message HERE
            System.out.println("Pussy");
            player.resetCharacter();
            gameover = false;
        }

    }

    public void welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.print(   "************************************\n" +
                            "************* Zorklite *************\n" +
                            "************************************\n\n" +
                            "Press enter to begin or \"q\" to exit\n");

        String userInput = sc.nextLine();

        if (userInput.equals("q")) System.exit(0);
    }

    public void processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord().toLowerCase();

        switch (commandWord) {
            case "help":
                printHelp();
                break;

            case "tutorial":
                printTutorial(command);
                break;

            case "explore":
                Random rn = new Random();
                double surpriseEncounter = rn.nextDouble();

                if(surpriseEncounter <= 0.10) {
                    Battle battle = new Battle();
                    System.out.println("While exploring the room, you made a loud noise and attracted all the enemies in this room. Prepare to fight.");
                    gameover = battle.startEncounter(player, currentRoom.getMonsters(), currentRoom.getLoot());
                    currentRoom.printDescription();
                    currentRoom.setExplored(true);
                    return;
                }

                if (currentRoom.isExplored()) {
                    System.out.println("You have already explored this room... type \"map\"");
                    return;
                }

                currentRoom.printDescription();
                currentRoom.setExplored(true);
                break;

            case "attack":
                if (!currentRoom.isExplored()) {
                    System.out.println("You need to explore this room first... type \"explore\"");
                    return;
                }

                Battle battle = new Battle();
                gameover = battle.startEncounter(player, currentRoom.getMonsters(), currentRoom.getLoot());
                break;

            case "take":
                takeItem();
                break;

            case "info":
                showCharacterInfo();
                break;

            case "map":
                currentRoom.printMap();
                break;

            case "go":
                goRoom(command);
                break;

            case "sneak":
                sneak(command);
                break;

            case "quit":
                gameover = true;
                break;
        }
    }

    public void printHelp() {
        if (currentRoom.getRoomType() == Room.RoomType.START) {
            System.out.println( "You picked up your equipment, there is nothing left here to do. With only the red door in the \"east\" remaining,\n" +
                                "this is the only way you can \"go\" now.");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.isExplored() == false) {
            System.out.println( "The door behind you is locked tight, you can't get through. You can't make out what lies inside this dark room. \n" +
                                "You should probably \"explore\" it to learn more.");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getMonsters().isEmpty() == false) {
            System.out.println( "There is no way back it seems... Even though you hear terrible screams ahead, the only option you have left is\n" +
                                "to push east, but you need to find a way around these monsters... Hmm... maybe \"attack\" or \"sneak\"");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getMonsters().isEmpty()) {
            System.out.println( "There seem to be no more threats in this area... it's time to \"go east\"");
        }
        System.out.println(     "You can use the following command words: ");
        System.out.println(parser.showCommands());
    }

    public void printTutorial(Command command) {
        String tutorialSubject = "null";

        if (command.hasSecondWord()) {
            tutorialSubject = command.getSecondWord().toLowerCase();
        }

        switch (tutorialSubject) {
            case "attack":
                System.out.println( "If there are Monsters in a Room, you can choose to attack them. To do this, type \"attack\" and you will enter\n" +
                                    "battle immediately. If there are multiple monsters in the room, you'll be fighting against all of them at the same\n" +
                                    "time. The battle ends when all the monsters are slain or you lose all HP. Fighting multiple monsters at once\n" +
                                    "gives you a big experience bonus.");
                break;
            case "go":
                System.out.println( "With \"go\" you can enter doors and progress further through the game. If there are Monsters nearby\n" +
                                    "you will not be able to use the command.");
                break;
            case "map":
                System.out.println( "With \"map\" you can view the currently explored room, and what's inside it. It's shorter than the \"explore\" command\n" +
                                    "and you can't get into surprise encounters when using it.");
            case "sneak":
                System.out.println( "With the command \"sneak\" you are able to do two things: 1 - You can try to sneak past monsters and exit\n" +
                                    "a room without clearing it or 2 - you can sneak attack a target, which allows you to cause damage to it before\n" +
                                    "the battle even begins. The success of both of those actions depends on your \"stealth\" attribute. If you\n" +
                                    "fail a sneak action, you are immediately thrown into battle with 25% of your MAX hp removed. (You can die from this)");
                break;
            case "explore":
                System.out.println( "Every Room except Boss Rooms need to be explored first before you know what lies within them. Depending on your\n" +
                                    "\"perception\" attribute, you are able to spot additional secrets. Exploring a room has a chance of monsters\n" +
                                    "ambushing you and starting a battle encounter (even when the room has been cleared). Exploring a room also has a rare\n" +
                                    "chance for finding secret loot every time you use the command.");
                break;
            case "help":
                System.out.println( "Using this command will occasionally give you small hints on what to do or where to go.");
                break;
            case "info":
                System.out.println( "With this command you can see your inventory as well as all attributes and stats of your character.");
                break;
            case "take":
                System.out.println( "To pick up items that have dropped by enemies or that you found in rooms, type \"take\". With it, you will open the\n" +
                                    "loot menu, where you can pick and choose which items to take with you, and which to leave behind. Attention: Once\n" +
                                    "you replace an accessory for another one, the old one gets destroyed and cannot be picked up again. Choose wisely.");
                break;
            case "quit":
                System.out.println("This command ends your current Run and you can start over again.");
                break;
            case "game":
                System.out.println( "The goal of the game is to make it as far east as you can until you reach the final boss. The weapons, Monsters, Loot and\n" +
                                    "Environments are all randomly generated. Every playthrough is different and some are unlucky from the start,\n" +
                                    "so feel free to restart a run whenever. With every Monster slain, you increase your Death Tokens and attributes\n" +
                                    "which are permanent and stay with every new run. So the game gets easier the longer you stick with it.");
                break;
            default:
                System.out.println( "Tutorial of what? You can view tutorials for any of these:\n" +
                                    "\"attack\"\n" +
                                    "\"explore\"\n" +
                                    "\"go\"\n" +
                                    "\"info\"\n" +
                                    "\"game\"\n" +
                                    "\"map\"\n" +
                                    "\"sneak\"\n" +
                                    "\"take\"\n" +
                                    "\"quit\"\n\n" +
                                    "To view type \"tutorial\" + any of the words above");
        }

        if (command.hasSecondWord()) {
            System.out.print("\nWhat do you wanna do next? (type \"help\" if you're stuck)\n");
        }
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
        } else {

            String direction = command.getSecondWord().toLowerCase();

            if (currentRoom.isExplored() && currentRoom.getMonsters().isEmpty()) {
                Room nextRoom = currentRoom.getExit(direction);

                if (nextRoom != null) {
                    currentRoom = nextRoom;
                    if (currentRoom.isExplored()) {
                        currentRoom.printDescription();
                    } else {
                        System.out.println( "You walk through the door and find yourself in a Room you haven't been in before... As you enter, the\n" +
                                            "door behind you slams shut. It's locked tight, there is no way back...");
                    }
                } else {
                    System.out.println("There is no way through here...");
                }
            } else if (currentRoom.isExplored() && !currentRoom.getMonsters().isEmpty()) {
                System.out.println("You can't do that, there are monsters nearby...");
            } else {
                System.out.println("You need to \"explore\" the room first.");
            }
        }
    }

    private void takeItem() {
        String userChoice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Items in this room:");
        int index = 0;
        for(Item item : currentRoom.getLoot()) {
            System.out.println(index + " - " + item.getName());
            index++;
        }
        System.out.print("Pick an item or type \"q\" to exit\n>");
        do{
            userChoice = sc.nextLine();

            if(userChoice.toLowerCase().equals("q")) {
                return;
            }

            try{
                index = Integer.parseInt(userChoice);

                if(index > currentRoom.getLoot().size() && index < currentRoom.getLoot().size()){
                    System.out.println("No such item exists...");
                }
                else {
                    Item item = currentRoom.getLoot().get(index);
                    switch(item.getitemType()) {
                        case GEM:
                            String gemName = currentRoom.getLoot().get(index).getName();

                            if(gemName.equals("Lightning Sparks Gem")) {
                                player.getWeapon().setDamageType(DamageType.UNIVERSAL);
                            }
                            else if(gemName.equals("Blind Rage Gem")) {
                                player.getAttributes().setDamage(player.getAttributes().getDamage() * 2);
                                player.getAttributes().setAccuracy(0.50);
                            }
                            else if(gemName.equals("Blitz Gem")) {
                                if(player.getWeapon().getWeaponType() == Weapon.WeaponType.MELEE) {
                                    player.getAttributes().setAoeDamage(player.getAttributes().getAoeDamage() + 0.35);
                                } else {
                                    player.getAttributes().setProjectiles(player.getAttributes().getProjectiles() + 2);
                                    player.getAttributes().setDamage((int) (player.getAttributes().getDamage() * 0.70));
                                }
                            }
                            else if(gemName.equals("Ember Gem")){
                                if(player.getWeapon().getDamageType() == DamageType.MAGICAL) {
                                    player.getAttributes().setDamage(player.getAttributes().getDamage() + 50);
                                } else if (player.getWeapon().getDamageType() == DamageType.UNIVERSAL) {
                                    player.getAttributes().setDamage(player.getAttributes().getDamage() + 10);
                                } else {
                                    player.getWeapon().setDamageType(DamageType.MAGICAL);
                                    player.getAttributes().setDamage(player.getAttributes().getDamage() + 20);
                                }
                            }

                            if(((Gems)item).getAbility().getAbilityType() == Ability.AbilityType.ACTIVE) {
                                player.getActives().add(((Gems)item).getAbility());
                            } else {
                                player.getPassives().add(((Gems)item).getAbility());
                            }

                            break;
                        case ACCESSORY:
                            player.setAccessory((Accessories) item);
                            break;
                        case POTION:
                            player.updatePotions(1);
                            break;
                        case ESSENCE:
                            player.updateDemonicEssence(1);
                            break;
                        case TOKENS:
                            player.updateTokens(((Tokens)item).getValue());
                            break;
                    }
                    currentRoom.getLoot().remove(item);
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("Please only use numbers or \"q\"...");
            }

        }while(!currentRoom.getLoot().isEmpty() && !userChoice.toLowerCase().equals("q"));
    }

    private void sneak(Command command) {
        if(!currentRoom.isExplored()) {
            System.out.println("You need to explore this room first...");
            return;
        }

        String secondWord = command.getSecondWord().toLowerCase();

        if(!command.hasSecondWord()) {
            System.out.println("Do what while sneaking?");
        } else {

            if(secondWord.equals("attack")) {
                if (currentRoom.getMonsters().isEmpty()) {
                    System.out.println("There is nothing to ambush in this room...");
                    return;
                }

                Battle battle = new Battle();

                for (Character enemy : currentRoom.getMonsters()) {
                    if (enemy.getAttributes().getPerception() >= player.getAttributes().getStealth()) {
                        battle.preemptiveStrikeFail(player);
                    }
                }

                battle.preemptiveStrikeSuccess(player, currentRoom.getMonsters());
                battle.startEncounter(player, currentRoom.getMonsters(), currentRoom.getLoot());
            }
            else {
                if (!currentRoom.getMonsters().isEmpty()) {
                    for (Character enemy : currentRoom.getMonsters()) {
                        if (enemy.getAttributes().getPerception() >= player.getAttributes().getStealth()) {
                            Battle battle = new Battle();
                            battle.preemptiveStrikeFail(player);
                            battle.startEncounter(player, currentRoom.getMonsters(), currentRoom.getLoot());
                            break;
                        }
                    }
                    Room nextRoom = currentRoom.getExit(secondWord);

                    if (nextRoom != null) {
                        currentRoom = nextRoom;

                        if (currentRoom.isExplored()) {
                            currentRoom.printDescription();
                        } else {
                            System.out.println("You managed to sneak past the enemy and find yourself in a new room in the east. You feel the gate" +
                                    "behind you slam shut. It's locked tight...");
                        }
                    } else {
                        System.out.println("You snuck all the way to a dead end, there is no way through here...");
                    }
                } else {
                    System.out.println("Slowly you crouch-walked your way to the east in a completely empty room. Unsurprisingly, you manage " +
                            "to sneak by the air undetected and open the gate. As you enter the new room, you can hear the gate bang shut." +
                            "No way back...");
                }
            }
        }
    }

    private void showCharacterInfo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\nYOU, lvl " + player.getAttributes().getLevel() + ":");

        System.out.printf("%-17s", player.getCurrenthealth() + "/" + player.getMaxHealth() + " HP");
        System.out.printf("%-17s", player.getCurrentMana() + "/" + player.getMaxMana() + " MP");
        System.out.printf("%-17s", player.getCurrentArmor() + "/" + player.getArmorPoints() + " Armor");
        System.out.printf("%-17s", player.getCurrentShield() + "/" + player.getShieldPoints() + " Shield");


        System.out.println("\n\nAttributes:");

        System.out.printf("%-13s", "Strength");
        System.out.printf("%7s", player.getAttributes().getStrength());
        System.out.printf("%-13s", "\tIntelligence");
        System.out.printf("%7s", player.getAttributes().getIntelligence());
        System.out.printf("%-13s", "\tAgility");
        System.out.printf("%7s", player.getAttributes().getAgility());

        System.out.printf("\n%-13s", "Perception");
        System.out.printf("%7s", player.getAttributes().getPerception());
        System.out.printf("%-13s", "\tStealth");
        System.out.printf("%7s", player.getAttributes().getStealth());
        System.out.printf("%-13s", "\tSpeed");
        System.out.printf("%7s", player.getAttributes().getSpeed());

        System.out.println("\n\nCombat:");

        System.out.printf("%-13s", "Damage");
        System.out.printf("%7s", player.getAttributes().getDamage());
        System.out.printf("%-13s", "\tAccuracy");
        System.out.printf("%7s", (player.getAttributes().getAccuracy() * 100) + "%");
        System.out.printf("%-13s", "\tCleave");
        System.out.printf("%7s", (player.getAttributes().getAoeDamage() * 100) + "%");

        System.out.printf("\n%-13s", "Crit chance");
        System.out.printf("%7s", (player.getAttributes().getCritChance() * 100) + "%");
        System.out.printf("%-13s", "\tCritical");
        System.out.printf("%7s", (player.getAttributes().getCritPercentage() * 100) + "%");
        System.out.printf("%-13s", "\tEvasion");
        System.out.printf("%7s", (player.getAttributes().getEvasion() * 100) + "%");

        System.out.printf("\n%-13s", "Dmg Redux");
        System.out.printf("%7s", (player.getAttributes().getDamageReduction() * 100) + "%");
        System.out.printf("%-13s", "\tMagic Res");
        System.out.printf("%7s", (player.getAttributes().getMagicResistance() * 100) + "%");
        System.out.printf("%-13s", "\tAttacks");
        System.out.printf("%7s", player.getAttributes().getAttacks());

        if(player.getWeapon().getWeaponType() == Weapon.WeaponType.PROJECTILE) {
            System.out.printf("\n%-13s", "Projectiles");
            System.out.printf("%7s", player.getAttributes().getProjectiles());
        }

        System.out.println("\n\nMisc:");
        System.out.printf("%-13s", "Potions");
        System.out.printf("%7s", player.getPotions());
        System.out.printf("%-13s", "\tDeath Tokens");
        System.out.printf("%7s", player.getDeathTokens());
        System.out.printf("%-13s", "\tEssences");
        System.out.printf("%7s", player.getDemonicEssence());

        System.out.printf("\n%-14s", "Attrib. Points");
        System.out.printf("%6s", player.getAttributePoints());

        System.out.println("\n\nAbilities:");
        if(player.getActives().isEmpty() && player.getPassives().isEmpty()) {
            System.out.print("NONE");
        } else {
            for(Ability active : player.getActives()) {
                System.out.println(active.getAbilityName() + ", " + active.getDescription());
            }
            for(Ability passive : player.getPassives()) {
                System.out.println(passive.getAbilityName() + ", " + passive.getDescription());
            }
        }

        System.out.println("\n\nEquipment:");
        System.out.printf("%-15s", "Weapon: ");
        System.out.printf("%-15s", player.getWeapon().getName());
        System.out.printf("%-15s", "Armor: ");
        System.out.printf("%-15s", player.getArmor().getName());
        if(player.getFirstAcc() != null) {
            System.out.printf("\n%-15s", "Accessory 1: ");
            System.out.printf("%-15s", player.getFirstAcc().getName());
        }
        if(player.getSecondAcc() != null) {
            System.out.printf("\n%-15s", "Accessory 2: ");
            System.out.printf("%-15s", player.getSecondAcc().getName());
        }
        System.out.print("\n\nPress any key to continue...");
        sc.nextLine();

        printHelp();
    }
}