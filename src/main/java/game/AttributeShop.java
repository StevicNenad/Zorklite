package game;

import game.characters.Player;

import java.util.Scanner;

public class AttributeShop {
    int requirement;

    public AttributeShop() {
        requirement = 100;
    }

    public void startInterface(Player player) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        requirement = requirement * player.getAttributes().getLevel();

        while(requirement < player.getDeathTokens()) {
            player.addAP(1);
            player.getAttributes().addLevel(1);
            player.updateAttributePoints(3);
            player.updateTokens(-requirement);
            System.out.println("Level up! You have gained 3 attribute points!");
            requirement = (int) (100 * (1.33 * player.getAttributes().getLevel()));
        }

        int percent = (int) ((double)player.getDeathTokens() / requirement * 35);
        String progressBar = "";

        for(int i = 0; i < percent; i++){
            progressBar += "▓";
        }


        System.out.printf("\n▓%-35s", progressBar);
        System.out.println("\n" + player.getDeathTokens() + "/" + requirement + " Death Tokens for next level\n");

        System.out.println("What do you want to do?\n1 - Improve Attributes\nQ - Go back to Main Menu");
        System.out.print(">");

        userInput = sc.nextLine();

        switch(userInput.toLowerCase()) {
            case "1":
                upgradeAttributes(player);
                return;
            case "q":
                return;
            default:
                System.out.println("Invalid Input");
        }
    }

    private void upgradeAttributes(Player player) {
        Scanner sc = new Scanner(System.in);
        String userInput;

        while(true) {
            System.out.printf("%-20s", "Attr. Points");
            System.out.printf("%3s", player.getAttributePoints());

            System.out.printf("\n\n1 - %-15s", "Strength");
            System.out.printf("%3s", player.getAttributes().getStrength());

            System.out.printf("\n2 - %-15s", "Intelligence");
            System.out.printf("%3s", player.getAttributes().getIntelligence());

            System.out.printf("\n3 - %-15s", "Agility");
            System.out.printf("%3s", player.getAttributes().getAgility());

            System.out.printf("\n4 - %-15s", "Perception");
            System.out.printf("%3s", player.getAttributes().getPerception());

            System.out.printf("\n5 - %-15s", "Stealth");
            System.out.printf("%3s", player.getAttributes().getStealth());

            System.out.printf("\n6 - %-15s", "Speed");
            System.out.printf("%3s", player.getAttributes().getSpeed());

            System.out.println("\n\nQ - Main Menu");

            userInput = sc.nextLine();

            if(player.getAttributePoints() > 0 && !userInput.toLowerCase().equals("q")){

                switch(userInput.toLowerCase()) {
                    case "1":
                        player.getAttributes().setStrength(player.getAttributes().getStrength() + 1);
                        break;
                    case "2":
                        player.getAttributes().setIntelligence(player.getAttributes().getIntelligence() + 1);
                        break;
                    case "3":
                        player.getAttributes().setAgility(player.getAttributes().getAgility() + 1);
                        break;
                    case "4":
                        player.getAttributes().setPerception(player.getAttributes().getPerception() + 1);
                        break;
                    case "5":
                        player.getAttributes().setStealth(player.getAttributes().getStealth() + 1);
                        break;
                    case "6":
                        player.getAttributes().setSpeed(player.getAttributes().getSpeed() + 1);
                        break;
                    case "q":
                        return;
                    default:
                        System.out.println("Invalid input!");
                        continue;
                }
                player.updateAttributePoints(-1);
                player.updateAllStatsAfterLevelup();
            }
            else if(userInput.toLowerCase().equals("q")){
                return;
            }
            else {
                System.out.println("Not enough attribute points!");
            }
        }
    }
}
