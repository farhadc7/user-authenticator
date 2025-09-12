package ir.dc.userAuthenticator.service;

import ir.dc.userAuthenticator.dto.UserDetailsImpl;
import ir.dc.userAuthenticator.entity.UserEntity;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import ir.dc.userAuthenticator.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.
                findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));

        return UserDetailsImpl.build(user);
    }
    public UserDetails loadUserByAuthUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.
                findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));

        return UserDetailsImpl.build(user);
    }
    public UserEntity loadUserById(long id){
        return userRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.UNAUTHORIZED));
    }
}
