package org.example.service;

import org.example.model.AdminModel;
import org.example.model.UserModel;
import org.example.repository.AdminRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthService  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    // mencari user berdasarkan username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AdminModel adminModel = adminRepository.findByEmail(email);
        if (adminModel != null) {
            // Cek jika pengguna memiliki peran admin
            if (adminModel.getRole().equals("admin")) {
                List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User(adminModel.getEmail(), adminModel.getPassword(), roles);
            }
        }

        UserModel userModel = userRepository.findByEmail(email);
        if (userModel != null) {
            // Cek jika pengguna memiliki peran admin
            if (userModel.getRole().equals("admin")) {
                List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User(userModel.getEmail(), userModel.getPassword(), roles);
            }
        }


        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
