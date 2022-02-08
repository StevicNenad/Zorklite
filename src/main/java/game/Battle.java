package game;

import game.characters.Boss;
import game.characters.Monster;
import game.characters.Player;
import game.items.Weapon;
import game.rooms.BossRoom;

import java.util.*;

public class Battle {

    private int     turns,          //tracks total number of turns in the battle. Not used but useful for future expansions.
                    playerTurns,    //tracks number of turns of player. Important for a certain passive (future expansions possible)
                    totalTokens;

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

    public void preemptiveStrikeSuccess(Player player, ArrayList<Character> enemies) {
        int critDamage = (int) (player.getAttributes().getDamage() * player.getAttributes().getCritPercentage());
        DamageType damageType = attackerDamageType(player);
        System.out.println("Preemptive Strike! You managed to sneak up on and damage your opponent.\n");
        for(Character enemy : enemies) {
            if(critDamage > enemy.maxHealth) {
                enemy.setCurrentHealth(1);
            }
            else {
                inflictDamage(player, enemy, critDamage, false, false, damageType);
            }
        }
        playerPreemptiveStrike = true;
    }

    public void preemptiveStrikeFail(Player player) {
        System.out.println("Sneak failed! The enemy has spotted and attacked you before you could react.\n");
        player.updateHealth(-(player.getMaxHealth() / 4));
        if(player.getCurrenthealth() <= 0) player.setCurrentHealth(1);
        monsterPreemptiveStrike = true;
    }

    public boolean startEncounter(Player player, ArrayList<Character> enemies, Room currentRoom) {
        battleIntro(currentRoom);
        ArrayList<Character> battleQueue = generateBattleQueue(player, enemies, currentRoom);

        //Main battle routine
        do {
            int index = 0;
            for (Character attacker : battleQueue) {
                if(attacker.getCurrenthealth() > 0) {
                    displayBattleInformation(battleQueue, index);
                    processTurn(attacker, player, enemies, currentRoom);
                }

                if(battle_over) break;

                if(index + 1 == battleQueue.size()) {
                    index = 0;
                } else {
                    index++;
                }
                turns++;
            }
        } while (!battle_over);


        //Checks what ended the battle. Returns game over true or false
        if(player.getCurrenthealth() <= 0) {

            if(surrender) {
                System.out.println("You drop your weapons and bend the knee, pleading for your life. Without mercy, the creatures start dragging\n" +
                        "you into the darkest pits of the dungeon, while the echo of your screams is the only thing filling these halls. " +
                        "Game over...");
            } else {
                System.out.println("You fall into a pool of your own blood, your eyes slowly closing. Game over...");
            }

            player.updateDeaths(1);
            pause(2000);

            return true;
        } else {
            boolean loot = false;

            System.out.print("Threat eliminated! You gained " + totalTokens + " Death Tokens! ");

            for (Character enemy : enemies) {
                if(!enemy.getLoot().isEmpty()) loot = true;
                currentRoom.getLoot().addAll(enemy.getLoot());
            }
            enemies.clear();

            if(loot) {
                System.out.print("The enemy also dropped some items (type \"look\" to view). ");
            }
            System.out.println("What do you want to do next?");
            pause(1000);

            return false;
        }
    }

    public void battleIntro(Room currentRoom) {
        Scanner sc = new Scanner(System.in);

        switch(currentRoom.getRoomType()) {

            case MONSTER:

                if(playerPreemptiveStrike) {
                    System.out.println("You catch the enemy by surprise! You manage to critically hit your opponent and go first!\n");
                } else if(monsterPreemptiveStrike) {
                    System.out.println("You were spotted! You take 25% damage of your health and attack last...\n");
                } else {
                    System.out.println("Battle started! Deal with the threat before they deal with you.\n");
                }
                pause(2000);
                break;

            case BOSS:

                switch(((BossRoom) currentRoom).getBossType()) {

                    case NEPHILIM:
                        System.out.println("You enter a brightly lit hall. A long dining table stretches for the full length of the room.\n" +
                                "At the other end, you see a creature with big, dark wings.\n");
                        System.out.println("\"Impressive. You have made it this far. It's been a long while since I could bathe my blade in blood.\n" +
                                "How long have I lusted for this moment...\"\n");
                        System.out.println("Before you can answer, the creature leaps towards you with it's weapon drawn. Prepare to fight!");
                        break;

                    case UNDERLORD:
                        System.out.println("A terrible stench hits you as you enter this room. In the middle of it stands a huge entitiy, it's body full of scars.\n");
                        System.out.println("\"I heard the rumblings of a new, human prey making it's way through our domain. It seems you forgot your place.\n" +
                                "It's high time someone teach you\"");
                        System.out.println("The ground trembles as the Monster makes it's way towards you... No way back now!");
                        break;

                    case FALSEGOD:
                        System.out.println("As you enter the room, you can't quite explain what you see. The walls are made of flesh, the floor drenched in blood.\n" +
                                "At the far end you see a disgusting abomination, with dozens of limbs, holding a blue glowing blade.");
                        System.out.println("\"What are you? Why was i brought here? What the hell do you want?\", you scream in it's direction, but you get no answer.\n" +
                                "As if by some twisted magic, you see the creature levitate high up in the air, a dark aura surrounding it.\n" +
                                "Before you can do anything, everything around you has been consumed by a black void. The only thing left is you and it.");
                        break;
                }

                sc.nextLine();
                break;
        }
    }

