package com.sarc.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarc.user_service.entity.User;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public abstract class UserRepository {

    public abstract Optional<User> findByMatricula(String matricula);

    public abstract List<User> findByNomeContainingIgnoreCase(String nome);

    public abstract boolean existsByMatricula(String matricula);

    public abstract boolean existsByEmail(String email);

    public User save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
