package ch.makery.address.util;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

//Classe qui permet d'enregistrer et charger une date en format XML
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	//transforme un string en local date pour charger et créer les objets
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }
    // transforme les dates en String pour enregistrer en XML
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}