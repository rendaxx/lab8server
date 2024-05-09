package ru.rendaxx.lab8server.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.rendaxx.lab8server.UserDto;
import ru.rendaxx.lab8server.entity.Role;
import ru.rendaxx.lab8server.entity.Users;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserDto, Users> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Users map(UserDto object) {
        Users user = new Users();
        copy(object, user);
        return user;
    }

    @Override
    public Users map(UserDto fromObject, Users toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserDto object, Users user) {
        user.setLogin(object.getUsername());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        user.setRole(Role.USER);
    }
}
