package ch.makery.address.model;
import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.makery.address.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	//property sert à etre averti en cas de changement (design pattern observer)
	//il est obligatoire d'utiliser la propriété JavaFX, comme la liste de personnes rendue avec tableView qui est modifiable. 
	//Pour refléter les modifications immédiatement dans la cellule modifiée, le champ lié sous-jacent doit être une property.
    private final StringProperty prenom;
    private final StringProperty nom;
    private final StringProperty adresse;
    private final IntegerProperty codePostal;
    private final StringProperty ville;
    private final StringProperty mail;
    private final StringProperty numero;
    private final ObjectProperty<LocalDate> anniversaire;
    

    //constructeur
    public Person() {
        this(null, null, null, 0, null, null, null);
    }
    public Person(String prenom, String nom,String adresse, int cp, String ville,String num,String mail) {
    	this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.adresse = new SimpleStringProperty(adresse);
        this.codePostal = new SimpleIntegerProperty(cp);
        this.ville = new SimpleStringProperty(ville);
        this.mail = new SimpleStringProperty(mail);
        this.numero = new  SimpleStringProperty(num);
        this.anniversaire = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

	//prenom
    public String getPrenom() {
        return prenom.get();
    }
    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }
    public StringProperty prenomProperty() {
        return prenom;
    }
    //nom
    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    public StringProperty nomProperty() {
        return nom;
    }
    //adresse
    public String getAdresse() {
        return adresse.get();
    }
    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }
    public StringProperty adresseProperty() {
        return adresse;
    }
    //CP
    public int getCodePostal() {
        return codePostal.get();
    }
    public void setCodePostal(int codePostal) {
        this.codePostal.set(codePostal);
    }
    public IntegerProperty codePostalProperty() {
        return codePostal;
    }
    //ville
    public String getVille() {
        return ville.get();
    }
    public void setVille(String ville) {
        this.ville.set(ville);
    }
    public StringProperty villeProperty() {
        return ville;
    }
    //mail
    public String getMail() {
        return mail.get();
    }
    public void setMail(String mail) {
        this.mail.set(mail);
    }
    public StringProperty mailProperty() {
        return mail;
    }
    //numero
    public String getNumero() {
        return numero.get();
    }
    public void setNumero(String numero) {
        this.numero.set(numero);
    }
    public StringProperty numeroProperty() {
        return numero;
    }
    //anniversaire
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getAnniversaire() {
        return anniversaire.get();
    }
    public void setAnniversaire(LocalDate anniversaire) {
        this.anniversaire.set(anniversaire);
    }    

}