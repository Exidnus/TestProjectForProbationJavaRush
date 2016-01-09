package testproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import testproject.data.PersonRepository;

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
    public String personsSimpleGet(Model model) {
        model.addAttribute("personsList", personRepository.getAll());
        return "persons";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToaddPerson() {
        return "addperson";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPerson() {

        return "persons";
    }
}
