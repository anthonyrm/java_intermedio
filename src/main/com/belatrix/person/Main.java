package com.belatrix.person;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        writePersonInFile();
    }

    public static void read() throws Exception {

        PersonService personService = PersonService.getInstance();
        List<Person> people = personService.read();

        for (Person p: people) {
            System.out.println(
                        "ID: "              + p.getId()
                    +   " - Name: "         + p.getName()
                    +   " - Last Name: "    + p.getLastName()
                    +   " - DNI: "          + p.getDni()
                    +   " - Age: "          + p.getAge()
                    +   " - Gender: "       + p.getGender());
        }
    }

    public static void insert() throws Exception {
        PersonService personService = PersonService.getInstance();

        boolean result  = personService.insert("Juan", "Ramirez", "87654321", 30, "m");

        if(result) {
            System.out.println("Correct");
            read();
        }
        else {
            System.out.println("Error");
        }
    }

    public static void delete() throws Exception {
        PersonService personService = PersonService.getInstance();

        boolean result  = personService.delete(3);

        if(result) {
            System.out.println("Correct");
            read();
        }
        else {
            System.out.println("Error");
        }
    }

    public static void update() throws Exception {
        PersonService personService = PersonService.getInstance();

        boolean result  = personService.update(2,"Juan", "Ramirez", "87654321", 30, "m");

        if(result) {
            System.out.println("Correct");
            read();
        }
        else {
            System.out.println("Error");
        }
    }

    public static void usingLocaleAndi18n() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("logging.properties");
        InputStream prop = classLoader.getResourceAsStream("app.properties");
        Properties properties = new Properties();
        Locale aLocale;
        ResourceBundle messages = null;

        try{
            // load app properties
            properties.load(prop);

            // load logging properties
            LogManager.getLogManager().readConfiguration(is);

            // Set locale
            aLocale = new Locale(properties.getProperty("locale.language"),properties.getProperty("locale.country"));
            messages = ResourceBundle.getBundle("MessagesBundle", aLocale);

            logger.log(Level.INFO, messages.getString("labels.generic.init"));
        } catch(IOException ioe) {
            logger.log(Level.SEVERE, messages.getString("labels.generic.error"), ioe);
        }

    }

    public static void usingDateFormat() {
        // Show date in spanish and full
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, new Locale("es", "ES"));
        System.out.print(formatter.getCalendar().getTime());
    }

    public static void usingSimpleDateFormat() throws Exception {
        // Calculate birthday
        String cumple = "08-12-1991";
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.parse(cumple);

        Calendar today = Calendar.getInstance();
        System.out.println(today.get(Calendar.YEAR) - formatter.getCalendar().get(Calendar.YEAR));
    }

    public static void writePersonInFile() throws Exception {
        PersonService personService = PersonService.getInstance();
        List<Person> personList = personService.read();
        FileOutputStream fileOutputStream;
        File personFile = new File("personList.txt");
        String inputText;
        String header = "Id\t\tName\t\tLasName\t\tDni\t\tGender\t\tAge\n";

        try {
            fileOutputStream = new FileOutputStream(personFile);
            if(!personFile.exists()) {
                personFile.createNewFile();
            }

            fileOutputStream.write(header.getBytes());
            for (Person p: personList) {
                inputText = p.toString();
                fileOutputStream.write(inputText.getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch(FileNotFoundException fnfe) {
            System.out.print("Error" + fnfe.getMessage());
        }
    }

}
