package game;

import game.characters.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Battle {
    private boolean battle_over,
                    playerPreemptiveStrike,
                    monsterPreemptiveStrike;

    public Battle() {
        battle_over = false;
        playerPreemptiveStrike = false;
        monsterPreemptiveStrike = false;
    }

    public void startEncounter(Player pc, ArrayList<Character> foes) {
        while(pc.getCurrenthealth() != 0 && !foes.isEmpty()) {
            ArrayList<Character> queue = generateAttackOrder(pc, foes);

            for(Character attacker : queue) {
                if (attacker.getCurrenthealth() > 0) {
                    displayBattle(queue);
                    processTurn(attacker, foes);
                }
            }
        }
    }

    public void preemptiveStrikeSuccess(ArrayList<Character> foes) {
        for(Character enemy : foes) {
            enemy.updateHealth(0 - (enemy.getMaxHealth() / 10));
        }
        playerPreemptiveStrike = true;
    }

    public void preemptiveStrikeFail(Player pc) {
        pc.updateHealth(0 - (pc.getMaxHealth() / 4));
        monsterPreemptiveStrike = true;
    }

    //Function that puts all characters in an array and sorts them by the attribute "Speed". The character with the highest Speed attacks first.
    private ArrayList<Character> generateAttackOrder(Player pc, ArrayList<Character> foes) {
        ArrayList<Character> participants = new ArrayList<Character>();
        participants.addAll(foes);
        participants.add(pc);

        Collections.sort(participants, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o2.getAttributes().getSpeed() - o1.getAttributes().getSpeed();
            }
        });

        if(playerPreemptiveStrike) {
            participants.remove(pc);
            participants.add(0, pc);
            playerPreemptiveStrike = false;
        }
        else if(monsterPreemptiveStrike) {
            participants.remove(pc);
            participants.add(pc);
            monsterPreemptiveStrike = false;
        }

        return participants;
    }

    private void displayBattle(ArrayList<Character> queue) {
        for (Character findPlayer : queue) {
            if(findPlayer.getType() == Character.CharacterType.PLAYER) {
                System.out.println("YOU: " + findPlayer.getCurrenthealth() + "/" + findPlayer.getMaxHealth() + " HP\n");
            }
        }

        for (Character findMonster : queue) {
            if(findMonster.getType() == Character.CharacterType.MONSTER) {
                System.out.print(findMonster.getName());

                if(findMonster.getCurrenthealth() <= 0) {
                    System.out.println(" DEAD");
                }
                else{
                    System.out.println(" " + findMonster.getCurrenthealth() + "/" + findMonster.getMaxHealth() + " HP");
                }
            }
        }
    }

    private void processTurn(Character attacker, ArrayList<Character> foes) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            boolean turnSuccessful = false;
            Scanner sc = new Scanner(System.in);

            while(turnSuccessful == false) {
                System.out.print("\nWhat do you wanna do?\n\n1 - Attack\n2 - Abilities\n3 - Block\n>");

                String menuChoice = sc.nextLine();

                switch (menuChoice) {
                    case "1":
                        System.out.println("Attack:");
                        int index = 0;
                        for(Character target : foes) {
                            if(target.getCurrenthealth() > 0) {
                                System.out.println(index + " - " + target.getName());
                            }
                            index++;
                        }

                        String chooseTarget = sc.nextLine();

                        try{
                            int target = Integer.parseInt(chooseTarget);

                            if(target < 0 || target >= foes.size()) {
                                System.out.println("Target does not exist...");
                            }
                        }
                        catch (NumberFormatException ex) {
                            System.out.println("Please use only valid inputs (numbers).");
                        }
                }
            }
        }
    }
}
