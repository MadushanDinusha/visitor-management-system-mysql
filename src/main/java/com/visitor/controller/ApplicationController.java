package com.visitor.controller;

import com.visitor.domain.User;
import com.visitor.service.RoleService;
import com.visitor.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
public class ApplicationController {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/user/visitorRegistor")
    public String visitor(Model model) {
        return "/user/visitorRegistor";
    }

    @GetMapping("/guard/parking")
    public String parking(Model model) {
        return "/guard/parking";
    }

    @GetMapping("/admin/adminHome")
    public String adminHome() {
        return "/admin/adminHome";
    }

    @GetMapping("/admin/allUsers")
    public String getUsers() {
        return "/admin/allUsers";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/login";
    }

    @GetMapping("/admin/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "/admin/registration";
    }

    @RequestMapping(value = {"/", "/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(Model model, String error, String logout) {
        ModelAndView models = new ModelAndView();
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        models.setViewName("home/login");
        return models;
    }




    @RequestMapping(value = {"/home/index"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUsersByUsername(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("home/index");
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value = "/admin/getUserName/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> sendMessage(@PathVariable("userName") String userName) {
        try {
            LOGGER.info("Sending message {}", userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable t) {
            LOGGER.error("Error occurred while sending message", t);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        try {
            User existingUsers = userService.getUsersByUsername(request.get("userName"));
            if (existingUsers == null) {
                userService.saveUser(request);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            }
        } catch (Throwable t) {
            LOGGER.error("Error occurred while saving user", t);
            return new ResponseEntity<>("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> request) {
        try {
            LOGGER.info("username {}", request.get("userName"));
            userService.deleteUser(request.get("userName"));
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getGroupList() {
        try {
            List<User> groupsList = userService.getAllUsers();
            LOGGER.info("Getting all users lists {}",groupsList);
            return new ResponseEntity<>(groupsList, HttpStatus.OK);
        } catch (Throwable t) {
            LOGGER.error("Error occurred while getting group list", t);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}