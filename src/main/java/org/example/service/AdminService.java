package org.example.service;

import org.example.dto.AdminDTO;
import org.example.model.AdminModel;
import org.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public AdminService() {
    }

    public AdminModel savePenggunaRoleAdmin(AdminDTO adminDTO) throws Exception {
        // Periksa apakah email sudah ada dalam basis data
        if (adminRepository.findByEmail(adminDTO.getEmail()) != null) {
            throw new IllegalAccessException("Email already exists");
        }
        AdminModel admin = new AdminModel();
        admin.setEmail(adminDTO.getEmail());
        admin.setImageAdmin(adminDTO.getImageAdmin());
        admin.setIdOrganisasi(adminDTO.getIdOrganisasi());
        admin.setRole("admin");
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(bcryptEncoder.encode(adminDTO.getPassword()));
        return adminRepository.save(admin);
    }
}