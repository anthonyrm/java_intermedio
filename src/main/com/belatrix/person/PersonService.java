package com.belatrix.person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by anthony on 4/20/17.
 */
public class PersonService {

    private static final Logger logger = Logger.getLogger(PersonService.class.getName());
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

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            InputStream is = classLoader.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(is);
        } catch(IOException ioe) {
            logger.log(Level.INFO, "Error", ioe);
        }
    }

    public void setPersonDAO(PersonDAO p) {
        this.personDAO = p;
    }

    public List<Person> read() throws Exception {
        logger.info("Retrieving person list");
        PersonDAO personDAO = this.personDAO;
        return personDAO.read();
    }

    public boolean insert(String name, String lastName, String dni, int age, String gender) throws Exception {
        logger.info("Insert person");
        PersonDAO personDAO = this.personDAO;

        Person p = new Person( 0, name, lastName, dni, age, gender);
        int result  = personDAO.insert(p);

        return result > 0;
    }

    public boolean delete(int id) throws Exception {
        logger.info("Delete person");
        PersonDAO personDAO = this.personDAO;

        int result  = personDAO.delete(id);

        return result > 0;
    }

    public boolean update(int id, String name, String lastName, String dni, int age, String gender) throws Exception {
        logger.info("Update person");
        PersonDAO personDAO = this.personDAO;

        Person p = new Person( id, name, lastName, dni, age, gender);
        int result  = personDAO.update(p);

        return result > 0;
    }
}
