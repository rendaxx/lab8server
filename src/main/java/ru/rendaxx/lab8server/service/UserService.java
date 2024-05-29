package ru.rendaxx.lab8server.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rendaxx.lab8server.dto.UserDto;
import ru.rendaxx.lab8server.entity.Users;
import ru.rendaxx.lab8server.mapper.UserCreateMapper;
import ru.rendaxx.lab8server.repository.UsersRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class  UserService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserCreateMapper userCreateMapper;

    @Autowired
    public UserService(UsersRepository usersRepository, UserCreateMapper userCreateMapper) {
        this.usersRepository = usersRepository;
        this.userCreateMapper = userCreateMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByLogin(username)
                .map(user -> new User(
                        user.getLogin(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    public Optional<Users> findByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    @Transactional
    public void createUser(UserDto userDto) {
        Optional.of(userDto).map(userCreateMapper::map).map(usersRepository::save).orElseThrow();
    }
}
