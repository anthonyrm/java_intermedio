package com.belatrix.person;

import java.util.List;

/**
 * Created by anthony on 4/20/17.
 */
public class PersonService {

    public static PersonService personService = null;

    public static PersonService getInstancia () {
        if(personService == null) {
            personService = new PersonService();
        }

        return personService;
    }
    private PersonService() {}

    public List<Person> read() throws Exception {
        PersonDAO personDAO = PersonDAOImpl.getInstancia();
        List<Person> people = personDAO.read();

        return people;
    }

    public boolean insert(String name, String lastName, String dni, int age, String gender) throws Exception {
        PersonDAO personDAO = PersonDAOImpl.getInstancia();

        Person p = new Person( 0, name, lastName, dni, age, gender);
        int result  = personDAO.insert(p);

        if(result > 0) {
            return true;
        }

        return false;
    }
}
