package com.findme.controller.viewResponseController;

import com.findme.exception.NotFoundException;
import com.findme.models.User;
import com.findme.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(path = "/login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }

//    @RequestMapping(path = "/perform-login", method = RequestMethod.POST)
//    public String login(HttpSession session, Model model, @ModelAttribute LoginDTO loginDTO)
//            throws Exception {
//        log.info("UserController login method.");
//
//        System.out.println("perflog");
//
//        User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
//        session.setAttribute("user", user);
//
//        return profile(model, user.getId().toString());
//    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public String profile(Model model, @PathVariable String userId) throws Exception {
        log.info("UserController profile method. Moving to user page");

        Long id = Long.parseLong(userId);
        User user = userService.findById(id);

        if (user == null) {
            log.error("UserController profile method. User with id " + id + " was not found");
            throw new NotFoundException("User with id " + id + " was not found");
        }

        model.addAttribute("user", user);
        return "profile";
    }
}
