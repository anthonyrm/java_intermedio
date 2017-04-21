package com.belatrix.person;
/**
 * Created by anthony on 4/18/17.
 */
public class Person {
    private int id;
    private String name;
    private String lastName;
    private String dni;
    private int age;
    private String gender;

    public Person(int id, String name, String lastName, String dni, int age, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
