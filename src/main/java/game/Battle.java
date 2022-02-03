package game;

import game.characters.Boss;
import game.characters.Monster;
import game.characters.Player;
import game.items.Weapon;

import java.util.*;

public class Battle {
    private int     turns,          //tracks total number of turns in the battle
                    playerTurns;    //tracks number of turns of player

    private boolean battle_over,
                    surrender,
                    playerPreemptiveStrike,
                    monsterPreemptiveStrike;

    public Battle() {
        turns = 0;
        playerTurns = 0;
        battle_over = false;
        playerPreemptiveStrike = false;
        monsterPreemptiveStrike = false;
    }

    public boolean startEncounter(Player pc, ArrayList<Character> foes) {
        battleIntro();

        while(pc.getCurrenthealth() != 0 && !foes.isEmpty()) {
            ArrayList<Character> queue = generateAttackOrder(pc, foes);
            turns++;

            int index = 0;  //needed to display queue
            for (Character attacker : queue) {
                if (attacker.getCurrenthealth() > 0) {
                    displayBattle(queue, index);
                    processTurn(attacker, foes, pc);
                }
                if (battle_over) {break;}

                if(index + 1 == queue.size()) {
                    index = 0;
                } else {
                    index++;
                }
            }
        }

        if(foes.isEmpty()) {
            System.out.println("Threat eliminated! What do you want to do next?");
            return false;
        }
        else {
            if(surrender) {
                System.out.println( "You drop your weapons and bend the knee, pleading for your life. Without mercy, the creatures start dragging\n" +
                                    "you into the darkest pits of the dungeon, while the echp of your screams fill the rooms. Game over...");
            }else {
                System.out.println("You fall into a pool of your own blood, your eyes slowly closing. Game over...");
            }

            pause(2000);

            return true;
        }
    }

    //Intro message when entering Battle.
    public void battleIntro() {
        System.out.println("Battle started! Deal with the threat before they deal with you.\n");
    }

    public void preemptiveStrikeSuccess(Player pc, ArrayList<Character> foes) {
        int critDamage = (int) (pc.getAttributes().getDamage() * pc.getAttributes().getCritPercentage());
        System.out.println("Preemptive Strike! You managed to sneak up on and damage your opponent.\n");
        for(Character enemy : foes) {
            if(critDamage > enemy.maxHealth) {
                enemy.setCurrentHealth(1);
            }
            else {
                inflictDamage(pc, enemy, foes, critDamage, false);
            }
        }
        playerPreemptiveStrike = true;
    }

    public void preemptiveStrikeFail(Player pc) {
        System.out.println("Sneak failed! The enemy has spotted and attacked you before you could react.\n");
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

    private void displayBattle(ArrayList<Character> queue, int index) {

        //For loop that prints the players HP, Armor and Shield values to the terminal
        for (Character findPlayer : queue) {
            if(findPlayer.getType() == Character.CharacterType.PLAYER) {
                System.out.print("YOU: " + findPlayer.getCurrenthealth() + "/" + findPlayer.getMaxHealth() + " HP");
                if(findPlayer.getArmorPoints() > 0) {
                    System.out.print("  |  " + findPlayer.getCurrentArmor() + "/" + findPlayer.getArmorPoints() + " Armor");
                }
                if (findPlayer.getShieldPoints() > 0) {
                    System.out.print("  |  " + findPlayer.getCurrentShield() + "/" + findPlayer.getShieldPoints() + " Magic Shield");
                }
                System.out.println("\n");
                break;
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
                    System.out.print(findMonster.getCurrenthealth() + "/" + findMonster.getMaxHealth() + " HP");
                    if(findMonster.getArmorPoints() > 0) {
                        System.out.print("  |  " + findMonster.getCurrentArmor() + "/" + findMonster.getArmorPoints() + " Armor");
                    }
                    if (findMonster.getShieldPoints() > 0) {
                        System.out.print("  |  " + findMonster.getCurrentShield() + "/" + findMonster.getShieldPoints() + " Magic Shield");
                    }
                    System.out.print("\n\n");
                }
            }
        }

        //For loop that prints the turn order (who goes first, who second, who third etc... So player can plan his next attack
        int count = index;
        System.out.println("Queue:");
        for(int i = 0; i < queue.size(); i++) {
            if (queue.get(count).getCurrenthealth() <= 0) {
                if(count + 1 >= queue.size()) {
                    count = 0;
                } else {
                    count++;
                }
                continue;
            }

            if(i > 0) System.out.print("  |  ");

            System.out.print(queue.get(count).getShortName());

            if(count + 1 >= queue.size()) {
                count = 0;
            } else {
                count++;
            }
        }
        System.out.print("\n\n");
    }

