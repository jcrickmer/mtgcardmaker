package us.newplatyp.mtgcardmaker;

public class Mana {
	public static Mana X = new Mana("X");
	public static Mana c0 = new Mana("0");
	public static Mana c1 = new Mana("1");
	public static Mana c2 = new Mana("2");
	public static Mana c3 = new Mana("3");
	public static Mana c4 = new Mana("4");
	public static Mana c5 = new Mana("5");
	public static Mana c6 = new Mana("6");
	public static Mana c7 = new Mana("7");
	public static Mana c8 = new Mana("8");
	public static Mana c9 = new Mana("9");
	public static Mana c10 = new Mana("10");
	public static Mana c11 = new Mana("11");
	public static Mana c12 = new Mana("12");
	public static Mana c13 = new Mana("13");
	public static Mana c14 = new Mana("14");
	public static Mana c15 = new Mana("15");
	public static Mana W = new Mana("W");
	public static Mana U = new Mana("U");
	public static Mana B = new Mana("B");
	public static Mana R = new Mana("R");
	public static Mana G = new Mana("G");
	/*	static {
		Mana.X = new Mana("X");
		Mana.X.value = "X";
		} */
	private String value;
	private Mana(String value) {
		this.value = value;
	}
	public String getValue() {
		return "{" + this.value + "}";
	}
	public String getSymbolFilename() {
		return "symbol_mana_" + this.value + "_small.gif";
	}
}
