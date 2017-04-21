package com.belatrix.person;

import java.util.List;

/**
 * Created by anthony on 4/18/17.
 */
public interface PersonDAO {


    public List<Person> read() throws Exception;

    public int insert(Person person) throws Exception;
}
