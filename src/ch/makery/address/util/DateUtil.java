package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    //format final que nous désirons
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    //Retourne la date donnée en bon format String 

    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    // Converti un String au bon format en date. Retourne null si c'est pas convertible    		 
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    //Vérifie si la chaîne est une date valide.Retourne vrai si la chaîne est une date valide
    public static boolean validDate(String dateString) {
        // Essai de transformer le string si non null
        return DateUtil.parse(dateString) != null;
    }
}