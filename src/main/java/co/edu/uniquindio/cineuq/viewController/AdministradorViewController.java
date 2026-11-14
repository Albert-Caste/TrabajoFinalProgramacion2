package co.edu.uniquindio.cineuq.viewController;

import co.edu.uniquindio.cineuq.controller.AdministradorController;
import co.edu.uniquindio.cineuq.model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorViewController {

    private Usuario administrador;



    @FXML
    private Label lblCorreoAdministrador;

    @FXML
    private Label lblRol;

    @FXML
    private Label lblTotalPeliculas;

    @FXML
    private Label lblTotalFunciones;

    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblTotalCompras;

    @FXML
    private Label lblMensaje;

    @FXML
    public void onInicio() {
        cargarInformacion();
        lblMensaje.setText("Estás en el inicio del panel administrativo.");
    }

    private AdministradorController administradorController;

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;

        administradorController =
                new AdministradorController(administrador);

        cargarInformacion();
    }

    private void cargarInformacion() {

        lblCorreoAdministrador.setText(
                administradorController.obtenerCorreoAdministrador()
        );

        lblRol.setText(
                administradorController.obtenerRolAdministrador()
        );

        lblTotalPeliculas.setText(
                String.valueOf(
                        administradorController.obtenerTotalPeliculas()
                )
        );

        lblTotalFunciones.setText(
                String.valueOf(
                        administradorController.obtenerTotalFunciones()
                )
        );

        lblTotalClientes.setText(
                String.valueOf(
                        administradorController.obtenerTotalClientes()
                )
        );

        lblTotalCompras.setText(
                String.valueOf(
                        administradorController.obtenerTotalCompras()
                )
        );
    }

    private void abrirGestion(String modulo) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/cineuq/gestion-view.fxml"
                    )
            );

            Parent root = loader.load();

            GestionViewController viewController =
                    loader.getController();

            viewController.setAdministrador(administrador);
            viewController.seleccionarModulo(modulo);

            Stage stage = (Stage) lblCorreoAdministrador
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Gestión");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo abrir el módulo de gestión.");
            e.printStackTrace();
        }
    }

    @FXML
    public void onGestionarPeliculas() {
        abrirGestion("Películas");
    }

    @FXML
    public void onGestionarSalas() {
        abrirGestion("Salas");
    }

    @FXML
    public void onGestionarFunciones() {
        abrirGestion("Funciones");
    }

    @FXML
    public void onGestionarProductos() {
        abrirGestion("Productos");
    }

    @FXML
    public void onGestionarClientes() {
        abrirGestion("Clientes");
    }

    @FXML
    public void onGestionarCompras() {
        abrirGestion("Compras");
    }


    @FXML
    public void onConsultarReportes() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/cineuq/reportes-view.fxml"
                    )
            );

            Parent root = loader.load();

            ReportesViewController viewController =
                    loader.getController();

            viewController.setAdministrador(administrador);

            Stage stage = (Stage) lblCorreoAdministrador
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Reportes");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo abrir la ventana de reportes.");
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    @FXML
    public void onCerrarSesion() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/cineuq/login-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) lblCorreoAdministrador
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Iniciar sesión");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo cerrar la sesión.");
            e.printStackTrace();
        }
    }
}