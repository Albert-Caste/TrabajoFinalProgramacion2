package co.edu.uniquindio.cineuq.viewController;

import co.edu.uniquindio.cineuq.controller.LoginController;
import co.edu.uniquindio.cineuq.model.Cliente;
import co.edu.uniquindio.cineuq.model.RolUsuario;
import co.edu.uniquindio.cineuq.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Label lblMensaje;

    private LoginController loginController;

    @FXML
    public void initialize() {
        loginController = new LoginController();
    }

    @FXML
    public void onIniciarSesion() {

        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        if (correo.isBlank() || contrasena.isBlank()) {
            lblMensaje.setText("Complete todos los campos");
            return;
        }

        Usuario usuario = loginController.iniciarSesion(
                correo, contrasena
        );

        if (usuario == null) {
            lblMensaje.setText("Correo o contraseña incorrectos");
            return;
        }

        abrirVentanaPrincipal(usuario);
    }

    private void abrirVentanaPrincipal(Usuario usuario) {

        try {
            String ruta;

            if (usuario.getRol() == RolUsuario.CLIENTE) {
                ruta = "/co/edu/uniquindio/cineuq/cliente-view.fxml";
            } else {
                ruta = "/co/edu/uniquindio/cineuq/administrador-view.fxml";
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(ruta)
            );

            Parent root = loader.load();

            if (usuario instanceof Cliente) {

                Cliente cliente = (Cliente) usuario;

                ClienteViewController viewController =
                        loader.getController();

                viewController.setCliente(cliente);

            } else if (usuario.getRol() == RolUsuario.ADMINISTRADOR) {

                AdministradorViewController viewController =
                        loader.getController();

                viewController.setAdministrador(usuario);
            }

            Stage stage = (Stage) txtCorreo.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ");

        } catch (IOException e) {
            lblMensaje.setText("Error al abrir la ventana.");
            e.printStackTrace();
        }
    }
}