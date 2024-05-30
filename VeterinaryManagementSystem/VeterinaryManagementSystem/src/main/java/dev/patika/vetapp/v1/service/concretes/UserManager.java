package dev.patika.vetapp.v1.service.concretes;

import dev.patika.vetapp.v1.core.config.modelMapper.ModelMapperService;
import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.core.utilities.ResultHelper;
import dev.patika.vetapp.v1.dao.UserRepository;
import dev.patika.vetapp.v1.dto.request.user.UserSaveRequest;
import dev.patika.vetapp.v1.dto.response.user.UserResponse;
import dev.patika.vetapp.v1.entities.User;
import dev.patika.vetapp.v1.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ModelMapperService mapperService;

    @Override
    public ResultData<UserResponse> createUser(UserSaveRequest user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return ResultHelper.CREATED(mapperService.forResponse().
                map(userRepository.save(mapperService.forRequest().map(user, User.class)),UserResponse.class));
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
