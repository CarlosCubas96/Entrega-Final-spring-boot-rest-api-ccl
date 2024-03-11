package com.proyectdwes.api.proyect.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectdwes.api.proyect.dto.UserResponseDTO;
import com.proyectdwes.api.proyect.services.UserServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
@Api(value = "AuthorizationAdminController", tags = "Admin Operations")
public class AuthorizationAdminController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationAdminController.class);

    private UserServiceI userService;

    public AuthorizationAdminController(UserServiceI userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Get all users", notes = "Retrieve a list of all users", response = UserResponseDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of users"),
            @ApiResponse(code = 403, message = "Forbidden - Access denied"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<List<UserResponseDTO>> showUsers() {
        logger.info("## AuthorizationAdminController :: showUsers");
        List<UserResponseDTO> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}
