package treeni;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka yksittäiselle TagID:lle
 * @author laalahma
 * @version 23.3.2017
 *
 */
public class TagID {
	
	private int liikeID;
	private int tagID;
	
	
	
	/**
	 * Muodostaja TagID:lle
	 * @param liike johon tagi halutaan liittää
	 * @param tag tagi joka liikkeeseen halutaan liittää
	 */
	public TagID(Liike liike, Tag tag) {
		this.liikeID = liike.getLiikeID();
		this.tagID = tag.getTagID();
	}

	/**
	 * Tyhjä muodostaja
	 */
	public TagID() {
	    
	}
	
	/**
	 * Palauttaa liikeID:n
	 * @return liikeID
	 * @example
	 * <pre name="test">
	 * Liike leuanveto = new Liike();
	 * leuanveto.rekisteroi();
	 * Liike punnerrus = new Liike();
	 * punnerrus.rekisteroi();
	 * Tag kadet = new Tag("kadet");
	 * TagID yhdista1 = new TagID(leuanveto, kadet);
	 * TagID yhdista2 = new TagID(punnerrus, kadet);
	 * 
	 * yhdista1.getLiikeID() === 1;
	 * yhdista1.getTagID() === 1;
	 * yhdista2.getLiikeID() === 2;
	 * yhdista2.getTagID() === 1;
	 * </pre>
	 */
	public int getLiikeID() {
		return liikeID;
	}

    @Override
    public String toString() {
        return getLiikeID() + "|" + tagID;
    }

	/**
	 * palauttaa tagID:n
	 * @return tagID
	 */
	public int getTagID() {
		return tagID;
	}

    /**
     * Ottaa olion tiedot merkkijonosta
     * @param rivi josta tiedot otetaan
     * @example
     * <pre name="test">
     * TagID testID = new TagID();
     * testID.parse("1|2");
     * testID.getLiikeID() === 1;
     * testID.getTagID() === 2;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        
        liikeID = Mjonot.erota(sb, '|', getLiikeID());
        
        tagID = Mjonot.erota(sb, '|', tagID);  
    }

    /**
     * main
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        TagID testi1 = new TagID();
        
        testi1.parse("2|4");
        
        
    }
	
}
