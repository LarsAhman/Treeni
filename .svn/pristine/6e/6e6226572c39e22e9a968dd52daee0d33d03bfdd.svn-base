package treeni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Luokka joka kokoaa yksitt‰iset TagID:t
 * @author laalahma
 * @version 23.3.2017
 */
public class TagIDt implements Iterable<TagID> {
	private String tiedostonPerusNimi = "";
	private boolean muutettu = false;
	private final ArrayList<TagID> alkiot = new ArrayList<TagID>();
	
	/**
	 * Muodostaja TagIDt:lle
	 */
	public TagIDt() {
		
	}
	
	
	/** Lukee tiedostoa halutusta hakemistosta
	 * @param tied tiedosto jota luetaan
	 * @throws SailoException heitet‰‰n koska ei osata viel‰ lukea
	 */
	public void lueTiedostosta(String tied) throws SailoException {
		setTiedostonPerusNimi(tied);
        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi()))) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
                TagID tagID = new TagID();
                tagID.parse(rivi); // voisi olla virhek‰sittely
                lisaa(tagID);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
	}
	
    private void lisaa(TagID tagID) {
        alkiot.add(tagID);
        muutettu = true;
    }

    /**
     * Palauttaa tiedoston perusnimen
     * @return perusnimi
     */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".dat";
    }
    
    /**
     * Lukee tiedostosta
     * @throws SailoException jos ei onnistu
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
	
	/** Tallentaa tiedostoon
	 * @throws SailoException heitet‰‰n koska ei osata viel‰ tallentaa
	 */
    public void tallenna() throws SailoException {
        if (!muutettu) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete();
        ftied.renameTo(fbak);
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath()))) {
            for (TagID tagID : alkiot) {
                fo.println(tagID.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
        
        muutettu = false;
    }
   
    private String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
	
	/**
	 * Antaa integerein‰ kokoelman liikkeelle kuuluvista TagIDeist‰
	 * @param liike jolle kuuluvat tagID:t halutaan hakea
	 * @return liikkeelle kuuluvat tagID:t
	 */
	public Collection<Integer> annaTagIDt(Liike liike) {
		Collection<Integer> tulos = new ArrayList<Integer>();
		for (TagID tagID : alkiot)
			if (tagID.getLiikeID() == liike.getLiikeID()) tulos.add(tagID.getTagID());
		return tulos;
	}
	
	/**
	 * Palauttaa tagID:eiden lukum‰‰r‰n
	 * @return lukum‰‰r‰
	 */
	public int getLkm() {
	    return alkiot.size();
	}
	
	/**
	 * palauttaa TagID:n paikassa i
	 * @param i paikka josta etsit‰‰n
	 * @return tagID
	 */
	public TagID getTagID(int i) {
	    return alkiot.get(i);
	}
	
	/**
	 * Yhdist‰‰ liikkeen tagiin.
	 * @param liike lolle tagi halutaan antaa
	 * @param tag joka halutaan antaa liikkeelle
	 * @example
	 * <pre name="test">
	 * TagIDt tagidt = new TagIDt();
	 * Tag kadet = new Tag("kadet");
	 * Liike leuanveto = new Liike();
	 * tagidt.yhdista(leuanveto, kadet);
	 * Arrays.toString(tagidt.annaTagIDt(leuanveto).toArray()) === "[1]";
	 * 
	 * 
	 * Tag selka = new Tag("selk‰");
     * tagidt.yhdista(leuanveto, selka);
     * Arrays.toString(tagidt.annaTagIDt(leuanveto).toArray()) === "[1, 2]";
	 * 
	 * </pre>
	 */
	public void yhdista(Liike liike, Tag tag) {
		if ( onkoYhdistetty(liike, tag) ) return; 
		alkiot.add(new TagID(liike, tag));
		muutettu = true;
	}
	
	/**
	 * Kertoo onko liike ja tag yhdistetty.
	 * @param liike liike jota tutkitaan
	 * @param tag tag jota tutkitaan
	 * @return true tai false
	 */
	public boolean onkoYhdistetty(Liike liike, Tag tag) {
		for (int i = 0; i < alkiot.size(); i++) {
			TagID alkio = alkiot.get(i);
			if (alkio.getLiikeID() == liike.getLiikeID() && alkio.getTagID() == tag.getTagID()) return true; 
		}
		return false;
	}
	/**
	 * Asettaa tiedoston perusnimen
	 * @param tied uusi nimi
	 */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonPerusNimi = tied;
    }

    /**
     * palauttaa tiedoston perusnimen
     * @return tiedoston perusnimi
     */
    private String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    @Override
    public Iterator<TagID> iterator() {
        return new TagIDtIterator();
    }
    
    /**
     * Iteraattori tagID:eille
     * @author laalahma
     * @version 20.4.2017
     *
     */
    public class TagIDtIterator implements Iterator<TagID> {
        private int kohdalla = 0;


        /**
         * Onko olemassa viel‰ seuraavaa j‰sent‰
         * @see java.util.Iterator#hasNext()
         * @return true jos on viel‰ j‰seni‰
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        @Override
        public TagID next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return getTagID(kohdalla++);
        }


        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Me ei poisteta");
        }
    }

    /**
     * poistaa linkitykset tiettyyn tagiin
     * @param tag tagi johon linkitykset halutaan poistaa
     */
	public void poistaTagIDlla(Tag tag) {

		for (int i = 0; i < alkiot.size(); i++) {
			if (alkiot.get(i).getTagID() == tag.getTagID()) { alkiot.remove(i); i--; }
			
		}
		
		muutettu = true;
		
	}

	/**
	 * poistaa tietyn liikkeen linkitykset
	 * @param liike jonka linkitykset halutaan poistaa
	 */
	public void poistaLiikeIDlla(Liike liike) {
		
		for (int i = 0; i < alkiot.size(); i++) {
			if (alkiot.get(i).getLiikeID() == liike.getLiikeID()) { alkiot.remove(i); i--; }
		}
		
		muutettu = true;
		
	}

	/**
	 * poistaa liikkeelt‰ tagin
	 * @param liike jolta tag poistetaan
	 * @param tag joka liikkeelt‰ poistetaan
	 */
	public void poistaLiikkeenTag(Liike liike, Tag tag) {
		for (int i = 0; i < alkiot.size(); i++) {
			TagID tagID = alkiot.get(i);
			if (tagID.getLiikeID() == liike.getLiikeID() && tagID.getTagID() == tag.getTagID()) {
				alkiot.remove(i);
				muutettu = true;
				return;
			}
		}
		
	}

	/**
	 * Kertoo sis‰lt‰‰kˆ liike tietyt tagit
	 * @param liike jota tutkitaan
	 * @param hakuTagit joilla totuutta testataan
	 * @return true jos sis‰lt‰‰ kaikki hakuTagit, muuten false
	 */
	public boolean sisaltaakoTagit(Liike liike, ArrayList<Tag> hakuTagit) {
		int matcheja = 0;
		
		for (int i = 0; i < alkiot.size(); i++) {
			TagID alkio = alkiot.get(i);
			if (!(alkio.getLiikeID() == liike.getLiikeID())) continue;
			int alkionTagID = alkio.getTagID();
			for (int j = 0; j < hakuTagit.size(); j++) {
				if (alkionTagID == hakuTagit.get(j).getTagID()) matcheja++; 
			}
			
		}
		
		if (matcheja < hakuTagit.size()) return false;
		return true;
	}



	
}
