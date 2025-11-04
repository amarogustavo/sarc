package com.sarc.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Classes")
public class Class {
    @Id
    @NotBlank(message = "Código da Class é obrigatório")
    @Size(min = 3, max = 10, message = "Código deve ter entre 3 e 10 caracteres")
    private String codigo;

    @Column(nullable = false)
    @NotBlank(message = "type de Class é obrigatório")
    @Enumerated(EnumType.STRING)
    private ClassType type;

    public Class() {
    }

    public Class(String codigo, ClassType type) {
        this.codigo = codigo;
        this.type = type;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ClassType gettype() {
        return type;
    }

    public void settype(ClassType type) {
        this.type = type;
    }
}
