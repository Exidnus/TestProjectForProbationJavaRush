package testproject.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testproject.domain.Person;

import java.util.List;

/**
 * Created by Exidnus on 26.01.2016.
 */
@Service
public class PersonManagerImpl implements PersonManager {

    private PersonRepository personRepository;

    @Autowired
    public PersonManagerImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return personRepository.getAll();
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getPage(int offset, int length) {
        return personRepository.getPage(offset, length);
    }

    @Override
    @Transactional
    public void deletePersonById(int id) {
        personRepository.deletePersonById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return personRepository.getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPersonById(int id) {
        return personRepository.getPersonById(id);
    }

    @Override
    @Transactional
    public void update(Person person) {
        personRepository.update(person);
    }

    @Override
    public void setOrder(String orderedBy, boolean isAscending) {
        personRepository.setOrder(orderedBy, isAscending);
    }
}
