package co.edu.uniquindio.cineuq.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarjetaVirtual {

    private String numero;
    private double saldo;
    private boolean activa;
    private List<Movimiento> listaMovimientos;

    public TarjetaVirtual(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        this.activa = true;
        this.listaMovimientos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        listaMovimientos.add(movimiento);
    }

    public void registrarRecarga(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(
                    "El valor de la recarga debe ser mayor que cero."
            );
        }

        saldo += valor;

        agregarMovimiento(new Movimiento(
                TipoMovimiento.RECARGA,
                "Recarga de tarjeta",
                valor,
                LocalDateTime.now()
        ));
    }

    public boolean realizarCompra(double valor, String descripcion) {
        if (!activa || valor <= 0 || saldo < valor) {
            return false;
        }

        saldo -= valor;

        agregarMovimiento(new Movimiento(
                TipoMovimiento.COMPRA,
                descripcion,
                valor,
                LocalDateTime.now()
        ));

        return true;
    }

    public void registrarReembolso(double valor, String descripcion) {
        if (valor <= 0) {
            throw new IllegalArgumentException(
                    "El valor del reembolso debe ser mayor que cero."
            );
        }

        saldo += valor;

        agregarMovimiento(new Movimiento(
                TipoMovimiento.REEMBOLSO,
                descripcion,
                valor,
                LocalDateTime.now()
        ));
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}