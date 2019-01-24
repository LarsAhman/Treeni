/*
 * TODO:
 * poista metodi k‰ytt‰m‰‰n equalsia eik‰ i--:sta. Tageilla haku myˆs kaipaa t‰t‰.
 * 
 * Haku kuntoon
 * 
 * alusta virheilmoitukset pois
 * 
 * Text wrap kuvauslaatikkoon?
 */

package fxTreeni;
	
import javafx.application.Application;
import javafx.stage.Stage;
import treeni.Treenipankki;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * Treenipankki -ohjelma erilaisten liikkeiden s‰‰st‰miseen.
 * @author Lars ≈hman
 * @version 14.2.2017
 *
 */
public class TreeniMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//VBox root = (VBox)FXMLLoader.load(getClass().getResource("TreeniGUIView.fxml"));
			
			final FXMLLoader ldr = new FXMLLoader(getClass().getResource("TreeniGUIView.fxml"));
			final Pane root = (Pane) ldr.load();
			final TreeniGUIController treeniCtrl = (TreeniGUIController) ldr.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("treeni.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Treenipankki pankki = new Treenipankki(); 
			treeniCtrl.setTreenipankki(pankki);
			treeniCtrl.avaa();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/** P‰‰ohjelma
	 * @param args -
	 */
	public static void main(String[] args) {
		launch(args);
		
	}
}
