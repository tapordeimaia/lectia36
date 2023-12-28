package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private ConnectionService connectionService;

    public PersonRepository(){
        connectionService = new ConnectionService();
    }

    public List<Person> getAllPerson() {
        try (Connection connection = connectionService.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select name, age from persons order by id");

            List<Person> personList = new ArrayList<>();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int age = resultSet.getInt(2);
//                System.out.println(name+ " " +age);

                Person person = new Person(name, age);

                personList.add(person);
            }
            return personList;
        } catch (SQLException e) {
            System.out.println("Eroare la conexiune");
        }
        return null;
    }

    public Person getPersonByAge(int age){
        try (Connection connection = connectionService.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select name, age from persons where age = "+age+";");

            if(resultSet.next()){
                String name = resultSet.getString(1);
                return new Person(name, age);
            }
        } catch (Exception e){
            System.out.println("Eroare la conexiune");
        }
        return null;
    }

    public void updatePersonAge(int id, int age) throws SQLException {
        try (Connection connection = connectionService.getConnection()) {
            Statement statement = connection.createStatement();
            int randuriModificate = statement.executeUpdate("update persons set age = "+age+ " where id = "+id+"");
        }
    }

//    public void addPerson(Person person) throws SQLException {
//        try (Connection connection = connectionService.getConnection()) {
//            Statement statement = connection.createStatement();
//            statement.execute("insert into persons (name, age) values("
//                    + "'" + person.getName()+ "'" + ", "
//                    +person.getAge()+")");
//        }
//    }

    public void addPerson(Person person) throws SQLException {
        try (Connection connection = connectionService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into persons (name, age) values(?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.execute();
        }
    }

    public Person findPersonByName(String name) throws SQLException {
        try (Connection connection = connectionService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select name, age from persons where name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int age = resultSet.getInt(2);
                return new Person(name, age);
            }
        }
        return null;
    }

    public void deletePerson(int id) throws SQLException {
        try (Connection connection = connectionService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from persons where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
}
