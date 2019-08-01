package com.findme.controller.viewResponseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuperAdminController {

    @RequestMapping(path = "/super-admin", method = RequestMethod.GET)
    public String superAdmin() {
        return "superAdmin";
    }
}
