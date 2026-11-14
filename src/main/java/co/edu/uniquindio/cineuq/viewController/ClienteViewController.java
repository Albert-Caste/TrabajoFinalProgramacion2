package co.edu.uniquindio.cineuq.viewController;

import co.edu.uniquindio.cineuq.controller.ClienteController;
import co.edu.uniquindio.cineuq.model.Cliente;
import co.edu.uniquindio.cineuq.viewController.CuentaViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteViewController {

    @FXML
    private Label lblNombreCliente;

    @FXML
    private Label lblCorreoCliente;

    @FXML
    private Label lblMensaje;

    private ClienteController clienteController;

    public void setCliente(Cliente cliente) {

        clienteController = new ClienteController(cliente);

        lblNombreCliente.setText(
                "Bienvenido, " + clienteController.obtenerNombreCliente()
        );

        lblCorreoCliente.setText(
                clienteController.obtenerCorreoCliente()
        );
    }

    @FXML
    public void onConsultarCartelera() {
        lblMensaje.setText("Cartelera: módulo pendiente de implementar.");
    }

    @FXML
    public void onComprarEntradas() {
        lblMensaje.setText("Compras: módulo pendiente de implementar.");
    }

    @FXML
    public void onMiCuenta() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/cineuq/cuenta-view.fxml"
                    )
            );

            Parent root = loader.load();

            CuentaViewController viewController = loader.getController();
            viewController.setCliente(
                    clienteController.obtenerCliente()
            );

            Stage stage = (Stage) lblNombreCliente.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Mi cuenta");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo abrir la cuenta.");
            e.printStackTrace();
        }
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

            Stage stage = (Stage) lblNombreCliente.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Iniciar sesión");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo cerrar la sesión.");
            e.printStackTrace();
        }
    }
}