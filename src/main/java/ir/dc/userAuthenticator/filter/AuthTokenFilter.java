package ir.dc.userAuthenticator.filter;




import ir.dc.userAuthenticator.service.UserDetailsServiceImpl;
import ir.dc.userAuthenticator.token.JwtTokenService;
import ir.dc.userAuthenticator.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    private UserDetailsServiceImpl userDetailsService;

    public AuthTokenFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService  ) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;

    }
/*must improve*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            UserDetails userDetails= null;


                if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                    String username = jwtUtil.getUserNameFromJwtToken(jwt);
                     userDetails = userDetailsService.loadUserByUsername(username);
                }


            if(userDetails!= null){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
//            log.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private boolean isClient(String jwt) {
        if(jwt != null){
            String clientName = JwtTokenService.getClaim(jwt,"clientName").orElse(null);
            if(clientName!= null){
                if( !clientName.startsWith("CLIENT_CLIENT_") && clientName.startsWith("CLIENT_")){
                    return true;
                }
            }
        }
        return false;
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}