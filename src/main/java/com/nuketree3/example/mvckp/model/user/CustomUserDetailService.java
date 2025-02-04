//package com.nuketree3.example.mvckp.model.user;
//
//import com.nuketree3.example.mvckp.model.enums.Role;
//import com.nuketree3.example.mvckp.model.service.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import java.sql.SQLException;
//import java.util.Optional;
//
//@org.springframework.stereotype.Service
//@Repository
//public class CustomUserDetailService implements UserDetailsService {
//    @Autowired
//    private UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            Optional<User> user = userRepo.findByEmail(username);
//            return (UserDetails) user.orElseThrow(() -> new UsernameNotFoundException(username));
//        } catch (SQLException e) {
//            System.out.println("ЧТО-ТО ПОШЛО НЕ ТАК");
//            throw new RuntimeException(e);
//        }
//    }
//}
