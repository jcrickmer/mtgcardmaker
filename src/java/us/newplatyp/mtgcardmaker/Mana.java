package us.newplatyp.mtgcardmaker;

public class Mana {
    public static Mana X = new Mana("X", "colorless");
	public static Mana c0 = new Mana("0","colorless");
	public static Mana c1 = new Mana("1","colorless");
	public static Mana c2 = new Mana("2","colorless");
	public static Mana c3 = new Mana("3","colorless");
	public static Mana c4 = new Mana("4","colorless");
	public static Mana c5 = new Mana("5","colorless");
	public static Mana c6 = new Mana("6","colorless");
	public static Mana c7 = new Mana("7","colorless");
	public static Mana c8 = new Mana("8","colorless");
	public static Mana c9 = new Mana("9","colorless");
	public static Mana c10 = new Mana("10","colorless");
	public static Mana c11 = new Mana("11","colorless");
	public static Mana c12 = new Mana("12","colorless");
	public static Mana c13 = new Mana("13","colorless");
	public static Mana c14 = new Mana("14","colorless");
	public static Mana c15 = new Mana("15","colorless");
	public static Mana W = new Mana("W","white");
	public static Mana U = new Mana("U","blue");
	public static Mana B = new Mana("B","black");
	public static Mana R = new Mana("R","red");
	public static Mana G = new Mana("G","green");
	/*	static {
		Mana.X = new Mana("X");
		Mana.X.value = "X";
		} */
    private String value;
    private String name;
    private Mana(String value, String name) {
	this.value = value;
	this.name = name;
    }
	public String getValue() {
		return "{" + this.value + "}";
	}
	public String getName() {
	    return this.name;
	}
	public String getSymbolFilename() {
		return "symbol_mana_" + this.value + "_small.gif";
	}
}
