package com.visitor.controller;

import com.visitor.domain.Request;
import com.visitor.domain.User;
import com.visitor.domain.Vehicle;
import com.visitor.domain.Visitor;
import com.visitor.service.*;
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

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.*;


@Controller
public class ApplicationController {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    @Autowired
    MailService mailService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    RequestService requestService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    RoleService roleService;


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

    @GetMapping("/user/resetPassword")
    public String getChangePassword() {
        return "user/resetPassword";
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

    @RequestMapping(value = "/user/userRequests", method = RequestMethod.GET)
    public String getUserRequests() {
        return "/user/userRequests";
    }

    @RequestMapping(value = {"/user/getUserRole", "/admin/getUserRole"}, method = RequestMethod.GET)
    public ResponseEntity<?> getUserRole() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getUsersByUsername(authentication.getName());
            return new ResponseEntity<>(user.getRoles().iterator().next().getRole(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/admin/newRequest/{group_id}", "/user/newRequest/{group_id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Visitor>> getRequests(@PathVariable("group_id") String group_id) {
        try {
            LOGGER.info("request id {}", group_id);
            List<Visitor> visitors = visitorService.findVisitorByGroupId(group_id);
            LOGGER.info("visitor details {}", visitors);
            return new ResponseEntity<>(visitors, HttpStatus.OK);
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

    @RequestMapping(value = {"/admin/newRequest", "/user/newRequest"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getRequests() {
        try {
            List<Request> requests = userService.getRequest();
            return new ResponseEntity<>(requests, HttpStatus.OK);
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

    @RequestMapping(value = {"/user/allRequests", "/admin/allRequests"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getAllRequests() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            int employee_id = userService.getIdByUserName(authentication.getName());
            List<Request> requests = userService.getRequestByUserName(employee_id);
            Collections.reverse(requests);
            LOGGER.info("request by visitor {}", requests);
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/getUserDetails/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getUserDetails(@PathVariable("userName") String userName) {
        try {
            User user = userService.getUsersByUsername(userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/admin/getVehiclesByGroupId/{group_id}", "/user/getVehiclesByGroupId/{group_id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getVehicleDetailsByGroupId(@PathVariable("group_id") String group_id) {
        try {
            return new ResponseEntity<>(vehicleService.getVehicleListByGroupId(group_id), HttpStatus.OK);
        } catch (Exception e) {
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

    @RequestMapping(value = "/user/addVisitor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveVisitor(@RequestBody Visitor visitor) {
        try {
            LOGGER.info("request to add visitor {}", visitor);
            visitorService.saveVisitor(visitor);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            mailService.sendmail(authentication.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/addVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveVehicle(@RequestBody Map<String, String> vehicle) {
        try {
            LOGGER.info("request to add vehicle {}", vehicle);
            String groupId = vehicle.get("group_id");
            String vehicleNumber = vehicle.get("vehicle_number");
            Vehicle vehicleObj = new Vehicle();
            vehicleObj.setVehicleNumber(vehicleNumber);
            vehicleObj.setGroupId(groupId);
            vehicleService.save(vehicleObj);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/updateState", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateVisitorState(@RequestBody Map<String, String> request) {
        try {
            LOGGER.info("request {}", request);
            requestService.updateRequestState(request.get("group_id"), request.get("state"));
            requestService.updateComment(request.get("group_id"), request.get("message"));
            requestService.updateReadState(request.get("group_id"), "Read", "UnRead");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Error occurred while updating 'request' table", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/updateVisitor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateVisitor(@RequestBody Visitor visitor) {
        try {
            LOGGER.info("visitor updating {}", visitor);
            visitorService.updateVisitor(visitor);
            requestService.updateRequestState(visitor.getGroupId(), "Pending");
            requestService.updateReadState(visitor.getGroupId(), "UnRead", "Read");
            return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/admin/updateEmpState", "/user/updateEmpState"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateEmpState() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getUsersByUsername(authentication.getName());
            requestService.updateEmpState(user.getId(), "Read");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable t) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LOGGER.info("new pw {}", request.get("newPassword"));
            userService.updatePassword(authentication.getName(), request.get("newPassword"));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable t) {
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

    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail() throws AddressException, MessagingException, IOException {
        try {
            LOGGER.info("sending mail");

            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = {"/admin/updateUserDetails"}, method = RequestMethod.POST)
    public ResponseEntity<?> updateRoleByUserId(@RequestBody Map<String, String> request) {
        try {
            String userName = request.get("userName");
            String email = request.get("email");
            String hodMail = request.get("hodMail");
            String role = request.get("role");
            String department = request.get("department");
            userService.updateUser(userName,email,hodMail,department);
            User user = userService.getUsersByUsername(userName);
            int role_id = user.getRoles().iterator().next().getId();
            roleService.updateRoleByRoleId(role, role_id);
            LOGGER.info("updating role {}", request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/admin/getUserDetails",method = RequestMethod.POST)
    public ResponseEntity<?> getUserByUserId(@RequestBody Map<String,String> request){
        try{
            User user = userService.getUsersByUsername(request.get("userName"));
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}