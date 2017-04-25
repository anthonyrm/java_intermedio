package com.belatrix.person;

import java.util.List;

/**
 * Created by anthony on 4/20/17.
 */
public class PersonService {

    public static PersonService personService = null;
    private PersonDAO personDAO = null;

    public static PersonService getInstance() {
        if(personService == null) {
            personService = new PersonService();
        }

        return personService;
    }
    private PersonService() {
        if(personDAO == null) {
            personDAO = PersonDAOImpl.getInstance();
        }
    }

    public void setPersonDAO(PersonDAO p) {
        this.personDAO = p;
    }

    public List<Person> read() throws Exception {
        PersonDAO personDAO = this.personDAO;
        return personDAO.read();
    }

    public boolean insert(String name, String lastName, String dni, int age, String gender) throws Exception {
        PersonDAO personDAO = this.personDAO;

        Person p = new Person( 0, name, lastName, dni, age, gender);
        int result  = personDAO.insert(p);

        return result > 0;
    }

    public boolean delete(int id) throws Exception {
        PersonDAO personDAO = this.personDAO;

        int result  = personDAO.delete(id);

        return result > 0;
    }

    public boolean update(int id, String name, String lastName, String dni, int age, String gender) throws Exception {
        PersonDAO personDAO = this.personDAO;

        Person p = new Person( id, name, lastName, dni, age, gender);
        int result  = personDAO.update(p);

        return result > 0;
    }
}
