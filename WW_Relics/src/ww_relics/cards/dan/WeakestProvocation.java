package ww_relics.cards.dan;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import ww_relics.powers.PotentialPower;

public class WeakestProvocation extends CustomCard {

	public static final String ID = "WW_Relics:WeakestProvocation";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;
    private static final int VULNERABLE_AMOUNT = 1;
    private static final int POTENTIAL_NUMERATOR = 1;
    private static final int POTENTIAL_DENOMINATOR = 3;
	
    public WeakestProvocation() {
		super(ID, WeakestProvocation.NAME, "ww_relics/images/cards/temp_skill.png",
				WeakestProvocation.COST, WeakestProvocation.DESCRIPTION, CardType.SKILL,
				CardColor.COLORLESS, CardRarity.COMMON, CardTarget.ENEMY);

        this.exhaust = true;
	}
    
	@Override
	public void upgrade() {
		return;
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
        AbstractDungeon.actionManager.addToBottom(
        		new ApplyPowerAction(monster, player,
        				new VulnerablePower(monster, VULNERABLE_AMOUNT, false)));
        
        AbstractDungeon.actionManager.addToBottom(
        		new ApplyPowerAction(player, player, 
        				new PotentialPower(player, POTENTIAL_NUMERATOR, POTENTIAL_DENOMINATOR)));
	}
	
	static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = WeakestProvocation.cardStrings.NAME;
        DESCRIPTION = WeakestProvocation.cardStrings.DESCRIPTION;
    }

}
