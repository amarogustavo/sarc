package com.sarc.class_service.repository;

import java.util.List;
import java.util.Optional;
import com.sarc.class_service.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, String> {
    Optional<Class> findById(String codigo);

    @Query("SELECT t.horario FROM Class t WHERE t.codigo = :codigo")
    Optional<String> findHorarioByCodigo(@Param("codigo") String codigo);

    boolean existsByCodigo(String codigo);

    List<Class> findByCodigoIn(List<String> codigos);

    @Query("SELECT t FROM Class t JOIN t.estudantes e WHERE e.id = :estudanteId")
    List<Class> findClasssByEstudante(String estudanteId);
}