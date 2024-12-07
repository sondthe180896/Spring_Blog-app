package com.spr.blogapp;

import com.spr.blogapp.entity.Role;
import com.spr.blogapp.entity.User;
import com.spr.blogapp.reporisoty.RoleRepository;
import com.spr.blogapp.reporisoty.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class BlogAppApplication implements CommandLineRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user = new User();
        user.setName("Đặng Thái Sơn");
        user.setUsername("SonDTc");
        user.setEmail("thaison02004@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        var admin = roleRepository.findByType(Role.Type.ADMIN);
        var employee = roleRepository.findByType(Role.Type.EMPLOYEE);
        user.setRoles(Set.of(admin, employee));
        userRepository.save(user);
    }
}
