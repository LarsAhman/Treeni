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

/**
 * Luokka joka kokoaa yksitt‰iset liikkeet
 * 
 * @author Lars ≈hman
 * @version 1.0, 14.3.2017
 *
 */
public class Liikkeet implements Iterable<Liike> {

	private static final int MAX_LIIKKEITA = 5;
	private Liike[] alkiot = new Liike[MAX_LIIKKEITA];
	private int lkm;
	private String tiedostonPerusNimi = "";
	private boolean muutettu = false;
	
	/**
	 * Lis‰‰ liikkeen alkioihin
	 * @param liike jota lis‰t‰‰n
	 * @example
	 * <pre name="test">
	 * Liike leuanveto1 = new Liike(), leuanveto2 = new Liike(), leuanveto3 = new Liike();
	 * Liikkeet liikkeet = new Liikkeet();
	 * liikkeet.getLkm() === 0;
	 * liikkeet.lisaa(leuanveto1); liikkeet.getLkm() === 1;
	 * liikkeet.lisaa(leuanveto2); liikkeet.getLkm() === 2;
	 * liikkeet.lisaa(leuanveto3); liikkeet.getLkm() === 3;
	 * liikkeet.anna(0) === leuanveto1;
	 * liikkeet.anna(1) === leuanveto2;
	 * liikkeet.anna(2) === leuanveto3;
	 * </pre>
	 */
	public void lisaa(Liike liike) {
		if (lkm >= alkiot.length) {
			Liike[] alkiotVali = new Liike[lkm*2];
			
			System.arraycopy(alkiot, 0, alkiotVali, 0, lkm);
			alkiot = alkiotVali;
		}
		alkiot[lkm++] = liike;
		muutettu = true;
	}
	
	/**
	 * Hakee annetulla indeksill‰ liikkeen ja palauttaa sen
	 * @param i paikka, josta liike haetaan
	 * @return Liike haettu liike
	 * @throws IndexOutOfBoundsException jos annettua indeksi‰ ei ole
	 */
	public Liike anna(int i) throws IndexOutOfBoundsException {
		if (i < 0 || lkm <= i)
			throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
		return alkiot[i];
	}
	/**
	 * Palauttaa liikkeiden maaran
	 * @return lkm kuinka monta liikett‰ alkioissa on
	 */
	public int getLkm() {
		return lkm;
	}
	
