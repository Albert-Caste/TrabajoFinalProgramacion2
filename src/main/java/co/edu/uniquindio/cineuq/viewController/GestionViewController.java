package co.edu.uniquindio.cineuq.viewController;

import co.edu.uniquindio.cineuq.controller.GestionController;
import co.edu.uniquindio.cineuq.model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionViewController {

    @FXML
    private TabPane tabGestion;

    @FXML
    private Tab tabPeliculas;

    @FXML
    private Tab tabSalas;

    @FXML
    private Tab tabFunciones;

    @FXML
    private Tab tabProductos;

    @FXML
    private Tab tabClientes;

    @FXML
    private Tab tabCompras;

    @FXML
    private Label lblMensaje;

    private GestionController gestionController;
    private Usuario administrador;

    @FXML
    public void initialize() {
        gestionController = new GestionController();

        tabGestion.getSelectionModel().selectedItemProperty()
                .addListener((observable, anterior, seleccionada) -> {
                    if (seleccionada != null) {
                        gestionController.setModuloSeleccionado(
                                seleccionada.getText()
                        );

                        lblMensaje.setText(
                                "Módulo seleccionado: "
                                        + seleccionada.getText()
                        );
                    }
                });
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

    public void seleccionarModulo(String modulo) {

        if (modulo == null) {
            return;
        }

        switch (modulo.toLowerCase()) {
            case "películas":
            case "peliculas":
                tabGestion.getSelectionModel().select(tabPeliculas);
                break;

            case "salas":
            case "salas y asientos":
                tabGestion.getSelectionModel().select(tabSalas);
                break;

            case "funciones":
                tabGestion.getSelectionModel().select(tabFunciones);
                break;

            case "productos":
            case "productos y combos":
                tabGestion.getSelectionModel().select(tabProductos);
                break;

            case "clientes":
                tabGestion.getSelectionModel().select(tabClientes);
                break;

            case "compras":
                tabGestion.getSelectionModel().select(tabCompras);
                break;

            default:
                tabGestion.getSelectionModel().select(tabPeliculas);
                break;
        }
    }

    @FXML
    public void onAgregar() {
        mostrarOperacion("Agregar");
    }

    @FXML
    public void onEditar() {
        mostrarOperacion("Editar");
    }

    @FXML
    public void onEliminar() {
        mostrarOperacion("Eliminar");
    }

    private void mostrarOperacion(String operacion) {
        String modulo = tabGestion.getSelectionModel()
                .getSelectedItem().getText();

        gestionController.setModuloSeleccionado(modulo);

        lblMensaje.setText(
                gestionController.obtenerMensaje(operacion)
        );
    }

    @FXML
    public void onRegresar() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/cineuq/administrador-view.fxml"
                    )
            );

            Parent root = loader.load();

            AdministradorViewController viewController =
                    loader.getController();

            viewController.setAdministrador(administrador);

            Stage stage = (Stage) tabGestion.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Administración");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo regresar al panel administrativo.");
            e.printStackTrace();
        }
    }
}