    //Function that puts all characters in an array and sorts them by the attribute "Speed". The character with the highest Speed attacks first.
    //Exception: When fighting with a Boss, the Boss ALWAYS goes first.
    private ArrayList<Character> generateBattleQueue(Player player, ArrayList<Character> enemies, Room currentRoom) {
        ArrayList<Character> participants = new ArrayList<Character>();
        participants.addAll(enemies);
        participants.add(player);

        if(currentRoom.getRoomType() == Room.RoomType.MONSTER) {
            Collections.sort(participants, new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    if(o1.getType() == Character.CharacterType.PLAYER) {
                        return o2.getAttributes().getSpeed() - (o1.getAttributes().getSpeed() + ((Player) o1).getBonusSpeed());
                    } else if(o2.getType() == Character.CharacterType.PLAYER) {
                        return (o2.getAttributes().getSpeed() + ((Player) o2).getBonusSpeed()) - o1.getAttributes().getSpeed();
                    } else {
                        return o2.getAttributes().getSpeed() - o1.getAttributes().getSpeed();
                    }
                }
            });

            if(playerPreemptiveStrike) {
                participants.remove(player);
                participants.add(0, player);
                playerPreemptiveStrike = false;
            } else if(monsterPreemptiveStrike) {
                participants.remove(player);
                participants.add(player);
                monsterPreemptiveStrike = false;
            }
        }

        return participants;
    }

    private void displayBattleInformation(ArrayList<Character> queue, int index) {
        int timeDelay;
        if(turns == 0) {
            timeDelay = 350;
        } else {
            timeDelay = 25;
        }

        //For loop that prints the players HP, Armor and Shield values to the terminal
        for (Character findPlayer : queue) {
            if(findPlayer.getType() == Character.CharacterType.PLAYER) {
                System.out.println("YOU: ");
                pause(timeDelay);
                System.out.print(findPlayer.getCurrenthealth() + "/" + findPlayer.getMaxHealth() + " HP");
                pause(timeDelay);
                System.out.print("  |  " + findPlayer.getCurrentMana() + "/" + findPlayer.getMaxMana() + " Mana");
                pause(timeDelay);
                if(findPlayer.getArmorPoints() > 0) {
                    System.out.print("  |  " + findPlayer.getCurrentArmor() + "/" + findPlayer.getArmorPoints() + " Armor");
                    pause(timeDelay);
                }
                if(findPlayer.getShieldPoints() > 0) {
                    System.out.print("  |  " + findPlayer.getCurrentShield() + "/" + findPlayer.getShieldPoints() + " Magic Shield");
                    pause(timeDelay);
                }
                System.out.println("\n");
                pause(timeDelay);
                break;
            }
        }

        //For loop that prints all the monsters and bosses HP, Armor and Shield values to the terminal
        for (Character findMonster : queue) {
            if(findMonster.getType() == Character.CharacterType.MONSTER) {
                System.out.println(findMonster.getName());

                if(findMonster.getCurrenthealth() <= 0) {
                    System.out.println("DEAD\n");
                } else {
                    System.out.print(findMonster.getCurrenthealth() + "/" + findMonster.getMaxHealth() + " HP");
                    if(findMonster.getArmorPoints() > 0) {
                        System.out.print("  |  " + findMonster.getCurrentArmor() + "/" + findMonster.getArmorPoints() + " Armor");
                    }
                    if(findMonster.getShieldPoints() > 0) {
                        System.out.print("  |  " + findMonster.getCurrentShield() + "/" + findMonster.getShieldPoints() + " Magic Shield");
                    }
                    System.out.print("\n\n");
                }
                pause(timeDelay);
            }
            else if(findMonster.getType() == Character.CharacterType.BOSS) {
                System.out.println(findMonster.getName());
                pause(timeDelay);
                System.out.print(findMonster.getCurrenthealth() + "/" + findMonster.getMaxHealth() + " HP");
                pause(timeDelay);
                if(findMonster.getArmorPoints() > 0) {
                    System.out.print("  |  " + findMonster.getCurrentArmor() + "/" + findMonster.getArmorPoints() + " Armor");
                    pause(timeDelay);
                }
                if(findMonster.getShieldPoints() > 0) {
                    System.out.print("  |  " + findMonster.getCurrentShield() + "/" + findMonster.getShieldPoints() + " Magic Shield");
                    pause(timeDelay);
                }
                System.out.print("\n\n");
            }
        }

        //For loop that prints the turn order (who goes first, who second, who third etc... So player can plan his next attack
        int count = index;

        System.out.println("Queue:");
        for (int i = 0; i < queue.size(); i++) {

            if(queue.get(count).getCurrenthealth() <= 0) {

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
            pause(timeDelay);
        }
        System.out.print("\n\n");
    }

    private void processTurn(Character attacker, Player player, ArrayList<Character> enemies, Room currentRoom) {

        if(attacker.getType() == Character.CharacterType.PLAYER) {
            Scanner sc = new Scanner(System.in);
            boolean turnSuccessful = false; //Tracks if the player has performed a valid action this turn
            playerTurns++;
            attacker.updateMana(5); //Player restores 5 mana each turn

            while (turnSuccessful == false) {
                System.out.print("What do you wanna do?\n\n1 - Attack\n2 - Abilities\n3 - Potion\n4 - Surrender\n>");

                String menuChoice = sc.nextLine();

                switch(menuChoice) {
                    case "1":
                        Character target;

                        if(enemies.size() > 1) {
                            System.out.println("Attack:");
                            int index = 0;

                            for (Character enemy : enemies) {
                                if(enemy.getCurrenthealth() > 0) {
                                    System.out.println(index + " - " + enemy.getName());
                                }
                                index++;
                            }

                            System.out.print(">");
                            String chooseTarget = sc.nextLine();

                            try {
                                int targetIndex = Integer.parseInt(chooseTarget);

                                if(targetIndex < 0 || targetIndex >= enemies.size() || enemies.get(targetIndex).getCurrenthealth() <= 0) {
                                    System.out.println("Target does not exist...");
                                }
                                else {
                                    target = enemies.get(targetIndex);
                                    processAttack(attacker, target, enemies);
                                    turnSuccessful = true;
                                }
                            }
                            catch (NumberFormatException ex) {
                                System.out.println("Please use only valid inputs (numbers).");
                            }
                        }

                        else {
                            target = enemies.get(0);
                            processAttack(attacker, target, enemies);
                            turnSuccessful = true;
                        }
                        break;

                    case "2":

                        if(attacker.getActives().isEmpty()) {
                            System.out.println("No Abilities...");
                            pause(1000);

                        } else {
                            System.out.println("Abilities:");

                            int index = 0;
                            for (Ability ability : attacker.getActives()) {
                                System.out.println(index + " - " + ability.getAbilityName() + " | " + ability.getManaReq() + " Mana");
                                index++;
                            }
                            System.out.print(">");

                            String chooseAbility = sc.nextLine();

                            try {
                                int abilityIndex = Integer.parseInt(chooseAbility);

                                if(abilityIndex < 0 || abilityIndex >= attacker.getActives().size()) {
                                    System.out.println("Ability does not exist...");
                                    pause(1000);
                                }
                                else if(attacker.getCurrentMana() > attacker.getActives().get(abilityIndex).getManaReq()) {

                                    attacker.updateMana(-attacker.getActives().get(abilityIndex).getManaReq());
                                    turnSuccessful = processAbility(attacker, player, enemies, attacker.getActives().get(abilityIndex));
                                }
                                else {
                                    System.out.println("Not enough mana...");
                                    pause(1000);
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
                        }

                        else {
                            int healAmount = (int) (attacker.getMaxHealth() * 0.33);
                            attacker.updateHealth(healAmount);
                            System.out.println("You have used a potion and restored " + healAmount + " HP!");
                            attacker.updatePotions(-1);
                        }

                        pause(1500);
                        turnSuccessful = true;
                        break;

                    case "4":
                        System.out.print("Do you really want to surrender? y/n\n>");
                        menuChoice = sc.nextLine();

                        if(menuChoice.toLowerCase().equals("y")) {
                            battle_over = true;
                            surrender = true;
                            player.setCurrentHealth(0);
                            return;
                        }

                        break;
                    default:
                        System.out.println("Invalid input. (Only 1-4)");
                }
            }
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {

            processAttack(attacker, player, enemies);
        }
        else {

            switch(attacker.getShortName()) {
                case "Iri":
                    nephilimCombatLogic(attacker, player);
                    break;

                case "Ret":
                    underlordCombatLogic(attacker, player);
                    break;

                case "God":
                    godCombatLogic(attacker, player);
                    break;
            }
        }
    }

    private void processAttack(Character attacker, Character target, ArrayList<Character> enemies) {

        //cls();
        //Checks if passive "Extra Attack" is available and executes it
        if(searchPassive(attacker, "Extra Attack") && playerTurns != 1) {
            int attacks = attacker.getAttributes().getAttacks();

            if(playerTurns % 2 == 0) {
                attacks++;
                attacker.getAttributes().setAttacks(attacks);
            } else {
                if(attacks > 1) {
                    attacks -= 1;
                    attacker.getAttributes().setAttacks(attacks);
                } else {
                    attacker.getAttributes().setAttacks(1);
                }
            }
        }

        //Checks how many attacks current attacker can perform. Repeats attack sequence accordingly
        for (int attacks = 0; attacks < attacker.getAttributes().getAttacks(); attacks++) {
            DamageType damageType = attackerDamageType(attacker);

            //if attacker or target has no health left the attack is aborted
            if(attacker.getCurrenthealth() <= 0 || target.getCurrenthealth() <= 0) {
                return;
            }

            //Additional text that appears when attacker can hit multiple times
            String[] attackText = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eigth", "Ninth", "Tenth"};
            if(attacker.getAttributes().getAttacks() > 1) {
                System.out.println(attackText[attacks] + " attack: ");
            }

            //For-loop that iterates through every projectile. If weapon is Melee, just performs loop once
            int projectiles = returnProjectiles(attacker);   //calculates how many projectiles to shoot
            int enemyIndex = 0,
                    attackIndex = 0;

            if(projectiles > 0) {

                //Additional text that appears when attacker shoots multiple projectiles
                if(projectiles > 1 && attacker.getType() == Character.CharacterType.PLAYER) {
                    System.out.print(attackText[attackIndex] + " projectile: ");
                    pause(1000);
                    attackIndex++;
                }

                if(didAttackMiss(attacker, target, enemies)) {
                    continue;
                }

                int damage = calculateDamage(attacker, target);

                inflictDamage(attacker, target, damage, false, false, damageType);

                if(attacker.getType() != Character.CharacterType.PLAYER) break;

                for (Character enemy : enemies) {
                    if(enemyIndex == projectiles) {
                        return;
                    }
                    if(enemy == target) {
                        enemyIndex++;
                        continue;
                    }

                    System.out.print(attackText[attackIndex] + " projectile: ");
                    pause(1000);
                    attackIndex++;

                    damage = calculateDamage(attacker, target);
                    inflictDamage(attacker, target, damage, false, false, damageType);

                    if(battle_over) {
                        return;
                    }

                    enemyIndex++;

                }
            } else {

                if(didAttackMiss(attacker, target, enemies)) {
                    continue;
                }

                int damage = calculateDamage(attacker, target);

                inflictDamage(attacker, target, damage, false, false, damageType);

                if(attacker.getAttributes().getAoeDamage() > 0 && attacker.getType() == Character.CharacterType.PLAYER) {
                    for (Character enemy : enemies) {
                        if(enemy == target) {
                            continue;
                        }
                        else if(enemy.getCurrenthealth() > 0) {
                            inflictDamage(attacker, enemy, damage, false, true, damageType);
                        }
                    }
                }
            }

            pause(1000);

            //Processes the passive ability "Divine Combustion"
            if(searchPassive(attacker, "Divine Combustion")) {
                System.out.println("Your strike explodes and causes 35 magical damage around the target!");
                for (Character enemy : enemies) {
                    int netDamage = 35;
                    if(enemy.getCurrentShield() > 0 && enemy.getCurrenthealth() > 0) {
                        netDamage = attackShield(attacker, enemy, netDamage);
                        if(netDamage > 0) {
                            attackHealth(attacker, enemy, netDamage, true, false);
                        }
                    } else if(enemy.getCurrenthealth() > 0) {
                        attackHealth(attacker, enemy, netDamage, true, false);
                    }
                }
            }

            checkEnemiesCleared(enemies);
        }
        pause(500);
    }


    private void inflictDamage(Character attacker, Character target, int damage, boolean spell, boolean cleave, DamageType damageType) {
        if (cleave) damage = (int) (damage * attacker.getAttributes().getAoeDamage());
        int totalDMG = damage;

        switch(damageType) {
            case PHYSICAL:
                damage = attackArmor(attacker, target, damage);
                break;
            case MAGICAL:
                damage = attackShield(attacker, target, damage);
                break;
            case UNIVERSAL:
                damage = attackShield(attacker, target, damage);
                damage = attackArmor(attacker, target, damage);
                break;
        }

        if(damage > 0) {
            attackHealth(attacker, target, damage, spell, cleave);

            if(target.getCurrenthealth() == 0) {
                System.out.print(attacker.getName() + " has slain " + target.getName());
                if(cleave) System.out.print(" with cleave");
                System.out.println("! (" + totalDMG + ")");
                pause(1000);

            }
            else {
                System.out.print(attacker.getName() + " has hit " + target.getName());
                if(cleave) System.out.print(" with cleave");
                System.out.println(" for " + totalDMG + " damage!");
                pause(1000);

            }
            //Processes passive ability "Hydro TouchHydro Touch"
            if(!spell) {
                stealAttribute(attacker, target, "Hydro Touch", cleave);
            }
        }
        else {
            System.out.print(attacker.getName() + " has hit " + target.getName());
            if(cleave) System.out.print(" with cleave");
            System.out.println(" for " + totalDMG + " damage!");
            pause(1000);
        }
    }

    private DamageType attackerDamageType(Character attacker) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            return ((Player)attacker).getWeapon().getDamageType();
        } else if (attacker.getType() == Character.CharacterType.MONSTER) {
            return ((Monster)attacker).getDamageType();
        } else {
            return ((Boss)attacker).getDamageType();
        }
    }


    //Function that calculates total damage, with armor/magic reductions and critical hit chance
    private int calculateDamage(Character attacker, Character target) {
        int fullDamage,
                totalDamageReduction,
                netDamage,
                bonusDamage;

        //This block calculates Crit Chance
        Random rn = new Random();
        double critProbability = rn.nextDouble();

        if(critProbability <= attacker.getAttributes().getCritChance()) {
            System.out.print("Critical hit! ");

            fullDamage = (int) (attacker.getAttributes().getDamage() * attacker.getAttributes().getCritPercentage());
        } else {
            fullDamage = attacker.getAttributes().getDamage();
        }

        //Processes passive ability "escalating violence"
        bonusDamage = processEscalatingViolenceDamage(attacker, target);
        fullDamage += bonusDamage;


        //Calculates damage reduction based on DamageType of attacker
        DamageType damageType = attackerDamageType(attacker);

        switch(damageType) {
            case PHYSICAL:
                totalDamageReduction = (int) (fullDamage * target.getAttributes().getDamageReduction());
                break;
            case MAGICAL:
                totalDamageReduction = (int) (fullDamage * target.getAttributes().getMagicResistance());
                break;
            case UNIVERSAL:
                totalDamageReduction = (int) (fullDamage * target.getAttributes().getDamageReduction() * target.getAttributes().getMagicResistance());
                break;
            default:
                totalDamageReduction = 0;
        }

        netDamage = fullDamage - totalDamageReduction;
        return netDamage;
    }

    private boolean didAttackMiss(Character attacker, Character target, ArrayList<Character> enemies) {
        Random rn = new Random();
        double  hitChance = rn.nextDouble(),
                meleePenalty = 0;

        //If statement checks if attacker is melee and target is flying. if so, reduces accuracy.
        if(attacker.getType() == Character.CharacterType.PLAYER && target.getType() == Character.CharacterType.MONSTER) {
            if(((Monster)target).isFlying() && ((Player)attacker).getWeapon().getWeaponType() == Weapon.WeaponType.MELEE) {
                meleePenalty = -0.15;
            }
        }

        if(hitChance > attacker.getAttributes().getAccuracy() - meleePenalty) {
            System.out.println(attacker.getName() + "s attack missed!");
            pause(1000);
            return true;
        }

        hitChance = rn.nextDouble();
        if(hitChance - meleePenalty <= target.getAttributes().getEvasion()) {
            System.out.print(target.getName() + " evaded the attack from " + attacker.getName());

            //Processes the passive ability "Counter Attack"
            if(searchPassive(target, "Counter Attack")) {
                int damage = target.getAttributes().getDamage();
                DamageType damageType = attackerDamageType(target);

                System.out.println(" and hit back with a counter attack");
                inflictDamage(target, attacker, damage, false, false, damageType);
                checkEnemiesCleared(enemies);
            }
            System.out.println("!");
            pause(1000);
            return true;
        }

        return false;
    }

    private int returnProjectiles(Character attacker) {
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            if(((Player)attacker).getWeapon().getWeaponType() == Weapon.WeaponType.PROJECTILE) {
                return attacker.getAttributes().getProjectiles();
            }else {
                return 0;
            }
        }
        else if(attacker.getType() == Character.CharacterType.MONSTER) {
            if(((Monster)attacker).getAttackRange() == AttackRange.RANGED) {
                return attacker.getAttributes().getProjectiles();
            }
        }
        return 0;
    }

    private int attackArmor(Character attacker, Character target, int damage) {
        DamageType damageType;
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            damageType = ((Player)attacker).getWeapon().getDamageType();
        } else {
            damageType = attacker.getType() == Character.CharacterType.MONSTER ? ((Monster)attacker).getDamageType() : ((Boss)attacker).getDamageType();
        }

        if(target.getCurrentArmor() > 0) {
            if(damage > target.getCurrentArmor()) {
                target.updateArmor(-target.getCurrentArmor());
                if(damageType == DamageType.PHYSICAL) damage -= target.getCurrentArmor();
            }
            else {
                target.updateArmor(-damage);
                if(damageType == DamageType.PHYSICAL) damage = 0;
            }
        }
        return damage;
    }

    private int attackShield(Character attacker, Character target, int damage) {
        DamageType damageType;
        if(attacker.getType() == Character.CharacterType.PLAYER) {
            damageType = ((Player)attacker).getWeapon().getDamageType();
        } else {
            damageType = attacker.getType() == Character.CharacterType.MONSTER ? ((Monster)attacker).getDamageType() : ((Boss)attacker).getDamageType();
        }

        if(target.getCurrentArmor() > 0) {
            if(damage > target.getCurrentShield()) {
                target.updateShield(-target.getCurrentShield());
                if(damageType == DamageType.MAGICAL) damage -= target.getCurrentShield();
            }
            else {
                target.updateShield(-damage);
                if(damageType == DamageType.MAGICAL) damage = 0;
            }
        }
        return damage;
    }

    private void attackHealth(Character attacker, Character target, int damage, boolean spell, boolean cleave) {

        if(target.getCurrenthealth() <= damage) {

            //Process passive ability "Lifesteal"
            if(searchPassive(attacker, "Lifesteal") && !spell) {
                attacker.updateHealth((int) (target.getCurrenthealth() * 0.5));
            }

            if(target.getType() == Character.CharacterType.PLAYER) {
                battle_over = true;
                target.updateHealth(-target.getCurrenthealth());
            }
            else {
                attacker.updateTokens(target.getDeathTokens());
                totalTokens += target.getDeathTokens();
                target.updateHealth(-target.getCurrenthealth());
            }
        }

        else  {
            target.updateHealth(-damage);

            //Process passive ability "Lifesteal"
            if(searchPassive(attacker, "Lifesteal") && !spell) {
                attacker.updateHealth((int) (damage * 0.5));
            }
        }
    }

    private void checkEnemiesCleared(ArrayList<Character> enemies) {
        for(Character enemy: enemies) {
            if(enemy.getCurrenthealth() > 0) {
                return;
            }
        }
        battle_over = true;
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
                    target.getAttributes().setDamage(target.getAttributes().getDamage() - 5);
                    attacker.getAttributes().setDamage(attacker.getAttributes().getDamage() + 5);
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

    private boolean processAbility(Character attacker, Player player, ArrayList<Character> enemies, Ability ability) {

        if(attacker.getType() == Character.CharacterType.PLAYER) {
            if(ability.isTargetedAbility()) {
                Scanner sc = new Scanner(System.in);
                int index = 0;

                System.out.println("Choose Target:");
                for (Character enemy : enemies) {
                    if(enemy.getCurrenthealth() > 0) {
                        System.out.println(index + " - " + enemy.getName());
                    }
                    index++;
                }

                System.out.print(">");
                String chooseTarget = sc.nextLine();

                try {
                    int targetIndex = Integer.parseInt(chooseTarget);

                    if(targetIndex < 0 || targetIndex >= enemies.size() || enemies.get(targetIndex).getCurrenthealth() <= 0) {
                        System.out.println("Target does not exist...");
                    }
                    else {
                        executeTargetedAbility(attacker, enemies.get(targetIndex), enemies, ability);
                        return true;
                    }
                }
                catch (NumberFormatException ex) {
                    System.out.println("Please use only valid inputs (numbers).");
                }
            }
            else {
                executeAbility(attacker, player, enemies, ability);
                return true;
            }

            return false;
        }
        else {

            if(ability.isTargetedAbility()) {
                executeTargetedAbility(attacker, player, enemies, ability);
            }
            else {
                executeAbility(attacker, player, enemies, ability);
            }

            return true;
        }
    }

    //function that executes non-targeted abilities
    private void executeAbility(Character attacker, Player player, ArrayList<Character> enemies, Ability ability) {

        int intelligence = attacker.getAttributes().getIntelligence();
        if (attacker.getType() == Character.CharacterType.PLAYER) intelligence += ((Player)attacker).getBonusIntelligence();

        int agility = attacker.getAttributes().getAgility();
        if(attacker.getType() == Character.CharacterType.PLAYER) agility += ((Player)attacker).getBonusAgility();

        switch(ability.getAbilityName()) {
            case "Blade Jump":
                int     jumps = 5 + (agility / 5);

                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    if(((Player) attacker).getWeapon().getWeaponType() == Weapon.WeaponType.PROJECTILE) {
                        System.out.println("The attack missed... perhaps you don't have the right Weapon type for this ability (Melee)");
                        pause(1000);
                        return;
                    }


                    System.out.println("With one fast leap you start slashing your foes!");

                    double critChance = attacker.getAttributes().getCritChance();
                    attacker.getAttributes().setCritChance(1);

                    for (int i = 0; i < jumps; i++) {
                        Random rn = new Random();
                        int     index = rn.nextInt(enemies.size()),
                                netDamage = calculateDamage(attacker, enemies.get(index));

                        System.out.print("(" + (i + 1) + "/" + jumps + ")");

                        if(enemies.get(index).getCurrenthealth() > 0) {
                            System.out.print(" Critical hit! ");
                            inflictDamage(attacker, enemies.get(index), netDamage, false, false, DamageType.PHYSICAL);
                        } else {
                            System.out.println(" Missed!");
                        }
                    }
                    attacker.getAttributes().setCritChance(critChance);
                }

                else {
                    jumps = 3;
                    double  critChance = attacker.getAttributes().getCritChance();
                    attacker.getAttributes().setCritChance(1);

                    for (int i = 0; i < jumps; i++) {
                        int     netDamage = calculateDamage(attacker, player);

                        System.out.print("(" + (i + 1) + "/" + jumps + ")");

                        if(player.getCurrenthealth() > 0) {
                            System.out.print(" Critical hit! ");
                            inflictDamage(attacker, player, netDamage, false, false, DamageType.PHYSICAL);
                        } else {
                            System.out.println(" Missed!");
                        }
                        pause(1000);

                    }
                    attacker.getAttributes().setCritChance(critChance);
                }
                pause(1000);
                break;

            case "Flash Bomb":

                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    for (Character target : enemies) {
                        target.getAttributes().setEvasion(target.getAttributes().getEvasion() - 0.25);
                        target.getAttributes().setAccuracy(target.getAttributes().getAccuracy() - 0.25);
                        target.getAttributes().setCritChance(target.getAttributes().getCritChance() - 0.25);
                    }
                    System.out.println("The bomb explodes in a huge blinding light, lowering the stats of the enemies...");
                    pause(1000);
                }
                else {
                    player.getAttributes().setEvasion(player.getAttributes().getEvasion() - 0.25);
                    player.getAttributes().setAccuracy(player.getAttributes().getAccuracy() - 0.25);
                    player.getAttributes().setCritChance(player.getAttributes().getCritChance() - 0.25);

                    System.out.println("The bomb explodes in a huge blinding light, you are disoriented and got lowered stats...");
                    pause(1000);
                }
                break;

            case "Russian Roulette":
                Random rn = new Random();
                double chance = rn.nextDouble();

                if(chance <= 1.00) {
                    int randomTarget = rn.nextInt(enemies.size() + 1),
                            damage;

                    String targetName;

                    if(randomTarget == 0) {
                        damage = attacker.getCurrenthealth();
                        attacker.updateHealth(-damage);
                        targetName = "himself";
                        battle_over = true;
                    }else {
                        damage = enemies.get(randomTarget - 1).getCurrenthealth();
                        enemies.get(randomTarget -1).updateHealth(-damage);
                        targetName = enemies.get(randomTarget - 1).getName();
                    }
                    System.out.println(attacker.getName() + " has automatically executed " + targetName + " for " + damage + " damage!");
                } else {
                    System.out.println("The chamber was empty!");
                }
                pause(1000);

                break;

            case "Fireball":
                int fireDamage = 400 + (intelligence * 40);

                if(attacker.getType() == Character.CharacterType.PLAYER) {
                    System.out.println("You channel and hurl a huge fireball towards the enemy");

                    for (Character target : enemies) {
                        inflictDamage(attacker, target, fireDamage, true, false, DamageType.MAGICAL);
                    }
                }
                else {
                    inflictDamage(attacker, player, fireDamage, true, false, DamageType.MAGICAL);
                }

                break;

            case "Divine Light":
                int heal = 100 + (intelligence * 10);

                System.out.println("A holy light shines and reinvigorates you! You restore " + heal + " hp.");

                attacker.updateHealth(heal);

                break;


            case "Boston Shell":
                int armorRestore = 50 + (agility * 2),
                        shieldRestore = 50 + (intelligence * 2);

                System.out.println("You take a step back and strengthen your defenses! +" + armorRestore + " armor, +" + shieldRestore + " shield");

                attacker.updateShield(shieldRestore);
                attacker.updateArmor(armorRestore);

                break;
        }
    }


    //Function that executes targeted abilities
    private void executeTargetedAbility(Character attacker, Character target, ArrayList<Character> targets, Ability ability) {

        int intelligence = attacker.getAttributes().getIntelligence();
        if (attacker.getType() == Character.CharacterType.PLAYER) intelligence += ((Player)attacker).getBonusIntelligence();

        int strength = attacker.getAttributes().getStrength();
        if (attacker.getType() == Character.CharacterType.PLAYER) strength += ((Player)attacker).getBonusStrength();

        switch(ability.getAbilityName()) {
            case "Vitality Swap":
                double  attackerHealthPercentage = attacker.currentHealth / attacker.maxHealth,
                        targetHealthPercentage = target.currentHealth / attacker.maxHealth;

                attacker.setCurrentHealth((int)(attacker.getMaxHealth() * attackerHealthPercentage));
                target.setCurrentHealth((int)(attacker.getMaxHealth() * targetHealthPercentage));

                break;

            case "Soul Siphon":
                int siphonDamage = 100 + (10 * intelligence);
                inflictDamage(attacker, target, siphonDamage, true, false, DamageType.MAGICAL);

                break;

            case "Reckless Charge":
                int     chargeDamage = 150 + (20 * strength),
                        chargeSelfDamage = (int) (chargeDamage * 0.25);

                inflictDamage(attacker, target, chargeDamage, true, false, DamageType.PHYSICAL);

                if(attacker.getCurrenthealth() < chargeSelfDamage) {
                    attacker.setCurrentHealth(1);
                }
                else{
                    attacker.updateHealth(chargeSelfDamage);
                }

                break;

            case "Hail Mary":
                int maryDamage = (int) (target.getMaxHealth() * 0.25) * (1 - (attacker.getCurrenthealth() / attacker.getMaxHealth())) + 75;

                inflictDamage(attacker, target, maryDamage, false, false, DamageType.PHYSICAL);

                break;
        }
    }

    //Combat Logic of "Nephilim"
    private void nephilimCombatLogic(Character boss, Player player){
        Random rn = new Random();
        double abilityChance = rn.nextDouble();
        int damage = calculateDamage(boss, player);
        DamageType damageType = attackerDamageType(boss);
        boss.updateMana(20);

        if(abilityChance < 0.90) {
            inflictDamage(boss, player, damage, false, false, damageType);
        }
        else {
            if(boss.getCurrentMana() >= boss.getActives().get(0).getManaReq()) {
                executeTargetedAbility(boss, player, null, boss.getActives().get(0));
                boss.updateMana(-boss.getActives().get(0).getManaReq());
            }
            else {
                inflictDamage(boss, player, damage, false, false, damageType);
            }
        }
    }

    //Combat logic of "Underlord"
    private void underlordCombatLogic(Character boss, Player player) {
        Random rn = new Random();
        double abilityChance = rn.nextDouble();
        int damage = calculateDamage(boss, player);
        DamageType damageType = attackerDamageType(boss);
        boss.updateMana(20);

        if(abilityChance < 0.80) {
            inflictDamage(boss, player, damage, false, false, damageType);
        }
        else {
            if(boss.getCurrentMana() >= boss.getActives().get(0).getManaReq()) {
                executeTargetedAbility(boss, player, null, boss.getActives().get(0));
                boss.updateMana(-boss.getActives().get(0).getManaReq());
            }
            else {
                inflictDamage(boss, player, damage, false, false, damageType);
            }
        }
    }

    //Combat Logic of "False God"
    private void godCombatLogic(Character boss, Player player) {
        Random rn = new Random();
        double abilityChance = rn.nextDouble();
        int damage = calculateDamage(boss, player);
        DamageType damageType = attackerDamageType(boss);
        boss.updateMana(20);

        if(abilityChance < 0.70) {
            inflictDamage(boss, player, damage, false, false, damageType);
        }
        else {
            abilityChance = rn.nextDouble();
            Ability ability;
            if(abilityChance <= 0.50) {
                ability = boss.getActives().get(0);
            }
            else {
                ability = boss.getActives().get(1);
            }

            if(boss.getCurrentMana() >= ability.getManaReq()) {
                executeTargetedAbility(boss, player, null, ability);
                boss.updateMana(-boss.getActives().get(0).getManaReq());
            }
            else {
                inflictDamage(boss, player, damage, false, false, damageType);
            }
        }
    }

    //Function that clears the screen
    private void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    //Function that checks if character possesses a Passive ability
    private boolean searchPassive(Character character, String keyword) {
        for(Ability ability : character.getPassives()) {
            if (ability.abilityName.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    //Processes the passive ability "Escalating Violence"
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

    //Function that pauses the output screen so the user can read or whatever
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}