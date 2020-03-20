
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
        numeroField.setText(Integer.toString(person.getNumero()));
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
            person.setNumero(Integer.parseInt(numeroField.getText()));
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
        //si les attributs sont null ou vide alors j'affiche le message
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            msgErreur += "Le prénom est vide!\n";
        }
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            msgErreur += "Le nom est vide!\n";
        }
        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
            msgErreur +="Adresse est vide!\n";
        }

        if (codePostalField.getText() == null || codePostalField.getText().length() == 0) {
            msgErreur += "Le code postal est vide!\n";
        } else {
            // On essaie de transformer le String en int
            try {
                Integer.parseInt(codePostalField.getText());
            } catch (NumberFormatException e) {
                msgErreur += "Le code postal est invalide!\n"
                		+ "Le code postal doit etre composer de chiffre uniquement";
            }
        }
        if (numeroField.getText() == null || numeroField.getText().length() == 0) {
            msgErreur += "Le numéro de téléphone est vide!\n";
        } else {
            // On essaie de transformer le String en int
            try {
                Integer.parseInt(numeroField.getText());
            } catch (NumberFormatException e) {
                msgErreur += "Le numéro est invalide!\n"
                		+ "Le numéro doit etre composer de chiffre uniquement";
            }
        }

        if (villeField.getText() == null || villeField.getText().length() == 0) {
            msgErreur += "La ville est vide!\n";
        }
        if (mailField.getText() == null || mailField.getText().length() == 0) {
            msgErreur += "La mail est vide!\n";
        }

        if (anniversaireField.getText() == null || anniversaireField.getText().length() == 0) {
            msgErreur += "L'anniversaire est vide!\n";
        } else {
            if (!DateUtil.validDate(anniversaireField.getText())) {
                msgErreur += "L'anniversaire est invalide. Utilisez le format : dd.mm.yyyy!\n";
            }
        }

        if (msgErreur.length() == 0) {
            return true;
        } else {
            // Message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Les champs sont invalides");
            alert.setHeaderText("Merci de renseigner correctement tous les champs");
            alert.setContentText(msgErreur);
            
            alert.showAndWait();
            return false;
        }
    }
}