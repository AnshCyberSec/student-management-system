package com.sms.studentmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.studentmanagementsystem.entity.User;
import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
