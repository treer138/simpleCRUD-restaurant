package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser() {
        String email = "xxxxx@ppp.com";
        String name = "xxx";
        String password = "bbb";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
    public void registerUserWithExistedEmail() {

        String email = "tst@example.com";
        String name = "tester";
        String password = "test";
        User mockUser = User.builder().email(email).name(name).password(password).build();

        given(userService.registerUser(email, name, password)).willReturn(mockUser);

        userService.registerUser(email,name,password);

        verify(userRepository, times(2)).findByEmail(email);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void authenticateWithValidAttributes(){

        String email = "tst@example.com";
        String password = "test";

        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any())).willReturn(true);

        User user = userService.authenticate(email, password);

        assertEquals(user.getEmail(), email);
    }

    @Test
    public void authenticateWithNotExistedEmail(){

        String email = "x@example.com";
        String password = "test";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        assertThrows(EmailNotExistedException.class, ()->
                userService.authenticate(email, password)
                );
    }

    @Test
    public void authenticateWithWrongPassword(){

        String email = "tester@example.com";
        String password = "x";

        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        given(passwordEncoder.matches(any(),any())).willReturn(false);

        assertThrows(PasswordWrongException.class, ()->
                userService.authenticate(email, password)
        );
    }
}