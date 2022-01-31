package game;

import game.abilities.passive.Lifesteal;
import game.characters.Boss;
import game.characters.Monster;
import game.characters.Player;
import game.items.Weapon;

import java.util.*;

public class Battle {
    private boolean battle_over,
                    playerPreemptiveStrike,
                    monsterPreemptiveStrike;

    public Battle() {
        battle_over = false;
        playerPreemptiveStrike = false;
        monsterPreemptiveStrike = false;
    }

    public boolean startEncounter(Player pc, ArrayList<Character> foes) {
        while(pc.getCurrenthealth() != 0 && !foes.isEmpty()) {
            ArrayList<Character> queue = generateAttackOrder(pc, foes);

            for(Character attacker : queue) {
                if (attacker.getCurrenthealth() > 0) {
                    displayBattle(queue);
                    processTurn(attacker, foes, pc);
                }
                if(battle_over) {
                    break;
                }
            }
        }

        if(foes.isEmpty()) {
            System.out.println("Threat eliminated! What do you want to do next?");
            return false;
        }
        else {
            System.out.println("You fall into a pool of your own blood, your eyes slowly closing. Game over...");

            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            return true;
        }
    }

    //Intro message when entering Battle.
    public void battleIntro() {

    }

    public void preemptiveStrikeSuccess(ArrayList<Character> foes) {
        for(Character enemy : foes) {
            enemy.updateHealth(-(enemy.getMaxHealth() / 10));
        }
        playerPreemptiveStrike = true;
    }

    public void preemptiveStrikeFail(Player pc) {
        pc.updateHealth(-(pc.getMaxHealth() / 4));
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

        //For loop that prints the players HP, Armor and Shield values to the terminal
        for (Character findPlayer : queue) {
            if(findPlayer.getType() == Character.CharacterType.PLAYER) {
                System.out.print("YOU: " + findPlayer.getCurrenthealth() + "/" + findPlayer.getMaxHealth() + " HP");
                if(findPlayer.getArmorPoints() > 0) {
                    System.out.print("\t" + findPlayer.getCurrentArmor() + "/" + findPlayer.getArmorPoints() + " Armor");
                }
                if (findPlayer.getShieldPoints() > 0) {
                    System.out.print("\t" + findPlayer.getCurrentShield() + "/" + findPlayer.getShieldPoints() + " Magic Shield");
                }
                System.out.println("\n");
            }
        }

        //For loop that prints all the monsters HP, Armor and Shield values to the terminal
        for (Character findMonster : queue) {
            if(findMonster.getType() == Character.CharacterType.MONSTER) {
                System.out.println(findMonster.getName());

                if(findMonster.getCurrenthealth() <= 0) {
                    System.out.println("DEAD\n");
                }
                else{
                    System.out.println(findMonster.getCurrenthealth() + "/" + findMonster.getMaxHealth() + " HP");
                    if(findMonster.getArmorPoints() > 0) {
                        System.out.println(findMonster.getCurrentArmor() + "/" + findMonster.getArmorPoints() + " Armor");
                    }
                    if (findMonster.getShieldPoints() > 0) {
                        System.out.println(findMonster.getCurrentShield() + "/" + findMonster.getShieldPoints() + " Magic Shield");
                    }
                    System.out.print("\n");
                }
            }
        }

        //For loop that prints the turn order (who goes first, who second, who third etc... So player can plan his next attack
    }

    private void processTurn(Character attacker, ArrayList<Character> foes, Player pc) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            boolean turnSuccessful = false;
            Scanner sc = new Scanner(System.in);

