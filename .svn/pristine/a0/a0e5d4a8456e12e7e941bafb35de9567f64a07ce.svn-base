/**
 * 
 */
package treeni;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Luokka treenipankille, joka kokoaa liikeet, tagit ja tagIDt
 * @author Lars ≈hman
 * @version 1.0, 14.3.2017
 *
 */
public class Treenipankki {

	private Liikkeet liikkeet = new Liikkeet();
	private TagIDt tagIDt = new TagIDt();
	private Tagit tagit = new Tagit();

	
	/**
	 * Lis‰‰ pankkiin uuden liikkeen
	 * @param liike lis‰tt‰v‰ liike
	 * @throws SailoException jos lis‰yst‰ ei voi tehd‰
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException
	 * Treenipankki pankki = new Treenipankki();
	 * Liike leuanveto1 = new Liike(), leuanveto2 = new Liike(), leuanveto3 = new Liike();
	 * leuanveto1.rekisteroi(); leuanveto2.rekisteroi();
	 * pankki.getLiikkeita() === 0;
	 * pankki.lisaa(leuanveto1); pankki.getLiikkeita() === 1;
	 * pankki.lisaa(leuanveto2); pankki.getLiikkeita() === 2;
	 * pankki.lisaa(leuanveto3); pankki.getLiikkeita() === 3;
	 * pankki.annaLiike(0) === leuanveto1;
	 * pankki.annaLiike(1) === leuanveto2;
	 * pankki.annaLiike(2) === leuanveto3;
	 * pankki.annaLiike(3) === leuanveto3; #THROWS IndexOutOfBoundsException
	 * pankki.lisaa(leuanveto1);
	 * pankki.lisaa(leuanveto1);
	 * pankki.lisaa(leuanveto1);
	 * </pre>
	 */
	//--------------------------------Liikkeet--------------------------------------
	public void lisaa(Liike liike) throws SailoException {
		liikkeet.lisaa(liike);
	}
	
	/** Metodi jolla saa liikkeiden lukum‰‰r‰n
	 * @return liikkeiden lukum‰‰r‰
	 */
	public int getLiikkeita () {
		return liikkeet.getLkm();
	}
	
	/** Hakee ja palauttaa liikkeen annetulla indeksill‰
	 * @param i indeksi, jolla liikett‰ haetaan
	 * @return Liike annetussa indeksiss‰
	 * @throws IndexOutOfBoundsException jos kyseist‰ indeksi‰ ei ole olemassa
	 */
	public Liike annaLiike(int i) throws IndexOutOfBoundsException {
		return liikkeet.anna(i);
	}
	
	//--------------------------------TAGIT--------------------------------------
	
	/**
	 * Lis‰‰ pankkiin uuden tagin
	 * @param tag lis‰tt‰v‰ tagi
	 * @example
	 * <pre name="test">
	 * Treenipankki pankki = new Treenipankki();
	 * Tag kadet = new Tag("kadet"), jalat = new Tag("jalat"), selka = new Tag("selka");
	 * pankki.getTageja() === 0;
	 * pankki.lisaa(kadet); pankki.getTageja() === 1;
	 * pankki.lisaa(jalat); pankki.getTageja() === 2;
	 * pankki.lisaa(selka); pankki.getTageja() === 3;
	 * pankki.annaTagI(0) === kadet;
	 * pankki.annaTagI(1) === jalat;
	 * pankki.annaTagI(2) === selka;
	 * pankki.annaTagI(3) === leuanveto3; #THROWS IndexOutOfBoundsException
	 * pankki.lisaa(leuanveto1);
	 * pankki.lisaa(leuanveto1);
	 * pankki.lisaa(leuanveto1);
	 * </pre>
	 */
	public void lisaa(Tag tag) {
		tagit.lisaa(tag);
	}
	
