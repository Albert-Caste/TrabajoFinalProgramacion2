package co.edu.uniquindio.cineuq.controller;

import co.edu.uniquindio.cineuq.model.Cliente;
import co.edu.uniquindio.cineuq.model.Movimiento;

import java.util.List;

public class CuentaController {

    private Cliente cliente;

    public CuentaController(Cliente cliente) {
        this.cliente = cliente;
    }

    public String obtenerNombre() {
        return cliente.getNombre();
    }

    public String obtenerCorreo() {
        return cliente.getCorreo();
    }

    public String obtenerDocumento() {
        return cliente.getDocumento();
    }

    public String obtenerNumeroTarjeta() {
        return cliente.getTarjetaVirtual().getNumero();
    }

    public double obtenerSaldo() {
        return cliente.getTarjetaVirtual().getSaldo();
    }

    public int obtenerPuntos() {
        return cliente.getPuntos();
    }

    public boolean tarjetaActiva() {
        return cliente.getTarjetaVirtual().isActiva();
    }

    public List<Movimiento> obtenerMovimientos() {
        return cliente.getTarjetaVirtual().getListaMovimientos();
    }
}