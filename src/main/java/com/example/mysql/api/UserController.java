package com.example.mysql.api;
import com.example.mysql.data_layer.User;
import com.example.mysql.exception.ResourceNotFoundException;
import com.example.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;




@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }



    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Long userId) {
        try {
            User user = userService.getUser(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Failed to get user from DB ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }


}
