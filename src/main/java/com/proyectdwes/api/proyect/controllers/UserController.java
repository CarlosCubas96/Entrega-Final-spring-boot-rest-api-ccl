package com.proyectdwes.api.proyect.controllers;

import com.proyectdwes.api.proyect.dto.UserResponseDTO;
import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.services.UserServiceI;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(value = "UserController", tags = "User Operations")
public class UserController {

    private UserServiceI userService;

    public UserController(UserServiceI userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Get all users", notes = "Retrieve a list of all users", response = UserResponseDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of users"),
            @ApiResponse(code = 403, message = "Forbidden - Access denied"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "Get user by ID", notes = "Retrieve a user by their ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the user"),
            @ApiResponse(code = 403, message = "Forbidden - Access denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Create a new user", notes = "Create a new user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 403, message = "Forbidden - Access denied"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Update user by ID", notes = "Update a user by their ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 403, message = "Forbidden - Access denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Delete user by ID", notes = "Delete a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 403, message = "Forbidden - Access denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
