package fxTreeni;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import treeni.Liike;
import treeni.SailoException;
import treeni.Tag;
import treeni.Treenipankki;

/**
 * Controlleri p‰‰n‰kym‰lle
 * @author Lars
 * @version 23.3.2017
 *
 */
public class TreeniGUIController implements Initializable {

	// V‰liaikaisesti t‰ss‰
	 
    @FXML private ListChooser<Tag> chooserTag;
    @FXML private TextField textTag;
    @FXML private Button buttonUusiTag;
    @FXML private Button buttonPoistaTag;
    @FXML private ListChooser<Tag> chooserLiikkeenTagit;
    @FXML private TextField textHakuNimi;
    @FXML private ComboBoxChooser<String> comboHakuVaikeus;
    
	// V‰liaikaisesti t‰ss‰ ^
	
    @FXML private ListChooser<Tag> chooserHakuTagit;
    
    @FXML private BorderPane panelLiike;
    @FXML private ListChooser<Liike> chooserLiikkeet;
	
    @FXML private TextField textTagHaku;
    
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		alusta(); 
	}
	
	/**
	 * Tehd‰‰n hakuehdoilla haku
	 */
    @FXML void handleToteutaHaku() {
    	toteutaHaku();
    }

    /**
     * Poistetaan valittu hakutag
     */
    @FXML void handlePoistaHakuTag() {
    	poistaHakuTag();
    	toteutaHaku();
    }
    
    /**
     * Resetoidaan hakukent‰t
     */
    @FXML void handleResetoiHaku() {
    	textHakuNimi.clear();
    	chooserHakuTagit.clear();
    	comboHakuVaikeus.setSelectedIndex(0);
    	hakuTagit.clear();
    	toteutaHaku();
    }

    /**
     * Lis‰t‰‰n hakutag
     */
	@FXML void handleLisaaHakuTag() {
    	lisaaHakuTag();
    	toteutaHaku();
    }
	
	/**
	 * Avataan haluttu tiedosto
	 */
	@FXML void handleAvaa() {
        avaa();
    }
    
	/**
	 * Yhdistet‰‰n haluttu tagi haluttuun liikkeeseen
	 */
    @FXML void handleYhdista() {
        yhdista();
    }

    /**
     * Poistaa liikkeelt‰ valitun tagin
     */
	@FXML void handlePoistaLiikkeenTag() {
    	poistaLiikkeenTag();
    }


	/**
     * K‰sitell‰‰n koko ohjelman sulkeminen
     */
    @FXML void handleLopeta() {
        tallenna();
        Stage stage = (Stage) chooserLiikkeet.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Avataan Tietoja-ikkuna.
     */
    @FXML void handleTietoja() {
        ModalController.showModal(TreeniGUIController.class.getResource("AboutView.fxml"),
                "Tietoja", null,"");
    }
    
    /**
     * Avataan Muokkaa-ikuna
     */
    @FXML void handleMuokkaaLiike() throws SailoException {
        try {
        	if (liikeKohdalla == null) { 
        		Dialogs.showMessageDialog("Et ole valinnut liikett‰ muokattavaksi.");
        		return;
        	}
            Liike liike;
            liike = LisaaGUIController.kysyLiike(null, liikeKohdalla.clone(), "Muokkaa");
            if ( liike == null ) return;
            pankki.korvaaTaiLisaa(liike);
            haeLiike(liike.getLiikeID());
        } catch (CloneNotSupportedException e) {
           //
        }
    }
    

    
    /**
     * Avataan Lis‰‰ Liike -ikkuna
     */
    @FXML void handleLisaaLiike() {
        try {
            Liike uusi = new Liike();
            uusi = LisaaGUIController.kysyLiike(null, uusi, "Lis‰‰");
            if (uusi == null) return;
            uusi.rekisteroi();
            pankki.lisaa(uusi);
            haeLiike(uusi.getLiikeID());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        }
    }
    
    /**
     * Avataan Muokkaa tageja -ikkuna.
     */
    @FXML void handleMuokkaaTag() {
        TagGUIController.avaaIkkuna(null, pankki);
    }

    /**
     * K‰sitell‰‰n liikkeen poisto
     */
    @FXML void handlePoista() {
    	if ( liikeKohdalla == null ) { Dialogs.showMessageDialog("Et ole valinnut liikett‰ poistettavaksi."); return; }
        pankki.poistaLiike(liikeKohdalla);
        haeLiike(0);
    }
    
    /**
     * Avataan ohjelman k‰yttˆohje
     */
    @FXML void handleKayttoohje() {
        Dialogs.showMessageDialog("Lis‰‰ liike Uusi liike-napista. Lis‰tty‰si myˆs uuden tagin voit yhdist‰‰ tagin liikkeeseen valitsemalla ne ja painamalla yhdist‰. Voit hakea liikkeita nimen, tagien ja vaikeusasteen perusteella");
    }

    /**
     * K‰sitell‰‰n tietojen tallentaminen
     */
    @FXML void handleTallenna() {
        tallenna();
    }
    
    /**
     * Etsit‰‰n tageja hakukent‰n tiedoilla
     */
    @FXML void handleEtsiTageja() {
    	etsiTageja();
    }

     /**
      * Lis‰‰ tagin pankkiin ottaen tagin nimen tekstilaatikosta
      */
     @FXML void lisaaTag() {
    	if(!textTag.getText().trim().equals("")) {
     	Tag tag = new Tag(textTag.getText());
     	pankki.lisaa(tag);
     	haeTag(tag.getTagID());
     	textTag.clear();
    	}
    	  
     }

     /**
      * Poistaa kokonaan valitun tagin, poistaen sen myˆs liikkeilta joille se on annettu 
      */
     @FXML void handlePoistaTag() {
         pankki.poistaTag(tagKohdalla);
         haeTag(0);
         if (pankki.getTageja() < 1) tagKohdalla = null;
         naytaLiike();
     }
 	
    
    // ======================================================================================================
    private Treenipankki pankki;
    private Liike liikeKohdalla;
    private Tag tagKohdalla;
    private Tag liikkeenTagKohdalla;
    private TextArea areaLiike = new TextArea();
    private String nimi = "treeni";
    private Tag hakuTagKohdalla;
    private ArrayList<Tag> hakuTagit = new ArrayList<Tag>();
    
    /**
     * Aliohjelma tietojen tallentamiseen
     */
    private String tallenna() {
        try {
            pankki.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }

    private void yhdista() {
        if (liikeKohdalla == null || tagKohdalla == null) { Dialogs.showMessageDialog("Et ole valinnut tagia tai liikett‰."); return; }
        pankki.yhdista(liikeKohdalla, tagKohdalla);
        
        naytaLiike();
    }
    
    
    /**
     * Avaa ikkunan jossa kysyt‰‰n k‰ytetyn tiedoston nimi
     * @return tiedoston nimen joka halutaan avata
     */
    public boolean avaa() {
    	String uusinimi = NimiGUIController.kysyNimi(null, nimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    /**
     * Lukee halutun tiedoston
     * @param tiedosto mik‰ tiedosto luetaan
     * @return null jos meni hyvin, muuten palauttaa virheen
     */
    protected String lueTiedosto(String tiedosto) {
        this.nimi = tiedosto;
        try {
            pankki.lueTiedostosta(nimi);
            haeLiike(0);
            haeTag(0);
            return null;
        } catch (SailoException e) {
            haeLiike(0);
            haeTag(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }
	
    /**
     * Alustaa kuvausikkunan n‰ytt‰m‰‰n liikkeen chooserien tapahtumissa
     */
    protected void alusta() {
        
    	panelLiike.setCenter(areaLiike);
    	areaLiike.setFont(new Font ("Courier New", 12));
    	areaLiike.setEditable(false);
		
		chooserLiikkeet.clear();
		
		chooserLiikkeet.addSelectionListener(e -> naytaLiike());
		
		chooserTag.addSelectionListener(e -> valitseTag());
		
		chooserLiikkeenTagit.addSelectionListener(e -> valitseLiikkeenTag());
		
		chooserHakuTagit.addSelectionListener(e -> valitseHakuTag());
		
	}
    
    /**
     * Valitsee valitun hakutagin k‰sitelt‰v‰ksi hakutagiksi
     */
    private void valitseHakuTag() {
    	hakuTagKohdalla = chooserHakuTagit.getSelectedObject();
    }
    
    /**
     * Valitsee valitun liikkeen tagin k‰sitelt‰v‰ksi liikkeen tagiksi
     */
    private void valitseLiikkeenTag() {
		liikkeenTagKohdalla = chooserLiikkeenTagit.getSelectedObject();
	}

    /**
     * Valitsee valitun tagin k‰sitelt‰v‰ksi tagiksi
     */
	private void valitseTag() {
    	tagKohdalla = chooserTag.getSelectedObject();
    }
    
    /**
     * Naytt‰‰ kohdalla olevan liikkeen kuvaus-ikkunassa
     */
    protected void naytaLiike() {
    	liikeKohdalla = chooserLiikkeet.getSelectedObject();
    	
    	if (liikeKohdalla == null) return;
    	
    	areaLiike.setText("");
    	try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaLiike)) {
    		liikeKohdalla.tulosta(os);
			
			os.println();
			
			haeLiikkeenTag();
    	}
    }

    /**
     * Asettaa k‰ytetyn pankin
     * @param pankki treenipankki jota halutaan k‰ytt‰‰
     */
	public void setTreenipankki(Treenipankki pankki) {
		this.pankki = pankki;
	}
	
	/**
	 * poistaa liikkeelt‰ valitun tagin
	 */
    private void poistaLiikkeenTag() {
    	if (liikkeenTagKohdalla == null) { Dialogs.showMessageDialog("Et ole valinnut poistettavaa tagia."); return; }
		pankki.poistaLiikkeenTag(liikeKohdalla, liikkeenTagKohdalla);
		naytaLiike();
	}

    /**
     * Etsii tagin annetulla hakuehdolla
     */
    private void etsiTageja() {
    	String s = textTagHaku.getText();
    	
    	haeTag(s);
    	
	}

    /**
     * Lis‰‰ valitun tagien hakutagien chooseriin
     */
    private void lisaaHakuTag() {
		if (tagKohdalla == null) { Dialogs.showMessageDialog("Et ole valinnut tagien laatikosta tagia."); return; }
		
		if (hakuTagit.contains(tagKohdalla)) return;
		
		hakuTagit.add(tagKohdalla);
		
		chooserHakuTagit.clear();
		
		for (Tag tag : hakuTagit) {
			chooserHakuTagit.add(tag.getNimi(), tag);
		}
		
	}
    
    /**
     * Poistaa hakulistasta valitun tagin
     */
    private void poistaHakuTag() {
		if ( hakuTagKohdalla == null ) { Dialogs.showMessageDialog("Et ole valinnut poistettavaa hakutagia."); return; }
		
		hakuTagit.remove(hakuTagKohdalla);
		
		chooserHakuTagit.clear();
		
		for (Tag tag : hakuTagit) {
			chooserHakuTagit.add(tag.getNimi(), tag);
		}
	}

    /**
     * Hakee liikkeen tagit LiikkeenTagit chooseriin
     */
    private void haeLiikkeenTag() {
        chooserLiikkeenTagit.clear();
        
        Collection<Integer> idt = pankki.annaTagIDt(liikeKohdalla);
        
        for (int id : idt) {
            Tag tag = pankki.getTag(id);
            chooserLiikkeenTagit.add(tag.getNimi(), tag);
        } 
     }

    /** Hakee ja lis‰‰ chooseriin liikkeen
     * @param lnro jolla liike haetaan
     */
    protected void haeLiike(int lnro) {
        chooserLiikkeet.clear();

        if (pankki.getLiikkeita() < 1) { chooserLiikkeenTagit.clear(); areaLiike.clear(); liikeKohdalla = null; return; }
        
        int index = 0;
        Collection<Liike> liikkeet;  
        try {  
            liikkeet = pankki.etsi("", 1);  
            int i = 0;  
            for (Liike liike : liikkeet) {  
                if (liike.getLiikeID() == lnro) index = i;  
                chooserLiikkeet.add(liike.getNimi(), liike);  
                i++;  
            }  
        } catch (SailoException ex) {  
            Dialogs.showMessageDialog("Liikkeen hakemisessa ongelmia! " + ex.getMessage());  
        } 
        chooserLiikkeet.getSelectionModel().select(index);
    }
    
    /**
     * Tekee haun hakuehdoilla
     */
	private void toteutaHaku() {
		
		ArrayList<Liike> haetutLiikkeet = new ArrayList<Liike>(); 
		
		String hakuNimi = ".*" + textHakuNimi.getText().trim().toLowerCase() + ".*";
		
		int hakuVaikeus = comboHakuVaikeus.getSelectedIndex();
		
		for (int i = 0; i < pankki.getLiikkeita(); i++) {
			Liike liike = pankki.annaLiike(i);
			if (liike.getNimi().toLowerCase().matches(hakuNimi) && ((liike.getVaikeus() == hakuVaikeus) || hakuVaikeus == 0)  && pankki.sisaltaakoTagit(liike, hakuTagit)) {
				haetutLiikkeet.add(liike);
			}
		}
		
		listaaHaetutLiikkeet(haetutLiikkeet);
	}

	/**
	 * Listaa haetut liikkeet liikkeiden chooseriin
	 * @param haetutLiikkeet liikkeet jotka listataan
	 */
	public void listaaHaetutLiikkeet(ArrayList<Liike> haetutLiikkeet) {
		
		chooserLiikkeet.clear();
   
		for (Liike liike : haetutLiikkeet) {  
			chooserLiikkeet.add(liike.getNimi(), liike);  
		}
		
		if (haetutLiikkeet.contains(liikeKohdalla)) { chooserLiikkeet.setSelectedIndex(haetutLiikkeet.indexOf(liikeKohdalla)); return; }
		
		if (haetutLiikkeet.size() == 0) {
			liikeKohdalla = null;
			liikkeenTagKohdalla = null;
			chooserLiikkeet.clear();
			chooserLiikkeenTagit.clear();
			areaLiike.clear();
			
		}
		
		chooserLiikkeet.setSelectedIndex(0);
		
	}
	
    /**
     * Hakee ja lis‰‰ chooseriin tagin 
     * @param tagID jota haussa k‰ytet‰‰n
     */
    protected void haeTag(int tagID) {
        chooserTag.clear();

        int index = 0;
        for (int i = 0; i < pankki.getTageja(); i++) {
            Tag tag = pankki.getTagI(i);
            if (tag.getTagID() == tagID) index = i;
            chooserTag.add(tag.getNimi(), tag);
        }
        chooserTag.getSelectionModel().select(index);
    }
    
    /**
     * Hakee tageista hakuehtoa vastaavat tagit
     * @param s hakuehto
     */
    protected void haeTag(String s) {
        
        chooserTag.clear();
        
        String hakuehto = ".*" + s.trim() + ".*";
        
        int index = 0;
        for (int i = 0; i < pankki.getTageja(); i++) {
            Tag tag = pankki.getTagI(i);
            if (tag.getNimi().toLowerCase().matches(hakuehto.toLowerCase())) {
                index = i;
                chooserTag.add(tag.getNimi(), tag);
            }
            
        }
        chooserTag.getSelectionModel().select(index);

    }
}
