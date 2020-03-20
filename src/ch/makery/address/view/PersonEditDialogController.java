
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Label;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;


public class PersonEditDialogController {

	@FXML
	private TextField prenomField;
	@FXML
	private TextField nomField;
	@FXML
	private TextField adresseField;
	@FXML
	private TextField codePostalField;
	@FXML
	private TextField villeField;
	@FXML
	private TextField anniversaireField;
	@FXML
	private TextField mailField;
	@FXML
	private TextField numeroField;

	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	//Modification de la personne
	public void setPerson(Person person) {
		this.person = person;

		prenomField.setText(person.getPrenom());
		nomField.setText(person.getNom());
		adresseField.setText(person.getAdresse());
		codePostalField.setText(Integer.toString(person.getCodePostal()));
		villeField.setText(person.getVille());
		anniversaireField.setText(DateUtil.format(person.getAnniversaire()));
		anniversaireField.setPromptText("dd.mm.yyyy");
		mailField.setText(person.getMail());
		numeroField.setText(person.getNumero());
	}

	// retourne ok si l'utilisateur clique sur valider = permet de déterminer si on a appuyer sur valider
	public boolean isOkClicked() {
		return okClicked;
	}

	// Bouton valider = modification
	@FXML
	private void handleOk() {
		//si vérification de la saisie est ok
		if (isInputValid()) {
			//modification
			person.setPrenom(prenomField.getText());
			person.setNom(nomField.getText());
			person.setAdresse(adresseField.getText());
			person.setCodePostal(Integer.parseInt(codePostalField.getText()));
			person.setVille(villeField.getText());
			person.setAnniversaire(DateUtil.parse(anniversaireField.getText()));
			person.setMail(mailField.getText());
			person.setNumero(numeroField.getText());
			//retroune vrai et ferme la fenetre
			okClicked = true;
			dialogStage.close();
		}
	}

	//Bouton annuler = ferme la fenetre
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	//Vérification de la saisie
	private boolean isInputValid() {
		//initialise le message d'erreur
		String msgErreur = "";
		boolean b = true;
		
		//si les attributs sont null ou vide alors j'affiche le message
		if (prenomField.getText() == null || prenomField.getText().length() == 0) {
			msgErreur += "Le prénom est vide!\n";
		}
		
		if (nomField.getText() == null || nomField.getText().length() == 0) {
			msgErreur += "Le nom est vide!\n";
		}
		
		if (numeroField.getText() == null || numeroField.getText().length() == 0) {
			msgErreur += "Le numéro de téléphone est vide !\n";
		} else {
			// On essaie de transformer le String en float pour vérifier si c'est que des chiffres 
			try {				
				Float f = Float.parseFloat(numeroField.getText());
			} catch (NumberFormatException e) {
				b = false;
			}
			if(b == false) {
				msgErreur +="Le numéro doit contenir uniquement des chiffres";

			}
		}
	
			if (msgErreur.length() == 0) {
				return true;
			} else {
				// Message d'erreur
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Champs sont invalides");
				alert.setHeaderText("Merci de renseigner correctement tous les champs obligatoires");
				alert.setContentText(msgErreur);

				alert.showAndWait();
				return false;
			}
		}
	}