package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> prenomColonne;
    @FXML
    private TableColumn<Person, String> nomColonne;

    @FXML
    private Label prenomLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label codePostalLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label anniversaireLabel;
    @FXML 
    private Label mailLabel;
    @FXML
    private Label numeroLabel;

    // Reference to the main application.
    private MainApp mainApp;

    // The constructor is called before the initialize() method.
    public PersonOverviewController() {
    }

    // Initializes the controller class. This method is automatically called after the fxml file has been loaded.
    
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        prenomColonne.setCellValueFactory(
        		cellData -> cellData.getValue().prenomProperty());
        nomColonne.setCellValueFactory(
        		cellData -> cellData.getValue().nomProperty());
        
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    //Is called by the main application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
    //Fills all text fields to show details about the person. If the specified person is null, all text fields are cleared.

    private void showPersonDetails(Person person) {
    	if (person != null) {
    		// Fill the labels with info from the person object.
    		prenomLabel.setText(person.getPrenom());
    		nomLabel.setText(person.getNom());
    		adresseLabel.setText(person.getAdresse());
    		codePostalLabel.setText(Integer.toString(person.getCodePostal()));
    		villeLabel.setText(person.getVille());
    		anniversaireLabel.setText(DateUtil.format(person.getAnniversaire()));
    		mailLabel.setText(person.getMail());
    		numeroLabel.setText(Integer.toString(person.getNumero()));
    	} else {
    		// Person is null, remove all the text.
    		prenomLabel.setText("");
    		nomLabel.setText("");
    		adresseLabel.setText("");
    		codePostalLabel.setText("");
    		villeLabel.setText("");
    		anniversaireLabel.setText("");
    		mailLabel.setText("");
    		numeroLabel.setText("");
    	}
    }
    

}