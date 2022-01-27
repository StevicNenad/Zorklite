package game;

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

        Scanner sc = new Scanner(System.in);
        if(foes.isEmpty()) {
            System.out.println("Threat eliminated! This room seems to be clear... What do you want to do next?");
            return false;
        }
        else {
            System.out.println("You fall into a pool of your own blood, your eyes slowly closing. Game over...");
            sc.nextLine();
            return true;
        }
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
                    System.out.println(" DEAD");
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
                System.out.print("What do you wanna do?\n\n1 - Attack\n2 - Abilities\n3 - Fortify\n4 - Heal\n>");

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
                }
            }
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {
            processBotAttack((Monster) attacker,  pc);
        }
    }

    //So the way this mess of a method works, is that it processes any given attack by this scheme:
    //Every Move can have multiple attacks, those are iterated first
    //And every attack can have multiple projectiles, those are iterated second
    //While attacks hit the same target consecutively, projectiles spread to multiple targets
    private void processAttack(Character attacker, ArrayList<Character> foes, Player pc, int index) {

        //Checks how many attacks current attacker can perform. Repeats attack sequence accordingly
        for(int attacks = 0; attacks < attacker.getAttributes().getAttacks(); attacks++) {

            //For-loop that iterates through every projectile. If weapon is Melee, just performs loop once
            int     repeatLoop = calculateProjectileRepeat(attacker);   //calculates how many projectiles to shoot
            int     temporaryIndex = index;                             //needed for iteration of projectiles

            for(int projectiles = 0; projectiles < repeatLoop; projectiles++) {

                //Checks if attacker is accurate enough to hit the target. If not, attack fails
                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    if(didAttackMiss(attacker, foes.get(index))) {
                        continue;
                    }
                }
                else {
                    if (didAttackMiss(attacker, pc)) {
                        continue;
                    }
                }

                int     damage = calculateCriticalHitChance(attacker),  //calculates if attack is critical or regular
                        netDamage;                                      //variable to store the net damage (with damageReductions etc.)


                //Checks if attacker is Player or Computer to calculate netDamage
                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    netDamage = calculateNetDamage(attacker, foes.get(index), damage);
                }
                else {
                    netDamage = calculateNetDamage(attacker, pc, damage);
                }

                executeAttack(attacker, netDamage, foes, temporaryIndex);

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
            System.out.println("Attack missed!");
            return true;
        }

        hitChance = rn.nextDouble();
        if(hitChance <= target.getAttributes().getEvasion()) {
            System.out.println(target.getName() + " evaded the attack from " + attacker.getName());
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

    private void executeAttack(Character attacker, int netDamage, ArrayList<Character> foes, int index) {
        int aoeDamage = (int) (attacker.getAttributes().getDamage() * attacker.getAttributes().getAoeDamage());//calculates aoeDamage based on Damage
        int aoeIndex = 0;
        DamageType damageType;

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

                if (foes.get(index).getCurrentArmor() < netDamage && foes.get(index).getCurrentArmor() > 0) {
                    netDamage = netDamage - foes.get(index).getCurrentArmor();
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s armor (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateArmor(-foes.get(index).getCurrentArmor());
                } else if (foes.get(index).getCurrentArmor() > netDamage) {
                    foes.get(index).updateArmor(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s armor for " + netDamage + " damage!");
                    netDamage = 0;
                }

                break;

            case MAGICAL:

                if (foes.get(index).getCurrentShield() < netDamage && foes.get(index).getCurrentShield() > 0) {
                    netDamage = netDamage - foes.get(index).getCurrentShield();
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s magic shield (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateShield(-foes.get(index).getCurrentShield());
                } else if (foes.get(index).getCurrentShield() > netDamage) {
                    foes.get(index).updateShield(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s magic shield for " + netDamage + " damage!");
                    netDamage = 0;
                }

                break;

            case UNIVERSAL:

                if (foes.get(index).getCurrentShield() < netDamage && foes.get(index).getCurrentShield() > 0) {
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s magic shield (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateShield(-foes.get(index).getCurrentShield());
                } else if (foes.get(index).getCurrentShield() > netDamage) {
                    foes.get(index).updateShield(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s magic shield for " + netDamage + " damage!");
                }

                if (foes.get(index).getCurrentArmor() < netDamage && foes.get(index).getCurrentArmor() > 0) {
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s armor (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateArmor(-foes.get(index).getCurrentArmor());
                } else if (foes.get(index).getCurrentArmor() > netDamage) {
                    foes.get(index).updateArmor(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s armor for " + netDamage + " damage!");

                }

                break;
        }

        if (netDamage > 0) {
            if (foes.get(index).getCurrenthealth() <= netDamage) {
                System.out.println("You have slain " + foes.get(index).getName() + "! (" + netDamage + " damage)");
                foes.remove(index);
                if(foes.isEmpty()) {
                    battle_over = true;
                    return;
                }
            } else {
                foes.get(index).updateHealth(-netDamage);
                System.out.println("You have hit " + foes.get(index).getName() + "s health for " + netDamage + " damage!");
            }
        }

        //This block executes Cleave damage(Area of Effect or "aoe") on all enemies if the attribute is present (above 0.0)
        if(attacker.getAttributes().getAoeDamage() > 0) {
            for (int i = 0; i < foes.size() - 1; i++) {
                netDamage = aoeDamage;

                //if statement that makes sure the primary target does not get hit with cleave damage
                if(aoeIndex == index) {
                    if(aoeIndex + 1 >= foes.size()) {
                        aoeIndex = 0;
                    }
                    else {
                        aoeIndex++;
                    }
                }

                //copy of the above code (absolute catastrophe)
                switch (damageType) {
                    case PHYSICAL:

                        if (foes.get(aoeIndex).getCurrentArmor() < netDamage && foes.get(aoeIndex).getCurrentArmor() > 0) {
                            netDamage = netDamage - foes.get(aoeIndex).getCurrentArmor();
                            System.out.println("You have destroyed " + foes.get(aoeIndex).getName() + "s armor with cleave damage (" + foes.get(aoeIndex).getCurrentArmor() + " damage)");
                            foes.get(aoeIndex).updateArmor(-foes.get(aoeIndex).getCurrentArmor());
                        } else if (foes.get(aoeIndex).getCurrentArmor() > netDamage) {
                            foes.get(aoeIndex).updateArmor(-netDamage);
                            System.out.println("You have hit " + foes.get(aoeIndex).getName() + "s armor with cleave for " + netDamage + " damage!");
                            netDamage = 0;
                        }

                        break;

                    case MAGICAL:

                        if (foes.get(aoeIndex).getCurrentShield() < netDamage && foes.get(aoeIndex).getCurrentShield() > 0) {
                            netDamage = netDamage - foes.get(aoeIndex).getCurrentShield();
                            System.out.println("You have destroyed " + foes.get(aoeIndex).getName() + "s magic shield with cleave damage (" + foes.get(aoeIndex).getCurrentArmor() + " damage)");
                            foes.get(aoeIndex).updateShield(-foes.get(aoeIndex).getCurrentShield());
                        } else if (foes.get(aoeIndex).getCurrentShield() > netDamage) {
                            foes.get(aoeIndex).updateShield(-netDamage);
                            System.out.println("You have hit " + foes.get(aoeIndex).getName() + "s magic shield with cleave for " + netDamage + " damage!");
                            netDamage = 0;
                        }

                        break;

                    case UNIVERSAL:

                        if (foes.get(aoeIndex).getCurrentArmor() < netDamage && foes.get(aoeIndex).getCurrentArmor() > 0) {
                            System.out.println("You have destroyed " + foes.get(aoeIndex).getName() + "s armor with cleave damage (" + foes.get(aoeIndex).getCurrentArmor() + " damage)");
                            foes.get(aoeIndex).updateArmor(-foes.get(aoeIndex).getCurrentArmor());
                        } else if (foes.get(aoeIndex).getCurrentArmor() > netDamage) {
                            foes.get(aoeIndex).updateArmor(-netDamage);
                            System.out.println("You have hit " + foes.get(aoeIndex).getName() + "s armor with cleave for " + netDamage + " damage!");
                        }

                        if (foes.get(aoeIndex).getCurrentShield() < netDamage && foes.get(aoeIndex).getCurrentShield() > 0) {
                            System.out.println("You have destroyed " + foes.get(aoeIndex).getName() + "s magic shield with cleave damage (" + foes.get(aoeIndex).getCurrentArmor() + " damage)");
                            foes.get(aoeIndex).updateShield(-foes.get(aoeIndex).getCurrentShield());
                        } else if (foes.get(aoeIndex).getCurrentShield() > netDamage) {
                            foes.get(aoeIndex).updateShield(-netDamage);
                            System.out.println("You have hit " + foes.get(aoeIndex).getName() + "s magic shield with cleave for " + netDamage + " damage!");
                        }
                }

                if (netDamage > 0) {
                    if (foes.get(aoeIndex).getCurrenthealth() <= netDamage) {
                        System.out.println("You have slain " + foes.get(aoeIndex).getName() + " with cleave damage! (" + netDamage + " damage)");
                        foes.get(aoeIndex).updateHealth(foes.get(aoeIndex).getCurrenthealth());
                    } else {
                        foes.get(aoeIndex).updateHealth(-netDamage);
                        System.out.println("You have hit " + foes.get(aoeIndex).getName() + "s health with cleave for " + netDamage + " damage!");
                    }
                }
            }
        }
    }

    private void inflictDamage(Character attacker, Character target, int netDamage, boolean cleave) {
        DamageType damageType;

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
                    System.out.println(attacker.getName() + " has destroyed " + target.getName() + "s armor (" + target.getCurrentArmor() + " damage)");
                    target.updateArmor(-target.getCurrentArmor());
                } else if (target.getCurrentArmor() > netDamage) {
                    target.updateArmor(-netDamage);
                    System.out.println(attacker.getName() + " has hit " + target.getName() + "s armor for " + netDamage + " damage!");
                    netDamage = 0;
                }

                break;

            case MAGICAL:

                if (foes.get(index).getCurrentShield() < netDamage && foes.get(index).getCurrentShield() > 0) {
                    netDamage = netDamage - foes.get(index).getCurrentShield();
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s magic shield (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateShield(-foes.get(index).getCurrentShield());
                } else if (foes.get(index).getCurrentShield() > netDamage) {
                    foes.get(index).updateShield(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s magic shield for " + netDamage + " damage!");
                    netDamage = 0;
                }

                break;

            case UNIVERSAL:

                if (foes.get(index).getCurrentShield() < netDamage && foes.get(index).getCurrentShield() > 0) {
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s magic shield (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateShield(-foes.get(index).getCurrentShield());
                } else if (foes.get(index).getCurrentShield() > netDamage) {
                    foes.get(index).updateShield(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s magic shield for " + netDamage + " damage!");
                }

                if (foes.get(index).getCurrentArmor() < netDamage && foes.get(index).getCurrentArmor() > 0) {
                    System.out.println("You have destroyed " + foes.get(index).getName() + "s armor (" + foes.get(index).getCurrentArmor() + " damage)");
                    foes.get(index).updateArmor(-foes.get(index).getCurrentArmor());
                } else if (foes.get(index).getCurrentArmor() > netDamage) {
                    foes.get(index).updateArmor(-netDamage);
                    System.out.println("You have hit " + foes.get(index).getName() + "s armor for " + netDamage + " damage!");

                }

                break;
        }

        if (netDamage > 0) {
            if (foes.get(index).getCurrenthealth() <= netDamage) {
                System.out.println("You have slain " + foes.get(index).getName() + "! (" + netDamage + " damage)");
                foes.remove(index);
                if(foes.isEmpty()) {
                    battle_over = true;
                    return;
                }
            } else {
                foes.get(index).updateHealth(-netDamage);
                System.out.println("You have hit " + foes.get(index).getName() + "s health for " + netDamage + " damage!");
            }
        }
    }

    private void processBotAttack(Monster attacker, Player player) {
        Random rn = new Random();
        double hitChance;
        double critProbability;

        //Checks how many attacks player can perform, repeats attack sequence accordingly
        for (int attacks = 0; attacks < attacker.getAttributes().getAttacks(); attacks++) {

            //Checks if player is accurate enough (accuracy attribute) if not, attack fails
            hitChance = rn.nextDouble();
            if (hitChance > attacker.getAttributes().getAccuracy()) {
                System.out.println(attacker.getName() + " attack has missed!");
                return;
            }

            //Checks if target can evade attack (evasion attribute) if so, attack fails
            hitChance = rn.nextDouble();
            if (hitChance <= player.getAttributes().getEvasion()) {
                System.out.println("You have evaded " + attacker.getName() + "s attack!");
                return;
            }


            int     damage,
                    totalDamageReduction,
                    netDamage;

            critProbability = rn.nextDouble();
            if (critProbability <= attacker.getAttributes().getCritChance()) {
                damage = (int) (attacker.getAttributes().getDamage() * attacker.getAttributes().getCritPercentage());
                System.out.print("Critical hit! ");
            } else {
                damage = attacker.getAttributes().getDamage();
            }

            //Checks what damage type the attack is, calculates the damage accordingly
            DamageType attackerDamageType = attacker.getDamageType();

            switch (attackerDamageType) {
                case PHYSICAL:
                    totalDamageReduction = (int) (damage * player.getAttributes().getDamageReduction());
                    break;
                case MAGICAL:
                    totalDamageReduction = (int) (damage * player.getAttributes().getMagicResistance());
                    break;
                case UNIVERSAL:
                    totalDamageReduction = (int) (damage * player.getAttributes().getMagicResistance() *
                            player.getAttributes().getDamageReduction());
                    break;
                default:
                    totalDamageReduction = 0;
            }
            netDamage = damage - totalDamageReduction;

            executeBotAttack(attacker, netDamage, player, attackerDamageType);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

    private void executeBotAttack(Monster attacker, int netDamage, Player player, DamageType damageType) {
        switch (damageType) {
            case PHYSICAL:

                if (player.getCurrentArmor() < netDamage && player.getCurrentArmor() > 0) {
                    netDamage = netDamage - player.getCurrentArmor();
                    System.out.println(attacker.getName() + " has destroyed your armor (" + player.getCurrentArmor() + " damage)");
                    player.updateArmor(-player.getCurrentArmor());
                } else if (player.getCurrentArmor() > netDamage) {
                    player.updateArmor(-netDamage);
                    System.out.println(attacker.getName() + " has hit your armor for " + netDamage + " damage!");
                    netDamage = 0;
                }

                break;

            case MAGICAL:

                if (player.getCurrentShield() < netDamage && player.getCurrentShield() > 0) {
                    netDamage = netDamage - player.getCurrentShield();
                    System.out.println(attacker.getName() + " has destroyed your magic shield (" + player.getCurrentArmor() + " damage)");
                    player.updateShield(-player.getCurrentShield());
                } else if (player.getCurrentShield() > netDamage) {
                    player.updateShield(-netDamage);
                    System.out.println(attacker.getName() + " has hit your magic shield for " + netDamage + " damage!");
                    netDamage = 0;
                }

                break;

            case UNIVERSAL:

                if (player.getCurrentShield() < netDamage && player.getCurrentShield() > 0) {
                    System.out.println(attacker.getName() + " has destroyed your magic shield (" + player.getCurrentArmor() + " damage)");
                    player.updateShield(-player.getCurrentShield());
                } else if (player.getCurrentShield() > netDamage) {
                    player.updateShield(-netDamage);
                    System.out.println(attacker.getName() + " has hit your magic shield for " + netDamage + " damage!");
                }

                if (player.getCurrentArmor() < netDamage && player.getCurrentArmor() > 0) {
                    System.out.println(attacker.getName() + " has destroyed your armor (" + player.getCurrentArmor() + " damage)");
                    player.updateArmor(-player.getCurrentArmor());
                } else if (player.getCurrentArmor() > netDamage) {
                    player.updateArmor(-netDamage);
                    System.out.println(attacker.getName() + " has hit your  armor for " + netDamage + " damage!");

                }

                break;
        }

        if (netDamage > 0) {
            if (player.getCurrenthealth() <= netDamage) {
                System.out.println("You have been slain by " + attacker.getName() + "! (" + netDamage + " damage)");
                player.updateHealth(-player.getCurrenthealth());
                battle_over = true;
                return;
            } else {
                player.updateHealth(-netDamage);
                System.out.println(attacker.getName() + " has hit your health for " + netDamage + " damage!");
            }
        }
    }
}
