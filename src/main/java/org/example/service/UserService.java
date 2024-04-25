package org.example.service;

import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;
import org.example.model.AdminModel;
import org.example.model.UserModel;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserService() {
    }

    public UserModel savePenggunaRoleUser(UserDTO userDTO) throws Exception {
        // Periksa apakah email sudah ada dalam basis data
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IllegalAccessException("Email already exists");
        }
        UserModel user = new UserModel();
        user.setEmail(userDTO.getEmail());
        user.setOrganisasi(userDTO.getOrganisasi());
        user.setRole("user");
        user.setUsername(userDTO.getUsername());
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}