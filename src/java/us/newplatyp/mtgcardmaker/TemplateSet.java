package us.newplatyp.mtgcardmaker;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

public class TemplateSet {
    private Template fallback = null;

    private Map<String,Map<String,Template>> typeMap = new HashMap<String,Map<String,Template>>();

    public TemplateSet(String defDocFilename) {
	try {
	    this.loadConfig(defDocFilename);
	} catch (IOException ioe) {
	    System.err.println("Could not load template set from " + defDocFilename);
	    ioe.printStackTrace();
	}
	if (this.fallback == null) {
	    this.fallback = new Template();
	}

	this.typeMap.put("Land", new HashMap<String,Template>());
	this.typeMap.put("Planeswalker", new HashMap<String,Template>());
	this.typeMap.put("Creature", new HashMap<String,Template>());
	this.typeMap.put("_default_", new HashMap<String,Template>());

	this.typeMap.get("Land").put("_default_", new Template("input/templates/basic/mtg_card_std_colorless_150dpi.png"));
	this.typeMap.get("Planeswalker").put("_default_", new Template("input/templates/basic/mtg_card_cpw_colorless_150dpi.png"));
	this.typeMap.get("Planeswalker").put("white", new Template("input/templates/basic/mtg_card_cpw_white_150dpi.png"));
	this.typeMap.get("Planeswalker").put("blue", new Template("input/templates/basic/mtg_card_cpw_blue_150dpi.png"));
	this.typeMap.get("Planeswalker").put("black", new Template("input/templates/basic/mtg_card_cpw_black_150dpi.png"));
	this.typeMap.get("Planeswalker").put("red", new Template("input/templates/basic/mtg_card_cpw_red_150dpi.png"));
	this.typeMap.get("Planeswalker").put("green", new Template("input/templates/basic/mtg_card_cpw_green_150dpi.png"));
	this.typeMap.get("Planeswalker").put("multicolor", new Template("input/templates/basic/mtg_card_cpw_gold_150dpi.png"));
	this.typeMap.get("Planeswalker").put("colorless", new Template("input/templates/basic/mtg_card_cpw_colorless_150dpi.png"));
	this.typeMap.get("Creature").put("_default_", new Template("input/templates/basic/mtg_card_cpw_colorless_150dpi.png"));
	this.typeMap.get("Creature").put("white", new Template("input/templates/basic/mtg_card_cpw_white_150dpi.png"));
	this.typeMap.get("Creature").put("blue", new Template("input/templates/basic/mtg_card_cpw_blue_150dpi.png"));
	this.typeMap.get("Creature").put("black", new Template("input/templates/basic/mtg_card_cpw_black_150dpi.png"));
	this.typeMap.get("Creature").put("red", new Template("input/templates/basic/mtg_card_cpw_red_150dpi.png"));
	this.typeMap.get("Creature").put("green", new Template("input/templates/basic/mtg_card_cpw_green_150dpi.png"));
	this.typeMap.get("Creature").put("multicolor", new Template("input/templates/basic/mtg_card_cpw_gold_150dpi.png"));
	this.typeMap.get("Creature").put("colorless", new Template("input/templates/basic/mtg_card_cpw_colorless_150dpi.png"));
	this.typeMap.get("_default_").put("_default_", new Template("input/templates/basic/mtg_card_std_colorless_150dpi.png"));
	this.typeMap.get("_default_").put("white", new Template("input/templates/basic/mtg_card_std_white_150dpi.png"));
	this.typeMap.get("_default_").put("blue", new Template("input/templates/basic/mtg_card_std_blue_150dpi.png"));
	this.typeMap.get("_default_").put("black", new Template("input/templates/basic/mtg_card_std_black_150dpi.png"));
	this.typeMap.get("_default_").put("red", new Template("input/templates/basic/mtg_card_std_red_150dpi.png"));
	this.typeMap.get("_default_").put("green", new Template("input/templates/basic/mtg_card_std_green_150dpi.png"));
	this.typeMap.get("_default_").put("multicolor", new Template("input/templates/basic/mtg_card_std_gold_150dpi.png"));
	this.typeMap.get("_default_").put("colorless", new Template("input/templates/basic/mtg_card_std_colorless_150dpi.png"));
    }

    public Template get(String type, List<Mana> colors) {
	if (type.contains("Creature")) {
	    type = "Creature";
	}
	Template result = this.fallback;
	Map<String,Template> rTypeMap = this.typeMap.get(type);
	if (rTypeMap == null) {
	    rTypeMap = this.typeMap.get("_default_");
	}
	if (colors != null) {
	    ListIterator<Mana> it = colors.listIterator();
	    Mana curM = null;
	    boolean white = false;
	    boolean blue = false;
	    boolean black = false;
	    boolean red = false;
	    boolean green = false;
	    while (it.hasNext()) {
		curM = (Mana)(it.next());
		white = white || curM.getName() == "white";
		blue = blue || curM.getName() == "blue";
		black = black || curM.getName() == "black";
	        red = red || curM.getName() == "red";
		green = green || curM.getName() == "green";
	    }
	    if ((white && blue) || (white && black) || (white && red) || (white && green)
		|| (blue && black) || (blue && red) || (blue && green)
		|| (black && red) || (black && green)
		|| (red && green)) {
		// gold!!
		result = rTypeMap.get("multicolor");
	    } else if (white) {
		result = rTypeMap.get("white");
	    } else if (blue) {
		result = rTypeMap.get("blue");
	    } else if (black) {
		result = rTypeMap.get("black");
	    } else if (red) {
		result = rTypeMap.get("red");
	    } else if (green) {
		result = rTypeMap.get("green");
	    } else {
		result = rTypeMap.get("colorless");
	    }
	}
	if (result == null) {
	    result = this.fallback;
	}
	return result;
    }

    private void loadConfig(String filename) throws IOException {
	File file = new File(filename);
	System.out.println("TemplateSet dir = " + file.getParent());
	System.out.println("TemplateSet file = " + file.getName());

	this.fallback = new Template();
	this.fallback.setFilename("input/templates/basic/mtg_card_std_colorless_150dpi.png");

	return;
    }
}
