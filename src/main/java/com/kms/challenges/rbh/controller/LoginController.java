package com.kms.challenges.rbh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    protected static final String VIEW_NAME_LOGIN_PAGE = "user/login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        LOGGER.debug("Rendering login page.");
        return VIEW_NAME_LOGIN_PAGE;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String logout() {
        LOGGER.debug("Rendering logout page.");
        return RegistrationController.VIEW_NAME_REGISTRATION_PAGE;
    }
}
