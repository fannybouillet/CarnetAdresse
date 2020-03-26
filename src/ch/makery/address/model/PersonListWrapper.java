package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Classe d'aide pour envelopper une liste de personnes. Il est utilisé pour enregistrer la liste des personnes au format XML.

@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }
    
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}