package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;


public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    //Is called by the main application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    //Cr�e un carnet d'adresse vide
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    // Ouvre un browser pour chercher un fichier � ouvrir et charge les donn�es
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    // Sauvegarde dans le fichier courrant.
     
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    //Ouvre un browser pour sauvegarde en tant que...
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }


    //Ferme l'application    
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}