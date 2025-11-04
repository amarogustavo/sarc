package com.sarc.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @NotBlank(message = "Código do periférico é obrigatório")
    @Size(min = 3, max = 10, message = "Código deve ter entre 3 e 10 caracteres")
    private String codigo;

    @Column(nullable = false)
    @NotBlank(message = "type de periférico é obrigatório")
    @Enumerated(EnumType.STRING)
    private ToolType type;

    public Tool() {
    }

    public Tool(String codigo, ToolType type) {
        this.codigo = codigo;
        this.type = type;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ToolType gettype() {
        return type;
    }

    public void settype(ToolType type) {
        this.type = type;
    }
}
