package ch.makery.address;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import ch.makery.address.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //design pattern : observer
    //Données de la liste observable = si changement, on est averti
    private ObservableList<Person> personData = FXCollections.observableArrayList();



    // retourne les données de la liste observée
    public ObservableList<Person> getPersonData() {
        return personData;
    }
  

    // création de la page principale
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Carnet Adresse"); //le nom de l'appli

        // appel de la page principale(Stage) et de son contenu (scène)
        initRootLayout();
        showPersonOverview();
    }
    

    public void initRootLayout() {
        try {
            // Chargement du fichier FXML RootLayout(page principale)
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Ajout de la scène à l'intérieur
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
         // Donne l'accès au controller RootLayout
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Essaye de charger le dernier fichier ouvert
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }
    

    //Montre la vue PersonOverview à l'intérieur de RootLayout.
     
    public void showPersonOverview() {
        try {
            // Charge personOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Placement au centre de la fenêtre principale
            rootLayout.setCenter(personOverview);

            // Donne l'accès au controller
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //Ouvre la fenetre de modification avec les données de la personne sélectionnée
    // si on valide ça modifie ect and true is returned.
   //retourne vrai si Valider est cliquer sinon faux

    public boolean showPersonEditDialog(Person person) {
        try {
            // Chargement de la fenetre PersonEditDialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crée la nouvelle fenetre 
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modification d'un contact");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // modification du contact via le controller
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // afficher la fenetre jusque fermeture (valider/annuler)
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Gestion des données
    // Retourne le dernier fichier ouvert. Si aucun fichier alors null est retourné

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    //  Définit le chemin d'accès au fichier actuellement chargé
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Modification du titre
            primaryStage.setTitle("Carnet d'adresse - " + file.getName());
        } else {
            prefs.remove("filePath");
            // Modification du titre
            primaryStage.setTitle("Carnet d'adresse");
        }
    }
    // Charge les données du fichier spécifié

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            
            Unmarshaller um = context.createUnmarshaller();

            // Lit les données du fichier XML et  la méthode unmarshal() permet de créer les différents objets
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            //vide les contacts
            personData.clear();
            //charge le XML
            personData.addAll(wrapper.getPersons());

            //Enregistre le chemin du fichier
            setPersonFilePath(file);

        } catch (Exception e) { //Message d'erreur 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Chargement impossible");
            alert.setContentText("Impossible de charger les données du fichier :\n" + file.getPath());

            alert.showAndWait();
        }
    }

    // enregistrer le contact dans le fichier

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();//permet de formater le document XML
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//propriété qui indique que le XML doit etre formaté(true)

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling et sauvegarde XML dans le fichier.
            //L'appel de la méthode marshal() formate le document et précise où est envoyé le résultat
            m.marshal(wrapper, file);

            // Sauvegarde des données dans le fichier spécifié
            setPersonFilePath(file);
        } catch (Exception e) { //Message d'erreur 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Enregistrement impossible");
            alert.setContentText("Impossible d'enregistrer les données du fichier :\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    //getteur PrimaryStage
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}