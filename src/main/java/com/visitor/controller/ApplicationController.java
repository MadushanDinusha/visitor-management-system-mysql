package com.visitor.controller;

import com.visitor.domain.User;
import com.visitor.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ApplicationController {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value= {"/", "/login"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView login(Model model, String error, String logout) {
        ModelAndView models = new ModelAndView();
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        models.setViewName("home/login");
        return models;
    }

    @GetMapping("/admin/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "/admin/registration";
    }

    @PostMapping("/admin/registration")
    public String registration(@ModelAttribute("userForm") User userForm) {
        userService.saveUser(userForm);
        return "redirect:/admin/home";
    }

    @GetMapping("/user/visitorRegistor")
    public String visitor(Model model) {
        return "/user/visitorRegistor";
    }

    @GetMapping("/guard/parking")
    public String parking(Model model) {
        return "/guard/parking";
    }

    @RequestMapping(value= {"/home/index"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUsersByUsername(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("home/index");
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value = "/admin/getUserName/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> sendMessage(@PathVariable("userName") String userName) {
        try {
            LOGGER.info("Sending message {}",userName);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Throwable t) {
            LOGGER.error("Error occurred while sending message", t);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}