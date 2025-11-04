package com.sarc.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Bookingtions")
public class Booking {
    @Id
    @NotBlank(message = "Código da Booking é obrigatório")
    @Size(min = 3, max = 10, message = "Código deve ter entre 3 e 10 caracteres")
    private String codigo;

    @Column(nullable = false)
    @NotBlank(message = "Horário é obrigatório")
    private String hora;

    @Column(nullable = false)
    @NotBlank(message = "Data é obrigatória")
    private String data;

    @Column(nullable = false)
    @NotBlank(message = "Status de Booking é obrigatório")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking() {
    }

    public Booking(String codigo, String hora, String data) {
        this.codigo = codigo;
        this.hora = hora;
        this.data = data;
        this.status = BookingStatus.PENDENTE;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "codigo='" + codigo + '\'' +
                ", hora='" + hora + '\'' +
                ", data='" + data + '\'' +
                ", status=" + status +
                '}';
    }
}
