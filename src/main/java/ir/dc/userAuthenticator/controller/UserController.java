package ir.dc.userAuthenticator.controller;

import ir.dc.userAuthenticator.dto.JwtResponse;
import ir.dc.userAuthenticator.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestParam String username, @RequestParam String password) {
        userService.signup(username, password);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        return userService.login(username, password,request);

    }
}
