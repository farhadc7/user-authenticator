package ir.dc.userAuthenticator.controller;

import ir.dc.userAuthenticator.dto.JwtResponse;
import ir.dc.userAuthenticator.dto.SignupDto;
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
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
        userService.signup(signupDto.getUsername(),signupDto.getPassword());
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody SignupDto d, HttpServletRequest request) {
        return userService.login(d.getUsername(), d.getPassword(),request);

    }
    @GetMapping("/test")
    public String test(){
        return "test succeeded";
    }
}
