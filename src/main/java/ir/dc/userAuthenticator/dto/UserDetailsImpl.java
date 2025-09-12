package ir.dc.userAuthenticator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.dc.userAuthenticator.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String mobileNumber;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(Long id, String mobileNumber,  String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.authorities = authorities;
    }

    public UserDetailsImpl(Long id, String mobileNumber,  String password, String username,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.username= username;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities =user.getRole()==null?null: List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserDetailsImpl(
                user.getId(),
                user.getMobileNumber(),
                user.getPassword(),
                user.getUsername(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}