package ww_relics.modifiers;

import java.util.*;

import com.megacrit.cardcrawl.screens.custom.CustomMod;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import ww_relics.relics.chun_li.*;
import ww_relics.relics.guile.*;
import ww_relics.relics.ryu.*;

public class RelicSetModifiers {

	//Yes, I know this can be refactored to be a better class, I will do it, bear with me a while.
	
	public static final String WANDERING_WARRIOR_ID = "ww_relics:WanderingWarrior";
	
	public static final String BLUE_JADE_ID = "ww_relics:BlueJade";
	
	public static final String INDESTRUCTIBLE_FORTRESS_ID = "ww_relics:IndestructibleFortress";
	
	public static void addRelicSetModifiers(List<CustomMod> list) {
		CustomMod wandering_warrior = new CustomMod(RelicSetModifiers.WANDERING_WARRIOR_ID, "y", true);
		CustomMod blue_jade = new CustomMod(RelicSetModifiers.BLUE_JADE_ID, "y", true);
		CustomMod indestructible_fortress = new CustomMod(RelicSetModifiers.INDESTRUCTIBLE_FORTRESS_ID, "y", true);
		
		list.add(wandering_warrior);
		list.add(blue_jade);
		list.add(indestructible_fortress);
	}
	
	public static void addWanderingWarriorRelicsToCustomRun(ArrayList<String> relics) {
		addRelicToCustomRunRelicList(DuffelBag.ID, relics);
		addRelicToCustomRunRelicList(FightingGloves.ID, relics);
		addRelicToCustomRunRelicList(RedHeadband.ID, relics);
	}
	
	public static void addBlueJadeRelicsToCustomRun(ArrayList<String> relics) {
		addRelicToCustomRunRelicList(SpikyBracers.ID, relics);
		addRelicToCustomRunRelicList(WhiteBoots.ID, relics);
		addRelicToCustomRunRelicList(Handcuffs.ID, relics);
	}
	
	public static void addIndestructibleFortressToCustomRun(ArrayList<String> relics) {
		relics.add(ArmyBoots.ID);
		UnlockTracker.markRelicAsSeen(ArmyBoots.ID);
		relics.add(ChainWithNametags.ID);
		UnlockTracker.markRelicAsSeen(ChainWithNametags.ID);
		relics.add(CombatFatigues.ID);
		UnlockTracker.markRelicAsSeen(CombatFatigues.ID);
	}
	
	public static void addRelicToCustomRunRelicList(String ID, ArrayList<String> relics) {
		relics.add(ID);
		UnlockTracker.markRelicAsSeen(ID);
	}
	
	
}
