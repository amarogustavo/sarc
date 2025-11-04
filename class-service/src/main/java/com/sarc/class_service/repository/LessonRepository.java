package com.sarc.class_service.repository;

import com.sarc.class_service.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, String> {
    Lesson findLessonByCodigo(String codigo);

    Optional<Lesson> findByNomeIgnoreCase(String nome);

    List<Lesson> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCodigo(String codigo);

}