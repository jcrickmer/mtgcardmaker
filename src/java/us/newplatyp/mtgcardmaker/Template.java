package us.newplatyp.mtgcardmaker;

public class Template {

    private String filename;
    private String type;
    private String colors;

    public Template() {
	this.filename = "mtg_card_150_dpi_background.png";
    }

    public String getFilename() {
	return this.filename;
    }

    void setFilename(String filename) {
	this.filename = filename;
    }
}
