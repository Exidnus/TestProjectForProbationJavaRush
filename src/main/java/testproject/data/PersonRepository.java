package testproject.data;

import testproject.domain.Person;

import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 */
public interface PersonRepository {
    List<Person> getAll();
    void addPerson(Person person);
    void deletePersonById(int id);
    List<Person> findByName(String name);
    int getCount();
    Person getPersonById(int id);
    void update(Person person);
    //TODO методы для удаления и изменения, возможно, для пейджинга
}
