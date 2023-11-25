package com.example.arendapro.repository;

import com.example.arendapro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);
}
