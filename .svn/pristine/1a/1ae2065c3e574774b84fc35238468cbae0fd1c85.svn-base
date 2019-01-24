package fxTreeni;


import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import treeni.Tag;
import treeni.Treenipankki;

/**
 * Controlleri tagien muokkausikkunaa varten
 * @author Lars ≈hman
 * @version 23.3.2017
 *
 */
public class TagGUIController implements ModalControllerInterface<Treenipankki> { //, Initializable


    @FXML private ListChooser<Tag> chooserTag;
    @FXML private TextField textTag;
    @FXML private Button buttonSulje;
   
    /*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alusta();
        
    }
    
    protected void alusta() {
        //
    }
    */
    @Override
    public Treenipankki getResult() {
        return pankki;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Treenipankki oletus) {
        pankki = oletus;
        naytaTagit();
    }
    
    /**
     * Suljetaan ikkuna
     */
    @FXML void handleSulje() {
        sulje(buttonSulje);
    }

    /**
     * K‰sitell‰‰n tagin poisto
     */
    @FXML void handlePoista() {
        poistaTag();
    }

    /**
     * K‰sitell‰‰n tagin lis‰‰minen
     */
    @FXML void handleLisaaTag() {
        lisaaTag();
    }
    
  //============================================================================================  
    private Treenipankki pankki;
    
    /**
     * Aliohjelma joka sulkee nykyisen ikkuna
     * @param button
     */
    private void sulje(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    /**
     * 
     */
    public void naytaTagit() {
        haeTag(0);
    }
    
    public void lisaaTag() {
        if(!textTag.getText().trim().equals("")) {
        Tag tag = new Tag(textTag.getText());
        pankki.lisaa(tag);
        haeTag(tag.getTagID());
        textTag.clear();
        }
    }
    
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
 
    @FXML void poistaTag() {
        //TODO
    }

    public static Treenipankki avaaIkkuna(Stage modalityStage, Treenipankki oletus) {
        return ModalController.<Treenipankki, TagGUIController>showModal(
                TagGUIController.class.getResource("TagView.fxml"),
                "Muokkaa",
                modalityStage, oletus, null);
        
    } 
}
