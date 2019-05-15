package ww_relics.relics.sakura;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;

import basemod.abstracts.CustomRelic;
import ww_relics.WW_Relics_Mod;
import ww_relics.cards.dan.WeakestEnergyBlast;
import ww_relics.resources.relic_graphics.GraphicResources;

public class SchoolBackpack extends CustomRelic {

	public static final String ID = "WW_Relics:School_Backpack";
	
	public static final int NUMBER_OF_EXTRA_CARDS = 4;
	pubic static final float CHANCE_OF_UPGRADED_CARDS = 0.1f;
	
	public static int number_of_cards_left = 4;
	
	public static final Logger logger = LogManager.getLogger(SchoolBackpack.class.getName());
	
	public SchoolBackpack() {
		super(ID, GraphicResources.LoadRelicImage("White_Boots - steeltoe-boots - Lorc - CC BY 3.0.png"), 
				RelicTier.COMMON, LandingSound.HEAVY);	
	}
	
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public void atPreBattle() {
		AddReward();
	}
	
	private void AddReward() {
		
		if (number_of_cards_left > 0) {
			
			PlayerClass player_class = AbstractDungeon.player.chosenClass;
			
			//int random_class = AbstractDungeon.ran
			
			//PlayerClass reward_class = 
			
			RewardItem card_reward = new RewardItem();
			card_reward.cards.clear();
			card_reward.cards.add(new WeakestEnergyBlast());
			AbstractDungeon.getCurrRoom().addCardReward(card_reward);

		}
		
	}
	
	private ArrayList<AbstractCard> createCardsFromOtherClassForReward(PlayerClass a_class) {
		
		ArrayList<AbstractCard> retVal = new ArrayList();
	    
	    int numCards = 3;
	    if (AbstractDungeon.player.hasRelic("Question Card")) {
	      numCards++;
	    }
	    if (AbstractDungeon.player.hasRelic("Busted Crown")) {
	      numCards -= 2;
	    }
	    if (ModHelper.isModEnabled("Binary")) {
	      numCards--;
	    }
	    AbstractCard.CardRarity rarity;
	    for (int i = 0; i < numCards; i++)
	    {
	    	rarity = AbstractDungeon.rollRarity();
	    	AbstractCard card = null;
	    	switch (rarity)
	    	{
	    		case RARE: 
	    			AbstractDungeon.cardBlizzRandomizer = AbstractDungeon.cardBlizzStartOffset;
	    		break;
	    		case UNCOMMON: 
	    			break;
	    		case COMMON: 
	    			AbstractDungeon.cardBlizzRandomizer -= AbstractDungeon.cardBlizzGrowth;
			        if (AbstractDungeon.cardBlizzRandomizer <= AbstractDungeon.cardBlizzMaxOffset) {
			        	AbstractDungeon.cardBlizzRandomizer = AbstractDungeon.cardBlizzMaxOffset;
			        }
			        break;
	    		default: 
	    			logger.info("Paraphrasing the base game code: WTF?");
	    	}
	    	boolean containsDupe = true;
	    	while (containsDupe)
	    	{
	    		containsDupe = false;
	    		card = AbstractDungeon.getCard(rarity);
	        }
	        for (AbstractCard c : retVal) {
	        	if (c.cardID.equals(card.cardID))
	        	{
	        		containsDupe = true;
	        		break;
	        	}
	      	}
	        if (card != null) {
	        	retVal.add(card);
	        }
	    }
	    ArrayList<AbstractCard> retVal2 = new ArrayList();
	    for (AbstractCard c : retVal) {
	    	retVal2.add(c.makeCopy());
	    }
	    for (AbstractCard c : retVal2) {
	    	if ((c.rarity != AbstractCard.CardRarity.RARE) && 
	    			(AbstractDungeon.cardRng.randomBoolean(CHANCE_OF_UPGRADED_CARDS)) && (c.canUpgrade())) {
	    		c.upgrade();
	    	} else if ((c.type == AbstractCard.CardType.ATTACK) && 
	    			(AbstractDungeon.player.hasRelic("Molten Egg 2"))) {
	    		c.upgrade();
	    	} else if ((c.type == AbstractCard.CardType.SKILL) &&
	    			(AbstractDungeon.player.hasRelic("Toxic Egg 2"))) {
	    		c.upgrade();
	    	} else if ((c.type == AbstractCard.CardType.POWER) &&
	    			(AbstractDungeon.player.hasRelic("Frozen Egg 2"))) {
		        c.upgrade();
	    	}
	    }
	    return retVal2;
	  }
	
	@Override
	public CustomRelic makeCopy() {
		return new SchoolBackpack();
	}

}
