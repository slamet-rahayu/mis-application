package com.example.springboot;

import com.example.springboot.modules.auth.AuthService;
import com.example.springboot.modules.user.UserDetailServiceCore;
import com.example.springboot.modules.user.UserEntity;
import com.example.springboot.modules.user.UserRepository;
import com.example.springboot.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @InjectMocks
    UserDetailServiceCore userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSuccessLogin() {
        UserEntity user = new UserEntity();
        String username = "testusername";
        String password = "password";
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(username)).thenReturn("test-jwt");
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        String result = authService.authenticate(username, password);

        assertEquals("test-jwt", result);

        verify(jwtUtil, times(1)).generateToken(username);

    }


}
