package testproject.web;

import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import testproject.data.PersonManager;
import testproject.domain.Person;
import testproject.data.PersonRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 */
public class PersonControllerTest {
    private List<Person> persons = new ArrayList<>();

    @Before
    public void fillList() {
        persons.add(new Person("Dima", 25, false));
        persons.add(new Person("Petia", 38, true));
        persons.add(new Person("Людмила", 18, false));
    }

    @Test
    public void shouldGoToPersonsPage() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        Mockito.when(mockRepository.getPage(0, 10)).thenReturn(persons);

        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people"))
                .andExpect(MockMvcResultMatchers.view().name("persons"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("personsList"))
                .andExpect(MockMvcResultMatchers.model().attribute("personsList",
                        Matchers.hasItems(persons.toArray())));
    }

    @Test
    public void shouldGoToNextPage() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);

        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people/next"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/people"));
    }

    @Test
    public void shouldGoToPrevoiusPage() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);

        PersonController controller = new PersonController(mockRepository);
        System.out.println(controller.getCurrentPosition());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people/previous"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/people"));
    }

    @Test
    public void shouldGoToAddPersonPage() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people/add"))
                .andExpect(MockMvcResultMatchers.view().name("addperson"));
    }

    /*
    Закомментированная строка была нужна для прохождения теста, когда equals и hashcode
    в классе Person были реализованы не с помощью поля id. Проблемы были с различием Timestamp, возможно,
    Timestamp нужно создавать в другом месте?
    Как я понимаю, с закомментированной строкой тест был не самым лучшим.
    Оставил закомментированную строку на всякий случай: возможно, equals и hashcode
    в классе Person нужно реализовать иначе.
     */
    @Test
    public void shouldAddPerson() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        Person person = persons.get(0);
        //mockRepository.addPerson(person);

        PersonController personController = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/people/adding")
                .param("name", person.getName())
                .param("age", String.valueOf(person.getAge()))
                .param("isAdmin", person.isAdmin() ? "yes" : "no"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/people"));

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).addPerson(person);
    }

    @Test
    public void shouldGoToSearchByName() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people/search"))
                .andExpect(MockMvcResultMatchers.view().name("searchbyname"));
    }

    /*
    !!!
    Тест зависит от кодировок: для прохождения теста
    нужно использовать person с имененм на английском.
     */
    @Test
    public void shouldGetResultOfSearchAndGoToSearchByName() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        List<Person> fakeResultOfSearch = new ArrayList<>();
        fakeResultOfSearch.add(persons.get(0));
        Mockito.when(mockRepository.findByName(fakeResultOfSearch.get(0).getName())).thenReturn(fakeResultOfSearch);

        mockMvc.perform(MockMvcRequestBuilders.post("/people/search")
                .param("name", fakeResultOfSearch.get(0).getName()))
                .andExpect(MockMvcResultMatchers.view().name("searchbyname"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("personsList"))
                .andExpect(MockMvcResultMatchers.model().attribute("personsList", fakeResultOfSearch));
        Mockito.verify(mockRepository, Mockito.atLeastOnce()).findByName(fakeResultOfSearch.get(0).getName());
    }

    @Test
    public void shouldDeleteAndGoToPersonsList() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/people/delete")
                .param("id", "35"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/people"));
    }

    @Test
    public void shouldGoToChangePersonPageWithPerson() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        PersonController controller = new PersonController(mockRepository);
        Person person = persons.get(1);
        Mockito.when(mockRepository.getPersonById(1)).thenReturn(person);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/people/change")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.view().name("changeperson"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.model().attribute("person", person));
    }

    /*
    TODO Не самый лучший тест из-за реализации equals в классе Person, надо что-то с этим сделать
     */

    @Test
    public void shouldChangePersonAndGoToPersonsList() throws Exception {
        PersonManager mockRepository = Mockito.mock(PersonManager.class);
        PersonController personController = new PersonController(mockRepository);
        Person person = persons.get(0);
        person.setId(0);
        Mockito.when(mockRepository.getPersonById(0)).thenReturn(person);

        Person changedPerson = new Person();
        changedPerson.setId(0);
        person.setName("Semen");
        person.setAge(30);
        person.setCreatedDate(person.getCreatedDate());
        changedPerson.setAdmin(true);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/people/performchange")
                .param("id", String.valueOf(0))
                .param("name", "Semen")
                .param("age", String.valueOf(30))
                .param("isAdmin", "yes"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/people"));

        Assert.assertEquals(person, changedPerson);
        Mockito.verify(mockRepository, Mockito.atLeastOnce()).update(person);
    }
}
