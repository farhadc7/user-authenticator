package ir.dc.userAuthenticator.service;

import ir.dc.userAuthenticator.dto.JwtResponse;
import ir.dc.userAuthenticator.entity.UserEntity;
import ir.dc.userAuthenticator.entity.UserRole;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import ir.dc.userAuthenticator.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SignInConfirmationAuth signInConfirmationAuth;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
    }

    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void signup(String username, String password) {
        var current=findByUsername(username);
        if(current.isPresent()){
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password)); // Encode the password
        user.setPassword((password)); // Encode the password
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    public JwtResponse login(String username, String password, HttpServletRequest request) {
        return signInConfirmationAuth.perform(findByUsername(username),password,request);
    }
}
