package org.example.controller;

import org.example.dto.AdminDTO;
import org.example.model.AdminModel;
import org.example.response.CommonResponse;
import org.example.response.ResponseHelper;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/register-admin", method = RequestMethod.POST)
    public CommonResponse<AdminModel> registerAdmin(@RequestBody AdminDTO user) throws Exception {
        return ResponseHelper.ok(adminService.savePenggunaRoleAdmin(user));
    }
}
