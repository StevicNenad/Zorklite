package game;

import game.characters.Player;
import game.items.Armor;
import game.items.Weapon;
import game.rooms.StartRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player player;
    private int roomNumber;
    private Room currentRoom;
    private ArrayList<Room> rooms; // Stores all generated rooms into an arraylist. This helps to keep track of the players progress.

    public Game() {
        roomNumber = 1;
        rooms = new ArrayList<Room>();
        currentRoom = new StartRoom();
        player = new Player();
    }

    public void welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.print(   "************************\n" +
                            "******* Zorklite *******\n" +
                            "************************\n\n" +
                            "Press any key to begin...");

        sc.nextLine();
    }

    public void launch() {
        welcome();
        currentRoom = new StartRoom();
        roomNumber = 0;
        chooseLoadout((StartRoom)currentRoom);
        mainGame();
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

    public void mainGame() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while(roomNumber < 30) {
            RoomGenerator rg = new RoomGenerator();
            rooms.add(currentRoom);
            roomNumber++;

            //Rooms 10, 20 and 30 are boss rooms, the others normal monster rooms.
            if(roomNumber % 10 != 0) {
                currentRoom = rg.generateMonsterRoom(roomNumber);
            }
            else {
                currentRoom = rg.generateBossRoom(roomNumber);
            }

            currentRoom.printDescription();

            do {
                try {
                    command = br.readLine();

                } catch (IOException x) {
                    System.out.println("There was an error reading the input");
                }
            }while(true);
        }
    }
}