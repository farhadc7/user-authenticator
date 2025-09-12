package ir.dc.userAuthenticator.service;


import ir.dc.userAuthenticator.dto.JwtResponse;
import ir.dc.userAuthenticator.dto.UserDetailsImpl;
import ir.dc.userAuthenticator.entity.UserEntity;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import ir.dc.userAuthenticator.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SignInConfirmationAuth {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;





    protected JwtResponse perform(Optional<UserEntity> user,String password,HttpServletRequest request) {
//        var user = authUtil.getUser(username);
        JwtResponse res;
        UserEntity newUser;
        if(!user.isPresent()){
            throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);
        }else{
            newUser= user.get();
            if(!newUser.getPassword().equals(password)){
                throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);
            }
        }
        Authentication authentication= null;
        if(user!= null ){
            authentication = SecureAuthenticate(newUser,request);
        }

        res = createJwtResponse(authentication,newUser);


        log.info("user successfully logged in: "+ user.get().getUsername());

        return res;
    }

    public Authentication SecureAuthenticate(UserEntity user, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    public JwtResponse createJwtResponse(Authentication authentication, UserEntity user) throws CustomException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        try {
            JwtResponse res = new JwtResponse();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var jwt = createJwt(authentication);

            List<String> roles =null;
            if(userDetails.getAuthorities() != null){

                 userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
            }
            res.setToken(jwt);
            res.setId(userDetails.getId());
            res.setMobileNumber(userDetails.getMobileNumber());
            res.setRoles(roles);
            return res;

        } catch (UsernameNotFoundException e) {
            log.error("signin: username is not correct "+userDetails.getMobileNumber() );
            throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);
        } catch (BadCredentialsException e) {
            log.error("signin: password is not correct " +userDetails.getMobileNumber());
            throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);
        } catch (Exception e) {
            log.error("signin: error in sigin for user: " +userDetails.getMobileNumber());
            throw e;
        }
    }

    public String createJwt(Authentication authentication) {
        String jwt = jwtUtil.generateJwtToken(authentication);
        return jwt;
    }


}
