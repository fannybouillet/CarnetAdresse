package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ch.makery.address.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


class Tests {

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

	
}



	
	
	


