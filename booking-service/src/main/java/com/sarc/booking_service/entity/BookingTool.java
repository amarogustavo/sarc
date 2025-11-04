package com.sarc.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "class_reservations")
public class BookingTool {
    @Column(name = "codigo_professor", nullable = false)
    @NotBlank(message = "Código do professor é obrigatório")
    private String codigoProfessor;

    @Column(name = "codigo_turma", nullable = false)
    @NotBlank(message = "Código da turma é obrigatório")
    private String codigoTurma;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_Tool", referencedColumnName = "codigo")
    private Tool tool;

    @Column(name = "tipo_Tool", nullable = false)
    @NotBlank(message = "Tipo de Tool é obrigatório")
    @Enumerated(EnumType.STRING)
    private ToolType tipo;

    public BookingTool(String codigoProfessor, String codigoTurma, String hora, String data,
            Tool Tool) {
        super();
        this.codigoProfessor = codigoProfessor;
        this.codigoTurma = codigoTurma;
        this.tool = Tool;
        this.tipo = Tool.getTipo();
    }

    public BookingTool() {
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

    public ToolType getTipo() {
        return tipo;
    }

    public void setTipo(ToolType tipo) {
        this.tipo = tipo;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool Tool) {
        this.tool = Tool;
    }
}
