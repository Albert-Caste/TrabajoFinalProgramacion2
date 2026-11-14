
package co.edu.uniquindio.cineuq.model;

import java.time.LocalDateTime;

public class Reporte {

    private String nombre;
    private String descripcion;
    private LocalDateTime fechaGeneracion;

    public Reporte(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaGeneracion = LocalDateTime.now();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public String getFechaFormateada() {
        return fechaGeneracion.toString().replace("T", " ");
    }
}
