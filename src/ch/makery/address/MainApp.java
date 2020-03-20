package ch.makery.address;

//import
import java.io.IOException;
import java.time.LocalDate;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //Donn�es de la liste observable = si changement, on est averti
    private ObservableList<Person> personData = FXCollections.observableArrayList();


    public MainApp() {
        // Quelques donn�es pour test
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

            // Placement au centre de la fen�tre principale
            rootLayout.setCenter(personOverview);

            // Donne l'acc�s au controller
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
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