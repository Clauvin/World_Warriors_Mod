

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import ww_relics.relics.ryu.RedHeadband;

@SpireInitializer
public class WW_Relics_Mod implements PostInitializeSubscriber,
		EditRelicsSubscriber{

	//What exactly this does?
	public static final Logger logger = LogManager.getLogger(WW_Relics_Mod.class.getName()); // lets us log output

	public static final String MODNAME = "World Warriors' Relics"; // mod name
	public static final String AUTHOR = "Clauvin aka Dungeon Explorer Lan"; // your name
	public static final String DESCRIPTION = "v0.0.1" +
			"\r\n Adds a relic based in SF's Ryu to the game.";
	
	public WW_Relics_Mod() {
		BaseMod.subscribe(this);
	}

	public static void initialize()	{
	    new WW_Relics_Mod();
	}
	
	public void receivePostInitialize() {

		//Mod badge
		//Texture badgeTexture = new Texture();
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(null, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
		
	}
	
	public void receiveEditStrings()
	{
	    logger.info("begin editing strings");
	    BaseMod.loadCustomStrings(RelicStrings.class, loadJson("ww_relics/localization/eng/WW_Relics.json"));
	    logger.info("done editing strings");
	}
	
	 private static String loadJson(String jsonPath)
	 {
	    return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
	 }
	
	public void receiveEditRelics() {
		logger.info("Begin adding relics");
		BaseMod.addRelic(new RedHeadband(), RelicType.SHARED);
		logger.info("Done adding relics");
	}
}
