package com.belatrix.person;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        read();
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

}
