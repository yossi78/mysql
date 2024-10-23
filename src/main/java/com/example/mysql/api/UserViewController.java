package com.example.mysql.api;
import com.example.mysql.repository.User;
import com.example.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class UserViewController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<User> users =userRepository.findAll(); // Fetch data from service
        model.addAttribute("users", users); // Pass data to the view
        return "home"; // Refers to 'home.html' in 'templates' folder
    }

}
