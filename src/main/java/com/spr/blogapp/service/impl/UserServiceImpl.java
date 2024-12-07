package com.spr.blogapp.service.impl;

import com.spr.blogapp.dto.UserDto;
import com.spr.blogapp.entity.Role;
import com.spr.blogapp.form.UserCreateForm;
import com.spr.blogapp.mapper.UserMapper;
import com.spr.blogapp.reporisoty.RoleRepository;
import com.spr.blogapp.reporisoty.UserRepository;
import com.spr.blogapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public UserDto create(UserCreateForm form) {
        var user = UserMapper.map(form);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRoles(Set.of(roleRepository.findByType(Role.Type.EMPLOYEE)));
        var savedUser = userRepository.save(user);
        return UserMapper.map(savedUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        var authorities = new ArrayList<GrantedAuthority>();
        var roles = user.getRoles();
        for (var role : roles){
            var type = role.getType().toString();
            var authority = new SimpleGrantedAuthority(type);
            authorities.add(authority);
        }
        return new User(
                username,
                user.getPassword(),
                authorities
        );
    }
}