    private void processTurn(Character attacker, ArrayList<Character> foes, Player pc) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            boolean turnSuccessful = false;
            Scanner sc = new Scanner(System.in);
            playerTurns++;

            while(turnSuccessful == false) {
                System.out.print("What do you wanna do?\n\n1 - Attack\n2 - Abilities\n3 - Potion\n4 - Surrender\n>");

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

                            if(target < 0 || target >= foes.size() || foes.get(target).getCurrenthealth() <= 0) {
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
                        if(attacker.getActives().isEmpty()) {
                            System.out.println("No Abilities...");
                            pause(1000);
                        }else {
                            System.out.println("Abilities:");

                            index = 0;
                            for(Ability ability : attacker.getActives()) {
                                System.out.println(index + " - " + ability.getAbilityName());
                                index++;
                            }
                            System.out.print(">");

                            String chooseAbility = sc.nextLine();

                            try{
                                int ability = Integer.parseInt(chooseAbility);

                                if(ability < 0 || ability >= attacker.getActives().size()) {
                                    System.out.println("Ability does not exist...");
                                }
                                else {
                                    turnSuccessful = processAbility(attacker, foes, pc, attacker.getActives().get(ability));
                                }
                            }
                            catch (NumberFormatException ex) {
                                System.out.println("Please use only valid inputs (numbers).");
                            }
                        }
                        break;
                    case "3":
                        if(attacker.getPotions() == 0) {
                            System.out.println("No Potions...");
                            pause(1000);
                        } else {
                            //heal();
                        }
                        break;
                    case "4":
                        System.out.print("Do you really want to surrender? y/n\n>");
                        menuChoice = sc.nextLine();
                        if(menuChoice.toLowerCase().equals("y")) {
                            battle_over = true;
                            surrender = true;
                            pc.setCurrentHealth(0);
                            return;
                        }
                        break;
                    default:
                        System.out.println("Invalid input. (Only 1-4)");
                }
            }
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {
            processAttack(attacker, foes, pc, 0);
        }
    }

    private boolean processAbility(Character attacker, ArrayList<Character> foes, Player pc, Ability ability) {

        if(ability.isTargetedAbility()) {
            Scanner sc = new Scanner(System.in);
            int index = 0;

            System.out.println("Choose Target:");
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

                if(target < 0 || target >= foes.size() || foes.get(target).getCurrenthealth() <= 0) {
                    System.out.println("Target does not exist...");
                }
                else {
                    executeTargetedAbility(attacker, foes.get(target), ability);
                    return true;
                }
            }catch (NumberFormatException ex) {
                System.out.println("Please use only valid inputs (numbers).");
            }
        } else {
            return executeAbility(attacker, foes, ability);
        }

        return false;
    }

    private boolean executeAbility(Character attacker, ArrayList<Character> targets, Ability ability) {
        switch(ability.getAbilityName()) {
            case "Blade Jump":
                int     jumps = 5 + (attacker.getAttributes().getAgility() / 5),
                        jumpDamage;

                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    if(((Player) attacker).getWeapon().getWeaponType() == Weapon.WeaponType.PROJECTILE) {
                        System.out.println("The attack missed... perhaps you don't have the right Weapon type for this ability (Melee)");
                        return false;
                    }
                }

                for(int i = 0; i < jumps; i++) {
                    jumpDamage = (int) (attacker.getAttributes().getDamage() * attacker.getAttributes().getCritPercentage());
                    Random rn = new Random();
                    int index = rn.nextInt(targets.size()),
                        netDamage = calculateNetDamage(attacker, targets.get(index), jumpDamage);
                    System.out.println("With one fast leap you start slashing your foes!");
                    System.out.print("(" + (i+1) + "/" + jumps + ") Critical hit! ");
                    if(targets.get(index).getCurrenthealth() > 0) {
                        inflictDamage(attacker, targets.get(index), targets, netDamage, false);
                    }
                    pause(1000);
                }
                return true;

            case "Flash Bomb":
                for(Character target : targets) {
                    target.getAttributes().setEvasion(target.getAttributes().getEvasion() - 0.10);
                    target.getAttributes().setAccuracy(target.getAttributes().getAccuracy() - 0.10);
                    target.getAttributes().setCritChance(target.getAttributes().getCritChance() - 0.10);
                }
                System.out.println("The bomb explodes in a huge blinding light, lowering the stats of the enemies...");
                pause(1000);
                return true;

            case "Russian Roulette":
                Random rn = new Random();
                double chance = rn.nextDouble();

                if(chance <= 1.00) {
                    int randomTarget = rn.nextInt(targets.size() + 1),
                        damage;

                    String targetName;

                    if(randomTarget == 0) {
                        damage = attacker.getCurrenthealth();
                        attacker.updateHealth(-damage);
                        targetName = "himself";
                        battle_over = true;
                    }else {
                        damage = targets.get(randomTarget - 1).getCurrenthealth();
                        targets.get(randomTarget -1).updateHealth(-damage);
                        targetName = targets.get(randomTarget - 1).getName();
                    }
                    System.out.println(attacker.getName() + " has automatically executed " + targetName + " for " + damage + " damage!");
                } else {
                    System.out.println("The chamber was empty!");
                }
                pause(1000);
                return true;

            case "Fireball":
                int fireDamage = 250 + (attacker.getAttributes().getIntelligence() * 25);

                System.out.println("You channel and hurl a huge fireball towards the enemy");

                for(Character target : targets) {
                    inflictMagicDamage(attacker, target, fireDamage, false);
                }
                return true;

            case "Divine Light":
                int heal = 100 + (attacker.getAttributes().getIntelligence() * 10);

                System.out.println("A holy light shines and reinvigorates you! You restore " + heal + " hp.");

                attacker.updateHealth(heal);
                return true;

            case "Boston Shell":
                int armorRestore = 50 + (attacker.getAttributes().getAgility() * 2),
                    shieldRestore = 50 + (attacker.getAttributes().getIntelligence() * 2);

                System.out.println("You take a step back and strengthen your defenses! +" + armorRestore + " armor, +" + shieldRestore + " shield");

                attacker.updateShield(shieldRestore);
                attacker.updateArmor(armorRestore);
                return true;
        }
        return false;
    }

    private void executeTargetedAbility(Character attacker, Character target, Ability ability) {
        switch(ability.getAbilityName()) {
            case "Vitality Swap":
                double  attackerHealthPercentage = attacker.currentHealth / attacker.maxHealth,
                        targetHealthPercentage = target.currentHealth / attacker.maxHealth;

                attacker.setCurrentHealth((int)(attacker.getMaxHealth() * attackerHealthPercentage));
                target.setCurrentHealth((int)(attacker.getMaxHealth() * targetHealthPercentage));

                break;
            case "Soul Siphon":
                int siphonDamage = 100 + (10 * attacker.getAttributes().getIntelligence());
                inflictMagicDamage(attacker, target, siphonDamage, true);

                break;
            case "Reckless Charge":
                int     chargeDamage = 150 + (20 * attacker.getAttributes().getStrength()),
                        chargeSelfDamage = (int) (chargeDamage * 0.25);

                inflictMagicDamage(attacker, target, chargeDamage, false);

                if(attacker.getCurrenthealth() < chargeSelfDamage) {
                    attacker.setCurrentHealth(1);
                }
                else{
                    attacker.updateHealth(chargeSelfDamage);
                }

                break;
            case "Hail Mary":
                int maryDamage = (int) (target.getMaxHealth() * 0.25) * (1 - (attacker.getCurrenthealth() / attacker.getMaxHealth()));

                inflictMagicDamage(attacker, target, maryDamage, false);

                break;
        }
    }

    private void inflictMagicDamage(Character attacker, Character target, int damage, boolean succ) {
        int netDamage = damage;

        if(target.getCurrentShield() > 0) {
            if(target.getCurrentShield() > netDamage) {
                target.updateShield(-netDamage);
                netDamage = 0;
            }
            else {
                netDamage -= target.getCurrentShield();
                target.updateShield(-target.getCurrentShield());
            }
        } else {
            if(target.getCurrenthealth() > netDamage) {
                target.updateHealth(-netDamage);
                if(succ) attacker.updateHealth(netDamage);
            }
            else {
                if(succ) attacker.updateHealth(target.getCurrenthealth());
                target.setCurrentHealth(0);
            }
        }
    }

    private void processAttack(Character attacker, ArrayList<Character> foes, Player pc, int index) {

        //Processes passive "Extra Attack
        if(searchPassive(attacker, "Extra Attack") && playerTurns != 1) {
            if(playerTurns % 2 == 0) {
                attacker.getAttributes().setAttacks(attacker.getAttributes().getAttacks() + 1);
            }
            else {
                if(attacker.getAttributes().getAttacks() > 1) {
                    attacker.getAttributes().setAttacks(attacker.getAttributes().getAttacks() - 1);
                }
                else {
                    attacker.getAttributes().setAttacks(1);
                }
            }
        }

        //Checks how many attacks current attacker can perform. Repeats attack sequence accordingly
        for(int attacks = 0; attacks < attacker.getAttributes().getAttacks(); attacks++) {
            String[] attackText = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eigth", "Ninth", "Tenth"};

            //if attacker or target has no health left the attack is aborted
            if(attacker.getCurrenthealth() <= 0 || foes.get(index).getCurrenthealth() <= 0) {
                return;
            }

            //Additional text that appears when attacker can hit multiple times
            if(attacker.getAttributes().getAttacks() > 1) {
                System.out.println(attackText[attacks] + " attack: ");
            }

            //For-loop that iterates through every projectile. If weapon is Melee, just performs loop once
            int     repeatLoop = calculateProjectileRepeat(attacker);   //calculates how many projectiles to shoot
            int     temporaryIndex = index;                             //needed for iteration of projectiles

            for(int projectiles = 0; projectiles < repeatLoop; projectiles++) {
                if(repeatLoop > 1) {
                    System.out.print(attackText[projectiles] + " projectile: ");
                    pause(1000);
                }

                //Checks if attacker is accurate enough to hit the target. If not, attack fails
                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    if(didAttackMiss(attacker, foes.get(index), foes)) {
                        continue;
                    }
                } else {
                    if (didAttackMiss(attacker, pc, foes)) {
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

            pause(2000);
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

    private boolean didAttackMiss(Character attacker, Character target, ArrayList<Character> foes) {
        Random rn = new Random();
        double hitChance = rn.nextDouble();
        if(hitChance > attacker.getAttributes().getAccuracy()) {
            System.out.println(attacker.getName() + "s attack missed!");
            return true;
        }

        hitChance = rn.nextDouble();
        if(hitChance <= target.getAttributes().getEvasion()) {
            System.out.print(target.getName() + " evaded the attack from " + attacker.getName());

            //Processes the passive ability "Counter Attack"
            if(searchPassive(target, "Counter Attack")) {
                int damage = target.getAttributes().getDamage();

                System.out.println(" and hit back with a counter attack");
                damage = calculateNetDamage(target, attacker, damage);
                inflictDamage(target, attacker, foes, damage, false);
                for(Character enemy : foes) {
                    if(enemy.getCurrenthealth() > 0) {
                        return true;
                    }
                }
                foes.clear();
                battle_over = true;
            }
            System.out.println("!");
            return true;
        }

        return false;
    }

    private int calculateNetDamage(Character attacker, Character target, int damage) {
        int         totalDamageReduction,
                    netDamage,
                    bonusDamage;

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

        //Processes passive ability "escalating violence"
        bonusDamage = processEscalatingViolenceDamage(attacker, target);

        damage += bonusDamage;

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

        netDamage = damage  - totalDamageReduction;

        return netDamage;
    }

    private int calculateProjectileRepeat(Character attacker) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            if(((Player)attacker).getWeapon().getWeaponType() == Weapon.WeaponType.PROJECTILE) {
                return attacker.getAttributes().getProjectiles();
            }else {
                return 1;
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
                    continue;
                }

                if(attacker.getType() == Character.CharacterType.PLAYER && foes.get(aoeIndex).getCurrenthealth() > 0) {
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

        if(searchPassive(attacker, "Divine Combustion")){
            System.out.println("Your strike explodes and causes 35 magical damage around the target");
            for (Character enemy : foes) {
                inflictMagicDamage(attacker, enemy, 35, false);
            }
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

                //Process passive ability "Lifesteal"
                if(searchPassive(attacker, "Lifesteal")) {
                    attacker.updateHealth((int) (target.getCurrenthealth() * 0.5));
                }

                System.out.print(attacker.getName() + " has slain " + target.getName());
                if(cleave) System.out.print(" with cleave");
                System.out.println("!");

                //Process passive ability "Hydro Touch"
                stealAttribute(attacker, target, "Hydro Touch", cleave);

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
                //Process passive ability "Lifesteal"
                if(searchPassive(attacker, "Lifesteal")) {
                    attacker.updateHealth((int) (netDamage * 0.5));
                }
                target.updateHealth(-netDamage);
                System.out.print(attacker.getName() + " has hit " + target.getName());
                if(cleave) System.out.print(" with cleave");
                System.out.println(" for " + totalDMG + " damage!");

                //Processes passive ability "Hydro Touch"
                stealAttribute(attacker, target, "Hydro Touch", cleave);
            }
        }
        else {
            System.out.print(attacker.getName() + " has hit " + target.getName());
            if(cleave) System.out.print(" with cleave");
            System.out.println(" for " + totalDMG + " damage!");
        }
    }

    private void stealAttribute(Character attacker, Character target, String keyword, boolean cleave) {

        if(searchPassive(attacker, keyword) && !cleave) {
            Random rn = new Random();
            int rando = rn.nextInt(7);

            System.out.print(attacker.getName() + " has stolen 1 ");

            switch (rando) {
                case 0:
                    target.getAttributes().setStrength(target.getAttributes().getStrength() - 1);
                    attacker.getAttributes().setStrength(attacker.getAttributes().getStrength() + 1);
                    System.out.println("strength");
                    break;
                case 1:
                    target.getAttributes().setAgility(target.getAttributes().getAgility() - 1);
                    attacker.getAttributes().setAgility(attacker.getAttributes().getAgility() + 1);
                    System.out.println("agility");
                    break;
                case 2:
                    target.getAttributes().setIntelligence(target.getAttributes().getIntelligence() - 1);
                    attacker.getAttributes().setIntelligence(attacker.getAttributes().getIntelligence() + 1);
                    System.out.println("intelligence");
                    break;
                case 3:
                    target.getAttributes().setSpeed(target.getAttributes().getSpeed() - 1);
                    attacker.getAttributes().setSpeed(attacker.getAttributes().getSpeed() + 1);
                    System.out.println("speed");
                    break;
                case 4:
                    target.getAttributes().setPerception(target.getAttributes().getPerception() - 1);
                    attacker.getAttributes().setPerception(attacker.getAttributes().getPerception() + 1);
                    System.out.println("perception");
                    break;
                case 5:
                    target.getAttributes().setDamage(target.getAttributes().getDamage() - 1);
                    attacker.getAttributes().setDamage(attacker.getAttributes().getDamage() + 1);
                    System.out.println("damage");
                    break;
                case 6:
                    target.getAttributes().setStealth(target.getAttributes().getStealth() - 1);
                    attacker.getAttributes().setStealth(attacker.getAttributes().getStealth() + 1);
                    System.out.println("stealth");
                    break;
            }

            target.recalculateStats();
            attacker.recalculateStats();
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

    private int processEscalatingViolenceDamage(Character attacker, Character target) {

        if(searchPassive(attacker, "Escalating Violence")) {
            for(Ability passive : attacker.getPassives()) {
                if(passive.getAbilityName().equals("Escalating Violence")) {
                    passive.calculateBonusDamage(target);
                    return passive.getBonusDamage();
                }
            }
        }
        return 0;
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
