package game;

import game.characters.Player;
import game.items.Armor;
import game.items.Weapon;
import game.rooms.StartRoom;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player player;
    private int roomNumber;
    private Room currentRoom;
    private ArrayList<Room> rooms;

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
        StartRoom sr = (StartRoom) currentRoom;
        System.out.println(currentRoom.getDescription());//Print StartRoom description

        chooseLoadout(sr);
    }

    //Function that makes player choose loadout for the round
    public void chooseLoadout(StartRoom sr) {
        String user_pick = "0";
        Scanner sc = new Scanner(System.in);

        do{
            user_pick = sc.nextLine();

            try{
                int number = Integer.parseInt(user_pick);
                Weapon wpn;
                Armor arm;

                switch(number) {
                    case 1:
                        wpn = (Weapon) sr.getSetOne().get(0);
                        player.setWeapon(wpn);
                        arm = (Armor) sr.getSetOne().get(1);
                        player.setArmor(arm);

                        confirmLoadout(wpn, arm);

                        return;
                    case 2:
                        wpn = (Weapon) sr.getSetTwo().get(0);
                        player.setWeapon(wpn);
                        arm = (Armor) sr.getSetTwo().get(1);
                        player.setArmor(arm);

                        confirmLoadout(wpn, arm);

                        return;
                    case 3:
                        wpn = (Weapon) sr.getSetThree().get(0);
                        player.setWeapon(wpn);
                        arm = (Armor) sr.getSetThree().get(1);
                        player.setArmor(arm);

                        confirmLoadout(wpn, arm);

                        return;
                    default:
                        System.out.println("Please use only numbers between 1 and 3");
                }
            }
            catch (NumberFormatException ex){
                System.out.print("Please use only valid inputs (numbers)");
            }
        }while(true);
    }

    //Function that confirms user Loadout
    public void confirmLoadout(Weapon weapon, Armor armor) {
        System.out.println("You have chosen the " + weapon.getName() + " with a " + armor.getName());
    }
}
