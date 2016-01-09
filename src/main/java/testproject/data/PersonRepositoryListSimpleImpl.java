package testproject.data;

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
@Repository
public class PersonRepositoryListSimpleImpl implements PersonRepository {
    private List<Person> persons;

    public PersonRepositoryListSimpleImpl() {
        persons = new ArrayList<>();
        persons.add(new Person("Вася", 25, new Timestamp(new Date().getTime()), false));
        persons.add(new Person("Петя", 38, new Timestamp(new Date().getTime()), true));
        persons.add(new Person("Людмила", 18, new Timestamp(new Date().getTime()), false));
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
}
