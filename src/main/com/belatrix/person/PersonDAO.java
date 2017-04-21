package com.belatrix.person;

import java.util.List;

/**
 * Created by anthony on 4/18/17.
 */
public interface PersonDAO {

    List<Person> read() throws Exception;

    int insert(Person p) throws Exception;

    int delete(int id) throws Exception;

    int update(Person p) throws Exception;
}
