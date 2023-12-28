package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        PersonRepository personRepository = new PersonRepository();
//        List<Person> personList = personRepository.getAllPerson();
//        System.out.println("Total persoane: " + personList.size());
//        Person person = new Person("David", 35);
//        personRepository.addPerson(person);
//        personList = personRepository.getAllPerson();
//        System.out.println("Total persoane dupa: " + personList.size());

//        Person person = personRepository.getPersonByAge(59);
//        System.out.println(person);
//
//        personRepository.updatePersonAge(2, 20);
//        person = personRepository.getPersonByAge(20);
//        System.out.println(person);
//
        Person person = personRepository.findPersonByName("David");
        System.out.println(person);

        personRepository.deletePerson(3);
    }
}
