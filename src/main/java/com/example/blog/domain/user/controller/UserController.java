package com.example.blog.domain.user.controller;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<UserRes> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(userRes);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserReq userReq){
        User user = userService.createUser(userReq);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(userRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserReq userReq){
        User user = userService.updateUser(userId, userReq);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(userRes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?>  updatePassword(@PathVariable Long id, @RequestBody UserReq pwdReq){
        User updateUser = userService.updatePassword(id, pwdReq);
        UserRes userRes = UserRes.fromEntity(updateUser);
        return ResponseEntity.ok(userRes);
    }

}