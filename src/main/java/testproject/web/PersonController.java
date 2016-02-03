package testproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import testproject.data.PersonManager;
import testproject.domain.Person;

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

    @RequestMapping(value = "/changeorder", method = RequestMethod.POST)
    public String changeOrder(@RequestParam String newOrder) {
        switch (newOrder) {
            case "fromAtoZ":
                manager.setOrder("name", true);
                break;
            case "fromZtoA":
                manager.setOrder("name", false);
                break;
            case "ageAsc":
                manager.setOrder("age", true);
                break;
            case "ageDesc":
                manager.setOrder("age", false);
                break;
        }
        currentPosition = 0;
        return "redirect:/people";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddPerson() {
        return "addperson";
    }

    @RequestMapping(value = "/adding", method = RequestMethod.POST)
    public String addPersonAndGoToPersonList(Model model, @RequestParam String name,
                                             @RequestParam String age,
                                             @RequestParam(value = "isAdmin") String isAdminStr) {
        name = encodeFromISO88591ToUTF8(name);
        boolean isAdmin = isAdminStr.equals("yes");
        int ageInt = 0;
        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            model.addAttribute("isWrongInput", true);
        }

        if (ageInt < 1 || ageInt > 100 || name.length() < 2) {
            model.addAttribute("isWrongInput", true);
            return "addperson";
        }

        Person person = new Person(name, ageInt, isAdmin);
        manager.addPerson(person);
        return "redirect:/people";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String goToSearchByName() {
        return "searchbyname";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getResultOfSearch(Model model, @RequestParam String name) {
        name = encodeFromISO88591ToUTF8(name);
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
    public String changePersonAndGoToPersonsList(Model model, @RequestParam int id, @RequestParam String name,
                                                 @RequestParam String age,
                                                 @RequestParam(value = "isAdmin") String isAdminStr) {
        boolean isAdmin = isAdminStr.equals("yes");
        name = encodeFromISO88591ToUTF8(name);
        int ageInt = 0;
        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            model.addAttribute("person", manager.getPersonById(id));
            model.addAttribute("isWrongInput", true);
            return "changeperson";
        }

        if (ageInt < 1 || ageInt > 100 || name.length() < 2) {
            model.addAttribute("person", manager.getPersonById(id));
            model.addAttribute("isWrongInput", true);
            return "changeperson";
        }

        Person person = manager.getPersonById(id);
        person.setName(name);
        person.setAge(ageInt);
        person.setAdmin(isAdmin);
        manager.update(person);
        return "redirect:/people";
    }

    private String encodeFromISO88591ToUTF8(String name) {
        String rightName = null;
        try {
            rightName = new String(name.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ignored) {}
        return rightName;
    }

    /*
    Геттеры и сеттеры для тестов
     */

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
