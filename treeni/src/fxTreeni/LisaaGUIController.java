package fxTreeni;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import treeni.Liike;

/**
 * Controller liikkeen lis‰yst‰ ja muokkaamista varten
 * @author Lars ≈hman
 * @version 23.3.2017
 *
 */
public class LisaaGUIController implements ModalControllerInterface<Liike> { //,Initializable

    @FXML private Button buttonTallenna;    
    @FXML private Button buttonPeruuta;    
    @FXML private TextField editNimi;
    @FXML private ComboBoxChooser<String> editVaikeus;
    @FXML private TextArea editKuvaus;    
    @FXML private Label labelVirhe;
    private Liike liikeKohdalla = null;
    
    /**
     * K‰sitell‰‰n tehtyjen muokkausten tallennus
     */
    @FXML void handleVieMuokkaus() {
    	if (editNimi.getText().trim().equals("")) {
    		naytaVirhe("Nimi ei saa olla tyhj‰");
            return;
    	}
    	liikeKohdalla.setNimi(editNimi.getText());
    	liikeKohdalla.setVaikeus(editVaikeus.getSelectedIndex() +1 );
    	liikeKohdalla.setKuvaus(editKuvaus.getText());
        
        ModalController.closeStage(labelVirhe);
    }
    
    /**
     * Laittaa halutun virheen virheLabeliin
     * @param virhe
     */
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    /**
     * Suljetaan nykyinen ikkuna
     */
    @FXML void handlePeruuta() {
        liikeKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    /*
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
        
   
    }
    
    protected void alusta() {
    	

    }
    */
    
    /**
     * Tyhjent‰‰ kent‰t
     * 
     */
    
    public void tyhjenna() {
        editNimi.setText("");
        editVaikeus.setSelectedIndex(0);
        editKuvaus.setText("");
        
    }

    

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Liike oletus) {
        liikeKohdalla = oletus;
        naytaLiike(liikeKohdalla);
        
        
        Stage stage = (Stage) labelVirhe.getScene().getWindow();
        
        stage.setOnCloseRequest(e -> { 
        	liikeKohdalla = null;
        	stage.close();
        });
    	
        
        
    }
    
    @Override
    public Liike getResult() {
        return liikeKohdalla;
    }

    //=========================================================================================================
   /**
    * Luodaan liikkeen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
    * @param modalityStage mille ollaan modaalisia, null = sovellukselle
    * @param oletus mit‰ dataa n‰ytet‰‰n oletuksena
    * @param otsikko mik‰ on ikkunan otsikko
    * 
    * @return null jos painetaan peruuta, muuten palauttaa muutetun liikkeen
    */
    public static Liike kysyLiike(Stage modalityStage, Liike oletus, String otsikko) {
        return ModalController.<Liike, LisaaGUIController>showModal(
                LisaaGUIController.class.getResource("LisaaView.fxml"),
                otsikko,
                modalityStage, oletus, null);
    }


    /**
     * Laittaa liikkeen tiedot dialogin kenttiin
     * @param liike Liike jota k‰sitell‰‰n
     */
    public void naytaLiike(Liike liike) {
        if (liike == null) return;
        editNimi.setText(liikeKohdalla.getNimi()); 
        editVaikeus.setSelectedIndex(liike.getVaikeus() - 1);
        if (liike.getVaikeus() == 0) editVaikeus.setSelectedIndex(0);
        editKuvaus.setText(liikeKohdalla.getKuvaus());
    }

}
