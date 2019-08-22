//package com.visitor.controller;
//
//import com.visitor.domain.User;
//import com.visitor.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/secure/auth")
//public class AdminController {
//
//    @Qualifier("userRepository")
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//
//    @PostMapping("/admin/add")
//    public String addUserByAdmin(@RequestBody User user) {
//        String pwd = user.getPassword();
//        String encryptPwd = passwordEncoder.encode(pwd);
//        user.setPassword(encryptPwd);
//        userRepository.save(user);
//        return "user added successfully...";
//    }
//
////    @PreAuthorize("hasAnyRole('ADMIN')")
//    @GetMapping("/admin/all")
//    public String securedHello() {
//        return "Secured Hello";
//    }
//}