package testproject.data;

import testproject.domain.Person;

import java.util.List;

/**
 * Created by Exidnus on 26.01.2016.
 */
public interface PersonManager {
    List<Person> getAll();
    List<Person> getPage(int offset, int length);
    void addPerson(Person person);
    void deletePersonById(int id);
    List<Person> findByName(String name);
    long getCount();
    Person getPersonById(int id);
    void update(Person person);
}
