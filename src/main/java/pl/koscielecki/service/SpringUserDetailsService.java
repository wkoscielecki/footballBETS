package pl.koscielecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.koscielecki.role.Role;
import pl.koscielecki.user.CurrentUser;
import pl.koscielecki.user.User;

import java.util.HashSet;
import java.util.Set;

public class SpringUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserService userService;
    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {throw new UsernameNotFoundException(email); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new CurrentUser(user.getEmail(),user.getPassword(),
                grantedAuthorities, user);
    }
}
