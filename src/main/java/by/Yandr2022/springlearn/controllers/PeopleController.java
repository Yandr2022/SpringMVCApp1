package by.Yandr2022.springlearn.controllers;

import by.Yandr2022.springlearn.dao.PersonDAO;
import by.Yandr2022.springlearn.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

//    @GetMapping("/new")
//    public String newPerson( Model model) {
//        model.addAttribute("person", new Person());
//        return "people/new";
//    }

    @GetMapping("/new")
    public String newPerson( @ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create( @ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
    return "people/edit";
        }

    @ModelAttribute("headerMsg")
    public String populateHeaderMsg(){
        return "Welcome to our website";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person){
        personDAO.update(person.getId(), person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

}