            while(turnSuccessful == false) {
                System.out.print("What do you wanna do?\n\n1 - Attack\n2 - Abilities\n3 - Items\n4 - Surrender\n>");

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

                        System.out.print(">");
                        String chooseTarget = sc.nextLine();

                        try{
                            int target = Integer.parseInt(chooseTarget);

                            if(target < 0 || target >= foes.size()) {
                                System.out.println("Target does not exist...");
                            }
                            else {
                                processAttack(attacker, foes, pc, target);
                                turnSuccessful = true;
                            }
                        }
                        catch (NumberFormatException ex) {
                            System.out.println("Please use only valid inputs (numbers).");
                        }
                        break;
                    case "2":
                        System.out.println("");
                }
            }
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {
            processAttack(attacker, foes, pc, 0);
        }
    }

    //So the way this mess of a method works, is that it processes any given attack by this scheme:
    //Every Move can have multiple attacks, those are iterated first
    //And every attack can have multiple projectiles, those are iterated second
    //While attacks hit the same target consecutively, projectiles spread to multiple targets
    private void processAttack(Character attacker, ArrayList<Character> foes, Player pc, int index) {

        //Checks how many attacks current attacker can perform. Repeats attack sequence accordingly
        for(int attacks = 0; attacks < attacker.getAttributes().getAttacks(); attacks++) {
            String[] attackText = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eigth", "Ninth", "Tenth"};

            if(attacker.getAttributes().getAttacks() > 1) {
                System.out.println(attackText[attacks] + " attack: ");
            }

            //For-loop that iterates through every projectile. If weapon is Melee, just performs loop once
            int     repeatLoop = calculateProjectileRepeat(attacker);   //calculates how many projectiles to shoot
            int     temporaryIndex = index;                             //needed for iteration of projectiles

            for(int projectiles = 0; projectiles < repeatLoop; projectiles++) {
                if(repeatLoop > 1) {
                    System.out.print(attackText[projectiles] + " projectile: ");
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }

                //Checks if attacker is accurate enough to hit the target. If not, attack fails
                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    if(didAttackMiss(attacker, foes.get(index))) {
                        continue;
                    }
                } else {
                    if (didAttackMiss(attacker, pc)) {
                        continue;
                    }
                }

                int     damage = calculateCriticalHitChance(attacker),  //calculates if attack is critical or regular
                        netDamage;                                      //variable to store the net damage (with damageReductions etc.)


                //Checks if attacker is Player or Computer to calculate netDamage and perform attack
                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    netDamage = calculateNetDamage(attacker, foes.get(index), damage);
                    executeAttack(attacker, foes.get(index), netDamage, foes, temporaryIndex);
                }
                else {
                    netDamage = calculateNetDamage(attacker, pc, damage);
                    executeAttack(attacker, pc, netDamage, foes, temporaryIndex);
                }

                if(battle_over) {
                    return;
                }

                if(temporaryIndex + 1 >= foes.size()) {
                    temporaryIndex = 0;
                }
                else {
                    temporaryIndex++;
                }
            }

            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //Checks if attack is critical or not
    private int calculateCriticalHitChance(Character attacker) {
        Random rn = new Random();
        double critProbability = rn.nextDouble();

        if (critProbability <= attacker.getAttributes().getCritChance()) {
            System.out.print("Critical hit! ");

            return (int) (attacker.getAttributes().getDamage() * attacker.getAttributes().getCritPercentage());
        }
        else {
            return attacker.getAttributes().getDamage();
        }
    }

    private boolean didAttackMiss(Character attacker, Character target) {
        Random rn = new Random();
        double hitChance = rn.nextDouble();
        if(hitChance > attacker.getAttributes().getAccuracy()) {
            System.out.println(attacker.getName() + "s attack missed!");
            return true;
        }

        hitChance = rn.nextDouble();
        if(hitChance <= target.getAttributes().getEvasion()) {
            System.out.print(target.getName() + " evaded the attack from " + attacker.getName());
            if(searchPassive(target, "Counter Attack")) {
                System.out.print(" and hit back with a counter attack! (" + target.getAttributes().getDamage() + " dmg)");
                attacker.updateHealth(-target.getAttributes().getDamage());
            }
            return true;
        }

        return false;
    }

    private int calculateNetDamage(Character attacker, Character target, int damage) {
        int         totalDamageReduction,
                    netDamage;

        DamageType  damageType;

        if(attacker.getType() == Character.CharacterType.PLAYER) {
            damageType = ((Player) attacker).getWeapon().getDamageType();
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER){
            damageType = ((Monster)attacker).getDamageType();
        }
        else {
            damageType = ((Boss)attacker).getDamageType();
        }

        switch(damageType) {
            case PHYSICAL:
                totalDamageReduction = (int) (damage * target.getAttributes().getDamageReduction());
                break;
            case MAGICAL:
                totalDamageReduction = (int) (damage * target.getAttributes().getMagicResistance());
                break;
            case UNIVERSAL:
                totalDamageReduction = (int) (damage * target.getAttributes().getDamageReduction() * target.getAttributes().getMagicResistance());
                break;
            default:
                totalDamageReduction = 0;
        }

        netDamage = damage - totalDamageReduction;

        return netDamage;
    }

    private int calculateProjectileRepeat(Character attacker) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            if(((Player)attacker).getWeapon().getWeaponType() == Weapon.WeaponType.PROJECTILE) {
                return attacker.getAttributes().getProjectiles();
            }
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {
            if(((Monster)attacker).getAttackRange() == AttackRange.RANGED) {
                return attacker.getAttributes().getProjectiles();
            }
        }
        else {
            if(((Boss)attacker).getAttackRange() == AttackRange.RANGED) {
                return attacker.getAttributes().getProjectiles();
            }
        }
        return 1;
    }

    private void executeAttack(Character attacker, Character target, int netDamage, ArrayList<Character> foes, int index) {
        int aoeDamage = (int) (attacker.getAttributes().getDamage() * attacker.getAttributes().getAoeDamage());//calculates aoeDamage based on Damage
        int aoeIndex = 0;

        if(attacker.getType() == Character.CharacterType.PLAYER) {
            inflictDamage(attacker, foes.get(index), foes, netDamage, false);
        }
        else {
            inflictDamage(attacker, target, foes, netDamage, false);
        }

        //This block executes Cleave damage(Area of Effect or "aoe") on all enemies if the attribute is present (above 0.0)
        if(attacker.getAttributes().getAoeDamage() > 0 && foes.size() > 1) {
            for (int i = 0; i < foes.size() - 1; i++) {

                //if statement that makes sure the primary target does not get hit with cleave damage
                if(aoeIndex == index) {
                    if(aoeIndex + 1 >= foes.size()) {
                        aoeIndex = 0;
                    }
                    else {
                        aoeIndex++;
                    }
                }

                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    inflictDamage(attacker, foes.get(aoeIndex), foes, aoeDamage, true);
                }
                else {
                    inflictDamage(attacker, target, foes, aoeDamage, true);
                }

                aoeIndex++;
            }
        }
    }

    private void inflictDamage(Character attacker, Character target, ArrayList<Character> foes, int netDamage, boolean cleave) {
        DamageType damageType;
        int totalDMG = netDamage;

        if(attacker.getType() == Character.CharacterType.PLAYER) {
            damageType = ((Player)attacker).getWeapon().getDamageType();
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {
            damageType = ((Monster)attacker).getDamageType();
        }
        else {
            damageType = ((Boss)attacker).getDamageType();
        }

        //This block exectues Attack on primary Target
        switch (damageType) {

            case PHYSICAL:

                if (target.getCurrentArmor() < netDamage && target.getCurrentArmor() > 0) {
                    netDamage = netDamage - target.getCurrentArmor();
                    target.updateArmor(-target.getCurrentArmor());
                } else if (target.getCurrentArmor() > netDamage) {
                    target.updateArmor(-netDamage);
                    netDamage = 0;
                }

                break;

            case MAGICAL:

                if (target.getCurrentShield() < netDamage && target.getCurrentShield() > 0) {
                    netDamage = netDamage - target.getCurrentShield();
                    target.updateShield(-target.getCurrentShield());
                } else if (target.getCurrentShield() > netDamage) {
                    target.updateShield(-netDamage);
                    netDamage = 0;
                }

                break;

            case UNIVERSAL:

                if (target.getCurrentShield() < netDamage && target.getCurrentShield() > 0) {
                    target.updateShield(-target.getCurrentShield());
                } else if (target.getCurrentShield() > netDamage) {
                    target.updateShield(-netDamage);
                }

                if (target.getCurrentArmor() < netDamage && target.getCurrentArmor() > 0) {
                    target.updateArmor(-target.getCurrentArmor());
                } else if (target.getCurrentArmor() > netDamage) {
                    target.updateArmor(-netDamage);
                }

                break;
        }

        if (netDamage > 0) {
            if (target.getCurrenthealth() <= netDamage) {
                if(searchPassive(attacker, "Lifesteal")) {
                    attacker.updateHealth((int) (target.getCurrenthealth() * 0.5));
                }

                System.out.print(attacker.getName() + " has slain " + target.getName());
                if(cleave) System.out.print(" with cleave");
                System.out.println("!");

                if(target.getType() == Character.CharacterType.PLAYER) {
                    battle_over = true;
                    target.updateHealth(-target.getCurrenthealth());
                }
                else {
                    target.updateHealth(-target.getCurrenthealth());
                    for(Character enemies : foes) {
                        if(enemies.getCurrenthealth() > 0) {
                            return;
                        }
                    }
                    foes.clear();
                    battle_over = true;
                }
            } else if(target.getCurrenthealth() > 0){
                if(searchPassive(attacker, "Lifesteal")) {
                    attacker.updateHealth((int) (netDamage * 0.5));
                }
                target.updateHealth(-netDamage);
                System.out.print(attacker.getName() + " has hit " + target.getName());
                if(cleave) System.out.print(" with cleave");
                System.out.println(" for " + totalDMG + " damage!");
            }
        }
    }

    private boolean searchPassive(Character character, String keyword) {
        for(Ability ability : character.getPassives()) {
            if (ability.abilityName.equals(keyword)) {
                return true;
            }
        }
        return false;
    }
}
