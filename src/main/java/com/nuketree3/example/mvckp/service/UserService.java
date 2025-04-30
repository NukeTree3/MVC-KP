package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.enums.Role;
import com.nuketree3.example.mvckp.model.user.User;
import com.nuketree3.example.mvckp.userdetails.UserDetailsImpl;
import com.nuketree3.example.mvckp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if(userRepository.getUserRole(user.getId()).equals(String.valueOf(Role.ROLE_NOT_ACTIVATED))) {
            throw new DisabledException("User account is not activated");
        }
        return UserDetailsImpl.buildUserDetails(user, userRepository.getUserRole(user.getId()));
    }

    public User getUserByID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
    }

    public boolean createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        userRepository.setRole(user.getId(), String.valueOf(Role.ROLE_NOT_ACTIVATED), UUID.randomUUID().toString());


        if(!user.getEmail().isEmpty()) {
            String mailText = "Hello " + user.getFirstName() + "!" +
                    "\n" +
                    "Please, visit http://localhost:8080/activationcode/" +
                    userRepository.getUserActivationCode(user.getId());
            mailSender.sendSimpleMail(user.getEmail(), "Activation", mailText);
        }


        return true;
    }



    public Long getUserId(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email)).getId();
    }

    public boolean activateUser(String code) {
        Long id = userRepository.getUserIDByActivationCode(code);
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(code));

        if(user == null || !userRepository.getUserRole(id).equals(String.valueOf(Role.ROLE_NOT_ACTIVATED))) {
            return false;
        }

        userRepository.setUserActivationCode(id, "null");
        userRepository.setUserRole(id, String.valueOf(Role.ROLE_USER));

        return true;
    }
}
