package edu.rit.project;

import edu.rit.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FitReserveController {

    @Autowired
    private FitReserveService service;


    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", service.getUsers());
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserPage() {
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam int userId,
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam int phoneNumber,
                          @RequestParam String password,
                          @RequestParam String membershipType) {
        service.addUser(new User(userId, name, email, phoneNumber, password, membershipType));
        return "redirect:/add/success/user";
    }

    @GetMapping("/add/success/{entity}")
    public String showSuccessPage(@PathVariable String entity, Model model) {
        model.addAttribute("entity",entity);
        return "success";
    }
}
