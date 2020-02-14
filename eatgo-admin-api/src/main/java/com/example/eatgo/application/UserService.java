package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(String email, String name, Long level) {
       User user = User.builder()
                    .email(email)
                    .name(name)
                    .level(level)
                    .build()
                    ;
       return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {
       //TODO: restaurantService의 예외 처리 참고

       // Optional<User> OptionalUser = userRepository.findById(id);

        User user = userRepository.findById(id).orElse(null);

       // User user = OptionalUser.get();

        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);

        userRepository.save(user);

        return user;
    }

    public User deactiveUser(long id) {
        //TODO: 실제로 작업 필요;
        User user = userRepository.findById(id).orElse(null);

        user.deactivate();

        userRepository.save(user);

        return user;
    }
}
