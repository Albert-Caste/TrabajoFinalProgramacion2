package co.edu.uniquindio.cineuq.model;


public class Cliente extends Usuario {

    private String nombre;
    private String documento;
    private TarjetaVirtual tarjetaVirtual;
    private int puntos;

    public Cliente(String correo, String contrasena,
                   String nombre, String documento) {

        super(correo, contrasena, RolUsuario.CLIENTE);

        this.nombre = nombre;
        this.documento = documento;
//Datos de prueba

        this.tarjetaVirtual = new TarjetaVirtual(
                "**** **** **** 1234",
                50000
        );

        this.puntos = 100;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TarjetaVirtual getTarjetaVirtual() {
        return tarjetaVirtual;
    }

    public void setTarjetaVirtual(TarjetaVirtual tarjetaVirtual) {
        this.tarjetaVirtual = tarjetaVirtual;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
