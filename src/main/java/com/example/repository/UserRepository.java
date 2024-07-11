package com.example.repository;

import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Azizbek Toshpulatov
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
