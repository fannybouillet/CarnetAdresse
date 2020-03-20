package ch.makery.address;

//imports
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
    
    //Donn�es de la liste observable = si changement, on est averti
    private ObservableList<Person> personData = FXCollections.observableArrayList();


    public MainApp() {
    }
  
    // retourne les donn�es de la liste observ�e
    public ObservableList<Person> getPersonData() {
        return personData;
    }
  

    // cr�ation de la page principale
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Carnet Adresse"); //le nom de l'appli

        // appel de la page principale(Stage) et de son contenu (sc�ne)
        initRootLayout();
        showPersonOverview();
    }
    

    public void initRootLayout() {
        try {
            // Chargement du fichier FXML RootLayout(page principale)
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Ajout de la sc�ne � l'int�rieur
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
         // Donne l'acc�s au controller RootLayout
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
    

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Charge personOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Placement au centre de la fen�tre principale
            rootLayout.setCenter(personOverview);

            // Donne l'acc�s au controller
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //Ouvre la fenetre de modification avec les donn�es de la personne s�lectionn�e
    // si on valide �a modifie ect and true is returned.
   //retourne vrai si Valider est cliquer sinon faux

    public boolean showPersonEditDialog(Person person) {
        try {
            // Chargement de la fenetre PersonEditDialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
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
    //Gestion des donn�es
    // Retourne le dernier fichier ouvert. Si aucun fichier alors null est retourn�

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    //  D�finit le chemin d'acc�s au fichier actuellement charg�
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
    // Charge les donn�es du fichier sp�cifi�

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            //
            Unmarshaller um = context.createUnmarshaller();

            // Lit les donn�es du fichier XML et  la m�thode unmarshal() permet de cr�er les diff�rents objets
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
            alert.setContentText("Impossible de charger les donn�es du fichier :\n" + file.getPath());

            alert.showAndWait();
        }
    }

    // enregistrer le contact dans le fichier

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();//permet de formater le document XML
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//propri�t� qui indique que le XML doit etre format�(true)

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling and saving XML to the file.
            //L'appel de la m�thode marshal() formate le document et pr�cise o� est envoy� le r�sultat
            m.marshal(wrapper, file);

            // Sauvegarde des donn�es dans le fichier sp�cifi�
            setPersonFilePath(file);
        } catch (Exception e) { //Message d'erreur 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Enregistrement impossible");
            alert.setContentText("Impossible d'enregistrer les donn�es du fichier :\n" + file.getPath());

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