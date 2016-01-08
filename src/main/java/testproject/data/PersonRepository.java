package testproject.data;

import testproject.Person;

import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 */
public interface PersonRepository {
    List<Person> getAll();
    void addPerson(Person person);
    //TODO методы для удаления и изменения, возможно, для пейджинга
}
