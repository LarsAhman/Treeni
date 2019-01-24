package fxTreeni;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlleri tiedoston nimen kysymist� varten
 * @author Lars �hman
 * @version 20.4.2017
 *
 */
public class NimiGUIController implements ModalControllerInterface<String> {

    @FXML private TextField textVastaus;
    private String vastaus = null;
    
    @FXML void handleOk() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }
    
    
    
    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void handleShown() {
        textVastaus.requestFocus();        
    }

    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);        
    }

    /**
     * @param modalityStage Mille ollaan modaalisia
     * @param oletus Mik� on oletus tiedoston nimi
     * @return K�ytt�j�n antaman vastauksen tiedoston nimeksi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(NimiGUIController.class.getResource("TreeniNimiView.fxml"), 
                "Treeni", modalityStage, oletus);
    }
    
}
