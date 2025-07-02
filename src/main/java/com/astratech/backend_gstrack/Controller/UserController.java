package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.UserService;
import com.astratech.backend_gstrack.VO.Result;
import com.astratech.backend_gstrack.VO.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public Object saveUser(HttpServletResponse response, @RequestBody User param) {
        boolean isSuccess = userService.saveUser(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to save user");
        }
    }

    @PutMapping("/user")
    public Object updateUser(HttpServletResponse response, @RequestBody User param) {
        boolean isSuccess = userService.updateUser(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to update user");
        }
    }

    @DeleteMapping("/user")
    public Object deleteUser(HttpServletResponse response, @RequestParam("userId") String userId) {
        boolean isSuccess = userService.deleteUser(userId);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to delete user");
        }
    }

    @PostMapping("/login")
    public Object login(HttpServletResponse response, @RequestBody User loginRequest) {
        User user = userService.getUserById(loginRequest.getUserId());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return new Result(200, "Login Successful", user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new Result(401, "Invalid username or password");
        }
    }

}
