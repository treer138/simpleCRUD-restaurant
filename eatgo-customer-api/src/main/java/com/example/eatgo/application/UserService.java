package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String name, String password) {
        //TODO: 제대로된 구현 요망
        //비교

        userRepository.findByEmail(email).ifPresent(user -> {
            throw new EmailExistedException(user.getEmail());
        });

        //BCrypt 암호화
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .level(1L)
                .password(encodedPassword)
                .build()
                ;

        return userRepository.save(user);

    }

    public User authenticate(String email, String password) {
        //TODO:
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new EmailNotExistedException());

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        };

        return user;

    }
}
