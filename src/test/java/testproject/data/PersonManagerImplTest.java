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
public class PersonManagerImplTest extends TestCase {

    @Autowired
    PersonManager manager;

    @Test
    public void shouldGetCount() {
        assertEquals(21, manager.getCount());
    }

    @Test
    public void shouldGetAll() {
        assertEquals(21, manager.getAll().size());
    }

    @Test
    public void shouldGetPage() {
        assertEquals(10, manager.getPage(0, 10).size());
        assertEquals(7, manager.getPage(7, 7).size());
    }

    @Test
    public void shouldGetAllWithoutOrder() {
        assertEquals(21, manager.getAllWithoutOrder().size());
    }

    @Test
    public void shouldGetPageWithoutOder() {
        assertEquals(10, manager.getPageWithoutOrder(0, 10).size());
        assertEquals(7, manager.getPageWithoutOrder(7, 7).size());
    }

    /*
    Пришлось вместе тестировать два метода, иначе начинались проблемы с методом shouldGetCount()
    Такое ощущение, что порядок выполнения тестовых методов в Maven и IntelliJ отличаются
     */
    @Test
    public void shouldAddPersonAndDeleteById() {
        Person addedPerson = new Person("Semen", 25, false);
        manager.addPerson(addedPerson);
        assertEquals(22, manager.getCount());
        List<Person> persons = manager.getAll();
        assertTrue(persons.contains(addedPerson));
        manager.deletePersonById(addedPerson.getId());
        assertEquals(21, manager.getCount());
    }

    /*
    Тест почему-то зависит от кодировок (поиск по имени на русском не работает)
     */

    @Test
    public void shouldFindByName() {
        List<Person> resultOfSearch = manager.findByName("Simeon");
        assertTrue(resultOfSearch.size() == 1);
        Person person = resultOfSearch.get(0);
        assertTrue(person.getName().equals("Simeon"));
        assertTrue(person.getAge() == 43);
    }

    @Test
    public void shouldGetPersonByIdAndUpdate() {
        Person person = manager.getPersonById(28);
        person.setName("Вова");
        person.setAdmin(true);
        person.setAge(45);
        manager.update(person);
        Person changedPerson = manager.getPersonById(28);
        assertEquals(person.getName(), changedPerson.getName());
        assertEquals(person.getAge(), changedPerson.getAge());
        assertEquals(person.isAdmin(), changedPerson.isAdmin());
    }
}
