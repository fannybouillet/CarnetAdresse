package ch.makery.address.view;
import ch.makery.address.model.Person;
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
    		numeroLabel.setText(person.getNumero());
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
    //Bouton qui permet de supprimer une personne
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Vous n'avez pas sélectionnez de contact");
            alert.setContentText("Veuillez sélectionner un contact svp.");

            alert.showAndWait();
        }
    }
    // appel la fenetre Edit pour ajouter une nouvelle personne (les champs sont vides)
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    // Appel la fenetre Edit pour modifier le contact sélectionne (les champs sont remplis de la personne selectionnée)

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Si pas de contact sélectionné
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Pas de contact sélectionné");
            alert.setContentText("Veuillez cliquer sur un des contact dans le tableau afin de le modifier.");

            alert.showAndWait();
        }
    }

}