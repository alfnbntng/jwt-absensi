package org.example.controller;

import org.example.dto.UserDTO;
import org.example.model.UserModel;
import org.example.response.CommonResponse;
import org.example.response.ResponseHelper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public CommonResponse<UserModel> registerAdmin(@RequestBody UserDTO user) throws Exception {
        return ResponseHelper.ok(userService.savePenggunaRoleUser(user));
    }
}
