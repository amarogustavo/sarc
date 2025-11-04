package com.sarc.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "class_reservations")
public class BookingClass {

    @Column(name = "codigo_professor", nullable = false)
    @NotBlank(message = "Código do professor é obrigatório")
    private String codigoProfessor;

    @Column(name = "codigo_turma", nullable = false)
    @NotBlank(message = "Código da turma é obrigatório")
    private String codigoTurma;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_Class", referencedColumnName = "codigo")
    private Class class;

    @Column(name = "tipo_Class", nullable = false)
    @NotBlank(message = "Tipo de Class é obrigatório")
    @Enumerated(EnumType.STRING)
    private ClassType tipo;

    public BookingClass(String codigoProfessor, String codigoTurma, String hora, String data, Class class) {
        super(codigoProfessor + codigoTurma + data, hora, data);
        this.codigoProfessor = codigoProfessor;
        this.codigoTurma = codigoTurma;
        this.class = class;
        this.tipo = Class.getTipo();
    }

    public BookingClass() {
    }

    public String getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(String codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public ClassType getTipo() {
        return tipo;
    }

    public void setTipo(ClassType tipo) {
        this.tipo = tipo;
    }

    public Class getClass() {
        return Class;
    }

    public void setClass(Class Class) {
        this.Class = Class;
    }
}
