package co.edu.uniquindio.cineuq.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {

    private TipoMovimiento tipo;
    private String descripcion;
    private double valor;
    private LocalDateTime fecha;

    public Movimiento(TipoMovimiento tipo, String descripcion,
                      double valor, LocalDateTime fecha) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaFormateada() {
        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return fecha.format(formato);
    }

    public String getValorFormateado() {
        return String.format("$%,.0f", valor);
    }
}
