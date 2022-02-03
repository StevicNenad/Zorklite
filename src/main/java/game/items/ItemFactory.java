package game.items;

import game.abilities.passive.ExtraAttack;
import game.items.accessories.*;
import game.items.gems.*;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.ArrayList;
import java.util.Random;

public class ItemFactory {
    private ArrayList<Accessories>  accessoryList;
    private ArrayList<Gems>         gemList;

    public ItemFactory() {
        accessoryList = new ArrayList<Accessories>();
        gemList = new ArrayList<Gems>();

        //Create every type of accessory and add to ArrayList
        BlackCape blackCape = new BlackCape();
        accessoryList.add(blackCape);

        BlazingGloves blazingGloves = new BlazingGloves();
        accessoryList.add(blazingGloves);

        Bracelet bracelet = new Bracelet();
        accessoryList.add(bracelet);

        ChaosShard chaosShard = new ChaosShard();
        accessoryList.add(chaosShard);

        DarkCloak darkCloak = new DarkCloak();
        accessoryList.add(darkCloak);

        DemonicTalisman demonicTalisman = new DemonicTalisman();
        accessoryList.add(demonicTalisman);

        HandWraps handWraps = new HandWraps();
        accessoryList.add(handWraps);

        Headband headband = new Headband();
        accessoryList.add(headband);

        HolyWater holyWater = new HolyWater();
        accessoryList.add(holyWater);

        IronChains ironChains = new IronChains();
        accessoryList.add(ironChains);

        LightningQuiver lightningQuiver = new LightningQuiver();
        accessoryList.add(lightningQuiver);

        MammothTusk mammothTusk = new MammothTusk();
        accessoryList.add(mammothTusk);

        Necklace necklace = new Necklace();
        accessoryList.add(necklace);

        ObsidianRing obsidianRing = new ObsidianRing();
        accessoryList.add(obsidianRing);

        SacredAmulet sacredAmulet = new SacredAmulet();
        accessoryList.add(sacredAmulet);

        ScorcherRing scorcherRing = new ScorcherRing();
        accessoryList.add(scorcherRing);

        SeraphicVisor seraphicVisor = new SeraphicVisor();
        accessoryList.add(seraphicVisor);

        SorcerersHat sorcerersHat = new SorcerersHat();
        accessoryList.add(sorcerersHat);

        UnholyHood unholyHood = new UnholyHood();
        accessoryList.add(unholyHood);

        WolfFang wolfFang = new WolfFang();
        accessoryList.add(wolfFang);

        //Create every type of gem and add to ArrayList
        BladeJumpGem bladeJumpGem = new BladeJumpGem();
        gemList.add(bladeJumpGem);

        BlindRageGem blindRageGem = new BlindRageGem();
        gemList.add(blindRageGem);

        BlitzGem blitzGem = new BlitzGem();
        gemList.add(blitzGem);

        BostonShellGem bostonShellGem = new BostonShellGem();
        gemList.add(bostonShellGem);

        CounterAttackGem counterAttackGem = new CounterAttackGem();
        gemList.add(counterAttackGem);

        DivineCombustGem divineCombustGem = new DivineCombustGem();
        gemList.add(divineCombustGem);

        EmberGem emberGem = new EmberGem();
        gemList.add(emberGem);

        EscalatingViolenceGem escalatingViolenceGem = new EscalatingViolenceGem();
        gemList.add(escalatingViolenceGem);

        ExtraAttackGem extraAttackGem = new ExtraAttackGem();
        gemList.add(extraAttackGem);

        FireballGem fireballGem = new FireballGem();
        gemList.add(fireballGem);

        FlashBombGem flashBombGem = new FlashBombGem();
        gemList.add(flashBombGem);

        HailMaryGem hailMaryGem = new HailMaryGem();
        gemList.add(hailMaryGem);

        HydroGem hydroGem = new HydroGem();
        gemList.add(hydroGem);

        LifestealGem lifestealGem = new LifestealGem();
        gemList.add(lifestealGem);

        LightningGem lightningGem = new LightningGem();
        gemList.add(lightningGem);

        RecklessChargeGem recklessChargeGem = new RecklessChargeGem();
        gemList.add(recklessChargeGem);

        RussianRouletteGem russianRouletteGem = new RussianRouletteGem();
        gemList.add(recklessChargeGem);

        SoulSiphonGem soulSiphonGem = new SoulSiphonGem();
        gemList.add(soulSiphonGem);

        VitalitySwapGem vitalitySwapGem = new VitalitySwapGem();
        gemList.add(vitalitySwapGem);

    }

    public Accessories getRandomAccessory() {
        Random rn = new Random();
        int index = rn.nextInt(accessoryList.size());

        return accessoryList.get(index);
    }

    public Gems getRandomGem() {
        Random rn = new Random();
        int index = rn.nextInt(gemList.size());

        return gemList.get(index);
    }

    public Potion getPotion() {
        Potion potion = new Potion();

        return potion;
    }
}
