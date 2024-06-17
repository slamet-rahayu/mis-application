package com.example.springboot;

import com.example.springboot.modules.user.UserEntity;
import com.example.springboot.modules.user.UserRepository;
import com.example.springboot.modules.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserEntity user = new UserEntity();
        String username = "username";
        String password = "testpassowrd";
        user.setUsername(username);
        user.setPassword(password);

        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        userService.register(user);

        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }
}
