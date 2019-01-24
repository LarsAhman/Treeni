package treeni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Luokka joka kokoaa yksitt‰iset tagit
 * @author laalahma
 * @version 23.3.2017
 *
 */
public class Tagit implements Iterable<Tag>{

	private String tiedostonPerusNimi = "";
	private boolean muutettu = false;
	private final ArrayList<Tag> alkiot = new ArrayList<Tag>();
	
	/**
	 * Muodostaja tageille
	 */
	public Tagit() {
		   
	}
	
	
	/** Metodi tagin lis‰‰miselle
	 * @param tag alkioihin lis‰tt‰v‰ tagi
	 * @example
	 * <pre name="test">
	 * Tagit tagit = new Tagit();
	 * Tag kadet = new Tag("kadet");
	 * tagit.lisaa(kadet);
	 * tagit.getLkm() === 1;
	 * Tag selka = new Tag("selka");
	 * tagit.lisaa(selka);
     * tagit.getLkm() === 2;
     * tagit.lisaa(new Tag("kadet"));
     * tagit.getLkm() === 3;
     * tagit.getTag(1) === kadet;
     * tagit.getTag(2) === selka;
     * tagit.getTagI(0) === kadet;
     * tagit.getTagI(1) === selka;
	 * </pre>
	 */
	public void lisaa(Tag tag) {
		alkiot.add(tag);
		muutettu = true;
	}
	
	/** Lukee halutusta hakemistosta 
	 * @param tied tiedosto jota luetaan
	 * @throws SailoException poikkeus joka heitet‰‰n koska ei osata viel‰ lukea
	 */
	public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi()))) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
                Tag tag = new Tag();
                tag.parse(rivi); // voisi olla virhek‰sittely
                lisaa(tag);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
	}

	/**
	 * palauttaa tiedoston nimen
	 * @return tiedoston nimi + .dat
	 */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".dat";
    }
	
    /**
     * Lukee perustiedostosta
     * @throws SailoException jos luku ei onnistu
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    
    /**
     * Palauttaa tiedoston perusnimen
     * @return tiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }

	
    /**
     * Tallentaa tiedostoon 
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna() throws SailoException {
         if (!muutettu) return;
         
         File fbak = new File(getBakNimi());
         File ftied = new File(getTiedostonNimi());
         fbak.delete();
         ftied.renameTo(fbak);
         
         try (PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath()))) {
             for (Tag tag : alkiot) {
                 fo.println(tag.toString());
             }
         } catch ( FileNotFoundException ex ) {
             throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
         } catch ( IOException ex ) {
             throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
         }
         
         muutettu = false;
     }
	
    
     /**
      * Palauttaa backup tiedoston nimen
      * @return
      */
     private String getBakNimi() {
         return tiedostonPerusNimi + ".bak";
     }

     
	/** Palauttaa alkiot-taulukon tagien lukum‰‰r‰n
	 * @return tagien lukum‰‰r‰
	 */
	public int getLkm() {
		return alkiot.size();
	}
	
	/** Etsii ja palauttaa tagin jolla on annettu TagID
	 * @param tagID mill‰ tagID:ll‰ haetaan
	 * @return Tag jota etsittiin
	 */
	public Tag getTag(int tagID) {
		Tag palautus = null;
		for (Tag tag : alkiot) {
			if (tag.getTagID() == tagID) { palautus = tag; break; }
		}
		return palautus;
	}
	
	
	/** Metodi joka hakee alkiot taulukossa tietyss‰ paikassa olevan Tagin
	 * @param i mist‰ paikasta haetaan
	 * @return Tag jota haettiin
	 * @throws IndexOutOfBoundsException heitet‰‰n jos kyseist‰ indeksi‰ ei ole olemassa
	 */
	public Tag getTagI(int i) throws IndexOutOfBoundsException {
		if (i < 0 || alkiot.size() <= i)
			throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
		return alkiot.get(i);
	}
	
	
	/** Testip‰‰ohjelma metodien testaamiselle
	 * @param args ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Tagit tagit = new Tagit();
		
		Tag kadet = new Tag("kadet");
		tagit.lisaa(kadet);
		
		Tag jalat = new Tag("jalat");
		tagit.lisaa(jalat);
		
		Tag paa = new Tag("p‰‰");
		tagit.lisaa(paa);
		
		
		for (Tag tag : tagit) {
			tag.tulosta(System.out);
		}


	}

	/**
	 * Asettaa uuden nimen k‰ytetylle tiedostolle
	 * @param tied uusi nimi
	 */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonPerusNimi = tied;
    }


    @Override
    public Iterator<Tag> iterator() {
        return new TagitIterator();
    }

    /**
     * Iteraattori tageille
     * @author laalahma
     * @version 20.4.2017
     *
     */
    public class TagitIterator implements Iterator<Tag> {
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
        public Tag next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return getTagI(kohdalla++);
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
	 * Poistaa tagin alkioista
	 * @param tag tag joka poistetaan
	 * @example
     * <pre name="test">
     * Tagit testTagit = new Tagit();
     * Tag testYks = new Tag();
     * testTagit.lisaa(testYks);
     * testTagit.getLkm() === 1;
     * testTagit.poista(testYks);
     * testTagit.getLkm() === 0;
     * </pre>
	 * 
	 */
	public void poista(Tag tag) {
		for (int i = 0; i < alkiot.size(); i++) {
			if (alkiot.get(i).getTagID() == tag.getTagID()) { alkiot.remove(i); return; }
			
		}
		muutettu = true;
	}



}
