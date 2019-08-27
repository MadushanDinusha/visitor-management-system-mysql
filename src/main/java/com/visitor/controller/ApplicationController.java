package com.visitor.controller;

import com.visitor.domain.Request;
import com.visitor.domain.User;
import com.visitor.domain.Visitor;
import com.visitor.service.RequestService;
import com.visitor.service.UserService;
import com.visitor.service.VisitorService;
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
    private VisitorService visitorService;

    @Autowired
    RequestService requestService;

    @GetMapping("/")
    public String loadIndex() {
        return "index";
    }

    @GetMapping("/user/visitorRequest")
    public String visitor(Model model) {
        return "user/visitorRequest";
    }

    @GetMapping("/user/visitorHome")
    public String getVisitorHome() {
        return "/user/visitorHome";
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

    @GetMapping("/admin/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "/admin/registration";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/";
    }

    @RequestMapping(value = {"/home/index"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUsersByUsername(auth.getName());
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value = {"/admin/getRequests"}, method = RequestMethod.GET)
    public ModelAndView getRequest() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/getRequests");
        return model;
    }

    @RequestMapping(value = {"/admin/requestDetails"}, method = RequestMethod.GET)
    public ModelAndView getRequestDetails() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/requestDetails");
        return model;
    }

    @RequestMapping(value = "/admin/newRequest/{group_id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Visitor>> sendMessage(@PathVariable("group_id") String group_id) {
        try {
            LOGGER.info("request id {}", group_id);
            List<Visitor> visitors = visitorService.findVisitorByGroupId(group_id);
            LOGGER.info("visitor details {}", visitors);
            return new ResponseEntity<>(visitors,HttpStatus.OK);
        } catch (Throwable t) {
            LOGGER.error("Error occurred while sending message", t);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getGroupList() {
        try {
            List<User> groupsList = userService.getAllUsers();
            LOGGER.info("Getting all users lists {}", groupsList);
            return new ResponseEntity<>(groupsList, HttpStatus.OK);
        } catch (Throwable t) {
            LOGGER.error("Error occurred while getting group list", t);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/newRequest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getRequests() {
        try {
            List<Request> requests = userService.getRequest();
            LOGGER.info("All requests {}", userService.getRequest());
            return new ResponseEntity<>(requests,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(Model model, String error, String logout) {
        ModelAndView models = new ModelAndView();
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        models.setViewName("home/login");
        return models;
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

    @RequestMapping(value = "/user/addVisitor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveVisitor(@RequestBody Visitor visitor) {
        try {
            visitorService.saveVisitor(visitor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
}