	/**
	 * Lukee tiedoston
	 * @param tied tiedoston nimen alkuosa
	 * @throws SailoException heitet‰‰n koska ei osata viel‰ lukea
	 */
	public void lueTiedostosta(String tied) throws SailoException {
	      setTiedostonPerusNimi(tied);
	        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi()))) {

	            String rivi;
	            while ( (rivi = fi.readLine()) != null ) {
	                rivi = rivi.trim();
	                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
	                Liike liike = new Liike();
	                liike.parse(rivi); // voisi olla virhek‰sittely
	                lisaa(liike);
	            }
	            muutettu = false;

	        } catch ( FileNotFoundException e ) {
	            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
	        } catch ( IOException e ) {
	            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
	        }
		
	}
	
	/**
	 * Lukee liikkeet tiedostosta
	 * @throws SailoException jos ei aukea
	 */
	public void lueTiedostosta() throws SailoException {
	    lueTiedostosta(getTiedostonPerusNimi());
	}
	
	 private String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }

    /**
     * Tallentaa tiedostoon
     * @throws SailoException heitet‰‰n jos ei onnistu
     */
	public void tallenna() throws SailoException {
	    if (!muutettu) return;
	    
	    File fbak = new File(getBakNimi());
	    File ftied = new File(getTiedostonNimi());
	    fbak.delete();
	    ftied.renameTo(fbak);
	    
	    try (PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath()))) {
	        for (Liike liike : this) {
	            fo.println(liike.toString());
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
	 * @return backup tiedoston nimi
	 */
	private String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }

	/**
	 * Palauttaa tiedoston nimen
	 * @return tiedoston nimi
	 */
    public String getTiedostonNimi() {
	    return tiedostonPerusNimi + ".dat";
	}
	
	/**
	 * Testip‰‰ohjelma
	 * @param args ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		Liikkeet liikkeet = new Liikkeet();
		
		Liike leuanveto = new Liike();
		Liike leuanveto2 = new Liike();
		
		leuanveto.vastaaLeuanveto();
		leuanveto2.vastaaLeuanveto();
		
		leuanveto.rekisteroi();
		leuanveto2.rekisteroi();
		
		
			
		liikkeet.lisaa(leuanveto);
		liikkeet.lisaa(leuanveto2);
		
		for (int i = 0; i < liikkeet.getLkm(); i++) {
			Liike liike = liikkeet.anna(i);
			System.out.println("Liike nro: " + i);
			liike.tulosta(System.out);
		}
		
		System.out.println("========Poistetaan leuanveto2=========");
		
		liikkeet.poista(leuanveto2);

		
		for (int i = 0; i < liikkeet.getLkm(); i++) {
            Liike liike = liikkeet.anna(i);
            System.out.println("Liike nro: " + i);
            liike.tulosta(System.out);
        }
        
	}

	/**
	 * Asettaa tiedoston nimen
	 * @param tied tiedoston nimi
	 */
	public void setTiedostonPerusNimi(String tied) {
	    tiedostonPerusNimi = tied;
	}
	
    @Override
    public Iterator<Liike> iterator() {
        return new LiikkeetIterator();
    }

    /**
     * Iteraattori liikkeeille
     * @author laalahma
     * @version 20.4.2017
     *
     */
    public class LiikkeetIterator implements Iterator<Liike> {
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
        public Liike next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return anna(kohdalla++);
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
     * Etsii hakuehdolla liikkeit‰
     * vajavainen viel‰, parametrej‰ ei k‰ytet‰
     * @param hakuehto hakuehto
     * @param i indeksi 
     * @return lˆytyneet liikkeet
     */
    @SuppressWarnings("unused")
    public Collection<Liike> etsi( String hakuehto, int i) {
        Collection<Liike> loytyneet = new ArrayList<Liike>();
        for (Liike liike : this) {
            loytyneet.add(liike);
        }
        return loytyneet;
    }

    /**
     * Korvaa liikkeen, jolla sama liikeID, muuten lis‰‰ liikkeen uutena
     * @param liike jota tutkitaan
     * @example
     * <pre name="test">
     * 
     * Liikkeet testLiikkeet2 = new Liikkeet();
     * 
     * Liike yks = new Liike();
     * yks.rekisteroi();
     * Liike kaks = new Liike();
     * kaks.rekisteroi();
     * Liike kolm = new Liike();
     * kolm.rekisteroi();
     * 
     * testLiikkeet2.lisaa(yks);
     * testLiikkeet2.lisaa(kaks);
     * testLiikkeet2.lisaa(kolm);
     * 
     * Liike nelj = new Liike();
     * nelj.rekisteroi();
     * 
     * testLiikkeet2.korvaaTaiLisaa(nelj);
     * 
     * testLiikkeet2.getLkm() === 4;
     * testLiikkeet2.annaLiikkeenIndeksi(nelj) === 3;
     * Liike viis = new Liike();
     * try {
     * viis = yks.clone();
     * } catch (CloneNotSupportedException e) {
     * //
     * }
     * testLiikkeet2.korvaaTaiLisaa(viis);
     * testLiikkeet2.getLkm() === 4;
     * testLiikkeet2.annaLiikkeenIndeksi(viis) === 0;
     * 
     * </pre>
     *
     */
    public void korvaaTaiLisaa(Liike liike) {
        int id = liike.getLiikeID();
        for (int i = 0; i < lkm; i++) {
            if ( alkiot[i].getLiikeID() == id) {
                alkiot[i] = liike;
                muutettu = true;
                return;
            }
        }
        lisaa(liike);
    }

    /**
     * Palauttaa liikkeen indeksin
     * @param liike liike jota tutkitaan
     * @return indeksi
     */
    public int annaLiikkeenIndeksi(Liike liike) {
    	for (int i = 0; i < alkiot.length; i++) {
			if (alkiot[i].getLiikeID() == liike.getLiikeID()) return i;
		}
    	return -1;
    }
    
    /**
     * Poistaa liikkeen 
     * @param liike joka poistetaan
     * @return 1 jos onnistui, 0 jos ei onnistunut
     * @example
     * <pre name="test">
     * 
     * Liikkeet testLiikkeet = new Liikkeet();
     * 
     * Liike yksi = new Liike();
     * yksi.rekisteroi();
     * Liike kaksi = new Liike();
     * kaksi.rekisteroi();
     * Liike kolme = new Liike();
     * kolme.rekisteroi();
     * 
     * testLiikkeet.lisaa(yksi);
     * testLiikkeet.lisaa(kaksi);
     * testLiikkeet.lisaa(kolme);
     * 
     * testLiikkeet.getLkm() === 3;
     * 
     * testLiikkeet.poista(kaksi);
     * 
     * testLiikkeet.getLkm() === 2;
     * 
     * testLiikkeet.annaLiikkeenIndeksi(yksi) === 0;
     * testLiikkeet.annaLiikkeenIndeksi(kolme) === 1;
     * 
     * </pre>
     * 
     */
    public int poista(Liike liike) { 
    	int ind = annaLiikkeenIndeksi(liike); 
    	if (ind < 0) return 0; 
    	lkm--; 
    	for (int i = ind; i < lkm; i++) 
    		alkiot[i] = alkiot[i + 1]; 
    	alkiot[lkm] = null; 
    	muutettu = true; 
    	return 1; 
    } 
}
