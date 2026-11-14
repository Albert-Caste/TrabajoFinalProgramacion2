
package co.edu.uniquindio.cineuq.controller;

public class GestionController {

    private String moduloSeleccionado;

    public GestionController() {
        this.moduloSeleccionado = "Películas";
    }

    public String getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(String moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }

    public String obtenerMensaje(String operacion) {
        return operacion + " en el módulo de "
                + moduloSeleccionado
                + ". Esta funcionalidad está pendiente.";
    }
}
