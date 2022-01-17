package game;

import game.characters.Player;
import game.items.Armor;
import game.items.Weapon;
import game.rooms.BossRoom;
import game.rooms.StartRoom;

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
        StartRoom sr = (StartRoom) currentRoom;
        roomNumber = 0;
        chooseLoadout(sr);
        mainGame();
    }

    //Function that makes player choose loadout for the round
    public void chooseLoadout(StartRoom sr) {
        String user_pick = "0";
        Scanner sc = new Scanner(System.in);

        do{
            sr.printDescription();
            user_pick = sc.nextLine();

            try{
                int number = Integer.parseInt(user_pick);
                Weapon wpn;
                Armor arm;

                switch(number) {
                    case 1:
                        wpn = (Weapon) sr.getSetOne().get(0);
                        arm = (Armor) sr.getSetOne().get(1);

                        if(confirmLoadout(wpn, arm)) return;

                        break;
                    case 2:
                        wpn = (Weapon) sr.getSetTwo().get(0);
                        arm = (Armor) sr.getSetTwo().get(1);

                        if(confirmLoadout(wpn, arm)) return;

                        break;
                    case 3:
                        wpn = (Weapon) sr.getSetThree().get(0);
                        arm = (Armor) sr.getSetThree().get(1);

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
        while(roomNumber < 30) {
            RoomGenerator rg = new RoomGenerator();
            rooms.add(currentRoom);
            roomNumber++;

            //Rooms 10, 20 and 30 are boss rooms, the others normal monster rooms.
            if(roomNumber % 10 != 0) {
                currentRoom = rg.generateMonsterRoom(roomNumber);
            }
            else{
                currentRoom = rg.generateBossRoom(roomNumber);
            }

            currentRoom.printDescription();
        }
    }
}