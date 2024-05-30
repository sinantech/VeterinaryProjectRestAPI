package dev.patika.vetapp.v1.service.abstracts;

import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.dto.request.user.UserSaveRequest;
import dev.patika.vetapp.v1.dto.response.user.UserResponse;
import dev.patika.vetapp.v1.entities.User;

public interface UserService {
    ResultData<UserResponse> createUser(UserSaveRequest user);
    User getByUsername(String name);
}
