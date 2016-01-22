package testproject.data;

import org.springframework.stereotype.Repository;
import testproject.domain.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 * Класс для тестирования
 */
@Repository //@Primary
public class PersonRepositoryListSimpleImpl implements PersonRepository {
    private List<Person> persons;

    public PersonRepositoryListSimpleImpl() {
        persons = new ArrayList<>();
        persons.add(new Person("Вася", 25, false));
        persons.add(new Person("Петя", 38, true));
        persons.add(new Person("Людмила", 18, false));
        persons.add(new Person("Vernon", 25, true));
    }

    @Override
    public List<Person> getAll() {
        return persons;
    }

    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public void deletePersonById(int id) {
        Iterator<Person> iterator = persons.iterator();
        while(iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getId() == id) iterator.remove();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().equals(name)) result.add(person);
        }
        return result;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getPersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) return person;
        }
        return null;
    }

    @Override
    public void update(Person person) {

    }
}
