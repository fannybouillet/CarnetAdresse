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
    private MainApp mainApp;


    // Initializes the controller class. This method is automatically called after the fxml file has been loaded.
    
    @FXML
    private void initialize() {
    	// Initialise le tableau avec 2 colonnes
        prenomColonne.setCellValueFactory(
        		cellData -> cellData.getValue().prenomProperty());
        nomColonne.setCellValueFactory(
        		cellData -> cellData.getValue().nomProperty());
        
        // Efface la partie détails
        showPersonDetails(null);
        
        //Design Pattern observer  
        // Écoute les changements de sélection et affiche les détails de la personne lorsqu'elle est modifiée.
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Ajoute la liste observée à la tableView
        personTable.setItems(mainApp.getPersonData());
    }
    
    // Remplit tous les text fields pour voir les détails des personnes. Si pas de sélection alors les text fields sont vides

    private void showPersonDetails(Person person) {
    	if (person != null) {
    		// Si la personne n'est pas nulle, on remplit les text fields
    		prenomLabel.setText(person.getPrenom());
    		nomLabel.setText(person.getNom());
    		adresseLabel.setText(person.getAdresse());
    		codePostalLabel.setText(Integer.toString(person.getCodePostal()));
    		villeLabel.setText(person.getVille());
    		anniversaireLabel.setText(DateUtil.format(person.getAnniversaire()));
    		mailLabel.setText(person.getMail());
    		numeroLabel.setText(person.getNumero());
    	} else {
    		//Sinon on efface les text fields
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
    //permet de supprimer une personne
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
            // Si pas de contact sélectionné message d'erreur
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Pas de contact sélectionné");
            alert.setContentText("Veuillez cliquer sur un des contact dans le tableau afin de le modifier.");

            alert.showAndWait();
        }
    }

}