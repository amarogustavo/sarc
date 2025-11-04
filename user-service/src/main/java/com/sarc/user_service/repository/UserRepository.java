package com.sarc.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarc.user_service.entity.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    
    Optional<User> findByMatricula(String matricula);

    List<User> findByNomeContainingIgnoreCase(String nome);

    boolean existsByMatricula(String matricula);
    
    boolean existsByEmail(String email);

}
}
