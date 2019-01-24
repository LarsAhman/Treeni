package treeni;

import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Luokka yksittäiselle liikkeelle
 * @author Lars Åhman
 * @version 1.0, 14.3.2017
 *
 */
public class Liike implements Cloneable {

	private int liikeID;
	private String nimi = "";
	private int vaikeus = 1;
	private String kuvaus = "";
	
	private static int seuraavaNro = 1;
	
	
	/**
	 * Täyttää testiarvot liikkeelle.
	 * 
	 */
	public void vastaaLeuanveto() {
		Random rn = new Random();
		nimi = "Leuanveto";
		vaikeus = rn.nextInt(5) + 1;
		kuvaus ="Ota kiinni tangosta, roiku, vedä itsesi ylös.";
	}
	
	@Override
	public String toString() {
	    return "" +
	            getLiikeID() + "|" +
	            nimi + "|" +
	            vaikeus + "|" +
	            kuvaus;
	}
	
	/**
	 * Tulostaa liikkeen tiedot haluttuun tietovirtaan
	 * @param out tietovirta
	 */
	public void tulosta(PrintStream out) {
		out.println("Liike: " + " " + nimi);
		out.println("vaikeus: " + vaikeus);
		out.println();
		out.println(" " + kuvaus);
	}

	 /**
     * Tulostetaan liikkeen tiedot 
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
	
	/**
	 * Antaa liikkeelle seuraavan LiikeID:n.
	 * @return liikkeen uusi LiikeID
	 * @example
	 * <pre name="test">
	 * 	Liike leuanveto1 = new Liike();
	 *  leuanveto1.getLiikeID() === 0;
	 *  leuanveto1.rekisteroi();
	 *  Liike leuanveto2 = new Liike();
	 *  leuanveto2.rekisteroi();
	 *  int n1 = leuanveto1.getLiikeID();
	 *  int n2 = leuanveto2.getLiikeID();
	 *  n1 === n2-1;
	 * </pre>
	 */
	public int rekisteroi() {
		if ( liikeID != 0) return liikeID;
		liikeID = seuraavaNro;
		seuraavaNro++;
		return liikeID;
	}
	
	/**
	 * Palauttaa liikeen TunnusNro:n
	 * @return liikeID
	 */
	public int getLiikeID() {
		return liikeID;
	}
	
	/**
	 * Palauttaa liikkeen nimen
	 * @return nimi
	 */
	public String getNimi() {
		return nimi;
	}
	
	private void setLiikeID(int nr) {
	    liikeID = nr;
	    if (liikeID >= seuraavaNro) seuraavaNro = liikeID + 1;
	}
	
	/**
	 * Palauttaa liikkeen vaikeuden
	 * @return vaikeus
	 */
	public int getVaikeus() {
		return vaikeus;
	}
	
	/**
	 * 
	 * Testiohjelma Liike-luokalle
	 * @param args ei käytössä
	 * @example
	 * <pre name="test">
	 * Liike leuanveto3 = new Liike();
	 * leuanveto3.vastaaLeuanveto();
	 * leuanveto3.rekisteroi();
	 * leuanveto3.getLiikeID() === 3;
	 * Liike leuanveto4 = new Liike();
	 * leuanveto4.vastaaLeuanveto();
	 * leuanveto4.rekisteroi();
	 * leuanveto4.getLiikeID() === 4;
	 * </pre>
	 */
	public static void main(String[] args) {
		Liike linkkari = new Liike();
		linkkari.parse("1|Linkkari|3|makaa selallasi");
		linkkari.tulosta(System.out);

		Liike linkkari2 = new Liike();
		linkkari2.parse("2|Linkkari|4|makaa selallasi");
		linkkari2.tulosta(System.out);
	}

    /**
     * Ottaa merkkijonosta liikkeen tiedot
     * @param rivi rivi jota luetaan
     * 
     * @example
     * <pre name="test">
     * Liike parseTest1 = new Liike();
     * parseTest1.parse("1|parseTest1|3|jee");
     * parseTest1.getLiikeID() === 1;
     * parseTest1.getNimi() === "parseTest1";
     * parseTest1.getVaikeus() === 3;
     * parseTest1.getKuvaus() === "jee";
     * Liike parseTest2 = new Liike();
     * parseTest2.parse("2||3|");
     * parseTest2.getLiikeID() === 2;
     * parseTest2.getNimi() === "";
     * parseTest2.getVaikeus() === 3;
     * parseTest2.getKuvaus() === "";
     * </pre>
     */
	public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        
        setLiikeID(Mjonot.erota(sb, '|', getLiikeID()));
        
        nimi = Mjonot.erota(sb, '|', nimi);
        vaikeus = Mjonot.erota(sb, '|', vaikeus);
        kuvaus = Mjonot.erota(sb, '|', kuvaus);
    }
    
    @Override
    public Liike clone() throws CloneNotSupportedException {
        Liike uusi;
        uusi = (Liike) super.clone();
        return uusi;
    }

    /**
     * Palauttaa liikkeen kuvauksen
     * @return kuvaus
     */
    public String getKuvaus() {
        return kuvaus;
    }

    /**
     * Asettaa liikkeelle nimen
     * @param s uusi nimi
     * @return null, jos onnistui
     */
    public String setNimi(String s) {
        nimi = s;
        return null;
        
    }

    /**
     * Asettaa liikkeelle kuvauksen
     * @param s uusi kuvaus
     * @return null, jos onnistui
     */
    public String setKuvaus(String s) {
        kuvaus = s;
        return null;
    }

    /**
     * Asettaa liikkeelle vaikeuden
     * @param i uusi vaikeus
     */
    public void setVaikeus(int i) {
        vaikeus = i;
        
    }


}


