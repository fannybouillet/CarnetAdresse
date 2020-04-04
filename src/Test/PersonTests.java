package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ch.makery.address.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;


class PersonTests {

	//données de base pour créer mes tests
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    private TableView<Person> personTable;
    Person p = new Person("Paul","Martinez","4 rue des ananas",75960, "Paris","0496587420","contact@martinez.com");
	
    
	@Test
	public void verifModifyPerson(){
		String prenom= "Pierre";
		p.setPrenom(prenom);
		assertEquals(prenom, p.getPrenom());
	}
	
	
	@Test
	public void verifAddPerson(){
		getPersonData().add(p);
		assertEquals(1,personData.size());
	}
	
	// test isInputValid
	//initialisation des données de vérification
	String verifMessage="";
	boolean verifBoolean;
	String prenom , nom =null, numero =null;
	//méthode
	private boolean isInputValid(String prenom, String nom, String numero) {
		//initialise le message d'erreur
		String msgErreur = "";
		boolean b = true;
		//si les attributs sont null ou vide alors j'affiche le message
		if (prenom == null || prenom.length() == 0) {
			msgErreur += "Le prénom est vide!";
		}
		if (nom == null || nom.length() == 0) {
			msgErreur += "Le nom est vide!";
		}
		if (numero == null || numero.length() == 0) {
			msgErreur += "Le numéro de téléphone est vide!";
		} else {
			// On essaie de transformer le String en float pour vérifier si c'est que des chiffres 
			try {				
				Float f = Float.parseFloat(numero);
			} catch (NumberFormatException e) {
				b = false;
			}
			if(b == false) {
				msgErreur +="Le numéro doit contenir uniquement des chiffres";
			}
		}
		if (msgErreur.length() == 0) {
			verifBoolean=true; //ajout de mon controle (avant le return sinon crash)
			return true;
		} else {
			verifBoolean =false; //ajout de mes controles 
			verifMessage = msgErreur;
			return false;
		}

	}
	
	//si tout est null
	@Test
	public void verifIsinputValid(){
		isInputValid(null, null, null);
		assertEquals("Le prénom est vide!"+"Le nom est vide!"+"Le numéro de téléphone est vide!",verifMessage);
		assertEquals(false,verifBoolean);
	}
	//Si le numéro ne contient pas uniquement des chiffres
	@Test
	public void verifIsinputValid2(){
		isInputValid(null, null, "073Y373");
		assertEquals("Le prénom est vide!"+"Le nom est vide!"+"Le numéro doit contenir uniquement des chiffres",verifMessage);
		assertEquals(false,verifBoolean);	
	}
	//Si les champs sont vides
	@Test
	public void verifIsinputValid3(){
		isInputValid("", "", "");
		assertEquals("Le prénom est vide!"+"Le nom est vide!"+"Le numéro de téléphone est vide!",verifMessage);
		assertEquals(false,verifBoolean);
	}
	//Si tout est ok
	@Test
	public void verifIsinputValid4(){
		isInputValid("Paul", "RICOUD", "0967575547");
		assertEquals("",verifMessage);
		assertEquals(true,verifBoolean);
	}
	//Si seulement nom ok
	@Test
	public void verifIsinputValid5(){
		isInputValid("", "HALS", "");
		assertEquals("Le prénom est vide!"+"Le numéro de téléphone est vide!",verifMessage);
		assertEquals(false,verifBoolean);
	}
	//Si seulement prenom ok
	@Test
	public void verifIsinputValid6(){
		isInputValid("Romuald", "", "");
		assertEquals("Le nom est vide!"+"Le numéro de téléphone est vide!",verifMessage);
		assertEquals(false,verifBoolean);
	}
	//Si seulement numero ok
	@Test
	public void verifIsinputValid7(){
		isInputValid("", "", "369274934");
		assertEquals("Le prénom est vide!"+"Le nom est vide!",verifMessage);
		assertEquals(false,verifBoolean);
	}
	

	
}



	
	
	