	/** Palauttaa tagin, joka sijaitsee annetussa indeksiss‰
	 * @param i josta tagia haetaan
	 * @return Tag
	 * @throws IndexOutOfBoundsException jos annettua indeksi‰ ei ole olemassa
	 */
	public Tag getTagI(int i) throws IndexOutOfBoundsException {
		return tagit.getTagI(i);
	}
	
	/** Hakee ja palauttaa tagin, jolla on annettu TagID
	 * @param tagID jolla tagia haetaan
	 * @return Tag jota haettiin
	 */
	public Tag getTag(int tagID) {
		return tagit.getTag(tagID);
	}
	
	/** Palauttaa tagian lukum‰‰r‰n
	 * @return tagien lukum‰‰r‰
	 */
	public int getTageja() {
		return tagit.getLkm();
	}
	

	//--------------------------------TAGIDT--------------------------------------
    /** Antaa liikkeelle tagin
     * @param liike jolle tagi annetaan
     * @param tag joka annetaan liikkeelle
     */
	public void yhdista(Liike liike, Tag tag) {
		tagIDt.yhdista(liike, tag);
	}
	
	/**
	 * Palauttaa kokoelman tagIDeist‰, jotka kuuluvat annetulle liikkeelle
	 * @param liike jonka tagien tagIDeit‰ haetaan
	 * @return kokoelman tagIDeist‰
	 */
	public Collection<Integer> annaTagIDt(Liike liike) {
		return tagIDt.annaTagIDt(liike);
	}
	
	
	
	/**
	 * Luo tiedoston ja asettaa tiedostojen perusnimet
	 * @param nimi tiedoston nimi
	 */
	public void setTiedosto(String nimi) {
	    File dir = new File(nimi);
	    dir.mkdirs();
	    String hakemistonNimi = "";
	    if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
	    liikkeet.setTiedostonPerusNimi(hakemistonNimi + "liikkeet");
	    tagIDt.setTiedostonPerusNimi(hakemistonNimi + "tagIDt");
	    tagit.setTiedostonPerusNimi(hakemistonNimi + "tagit");
	}

	/**
	 * Lukee tiedostosta
	 * @param nimi mist‰ tiedostosta luetaan
	 * @throws SailoException jos luku ei onnistu
	 */
    public void lueTiedostosta(String nimi) throws SailoException {
        liikkeet = new Liikkeet();
        tagIDt = new TagIDt();
        tagit = new Tagit();
        
        setTiedosto(nimi);
        liikkeet.lueTiedostosta();
        tagit.lueTiedostosta();
        tagIDt.lueTiedostosta();
    }
    
    /**
     * Tallentaa tiedostoon
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            liikkeet.tallenna();
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            tagit.tallenna();
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        
        try {
            tagIDt.tallenna();
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        
        if (!"".equals(virhe)) throw new SailoException(virhe);
        
        
    }

    /**
     * @param hakuehto mill‰ etsit‰‰n
     * @param k paikka
     * @return lˆytyneet
     * @throws SailoException jos ei lˆydy 
     */
    public Collection<Liike> etsi(String hakuehto, int k) throws SailoException {
        return liikkeet.etsi(hakuehto, k);
    }

    /**
     * Korvaa tai lis‰‰ liikkeen pankkiin
     * @param liike liike jota k‰sitell‰‰n
     * @throws SailoException jos ei onnistu
     */
    public void korvaaTaiLisaa(Liike liike) throws SailoException {
        liikkeet.korvaaTaiLisaa(liike);
        
    }

    /**
     * Poistaa pankista tagin
     * @param tag jota poistetaan
     */
	public void poistaTag(Tag tag) {
		
		tagIDt.poistaTagIDlla(tag);
		tagit.poista(tag);
		
	}

	/**
	 * Poistaa liikkeen
	 * @param liike liike jota poistetaan
	 */
	public void poistaLiike(Liike liike) {
		tagIDt.poistaLiikeIDlla(liike);
		liikkeet.poista(liike);
		
	}

