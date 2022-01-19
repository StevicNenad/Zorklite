package game;

import game.characters.Player;
import game.rooms.StartRoom;

import java.util.ArrayList;
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
        while(true) {
            welcome();
            RoomGenerator roomGen = new RoomGenerator();
            rooms = roomGen.getRooms();
            currentRoom = rooms.get(0);
            currentRoom.chooseLoadout(player);

            while(!gameover) {
                Command command = parser.getCommand();
                processCommand(command);
            }

            //Print stats, progress and goodbye message
            System.out.println("Pussy");
        }

        /*
        System.out.println("You enter a strange, dark dungeon. The air is thick, you feel uneasy.
        You pick up a torch nearby and plan your next move... (type help for commands)");
        */
    }

    public void welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.print(   "************************\n" +
                            "******* Zorklite *******\n" +
                            "************************\n\n" +
                            "Press enter to begin or \"q\" to quit\n");

        String userInput = sc.nextLine();

        if (userInput.equals("q")) System.exit(0);
    }

    public void processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("tutorial")) {
            printTutorial(command);
        }
        else if(commandWord.equals("explore")) {
            currentRoom.printDescription();
            currentRoom.setExplored(true);
        }
        else if(commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("quit")) {
            gameover = true;
        }
    }

    public void printHelp() {
        if(currentRoom.getRoomType() == Room.RoomType.START) {
            System.out.println( "You picked up your equipment, there is nothing left here to do. With only the red door in the \"east\" remaining,\n" +
                                "this is the only way you can \"go\" now.");
        }
        else if(currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.isExplored() == false) {
            System.out.println( "The door behind you is locked tight, you can't get through. You can't make out what lies inside this dark room. \n" +
                                "You should probably \"explore\" it to learn more.");
        }
        else if(currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getMonsters().isEmpty() == false) {
            System.out.println( "There is no way back it seems... Even though you hear terrible screams ahead, the only option you have left is\n" +
                                "to push east, but you need to find a way around these monsters... Hmm... maybe \"attack\" or \"sneak\"");
        }
        else if(currentRoom.getRoomType() == Room.RoomType.MONSTER && currentRoom.getMonsters().isEmpty()) {
            System.out.println( "There seem to be no more threats in this area... it's time to \"go east\"");
        }
        System.out.println("You can use the following command words: ");
        System.out.println(parser.showCommands());
    }

    public void printTutorial(Command command) {
        String tutorialSubject = "null";

        if(command.hasSecondWord()){
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
            case "sneak":
                System.out.println( "With the command \"sneak\" you are able to do two things: 1 - You can try to sneak past monsters and exit\n" +
                                    "a room without clearing it or 2 - you can sneak attack a target, which allows you to cause damage to it before\n" +
                                    "the battle even begins. The success of both of those actions depends on your \"stealth\" attribute. If you\n" +
                                    "fail a sneak action, you are immediately thrown into battle with the opposing monster having two turns in a row.");
                break;
            case "explore":
                System.out.println( "Every Room except Boss Rooms need to be explored first before you know what lies within them. Depending on your\n" +
                                    "\"perception\" attribute, you are able to spot additional secrets. Exploring a room has a chance of monsters\n" +
                                    "ambushing you and starting a battle encounter.");
                break;
            case "quit":
                System.out.println( "This command ends your current Run and you can start over again.");
                break;
            case "game":
                System.out.println( "The goal of the game is to make it as far as you can until you reach the final boss. The weapons, Monsters, Loot and\n" +
                                    "Environments are all randomly generated. Every playthrough is different and some are unlucky from the start\n" +
                                    "so feel free to start a new run whenever. With every Monster slain, you increase your experience and attributes\n" +
                                    "which are permanent and stay with every new run. So the game gets easier the longer you stick with it.");
                break;
            default:
                System.out.println( "Tutorial of what? You can view tutorials for any of these:\n" +
                                    "\"attack\"\n" +
                                    "\"go\"\n" +
                                    "\"sneak\"\n" +
                                    "\"explore\"\n" +
                                    "\"quit\"\n" +
                                    "\"game\"");
        }
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
        } else {

            String direction = command.getSecondWord().toLowerCase();

            if(currentRoom.isExplored() && currentRoom.getMonsters().isEmpty()) {
                Room nextRoom = currentRoom.getExit(direction);

                if(nextRoom != null) {
                    currentRoom = nextRoom;
                    if(currentRoom.isExplored()) {
                        currentRoom.printDescription();
                    }
                    else{
                        System.out.println( "You walk through the door and find yourself in a Room you haven't been in before... As you enter, the\n" +
                                "door behind you slams shut. It's locked tight, there is no way back...");
                    }
                }
                else {
                    System.out.println("There is no way through here...");
                }
            }
        }
    }

    private void startEncounter() {
        Battle battle = new Battle(player, currentRoom.getMonsters());
    }

    }