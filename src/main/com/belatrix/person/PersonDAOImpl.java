package com.belatrix.person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony on 4/18/17.
 */
public class PersonDAOImpl implements PersonDAO {

    private static PersonDAOImpl personDAO = null;

    private final String TABLE_NAME = "Person";

    public static PersonDAOImpl getInstancia() {
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
        List<Person> lista = new ArrayList<Person>();

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
            lista.add(p);
        }

        return lista;

    }

    public int insert(Person p) throws Exception{
        Connection con = connectToAndQueryDatabase("root", "mysql");

        Statement stmt = con.createStatement();
        String query = "INSERT into " + TABLE_NAME + " (nombre, apellido, dni, edad, genero) values (\""+ p.getName() + "\", \"" + p.getLastName() + "\" , \"" + p.getDni() + "\" , " + p.getAge() +", '" + p.getGender() + "')";
        int rs = stmt.executeUpdate(query);
        return rs;
    }

    public Connection connectToAndQueryDatabase(String username, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Db_Testing",
                username,
                password);
        return con;
    }
}
