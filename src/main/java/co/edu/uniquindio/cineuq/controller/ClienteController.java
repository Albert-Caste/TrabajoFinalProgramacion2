package co.edu.uniquindio.cineuq.controller;

import co.edu.uniquindio.cineuq.model.Cliente;

public class ClienteController {

    private Cliente cliente;

    public ClienteController(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente obtenerCliente() {
        return cliente;
    }

    public String obtenerNombreCliente() {
        return cliente.getNombre();
    }

    public String obtenerCorreoCliente() {
        return cliente.getCorreo();
    }
}