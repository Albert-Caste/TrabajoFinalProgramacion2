package co.edu.uniquindio.cineuq.viewController;

import co.edu.uniquindio.cineuq.controller.CuentaController;
import co.edu.uniquindio.cineuq.model.Cliente;
import co.edu.uniquindio.cineuq.model.Movimiento;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CuentaViewController {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblCorreo;

    @FXML
    private Label lblDocumento;

    @FXML
    private Label lblNumeroTarjeta;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblPuntos;

    @FXML
    private Label lblEstadoTarjeta;

    @FXML
    private Label lblMensaje;

    @FXML
    private TableView<Movimiento> tablaMovimientos;

    @FXML
    private TableColumn<Movimiento, String> colFecha;

    @FXML
    private TableColumn<Movimiento, String> colTipo;

    @FXML
    private TableColumn<Movimiento, String> colDescripcion;

    @FXML
    private TableColumn<Movimiento, String> colValor;

    private CuentaController cuentaController;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.cuentaController = new CuentaController(cliente);

        configurarTabla();
        cargarInformacion();
    }

    private void configurarTabla() {

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fechaFormateada")
        );

        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );

        colValor.setCellValueFactory(
                new PropertyValueFactory<>("valorFormateado")
        );
    }

    private void cargarInformacion() {

        lblNombre.setText(cuentaController.obtenerNombre());
        lblCorreo.setText(cuentaController.obtenerCorreo());
        lblDocumento.setText(cuentaController.obtenerDocumento());

        lblNumeroTarjeta.setText(
                cuentaController.obtenerNumeroTarjeta()
        );

        lblSaldo.setText(
                String.format("$%,.0f", cuentaController.obtenerSaldo())
        );

        lblPuntos.setText(
                String.valueOf(cuentaController.obtenerPuntos())
        );

        if (cuentaController.tarjetaActiva()) {
            lblEstadoTarjeta.setText("Activa");
        } else {
            lblEstadoTarjeta.setText("Inactiva");
        }

        tablaMovimientos.setItems(
                FXCollections.observableArrayList(
                        cuentaController.obtenerMovimientos()
                )
        );
    }

    @FXML
    public void onActualizar() {
        cargarInformacion();
        lblMensaje.setText("Información actualizada.");
    }

    @FXML
    public void onRegresar() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/cineuq/cliente-view.fxml"
                    )
            );

            Parent root = loader.load();

            ClienteViewController viewController = loader.getController();
            viewController.setCliente(cliente);

            Stage stage = (Stage) lblNombre.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Portal del cliente");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo regresar al portal.");
            e.printStackTrace();
        }
    }
}