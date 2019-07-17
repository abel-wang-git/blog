package com.api.base.config.auth.service;

import com.api.base.config.auth.AuthUser;
import com.api.base.model.Power;
import com.api.base.model.Role;
import com.api.base.model.User;
import com.api.base.service.PowerService;
import com.api.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailsServic implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PowerService powerService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findBy("username",s);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = userService.getRole(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> rolestr = new ArrayList<>();


        List<Power> powers = new ArrayList<>();
        for (Role r : roles) {
            rolestr.add(r.getDescription());
            authorities.add(new SimpleGrantedAuthority(r.getDescription()));
            List<Power> powers1 = powerService.getByRole(r.getId());
            if (powers1 != null) {
                powers.addAll(powers1);
            }
        }

        if (powers.size() > 0) {
            for (Power p : powers) {
                authorities.add(new SimpleGrantedAuthority(p.getUrl()));
            }
        }

        return new AuthUser(user.getUsername(), user.getPassword(), authorities, rolestr, user.getId(), user.getType(),user.getNickname(),user.getAvatar());

    }
}
