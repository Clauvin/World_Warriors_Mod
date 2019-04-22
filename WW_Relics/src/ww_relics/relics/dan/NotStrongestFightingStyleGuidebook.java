package ww_relics.relics.dan;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import ww_relics.resources.relic_graphics.GraphicResources;

public class NotStrongestFightingStyleGuidebook extends CustomRelic {

	public static final String ID = "WW_Relics:Not_Strongest_Fighting_Style_Guidebook";
	
	public NotStrongestFightingStyleGuidebook() {
		super(ID, GraphicResources.LoadRelicImage("White_Boots - steeltoe-boots - Lorc - CC BY 3.0.png"),
				RelicTier.COMMON, LandingSound.FLAT);
	}
	
	public String getUpdatedDescription() {
		return "test";
	}
	
	public AbstractRelic makeCopy() { 
		return new NotStrongestFightingStyleGuidebook();
	}
}