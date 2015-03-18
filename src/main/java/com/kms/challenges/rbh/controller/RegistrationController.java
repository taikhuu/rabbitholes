package com.kms.challenges.rbh.controller;

import com.kms.challenges.rbh.domain.User;
import com.kms.challenges.rbh.model.RegistrationForm;
import com.kms.challenges.rbh.service.security.SecurityUtil;
import com.kms.challenges.rbh.service.user.DuplicateEmailException;
import com.kms.challenges.rbh.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
    protected static final String MODEL_NAME_REGISTRATION_DTO = "user";
    protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registrationForm";

    private UserService service;

    @Autowired
    public RegistrationController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        model.addObject("contact", "Hello");
        model.setViewName("contact");
        return model;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute User contact) {
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/redirect")
    public ModelAndView home(ModelAndView model) throws IOException {
        model.setViewName("redirect:" + "/login");
        return model;
    }
    
    @RequestMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        //userService.updateData(user);
        return "redirect:/getList";

    }
    
    @RequestMapping("/list")
    public ModelAndView getUserLIst() {
        List<User> userList = new ArrayList<>();
        return new ModelAndView("userList", "userList", userList);
    }

    /**
     * Renders the registration page.
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        LOGGER.debug("Rendering registration page.");

        RegistrationForm registration = new RegistrationForm();
        LOGGER.debug("Rendering registration form with information: {}", registration);

        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, registration);

        return VIEW_NAME_REGISTRATION_PAGE;
    }

    /**
     * Processes the form submissions of the registration form.
     */
    @RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws DuplicateEmailException {
        LOGGER.debug("Registering user account with information: {}", userAccountData);
        if (result.hasErrors()) {
            LOGGER.debug("Validation errors found. Rendering form view.");
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        LOGGER.debug("No validation errors found. Continuing registration process.");

        User registered = createUserAccount(userAccountData, result);

        //If email address was already found from the database, render the form view.
        if (registered == null) {
            LOGGER.debug("An email address was found from the database. Rendering form view.");
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        LOGGER.debug("Registered user account with information: {}", registered);

        //Logs the user in.
        SecurityUtil.logInUser(registered);
        LOGGER.debug("User {} has been signed in", registered);

        return "redirect:/";
    }

    /**
     * Creates a new user account by calling the impl method. If the email address is found
     * from the database, this method adds a field error to the email field of the form object.
     */
    private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
        LOGGER.debug("Creating user account with information: {}", userAccountData);
        User registered = null;

        try {
            registered = service.registerNewUserAccount(userAccountData);
        }
        catch (DuplicateEmailException ex) {
            LOGGER.debug("An email address: {} exists.", userAccountData.getEmail());
            addFieldError(
                    MODEL_NAME_REGISTRATION_DTO,
                    RegistrationForm.FIELD_NAME_EMAIL,
                    userAccountData.getEmail(),
                    ERROR_CODE_EMAIL_EXIST,
                    result);
        }

        return registered;
    }

    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        LOGGER.debug(
                "Adding field error object's: {} field: {} with error code: {}",
                objectName,
                fieldName,
                errorCode
        );
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );

        result.addError(error);
        LOGGER.debug("Added field error: {} to binding result: {}", error, result);
    }
}
