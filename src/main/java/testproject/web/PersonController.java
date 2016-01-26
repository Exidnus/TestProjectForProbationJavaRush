package testproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import testproject.data.PersonManager;
import testproject.domain.Person;
import testproject.data.PersonRepository;

import java.io.UnsupportedEncodingException;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Controller
@RequestMapping(value = "/people")
public class PersonController {
    PersonManager manager;
    private int currentPosition = 0;
    private int length = 10; //Количество записей на странице

    @Autowired
    public PersonController(PersonManager manager) {
        this.manager = manager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String goToPersonsList(Model model) {
        model.addAttribute("personsList", manager.getPage(currentPosition, length));
        //model.addAttribute("personsList", personManager.getAll()); // Без пейджинга
        return "persons";
    }

    @RequestMapping(value = "/previous", method = RequestMethod.GET)
    public String goToPreviousPage() {
        if (currentPosition > 0) currentPosition -= length;
        return "redirect:/people";
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public String goToNextPage() {
        if (currentPosition + length < manager.getCount()) currentPosition += length;
        return "redirect:/people";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddPerson() {
        return "addperson";
    }

    @RequestMapping(value = "/adding", method = RequestMethod.POST)
    public String addPersonAndGoToPersonList(@RequestParam String name,
                                             @RequestParam int age,
                                             @RequestParam(value = "isAdmin") String isAdminStr) {
        boolean isAdmin = isAdminStr.equals("yes");
        name = encodeFromISO8859_1ToUTF8(name);
        Person person = new Person(name, age, isAdmin);
        manager.addPerson(person);
        return "redirect:/people";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String goToSearchByName() {
        return "searchbyname";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getResultOfSearch(Model model, @RequestParam String name) {
        name = encodeFromISO8859_1ToUTF8(name);
        model.addAttribute("personsList", manager.findByName(name));
        return "searchbyname";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePersonById(@RequestParam int id) {
        manager.deletePersonById(id);
        return "redirect:/people";
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String goToChangePersonWithPersonId(Model model, @RequestParam int id) {
        model.addAttribute("person", manager.getPersonById(id));
        return "changeperson";
    }

    @RequestMapping(value = "/performchange", method = RequestMethod.POST)
    public String changePersonAndGoToPersonsList(@RequestParam int id, @RequestParam String name,
                                                 @RequestParam int age,
                                                 @RequestParam(value = "isAdmin") String isAdminStr) {
        boolean isAdmin = isAdminStr.equals("yes");
        name = encodeFromISO8859_1ToUTF8(name);
        Person person = manager.getPersonById(id);
        person.setName(name);
        person.setAge(age);
        person.setAdmin(isAdmin);
        manager.update(person);
        return "redirect:/people";
    }

    private String encodeFromISO8859_1ToUTF8(String name) {
        String rightName = null;
        try {
            rightName = new String(name.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ignored) {}
        return rightName;
    }
}
