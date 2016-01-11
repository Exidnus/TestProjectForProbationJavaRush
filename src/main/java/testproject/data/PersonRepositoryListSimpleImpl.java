package testproject.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import testproject.Person;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Repository //@Primary
public class PersonRepositoryListSimpleImpl implements PersonRepository {
    private List<Person> persons;

    public PersonRepositoryListSimpleImpl() {
        persons = new ArrayList<>();
        persons.add(new Person("Вася", 25, new Timestamp(new Date().getTime()), false));
        persons.add(new Person("Петя", 38, new Timestamp(new Date().getTime()), true));
        persons.add(new Person("Людмила", 18, new Timestamp(new Date().getTime()), false));
        persons.add(new Person("Vernon", 25, new Timestamp(new Date().getTime()), true));
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
}
