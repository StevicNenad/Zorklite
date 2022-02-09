package game;

import game.characters.Player;
import game.items.Accessories;
import game.items.Gems;
import game.items.Tokens;
import game.items.Weapon;
import game.rooms.BonusRoom;
import game.rooms.BossRoom;
import game.rooms.StartRoom;
import game.rooms.TreasureRoom;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//Main class that handles all the out of battle logic
public class Game {
    private Parser parser;
    private Player player;
    private boolean gameover;
    private Room currentRoom;
    private ArrayList<Room> rooms;

    public Game() {
        parser = new Parser(System.in);
        rooms = new ArrayList<Room>();
        currentRoom = new StartRoom();
        player = new Player();
        gameover = false;
    }

    //The main game loop. Checks if game over etc.
    public void mainGame() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            welcome();
            RoomGenerator roomGen = new RoomGenerator();
            rooms = roomGen.getRooms();
            currentRoom = rooms.get(0);
            currentRoom.chooseLoadout(player);

            while (!gameover) {
                player.saveToFile();
                Command command = parser.getCommand();
                processCommand(command);
            }
            //Print stats, progress and goodbye message HERE
            if(player.getDeaths() == 1) {
                cls();
                System.out.println( "You have fallen for the first time. Don't worry, the game is incredibly hard at the beginning. For each monster\n" +
                                    "slain, you gain death tokens. These stay with you even when you perish. Use them in the main menu to improve\n" +
                                    "the attributes of your character and make them stronger. With each run, with each monster slain, the game becomes\n" +
                                    "easier. So just keep at it, and find out if you can defeat the final boss in the game.");
                sc.nextLine();
                cls();

            }
            player.saveToFile();
            player.resetCharacter();
            gameover = false;
        }
    }

    //Welcome message and show main menu. Level up menu is possible from here
    public void welcome() {
        Scanner sc = new Scanner(System.in);
        boolean continueGame = false;
        String userInput;

        File saveData = new File("saves/save.dat");

        while(true) {
            System.out.print(   "************************************\n" +
                                "************* Zorklite *************\n" +
                                "************************************\n\n" +
                                "Press enter to begin...\n");

            userInput = sc.nextLine();

            //Checks if Savedata is available
            if(saveData.exists()) {
                player.loadSaveFile();
                System.out.println("0 - Continue");
                continueGame = true;
            }
            System.out.println("1 - New Game");
            if(player.getDeaths() > 0) System.out.println("2 - Improve Character");
            System.out.println("Q - Quit Game");
            System.out.print(">");

            userInput = sc.nextLine();

            cls();

            switch(userInput.toLowerCase()) {
                case "0":
                    if(continueGame) {
                        player.loadSaveFile();
                        return;
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;
                case "1":
                    player = new Player();
                    return;
                case "2":
                    AttributeShop as = new AttributeShop();
                    if(player.getDeaths() > 0) {
                        as.startInterface(player);
                        player.saveToFile();
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;
                case "q":
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    //Function that processes the command (does it have second word or not, is the first word a valid command etc.)
    public void processCommand(Command command) {
        Scanner sc = new Scanner(System.in);
        cls();
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
                    sc.nextLine();
                    cls();
                    gameover = battle.startEncounter(player, currentRoom.getEnemies(), currentRoom);
                    currentRoom.setExplored(true);
                    currentRoom.generateDescription();
                    currentRoom.printDescription();
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
                else if (currentRoom.getEnemies().isEmpty()) {
                    System.out.println("There are no enemies left in this room...");
                    return;
                }

                Battle battle = new Battle();
                gameover = battle.startEncounter(player, currentRoom.getEnemies(), currentRoom);
                break;

            case "take":
                takeItem();
                break;

            case "info":
                showCharacterInfo();
                break;

            case "map":
                printMap(command);
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

    //Function that prints help
    public void printHelp() {
        if (currentRoom.getRoomType() == Room.RoomType.START) {
            System.out.println( "You picked up your equipment, there is nothing left here to do. With only the red door in the \"east\" remaining,\n" +
                                "this is the only way you can \"go\" now.");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.isExplored() == false) {
            System.out.println( "The door behind you is locked tight, you can't get through. You can't make out what lies inside this dark room. \n" +
                                "You should probably \"explore\" it to learn more.");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getEnemies().isEmpty() == false) {
            System.out.println( "There is no way back it seems... Even though you hear terrible screams ahead, the only option you have left is\n" +
                                "to push east, but you need to find a way around these monsters... Hmm... maybe \"attack\" or \"sneak\"");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getEnemies().isEmpty() && currentRoom.getLoot().isEmpty()) {
            System.out.println( "There seem to be no more threats in this area, neither any items... it's time to \"go east\"");
        } else if (currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getEnemies().isEmpty() && !currentRoom.getLoot().isEmpty()){
            System.out.println( "You can't spot any threats, but there still seem to be items left in this room. You could \"take\" them with you.");
        }
        System.out.println(     "You can use the following command words: ");
        System.out.println(parser.showCommands());
    }

    //Function that print tutorial
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
            case "attributes":
                System.out.println( "Strength - Increases your maximum HP as well as damage of some weapons\n" +
                                    "Intelligence - Increases your maximum Mana as well as damage of some weapons and spells\n" +
                                    "Agility - Increases your natural Armor as well as damage of some weapons\n" +
                                    "Stealth - Determines how likely \"sneak\" commands will work\n" +
                                    "Speed - Determines queue order in battle encounters\n" +
                                    "Perception - Determines if you can enter Bonus Rooms\n" +
                                    "Attacks - Determins how many consecutive attacks you can perform on a target in one turn\n" +
                                    "AoE/Cleave - Hits all enemies in battle for a percentage of the damage done to primary target\n" +
                                    "Projectiles - Every projectile can trigger a passive effect. Projectiles can't hit the same target in one turn\n");
            case "go":
                System.out.println( "With \"go\" you can enter doors and progress further through the game. It's how you navigate the world.\n" +
                                    "If there are Monsters nearby you will not be able to use the command.");
                break;
            case "map":
                System.out.println( "With \"map\" you can view the currently explored room, and what's inside it. It's shorter than the \"explore\" command\n" +
                                    "and you can't get into surprise encounters when using it. If you type \"map full\", you can see the world map.");
            case "sneak":
                System.out.println( "With the command \"sneak\" you are able to do two things: 1 - You can try to sneak past monsters and exit\n" +
                                    "a room without clearing it or 2 - you can sneak attack a target, which allows you to cause damage to it before\n" +
                                    "the battle even begins. The success of both of those actions depends on your \"stealth\" attribute. If you\n" +
                                    "fail a sneak action, you are immediately thrown into battle with 25% of your MAX hp removed. (You can die from this)");
                break;
            case "explore":
                System.out.println( "Every Monster room needs to be explored first before you know what lies within it. Depending on your\n" +
                                    "\"perception\" attribute, you are able to spot additional secrets. Exploring a room has a chance of monsters\n" +
                                    "ambushing you and starting a battle encounter. Exploring a room also has a rare chance for finding secret\n" +
                                    "loot every time you use the command.");
                break;
            case "help":
                System.out.println( "Using this command will occasionally give you small hints on what to do or where to go. It also displays all command words.");
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
                System.out.println("This command ends your current run and you can start over again.");
                break;
            case "game":
                System.out.println( "The goal of the game is to make it as far east as you can until you reach the final boss. The weapons, Monsters, Loot and\n" +
                                    "Environments are all randomly generated. Every play through is different and some are unlucky from the start,\n" +
                                    "so feel free to restart a run whenever. With every Monster slain, you increase your Death Tokens and attributes\n" +
                                    "which are permanent and stay with every new run. So the game gets easier the longer you stick with it.");
                break;
            default:
                System.out.println( "Tutorial of what? You can view tutorials for any of these:\n" +
                                    "\"attack\"\n" +
                                    "\"attributes\"\n" +
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

    //Function that makes player move through rooms
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
        } else {

            String direction = command.getSecondWord().toLowerCase();

            if (currentRoom.isExplored() && currentRoom.getEnemies().isEmpty()) {
                if(direction.toLowerCase().equals("north")) {
                    if(player.getAttributes().getPerception() + player.getBonusPerception() < ((BonusRoom)currentRoom.getExit("north")).getPerceptionRequirement()) {
                        System.out.println("You just don't see a way through...");
                        return;
                    }
                }

                if(direction.toLowerCase().equals("west")) {
                    System.out.println( "There is no way to go back. The door is shut firm, no matter how much you pull or push on it, it doesn't budge.\n" +
                                        "The only way to go now is deeper into the dungeon, east.");
                }

                Room nextRoom = currentRoom.getExit(direction);

                if(currentRoom.getRoomType() == Room.RoomType.TREASURY && ((TreasureRoom)currentRoom).getBossType() == BossRoom.BossType.FALSEGOD) {
                    endGame();
                } else if (currentRoom.getRoomType() == Room.RoomType.TREASURY) {
                    ItemShop itemShop = new ItemShop();
                    itemShop.openShop(player);
                }

                if (nextRoom != null) {
                    currentRoom = nextRoom;

                    if (currentRoom.isExplored()) {
                        currentRoom.printDescription();
                    } else {

                        //When entering a new Monster Room, restore 10% of hp, mana, armor and shield.
                        player.updateHealth((int) (player.getMaxHealth() * 0.10));
                        player.updateMana((int) (player.getMaxMana() * 0.10));
                        player.updateArmor((int) (player.getArmorPoints() * 0.10));
                        player.updateShield((int) (player.getShieldPoints() * 0.10));

                        System.out.println( "You walk through the door and find yourself in a Room you haven't been in before... As you enter, the\n" +
                                            "door behind you slams shut. It's locked tight, there is no way back...");
                    }
                } else {
                    System.out.println("There is no way through here...");
                }
            } else if (currentRoom.isExplored() && !currentRoom.getEnemies().isEmpty()) {
                System.out.println("You can't do that, there are monsters nearby...");
            } else {
                System.out.println("You need to \"explore\" the room first.");
            }
        }
    }

    //function that opens the loot menu and enables player to interact with objects
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

                            System.out.println( "As you touch the gem, it immediately breaks in your hands. You feel a burst of ancient knowledge run\n" +
                                                "run through your mind. You have learned a new technique...");

                            break;
                        case ACCESSORY:
                            System.out.println("You have picked up the artifact.");
                            player.setAccessory((Accessories) item);
                            break;
                        case POTION:
                            System.out.println("You have picked up this strange potion. It might be of use in battle.");
                            player.updatePotions(1);
                            break;
                        case ESSENCE:
                            System.out.println( "You can feel a strange power radiating from this relic. It seems as if it's pure evil that is radiating," +
                                                "from this object. As you pick it up, you feel your weapons and armor becoming more powerful.");
                            player.updateDemonicEssence(1);
                            break;
                        case TOKENS:
                            System.out.println("You have picked up " + (item.getValue()) + " death tokens!");
                            player.updateTokens(item.getValue());
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

    //Function that processes sneak attacks or sneaking by enemies
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
                if (currentRoom.getEnemies().isEmpty()) {
                    System.out.println("There is nothing to ambush in this room...");
                    return;
                }

                Battle battle = new Battle();

                for (Character enemy : currentRoom.getEnemies()) {
                    if (enemy.getAttributes().getPerception() >= player.getAttributes().getStealth() + player.getBonusStealth()) {
                        battle.preemptiveStrikeFail(player);
                        gameover = battle.startEncounter(player, currentRoom.getEnemies(), currentRoom);
                        return;
                    }
                }

                battle.preemptiveStrikeSuccess(player, currentRoom.getEnemies());
                gameover = battle.startEncounter(player, currentRoom.getEnemies(), currentRoom);
            }
            else {
                if (!currentRoom.getEnemies().isEmpty()) {
                    for (Character enemy : currentRoom.getEnemies()) {
                        if (enemy.getAttributes().getPerception() >= player.getAttributes().getStealth() + player.getBonusStealth()) {
                            Battle battle = new Battle();
                            battle.preemptiveStrikeFail(player);
                            battle.startEncounter(player, currentRoom.getEnemies(), currentRoom);
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

    //Function that shows attributes and current stats of th eplayer
    private void showCharacterInfo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\nYOU, lvl " + player.getAttributes().getLevel() + ":");

        System.out.printf("%-17s", player.getCurrenthealth() + "/" + player.getMaxHealth() + " HP");
        System.out.printf("%-17s", player.getCurrentMana() + "/" + player.getMaxMana() + " MP");
        System.out.printf("%-17s", player.getCurrentArmor() + "/" + player.getArmorPoints() + " Armor");
        System.out.printf("%-17s", player.getCurrentShield() + "/" + player.getShieldPoints() + " Shield");


        System.out.println("\n\nAttributes:");

        System.out.printf("%-13s", "Strength");
        System.out.printf("%7s", player.getAttributes().getStrength() + " +" + player.getBonusStrength());
        System.out.printf("%-13s", "\tIntelligence");
        System.out.printf("%7s", player.getAttributes().getIntelligence() + " +" + player.getBonusIntelligence());
        System.out.printf("%-13s", "\tAgility");
        System.out.printf("%7s", player.getAttributes().getAgility() + " +" + player.getBonusAgility());

        System.out.printf("\n%-13s", "Perception");
        System.out.printf("%7s", player.getAttributes().getPerception() + " +" + player.getBonusPerception());
        System.out.printf("%-13s", "\tStealth");
        System.out.printf("%7s", player.getAttributes().getStealth() + " +" + player.getBonusStealth());
        System.out.printf("%-13s", "\tSpeed");
        System.out.printf("%7s", player.getAttributes().getSpeed() + " +" + player.getBonusSpeed());

        System.out.println("\n\nCombat:");

        System.out.printf("%-13s", "Damage");
        System.out.printf("%7s", player.getAttributes().getDamage());
        System.out.printf("%-13s", "\tAccuracy");
        System.out.printf("%7s", ((int) (player.getAttributes().getAccuracy() * 100)) + "%");
        System.out.printf("%-13s", "\tCleave");
        System.out.printf("%7s", ((int) (player.getAttributes().getAoeDamage() * 100)));

        System.out.printf("\n%-13s", "Crit chance");
        System.out.printf("%7s", ((int) (player.getAttributes().getCritChance() * 100)) + "%");
        System.out.printf("%-13s", "\tCritical");
        System.out.printf("%7s", ((int) (player.getAttributes().getCritPercentage() * 100)) + "%");
        System.out.printf("%-13s", "\tEvasion");
        System.out.printf("%7s", ((int) (player.getAttributes().getEvasion() * 100)) + "%");

        System.out.printf("\n%-13s", "Dmg Redux");
        System.out.printf("%7s", ((int) (player.getAttributes().getDamageReduction() * 100)) + "%");
        System.out.printf("%-13s", "\tMagic Res");
        System.out.printf("%7s", ((int) (player.getAttributes().getMagicResistance() * 100)) + "%");
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

        cls();
        printHelp();
    }

    //Function that checks if the Player has reached the last room. If so, initializes ending.
    private void endGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println( "It is done... After you have slain the unborn monstrosity, you now find yourself in a completely white room.\n" +
                            "In front of you there is a strange Monolith, so dark no light can escape it. It draws you to it, with each step\n" +
                            "you are less able to resist it's draw.");
        pause(2000);
        System.out.println( "As you stand right before it, you can feel a warmth radiating through your body. You slowly raise your hand, your fingertips\n" +
                            "mere inches away from the strange artifact. The moment you touch it, everything fades to black. As you slowly open your eyes, you\n" +
                            "are blinded by a bright light. You lie next to a riverbank, in a place you've never seen before. How did you get here? How did you\n" +
                            "get out of the dungeon? Far in the distance, you see what appears to be a settlement. With the terrors and nightmares behind you,\n" +
                            "you slowly make your way to a new destination. Your journey will continue...but not now. To be continued...");
        pause(3000);
        System.out.println("Thank you for playing Zorklite! With the Unborn defeated, you have proven to be an expert Warrior! Press any key to exit the game...");
        sc.nextLine();

        System.exit(0);
    }

    //Function that prints map. Either full map or just a small list of things player can interact with in current room
    private void printMap(Command command) {

        if(command.hasSecondWord()) {
            if(command.getSecondWord().equals("full")) {
                for (Room room : rooms) {
                    if(room.getRoomType() == Room.RoomType.BOSS && room.isExplored() && room == currentRoom) {
                        System.out.print("||X|| ");
                    } else if(room.isExplored() && room == currentRoom) {
                        System.out.print("▓X▓ ");
                    } else if(room.getRoomType() == Room.RoomType.BOSS) {
                        System.out.print("||||| ");
                    } else if(room.isExplored()) {
                        System.out.print("▓▓▓ ");
                    } else if(room == currentRoom) {
                        System.out.print("░X░ ");
                    } else {
                        System.out.print("░░░ ");
                    }
                }
                System.out.println("");
                return;
            }
            System.out.println("You can only use \"map full\" or \"map\".");
            return;
        }

        if(!currentRoom.isExplored()) {
            System.out.println("You haven't \"explored\" this room yet...");
            return;
        }

        System.out.println("Monsters:");
        if(currentRoom.getEnemies().isEmpty()) {
            System.out.println("none");
        }
        else {
            for(Character monster : currentRoom.getEnemies()) {
                System.out.println(monster.getName() + ", lvl " + monster.getAttributes().getLevel());
            }
        }
        System.out.println("\nItems:");
        if(currentRoom.getLoot().isEmpty()) {
            System.out.println("none");
        }
        else {
            for (Item item : currentRoom.getLoot()) {
                System.out.println(item.getName());
            }
        }
        System.out.println("\nExits:");
        for(String exit : currentRoom.getExits().keySet()) {
            if(currentRoom.getExits().get(exit).getRoomType() == Room.RoomType.BONUS) {
                if(player.getAttributes().getPerception() + player.getBonusPerception() < ((BonusRoom)currentRoom.getExits().get(exit)).getPerceptionRequirement()) {
                    continue;
                }
            }
            Room.RoomType type = currentRoom.getExits().get(exit).getRoomType();
            System.out.println(exit + ", " + type.toString().toLowerCase() + " room door");
        }
    }

    //Function that adds a small pause
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    //Function that clears the console
    private void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}