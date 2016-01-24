package testproject.data;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testproject.domain.Person;

import java.util.List;

/**
 * Created by Exidnus on 23.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateRepositoryTestConfig.class)
public class PersonRepositoryHibernateTest extends TestCase {

    @Autowired
    PersonRepositoryHibernate personRepositoryHibernate;

    @Test
    public void shouldGetCount() {
        assertEquals(21, personRepositoryHibernate.getCount());
    }

    @Test
    public void shouldGetAll() {
        assertEquals(21, personRepositoryHibernate.getAll().size());
    }

    @Test
    public void shouldGetPage() {
        assertEquals(10, personRepositoryHibernate.getPage(0, 10).size());
        assertEquals(7, personRepositoryHibernate.getPage(7, 7).size());
    }

    /*
    Пришлось вместе тестировать два метода, иначе начинались проблемы с методом shouldGetCount()
    Такое ощущение, что порядок выполнения тестовых методов в Maven и IntelliJ отличаются
     */
    @Test
    public void shouldAddPersonAndDeleteById() {
        Person addedPerson = new Person("Semen", 25, false);
        personRepositoryHibernate.addPerson(addedPerson);
        assertEquals(22, personRepositoryHibernate.getCount());
        List<Person> persons = personRepositoryHibernate.getAll();
        assertTrue(persons.contains(addedPerson));
        personRepositoryHibernate.deletePersonById(addedPerson.getId());
        assertEquals(21, personRepositoryHibernate.getCount());
    }

    /*
    Тест почему-то зависит от кодировок (поиск по имени на русском не работает)
     */

    @Test
    public void shouldFindByName() {
        List<Person> resultOfSearch = personRepositoryHibernate.findByName("Simeon");
        assertTrue(resultOfSearch.size() == 1);
        Person person = resultOfSearch.get(0);
        assertTrue(person.getName().equals("Simeon"));
        assertTrue(person.getAge() == 43);
    }

    @Test
    public void shouldGetPersonByIdAndUpdate() {
        Person person = personRepositoryHibernate.getPersonById(28);
        person.setName("Вова");
        person.setAdmin(true);
        person.setAge(45);
        personRepositoryHibernate.update(person);
        Person changedPerson = personRepositoryHibernate.getPersonById(28);
        assertEquals(person.getName(), changedPerson.getName());
        assertEquals(person.getAge(), changedPerson.getAge());
        assertEquals(person.isAdmin(), changedPerson.isAdmin());
    }
}
