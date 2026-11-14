
package co.edu.uniquindio.cineuq.controller;

import co.edu.uniquindio.cineuq.model.Usuario;

public class AdministradorController {

    private Usuario administrador;

    public AdministradorController(Usuario administrador) {
        this.administrador = administrador;
    }

    public Usuario obtenerAdministrador() {
        return administrador;
    }

    public String obtenerCorreoAdministrador() {
        return administrador.getCorreo();
    }

    public String obtenerRolAdministrador() {
        return administrador.getRol().toString();
    }

    // Datos temporales para el panel principal

    public int obtenerTotalPeliculas() {
        return 12;
    }

    public int obtenerTotalFunciones() {
        return 24;
    }

    public int obtenerTotalClientes() {
        return 150;
    }

    public int obtenerTotalCompras() {
        return 320;
    }
}
