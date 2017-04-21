package com.belatrix.person;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        read();
    }

    public static void read() throws Exception {

        PersonService personService = PersonService.getInstancia();
        List<Person> people = personService.read();

        for (int i = 0; i < people.size(); i++) {
            System.out.println("ID: " + people.get(i).getId()
                    + " - Name: " + people.get(i).getName()
                    + " - Last Name: " + people.get(i).getLastName()
                    + " - DNI: " + people.get(i).getDni()
                    + " - Age: " + people.get(i).getAge()
                    + " - Gender: " + people.get(i).getGender());
        }
    }

    public static void insert() throws Exception {
        PersonService personService = PersonService.getInstancia();

        boolean result  = personService.insert("Juan", "Ramirez", "87654321", 30, "m");

        if(result) {
            System.out.println("Correct");
            read();
        }
        else {
            System.out.println("Error");
        }
    }

}
