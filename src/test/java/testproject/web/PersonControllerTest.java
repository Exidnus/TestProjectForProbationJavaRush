package testproject.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import testproject.Person;
import testproject.data.PersonRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 */
public class PersonControllerTest {
    @Test
    public void shouldGoToPersonsPage() throws Exception {
        List<Person> persons = createPersonsList();
        PersonRepository mockRepository = Mockito.mock(PersonRepository.class);
        Mockito.when(mockRepository.getAll()).thenReturn(persons);

        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people"))
                .andExpect(MockMvcResultMatchers.view().name("persons"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("personsList"))
                .andExpect(MockMvcResultMatchers.model().attribute("personsList",
                        Matchers.hasItems(persons.toArray())));
    }

    @Test
    public void shouldGoToAddPersonpage() throws Exception {
        PersonRepository mockRepository = Mockito.mock(PersonRepository.class);
        PersonController controller = new PersonController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/people/add"))
                .andExpect(MockMvcResultMatchers.view().name("addperson"));
    }

    private List<Person> createPersonsList() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Вася", 25, new Timestamp(new Date().getTime()), false));
        persons.add(new Person("Петя", 38, new Timestamp(new Date().getTime()), true));
        persons.add(new Person("Людмила", 18, new Timestamp(new Date().getTime()), false));
        return persons;
    }
}
