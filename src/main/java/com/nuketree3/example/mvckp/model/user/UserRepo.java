//package com.nuketree3.example.mvckp.model.user;
//
//import com.nuketree3.example.mvckp.model.service.Service;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.sql.SQLException;
//import java.util.Optional;
//
//@Repository
//public class UserRepo extends JpaRepository<User, Long> {
//    private final Service service = new Service();
//
//    public Optional<User> findByEmail(String email) throws SQLException {
//        System.out.println(service.getUserFromDb(email));
//        return Optional.of(service.getUserFromDb(email));
//    }
//}
