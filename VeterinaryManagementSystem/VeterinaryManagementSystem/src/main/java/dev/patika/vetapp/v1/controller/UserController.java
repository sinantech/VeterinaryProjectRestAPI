package dev.patika.vetapp.v1.controller;

import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.dto.request.user.UserSaveRequest;
import dev.patika.vetapp.v1.dto.response.user.UserResponse;
import dev.patika.vetapp.v1.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<UserResponse> createUser(@RequestBody UserSaveRequest user) {
        return userService.createUser(user);
    }
}
