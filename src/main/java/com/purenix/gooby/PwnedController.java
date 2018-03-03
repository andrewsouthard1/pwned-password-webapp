package com.purenix.gooby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.purenix.gooby.User;
import com.purenix.gooby.UserRepository;

@Controller
public class PwnedController {

    @Autowired
    private UserRepository userRepository;

    // Generates the form object.
    @GetMapping("/pwnedcheck")
    public String greetingForm(Model model) {
        model.addAttribute("pwnedcheck", new Pwned());
        return "pwnedcheck";
    }

    // Submits the form object.
    @PostMapping("/pwnedcheck")
    public String greetingSubmit(@ModelAttribute Pwned pwnedcheck) {
        // I haz debug
        /*
        System.out.println("Name : " + pwnedcheck.getName());
        System.out.println("Email : " + pwnedcheck.getEmail());
        System.out.println("Password : " + pwnedcheck.getPassword());
        */

        User n = new User();
        n.setName(pwnedcheck.getName());
        n.setEmail(pwnedcheck.getEmail());
        n.setPassword(pwnedcheck.getPassword());
        Pwned myPass = new Pwned(pwnedcheck.getPassword());
        if (pwnedcheck.isPwned(pwnedcheck.getPassword())) {
    			return "pwned";
    		}
        // Only save to the database if the password is not pwned.
    		userRepository.save(n);
        return "result";
    }

}
