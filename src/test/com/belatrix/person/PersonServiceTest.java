package com.belatrix.person;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by anthony on 4/20/17.
 */
public class PersonServiceTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void demo() throws Exception{
        // Insert user
        PersonService personService = PersonService.getInstance();
        boolean inserted = personService.insert("Kei", "Takayama", "18273645", 40, "m");

        // List user
        List<Person> list = personService.read();
        for (Person p: list) {
            System.out.println(
                    "ID: "              + p.getId()
                +   " - Name: "         + p.getName()
                +   " - Last Name: "    + p.getLastName()
                +   " - DNI: "          + p.getDni()
                +   " - Age: "          + p.getAge()
                +   " - Gender: "       + p.getGender());
        }

        int index = list.size();
        Assert.assertTrue(list.get(index -1).getDni().equals("18273645"));
        Assert.assertTrue(list.get(index -1).getName().equals("Kei"));
        Assert.assertTrue(list.get(index -1).getLastName().equals("Takayama"));

        // Delete user
        boolean deleted = personService.delete(list.get(list.size() - 1).getId());

        // List user
        list = personService.read();
        for (Person p: list) {
            System.out.println(
                    "ID: "              + p.getId()
                +   " - Name: "         + p.getName()
                +   " - Last Name: "    + p.getLastName()
                +   " - DNI: "          + p.getDni()
                +   " - Age: "          + p.getAge()
                +   " - Gender: "       + p.getGender());
        }
    }
}
