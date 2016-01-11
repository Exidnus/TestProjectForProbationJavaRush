package testproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import testproject.Person;
import testproject.data.PersonRepository;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Controller
@RequestMapping(value = "/people")
public class PersonController {
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String goToPersonsList(Model model) {
        model.addAttribute("personsList", personRepository.getAll());
        return "persons";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddPerson() {
        return "addperson";
    }

    @RequestMapping(value = "/adding", method = RequestMethod.POST)
    public String addPersonAndGoToPersonList(Model model, @RequestParam(value = "name") String name,
                                             @RequestParam(value = "age") int age,
                                             @RequestParam(value = "isAdmin") String isAdminStr) {
        boolean isAdmin = isAdminStr.equals("yes") ? true : false;
        String rightName = null;
        try {
            rightName = new String(name.getBytes("ISO8859-1"), "UTF-8"); //TODO Должен быть другой способ?
        } catch (UnsupportedEncodingException ignored) {}
        Person person = new Person(rightName, age, new Timestamp(new Date().getTime()), isAdmin);
        personRepository.addPerson(person);
        return "redirect:/people";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String goToSearchByName() {
        return "searchbyname";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getResultOfSearch(Model model, @RequestParam(value = "name") String name) {
        String rightName = null;
        try {
            rightName = new String(name.getBytes("ISO8859-1"), "UTF-8"); //TODO Должен быть другой способ?
        } catch (UnsupportedEncodingException ignored) {}
        model.addAttribute("personsList", personRepository.findByName(rightName));
        return "searchbyname";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePersonById(@RequestParam(value = "id") int id) {
        personRepository.deletePersonById(id);
        return "redirect:/people";
    }
}
