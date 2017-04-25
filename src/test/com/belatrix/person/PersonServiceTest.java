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

        int size = 0;

        // Insert user
        PersonService personService = PersonService.getInstance();
        boolean inserted = personService.insert("Kei", "Takayama", "18273645", 40, "m");

        // List user
        List<Person> list = personService.read();
        size = list.size();

        Assert.assertEquals("Kei", list.get(size - 1).getName());
        Assert.assertEquals("Takayama", list.get(size - 1).getLastName());
        Assert.assertEquals("18273645", list.get(size - 1).getDni());
        Assert.assertEquals(40, list.get(size - 1).getAge());
        Assert.assertEquals("m", list.get(size - 1).getGender());

        // Delete user
        boolean deleted = personService.delete(list.get(list.size() - 1).getId());

        // List user
        list = personService.read();

        Assert.assertEquals(size - 1, list.size());
    }
}
