package us.newplatyp.mtgcardmaker;

import java.util.List;
import java.util.ArrayList;

import us.newplatyp.util.Configuration;

public class Card {
    final static String C_SYMBOLS_DIR = "path.symbols";

    private String title = "";
    private String imageURI = null;
	private int power = 0;
	private int toughness = 0;
	private int loyalty = 0;
	private List<String> types = new ArrayList<String>();
	private List<String> subtypes = new ArrayList<String>();
	private String text = "";
	private String flavorText = "";
	private List<Mana> manaCost = new ArrayList<Mana>();

	public Card(String title) {
		this.setTitle(title);
	}

	public Card(String title, String type, String subtype, String text) {
		this.setTitle(title);
		this.setType(type);
		this.setSubtype(subtype);
		this.setText(text);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPower() {
		return this.power;
	}

	public void setToughness(int toughness) {
		this.toughness = toughness;
	}

	public int getToughness() {
		return this.toughness;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public int getLoyalty() {
		return this.loyalty;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setImageURI(String uri) {
		if (uri != null && uri.length() > 0) {
			this.imageURI = uri;
		}
	}

	public String getImageURI() {
		return this.imageURI;
	}

	public String getTextAsHTML() {
		String result = this.getText().replaceAll("(\r\n|\n)", "<br/>");
		return result;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public String getFlavorText() {
		return this.flavorText;
	}

	public void setType(String type) {
		this.types.clear();
		this.types.add(type);
	}

	public String getType() {
		StringBuffer result = new StringBuffer();
		for (int g = 0; g < this.types.size(); g++) {
			if (g > 0) {
				result.append(" ");
			}
			result.append(this.types.get(g));
		}
		return result.toString();
	}

	public void setSubtype(String subtype) {
		this.subtypes.clear();
		this.subtypes.add(subtype);
	}

	public String getSubtype() {
		StringBuffer result = new StringBuffer();
		for (int g = 0; g < this.subtypes.size(); g++) {
			if (g > 0) {
				result.append(" ");
			}
			result.append(this.subtypes.get(g));
		}
		return result.toString();
	}

	public boolean addType(String type) {
		return this.types.add(type);
	}

	public boolean addSubtype(String subtype) {
		return this.subtypes.add(subtype);
	}

	public void setMana(Mana mana) {
		this.manaCost.clear();
		this.manaCost.set(0, mana);
	}

	public void setManaFromText(String manastring) {
		this.manaCost.clear();
		int len = manastring.length();
		for (int h = 0; h < len ; h++) {
			String cc = manastring.substring(h, h+1);
			if (cc.toUpperCase().equals("X")) {
				this.addMana(Mana.X);
			} else if (cc.toUpperCase().equals("P")) {
				System.err.println("No support for Phyrexian mana yet. " + this.title + ", " + manastring);
			} else if (cc.toUpperCase().equals("W")) {
				this.addMana(Mana.W);
			} else if (cc.toUpperCase().equals("U")) {
				this.addMana(Mana.U);
			} else if (cc.toUpperCase().equals("B")) {
				this.addMana(Mana.B);
			} else if (cc.toUpperCase().equals("R")) {
				this.addMana(Mana.R);
			} else if (cc.toUpperCase().equals("G")) {
				this.addMana(Mana.G);
			} else if (cc.equals("1") || cc.equals("2") || cc.equals("3") || cc.equals("4") || cc.equals("5") || cc.equals("6") || cc.equals("7") || cc.equals("8") || cc.equals("9") || cc.equals("0")) {
				// check 1 ahead...
				if (len > h+1) {
					String ra = manastring.substring(h+1, h+2);
					if (ra.equals("1") || ra.equals("2") || ra.equals("3") || ra.equals("4") || ra.equals("5") || ra.equals("6") || ra.equals("7") || ra.equals("8") || ra.equals("9") || ra.equals("0")) {
						cc = cc + ra;
						h++;
					}
				}
				int colorless = Integer.parseInt(cc);
				switch (colorless) {
				case 0:  this.addMana(Mana.c0);
					break;
				case 1:  this.addMana(Mana.c1);
					break;
				case 2:  this.addMana(Mana.c2);
					break;
				case 3:  this.addMana(Mana.c3);
					break;
				case 4:  this.addMana(Mana.c4);
					break;
				case 5:  this.addMana(Mana.c5);
					break;
				case 6:  this.addMana(Mana.c6);
					break;
				case 7:  this.addMana(Mana.c7);
					break;
				case 8:  this.addMana(Mana.c8);
					break;
				case 9:  this.addMana(Mana.c9);
					break;
				case 10:  this.addMana(Mana.c10);
					break;
				case 11:  this.addMana(Mana.c11);
					break;
				case 12:  this.addMana(Mana.c12);
					break;
				case 13:  this.addMana(Mana.c13);
					break;
				case 14:  this.addMana(Mana.c14);
					break;
				default:  this.addMana(Mana.c15);
					break;
				}
				
			}
		}
	}

	public String getMana() {
		StringBuffer result = new StringBuffer();
		for (int g = 0; g < this.manaCost.size(); g++) {
			result.append(this.manaCost.get(g).getValue());
		}
		return result.toString();
	}

	public List<Mana> getManaList() {
		return this.manaCost.subList(0,this.manaCost.size());
	}

    /*
	public String getManaAsHTML() {
		StringBuffer result = new StringBuffer();
		for (int g = 0; g < this.manaCost.size(); g++) {
			result.append("<img src=\"file:///Users/JasonCrickmer/projects/mtgcards/");
			result.append(this.config.get(this.C_SYMBOLS_DIR) + "/" + this.manaCost.get(g).getSymbolFilename());
			result.append("\"/>");
		}
		return result.toString();
	}
    */
	public boolean addMana(Mana mana) {
		return this.manaCost.add(mana);
	}

	public String toString() {
		return  "[Card: " + this.title + "]";
	}
}
