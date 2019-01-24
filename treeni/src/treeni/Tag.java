package treeni;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/** 
 * Luokka yksitt‰iselle tagille
 * @author laalahma
 * @version 23.3.2017
 */
public class Tag {
	
	private int tagID;
	private String nimi = "";

	private static int seuraavaNro = 1;

	/**
	 * Muodostaja yksitt‰iselle tagille
	 * @param nimi tagin nimi
	 */
	public Tag(String nimi) {
		this.nimi = nimi;
		tagID = seuraavaNro++;
	}
	
	/**
	 * tyhj‰ muodostaja
	 */
	public Tag() {
	    
	}

	@Override
	public String toString() {
	    return getTagID() + "|" + nimi;
	}
	
	 /**
     * Tulostetaan tagin nimi 
     * @param out tietovirta johon tulostetaan
     */
	public void tulosta(PrintStream out) {
		out.print(tagID + " ");
	    out.println(nimi);
	}
	
	/**
	 * Tulostetaan tagin nimi 
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
		tulosta(new PrintStream(os));
	}
	
	/**
	 * Palauttaa tagin ID:n
	 * @return int tagID
	 * @example
	 * <pre name="test">
	 * Tag kadet = new Tag("kadet");
	 * kadet.getTagID() === 1;
	 * kadet.getNimi() === "kadet";
	 * Tag selka = new Tag("selka");
	 * selka.getTagID() === 2;
	 * selka.getNimi() === "selka";
	 * </pre>
	 */
	public int getTagID() {
		return tagID;
	}
	
	/**
	 * Palauttaa tagin nimen
	 * @return String tagin nimi
	 */
	public String getNimi() {
		return nimi;
	}
	
	/**
	 * P‰‰ohjelma luokan testaamiselle
	 * @param args ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Tag kadet = new Tag("k‰det");
		kadet.tulosta(System.out);
		
		Tag ylaselka = new Tag("yl‰selk‰");
		ylaselka.tulosta(System.out);
		
		Tag paa = new Tag();
		paa.parse("3|p‰‰");
		paa.tulosta(System.out);
	}

	/**
	 * Ottaa tagin tiedot merkkijonosta
	 * @param rivi rivi jota luetaan
	 
	 * @example
     * <pre name="test">
     * 
     * Tag yksi = new Tag();
     * 
     * yksi.parse("1|p‰‰");
     * 
     * yksi.getTagID() === 1;
     * yksi.getNimi() === "p‰‰";
     * 
     * Tag kaksi = new Tag();
     * 
     * kaksi.parse("|");
     * 
     * kaksi.getTagID() === 0
     * kaksi.getNimi() === "";
     * 
     * kaksi.setTagID(10);
     * kaksi.getTagID() === 10;
     * 
     * kaksi.setTagID(0);
     * kaksi.getTagID() === 0;
     * 
     * </pre>
	 */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        
        setTagID(Mjonot.erota(sb, '|', getTagID()));
        
        nimi = Mjonot.erota(sb, '|', nimi);
        
    }

    /** 
     * Asettaa tagille uuden id:n
     * @param nr uusi id
     */
    public void setTagID(int nr) {
        tagID = nr;
        if (tagID >= seuraavaNro) seuraavaNro = tagID + 1;
    }
	
}
