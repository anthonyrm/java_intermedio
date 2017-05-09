package com.belatrix.person;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

/**
 * Created by anthony on 4/18/17.
 */
public class PersonDAOImpl implements PersonDAO {

    private static PersonDAOImpl personDAO = null;

    private final String TABLE_NAME = "Person";

    public static PersonDAOImpl getInstance() {
        if(personDAO == null) {
            personDAO = new PersonDAOImpl();
        }
        return personDAO;
    }

    private PersonDAOImpl() {
    }

    @Override
    public List<Person> read() throws Exception{
        Connection con = connectToAndQueryDatabase("root", "mysql");
        List<Person> list = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, nombre, apellido, dni, edad, genero FROM " + TABLE_NAME);

        while (rs.next()) {
            Person p;
            int id = rs.getInt("id");
            String name = rs.getString("nombre");
            String lastName = rs.getString("apellido");
            String dni = rs.getString("dni");
            int age = rs.getInt("edad");
            String gender = rs.getString("genero");

            p = new Person(id, name , lastName, dni, age, gender);
            list.add(p);
        }

        return list;

    }

    @Override
    public int insert(Person p) throws Exception{
        Connection con = connectToAndQueryDatabase("root", "mysql");

        Statement stmt = con.createStatement();
        String query = "INSERT into " + TABLE_NAME + " (nombre, apellido, dni, edad, genero) values (\""+ p.getName() + "\", \"" + p.getLastName() + "\" , \"" + p.getDni() + "\" , " + p.getAge() +", '" + p.getGender() + "')";
        return stmt.executeUpdate(query);
    }

    @Override
    public int delete(int id) throws Exception{
        Connection con = connectToAndQueryDatabase("root", "mysql");

        Statement stmt = con.createStatement();
        String query = "DELETE from " + TABLE_NAME + " where id = " + id;
        return stmt.executeUpdate(query);
    }

    @Override
    public int update(Person p) throws Exception{
        Connection con = connectToAndQueryDatabase("root", "mysql");

        Statement stmt = con.createStatement();
        String query = "UPDATE " + TABLE_NAME + " set "
                + "nombre=\"" + p.getName() + "\", "
                + "apellido=\"" + p.getLastName() + "\", "
                + "dni=\"" + p.getDni() + "\", "
                + "edad=" + p.getAge() + ", "
                + "genero=\"" + p.getGender() + "\" "
                + "WHERE id=" + p.getId();
        return stmt.executeUpdate(query);
    }

    private Connection connectToAndQueryDatabase(String username, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Db_Testing?useSSL=false",
                username,
                password);
    }
}
