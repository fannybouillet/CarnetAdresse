package ch.makery.address;

//import
import java.io.IOException;
import java.time.LocalDate;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //Données de la liste observable = si changement, on est averti
    private ObservableList<Person> personData = FXCollections.observableArrayList();


    public MainApp() {
        // Quelques données pour test
        personData.add(new Person("Fanny", "Bouillet","4 rue des pommes",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Anthony", "Da Cruz","4 rue des fraises",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Estelle", "Avarello","4 rue des poires",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Floriane", "Monvoisin","4 rue des oranges",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Sana", "Yahyaoui","4 rue des peches",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Damien", "Dabernat","4 rue des cerises",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Natacha", "Patey","4 rue des mangues",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Laurine", "Peyrard","4 rue des myrtilles",74000,"ANNECY",40738263,"contact@pioupiou.com"));
        personData.add(new Person("Clement", "Giroud","4 rue des abricots",74000,"ANNECY",40738263,"contact@pioupiou.com"));
    }
  
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
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
    
    
    //getteur PrimaryStage
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}