	/**
	 * Poistaa liikkeelt‰ tagin
	 * @param liike liike jolta tagi poistetaan
	 * @param tag tagi jota poistetaan
	 */
	public void poistaLiikkeenTag(Liike liike, Tag tag) {
		tagIDt.poistaLiikkeenTag(liike, tag);
	}

	/** 
	 * Kertoo sis‰lt‰‰kˆ liike annetut hakutagit
	 * @param liike mit‰ liikett‰ tutkitaan
	 * @param hakuTagit mill tageilla totuutta tarkasteellaan
	 * @return true jos sis‰lt‰‰ kaikki tagit, muuten false
	 */
	public boolean sisaltaakoTagit(Liike liike, ArrayList<Tag> hakuTagit) {
		return tagIDt.sisaltaakoTagit(liike, hakuTagit);
	}
	
	/**
     * @param args ei k‰ytˆss‰
     * @throws SailoException  jos lis‰ys ei onnistu
     */
    public static void main(String[] args) throws SailoException {
        Treenipankki pankki = new Treenipankki();

        Liike leuanveto1 = new Liike();
        leuanveto1.vastaaLeuanveto();
        leuanveto1.rekisteroi();
        pankki.lisaa(leuanveto1);
        
        Liike leuanveto2 = new Liike();
        leuanveto2.vastaaLeuanveto();
        leuanveto2.rekisteroi();
        pankki.lisaa(leuanveto2);
        
        Tag kadet = new Tag("kadet");
        pankki.lisaa(kadet);
        Tag selka = new Tag("selka");
        pankki.lisaa(selka);
        Tag jalat = new Tag("jalat");
        pankki.lisaa(jalat);
        
        
        pankki.yhdista(leuanveto1, kadet);
        pankki.yhdista(leuanveto2, jalat);
        pankki.yhdista(leuanveto1, selka);
        pankki.yhdista(leuanveto2, selka);
        
        try {
            for (int i = 0; i < pankki.getLiikkeita(); i++) {
                Liike liike = pankki.annaLiike(i);
                System.out.println("Liike paikassa: "+ i);
                liike.tulosta(System.out);
                
                Collection<Integer> idt = pankki.annaTagIDt(liike);
                
                System.out.println("Tagit: ");
                
                for (int id : idt) {
                    System.out.println(pankki.getTag(id).getNimi());
                }
                
                System.out.println();
                
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("TESTATAAN poistaLiike poistaLiikkeenTag sisaltaakoTagit");
        
        System.out.println("poistetaan leuanveto1:lta tag kadet");
        
        pankki.poistaLiikkeenTag(leuanveto1, kadet);
        
        System.out.println("Tagit nyt: ");
        Collection<Integer> yksIDt = pankki.annaTagIDt(leuanveto1);
        for (int id : yksIDt) {
            System.out.println(pankki.getTag(id).getNimi());
        }
        
        System.out.println("sisaltaako leuanveto2 tagit jalat selka");
        
        ArrayList<Tag> hakuTagit = new ArrayList<Tag>();
        
        hakuTagit.add(jalat);
        hakuTagit.add(selka);
        
        System.out.println(pankki.sisaltaakoTagit(leuanveto2, hakuTagit));
     
        System.out.println("sisaltaako leuanveto2 tagit jalat selka kadet");
        
        hakuTagit.add(kadet);
        
        System.out.println(pankki.sisaltaakoTagit(leuanveto2, hakuTagit));
        System.out.println();
        
        System.out.println("Poistetaan leuanveto1");
        System.out.println();
        pankki.poistaLiike(leuanveto1);
        
        for (int i = 0; i < pankki.getLiikkeita(); i++) {
            Liike liike = pankki.annaLiike(i);
            System.out.println("Liike paikassa: "+ i);
            liike.tulosta(System.out);
            
            Collection<Integer> idt = pankki.annaTagIDt(liike);
            
            System.out.println("Tagit: ");
            
            for (int id : idt) {
                System.out.println(pankki.getTag(id).getNimi());
            }
            
            System.out.println();
            
        }
        
    }
	
}
