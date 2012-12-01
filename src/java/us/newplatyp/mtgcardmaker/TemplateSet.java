package us.newplatyp.mtgcardmaker;

import java.util.List;
import java.io.*;

public class TemplateSet {
    private Template fallback = null;

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
    }

    public Template get(String type, List<Mana> colors) {
	return this.fallback;
    }

    private void loadConfig(String filename) throws IOException {
	File file = new File(filename);
	System.out.println("TemplateSet dir = " + file.getParent());
	System.out.println("TemplateSet file = " + file.getName());

	this.fallback = new Template();
	this.fallback.setFilename("input/templates/basic/mtg_card_150_dpi_background.png");
	return;
    }
}
