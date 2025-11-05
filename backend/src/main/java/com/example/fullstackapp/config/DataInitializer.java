package com.example.fullstackapp.config;

import com.example.fullstackapp.entity.Role;
import com.example.fullstackapp.entity.Role.RoleName;
import com.example.fullstackapp.entity.User;
import com.example.fullstackapp.repository.RoleRepository;
import com.example.fullstackapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize roles
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName(RoleName.USER);
            roleRepository.save(userRole);
            
            Role adminRole = new Role();
            adminRole.setName(RoleName.ADMIN);
            roleRepository.save(adminRole);
        }
        
        // Initialize admin user
        if (!userRepository.existsByEmail("admin@example.com")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("password"));
            
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(RoleName.ADMIN).orElseThrow());
            admin.setRoles(roles);
            userRepository.save(admin);
        }
        
        // Initialize regular user
        if (!userRepository.existsByEmail("user@example.com")) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("password"));
            
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(RoleName.USER).orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}

