package com.belatrix.person;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by anthony on 4/25/17.
 */
public class PersonServiceMockitoTest {
    @Test
    public void demo(){
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void mockiteando() throws Exception{
        List<Person> people = new ArrayList<>();
        Person p1 = new Person(1, "Juan", "Perez", "12345678", 30, "m");
        people.add(p1);

        PersonDAO personDAO = mock(PersonDAOImpl.class);
        when(personDAO.read()).thenReturn(people);

        PersonService personService = PersonService.getInstance();
        personService.setPersonDAO(personDAO);

        List<Person> newList = personService.read();
        Assert.assertEquals(1, newList.size());

    }

    @Test
    public void probando_matchers() throws Exception{
        PersonDAO personDAO = mock(PersonDAOImpl.class);
        when(personDAO.insert(anyObject())).thenReturn(1);

        PersonService personService = PersonService.getInstance();
        personService.setPersonDAO(personDAO);

        boolean result = personService.insert("", "", "", 0, "");
        Assert.assertEquals(true, result);

    }
}
