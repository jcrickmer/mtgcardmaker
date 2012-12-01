package us.newplatyp.mtgcardmaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.imageio.ImageIO;

import us.newplatyp.framework.MainProgram;
import us.newplatyp.util.Configuration;

public class MTGCardMaker implements MainProgram {
	static public void main(String args[]) throws Exception {
		Configuration config = new Configuration();
		config.setProperty("path.output.card","output");
		config.setProperty("path.symbols","symbols");
		config.setProperty("template.definition.filename","input/templates/basic/basic.xml");

		String cardlist = "input/cards.xml";
		if (args.length > 0) {
		    cardlist = args[0];
		}
		MTGCardMaker maker = new MTGCardMaker(config);
		maker.go(cardlist);
	};

	private Configuration config;

	MTGCardMaker(Configuration config) {
		this.config = config;
	}

	public void go(String cardlist) {
		CardsXMLReader reader = new CardsXMLReader(this.config);
		reader.goForIt(cardlist);

		TemplateSet tset = new TemplateSet(this.config.getProperty("template.definition.filename"));

		CardImageProducer cip = new CardImageProducer(this, tset);

		List cards = reader.getCards();
		ListIterator lit = cards.listIterator();
		Card card;
		while (lit.hasNext()) {
			card = (Card)(lit.next());
			try {
				cip.produce(card);
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	public Configuration getConfiguration() {
		return this.config;
	}

}

		//Card card = new Card("Frodo of the Shire","Creature","Hobbit","Unblockable");
		//Card card = new Card("Marcie","Creature","Hobbit","When Marcie enters the battlefield, target husband makes coffee.\nHello!");
		/*
		Card card = new Card("Asia","Planeswalker","Asia","+1 - Look at target players hand.\n-2 - Return up to two target permenants to their owners hands.\n-5 - Take an extra turn after this turn.");
		card.setFlavorText("\"Arf!!  Lick lick\"\n- Asia");
		card.addMana(Mana.c2);
		card.addMana(Mana.W);
		card.addMana(Mana.U);
		card.addMana(Mana.U);
		card.setLoyalty(3);

		try {
			cip.produce(card);
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		card = new Card("Smaug", "Legendary", "Dragon","Flying\n\n{R}: Smog gets +1/+0.\n\nWhen Smaug enters the battlefield, you may search any library for an artifact and place that artifact tapped onto the battlefield under your control.");
		card.setFlavorText("Revenge! Revenge! The King under the Mountain is dead and where are his kin that dare seek revenge?\n-Smaug");
		card.addType("Creature");
		card.setPower(6);
		card.setToughness(6);
		card.addMana(Mana.c4);
		card.addMana(Mana.R);
		card.addMana(Mana.R);
		card.addMana(Mana.R);
		card.setImageURI("smog_image.png");

		try {
			cip.produce(card);
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		card = new Card("Sauron", "Planeswalker", "Sauron","+2 - All creatures get -1/-1 until end of turn.\n-3 Gain control of target creature.\n-5 - Gain control of target planeswalker.\n-9 - Exile target creature.  Gain control of another target creature from any player or any player's graveyard.  That creature gains deathtouch.");

		card.setLoyalty(5);
		card.addMana(Mana.c4);
		card.addMana(Mana.R);
		card.addMana(Mana.B);
		card.addMana(Mana.B);
		card.addMana(Mana.B);
		card.setImageURI("Sauron_art.png");

		try {
			cip.produce(card);
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		*/
