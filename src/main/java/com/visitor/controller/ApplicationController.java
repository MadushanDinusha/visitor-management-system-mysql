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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @GetMapping("/guard/search")
    public String loadSearchPage() {
        return "guard/search";
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

    @GetMapping("/admin/Reports")
    public String getReports(){
        return "admin/Reports";
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
    public String accessDenied() {
        return "errors/access_denied";
    }

    @RequestMapping(value = {"/admin/getRequests"}, method = RequestMethod.GET)
    public ModelAndView getRequest() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/getRequests");
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
            List<Visitor> visitors = visitorService.findVisitorByGroupId(group_id);
            return new ResponseEntity<>(visitors, HttpStatus.OK);
        } catch (Throwable t) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<?>> getGroupList() {
        try {
            List<User> groupsList = userService.getAllUsers();
            return new ResponseEntity<>(groupsList, HttpStatus.OK);
        } catch (Throwable t) {
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

    @RequestMapping(value = "/user/sendMail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> sendMail(@RequestBody Map<String, String> request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            mailService.sendmail(authentication.getName(), request.get("contextPath"));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/addVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveVehicle(@RequestBody Map<String, String> vehicle) {
        try {
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
            requestService.updateRequestState(request.get("group_id"), request.get("state"));
            requestService.updateComment(request.get("group_id"), request.get("message"));
            requestService.updateReadState(request.get("group_id"), "Read", "UnRead");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/updateVisitor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateVisitor(@RequestBody Visitor visitor) {
        try {
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
            userService.deleteUser(request.get("userName"));
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail() throws AddressException, MessagingException, IOException {
        try {

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
            if (request.get("password") == null || request.get("password").equals("")){
                LOGGER.info("inside if");
                userService.updateUser(userName, email, hodMail, department);
            }else {
                LOGGER.info("else {}",request.get("password"));
                userService.updateUser(userName, email, hodMail, department);
                userService.updatePassword(userName,request.get("password"));
            }
            User user = userService.getUsersByUsername(userName);
            int role_id = user.getRoles().iterator().next().getId();
            roleService.updateRoleByRoleId(role, role_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/getUserDetails", method = RequestMethod.POST)
    public ResponseEntity<?> getUserByUserId(@RequestBody Map<String, String> request) {
        try {
            User user = userService.getUsersByUsername(request.get("userName"));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/getUserName", method = RequestMethod.GET)
    public ResponseEntity<?> getUserName() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return new ResponseEntity<>(authentication.getName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/guard/getVisitorDetailsForCheckInAndCheckOut", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVisitorDetailsForCheckInAndCheckOut() {
        try {
            List<Visitor> visitorList = visitorService.getAllVisitors();
            List<Visitor> returnVisitorList = new ArrayList<>();

            for (Visitor visitor : visitorList) {
                java.util.Date today = new java.util.Date();
                String dates = new SimpleDateFormat("yyyy-MM-dd").format(today);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = (Date) formatter.parse(dates);
                String visitorDates = new SimpleDateFormat("yyyy-MM-dd").format(visitor.getDate());
                DateFormat formatters = new SimpleDateFormat("yyyy-MM-dd");
                Date VisitorDate = (Date) formatters.parse(visitorDates);
                List<Request> requests = requestService.getRequestByGroupId(visitor.getGroupId());
                if (VisitorDate.compareTo(date) >= 0 && requests.get(0).getState().equals("Approved")) {
                    returnVisitorList.add(visitor);
                }
            }
            return new ResponseEntity<>(returnVisitorList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/guard/updateVisitorCheckIn", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitorCheckIn(@RequestBody Map<String, String> request) {
        try {
            visitorService.updateVisitorCheckIn(request.get("checkIn"), Long.parseLong(request.get("visitorId")));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/guard/updateVisitorCheckOut", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitorCheckOut(@RequestBody Map<String, String> request) {
        try {
            visitorService.updateVisitorCheckOut(request.get("checkOut"), Long.parseLong(request.get("visitorId")));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/guard/getVehiclesDetails/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVehiclesDetails(@PathVariable("groupId") String groupId) {
        try {
            List<Vehicle> vehicles = vehicleService.getVehicleListByGroupId(groupId);
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/guard/updateVisitorPassId", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitorPassId(@RequestBody Map<String, String> request) {
        try {
            LOGGER.info(request.get("passId"));
            visitorService.updateVisitorPassId(request.get("passId"), Long.parseLong(request.get("visitorId")));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/generateReport", method = RequestMethod.POST)
    public ResponseEntity<?> generateReport(@RequestBody Map<String, java.sql.Date> request) {
        try {
            java.sql.Date from = (request.get("fromDate"));
            java.sql.Date to = (request.get("toDate"));
            List<Visitor> visitorList = visitorService.getVisitorDetailsByDate(from,to);
            LOGGER.info("visitor details form to {}",visitorList);
            return new ResponseEntity<>(visitorList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/guard/updateVehicleNumber", method = RequestMethod.POST)
    public ResponseEntity<?> updateVehicleNumber(@RequestBody Map<String, String> request) {
        try {
            LOGGER.info(request);
            vehicleService.updateVehicleNumber(Integer.parseInt(request.get("vehicleId")),request.get("vehicleNumber"));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Error Occurred while updating vehicle details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}