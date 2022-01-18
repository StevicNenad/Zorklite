package game;

import game.characters.Player;
import game.items.Armor;
import game.items.Weapon;
import game.rooms.StartRoom;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Player player;
    private int roomNumber;
    private Room currentRoom;
    private ArrayList<Room> rooms;

    public Game() {
        parser = new Parser(System.in);
        roomNumber = 0;
        rooms = new ArrayList<Room>();
        currentRoom = new StartRoom();
        player = new Player();
    }

    public void mainGame() {
        welcome();
        RoomGenerator roomGen = new RoomGenerator();
        rooms = roomGen.getRooms();
        currentRoom = rooms.get(0);
        chooseLoadout((StartRoom)currentRoom);

        while(roomNumber < 31) {
            Command command = parser.getCommand();
            processCommand(command);
        }

        /*RoomGenerator rg = new RoomGenerator();
        rooms.add(currentRoom);
        roomNumber++;
        currentRoom = rg.generateMonsterRoom(roomNumber);
        System.out.println("You enter a strange, dark dungeon. The air is thick, you feel uneasy. You pick up a torch nearby and plan your next move... (type help for commands)");
*/
    }

    public void welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.print(   "************************\n" +
                "******* Zorklite *******\n" +
                "************************\n\n" +
                "Press any key to begin...");

        sc.nextLine();
    }

    //Function that makes player choose loadout for the round
    public void chooseLoadout(StartRoom currentRoom) {
        String user_pick = "0";
        Scanner sc = new Scanner(System.in);

        do{
            currentRoom.printDescription();
            user_pick = sc.nextLine();

            try{
                int number = Integer.parseInt(user_pick);
                Weapon wpn;
                Armor arm;

                switch(number) {
                    case 1:
                        wpn = (Weapon) currentRoom.getSetOne().get(0);
                        arm = (Armor) currentRoom.getSetOne().get(1);

                        if(confirmLoadout(wpn, arm)) return;

                        break;
                    case 2:
                        wpn = (Weapon) currentRoom.getSetTwo().get(0);
                        arm = (Armor) currentRoom.getSetTwo().get(1);

                        if(confirmLoadout(wpn, arm)) return;

                        break;
                    case 3:
                        wpn = (Weapon) currentRoom.getSetThree().get(0);
                        arm = (Armor) currentRoom.getSetThree().get(1);

                        if(confirmLoadout(wpn, arm)) return;

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
    public boolean confirmLoadout(Weapon weapon, Armor armor) {
        player.setWeapon(weapon);
        player.setArmor(armor);

        Scanner sc = new Scanner(System.in);
        String user_confirm;
        System.out.println( "You have chosen the " + weapon.getName() + " with a " + armor.getName() +"\n" +
                            "Are you ready to start your journey? Y/N: ");

        user_confirm = sc.nextLine();

        return (user_confirm.toLowerCase().equals("y")) ? true : false;
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
        else if(commandWord.equals("explore")) {
            currentRoom.printDescription();
            currentRoom.setExplored(true);
        }
        else if(commandWord.equals("go")) {
            goRoom(command);
        }
    }

    public void printHelp() {
        System.out.println("You're confused in how you exactly arrived at this place, but you see no way to turn back. Ahead of you, you hear terrible screams,");
        System.out.println("but deep down you feel that this is the only way to get out of this hell.");
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
        } else {

            String direction = command.getSecondWord();

            if(currentRoom.isExplored()) {
                    Room nextRoom = currentRoom.getExit(direction);

                    if(nextRoom != null) {
                        currentRoom = nextRoom;
                        if(currentRoom.isExplored()) {
                            currentRoom.printDescription();
                        }
                        else{
                            System.out.println("You walk through another door and find yourself in a Room you haven't been in before...");
                        }
                    }
                    else {
                        System.out.println("There is nothing here...");
                    }
                }
            }
        }
    }