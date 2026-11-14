
package co.edu.uniquindio.cineuq.viewController;

import co.edu.uniquindio.cineuq.controller.ReporteController;
import co.edu.uniquindio.cineuq.model.Usuario;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ReportesViewController {

    @FXML
    private Label lblTotalCompras;

    @FXML
    private Label lblIngresos;

    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblTotalEntradas;

    @FXML
    private Label lblMensaje;

    @FXML
    private ComboBox<String> cbTipoReporte;

    @FXML
    private BarChart<String, Number> graficoVentas;

    @FXML
    private PieChart graficoPeliculas;

    @FXML
    private LineChart<String, Number> graficoFunciones;

    private ReporteController reporteController;
    private Usuario administrador;

    @FXML
    public void initialize() {

        reporteController = new ReporteController();

        cbTipoReporte.setItems(
                FXCollections.observableArrayList(
                        "Ventas",
                        "Películas",
                        "Funciones",
                        "Clientes",
                        "General"
                )
        );

        cbTipoReporte.getSelectionModel().select("General");

        cargarEstadisticas();
        cargarGraficos();
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

    private void cargarEstadisticas() {

        lblTotalCompras.setText(
                String.valueOf(reporteController.obtenerTotalCompras())
        );

        lblIngresos.setText(
                String.format(
                        "$%,.0f",
                        reporteController.obtenerIngresos()
                )
        );

        lblTotalClientes.setText(
                String.valueOf(reporteController.obtenerTotalClientes())
        );

        lblTotalEntradas.setText(
                String.valueOf(reporteController.obtenerTotalEntradas())
        );
    }

    private void cargarGraficos() {

        // Gráfico de barras: ventas mensuales
        XYChart.Series<String, Number> serieVentas =
                new XYChart.Series<>();

        serieVentas.setName("Ingresos");

        serieVentas.getData().add(
                new XYChart.Data<>("Enero", 1200000)
        );

        serieVentas.getData().add(
                new XYChart.Data<>("Febrero", 1800000)
        );

        serieVentas.getData().add(
                new XYChart.Data<>("Marzo", 1500000)
        );

        serieVentas.getData().add(
                new XYChart.Data<>("Abril", 2200000)
        );

        graficoVentas.getData().clear();
        graficoVentas.getData().add(serieVentas);

        // Gráfico circular: distribución de películas
        graficoPeliculas.setData(
                FXCollections.observableArrayList(
                        new PieChart.Data("Acción", 40),
                        new PieChart.Data("Comedia", 25),
                        new PieChart.Data("Drama", 20),
                        new PieChart.Data("Animación", 15)
                )
        );

        // Gráfico de líneas: funciones mensuales
        XYChart.Series<String, Number> serieFunciones =
                new XYChart.Series<>();

        serieFunciones.setName("Funciones");

        serieFunciones.getData().add(
                new XYChart.Data<>("Enero", 35)
        );

        serieFunciones.getData().add(
                new XYChart.Data<>("Febrero", 42)
        );

        serieFunciones.getData().add(
                new XYChart.Data<>("Marzo", 38)
        );

        serieFunciones.getData().add(
                new XYChart.Data<>("Abril", 50)
        );

        graficoFunciones.getData().clear();
        graficoFunciones.getData().add(serieFunciones);
    }

    @FXML
    public void onActualizar() {
        cargarEstadisticas();
        cargarGraficos();

        lblMensaje.setText(
                "Estadísticas actualizadas (datos de demostración)."
        );
    }

    @FXML
    public void onExportarTXT() {

        String tipo = cbTipoReporte.getValue();

        if (tipo == null) {
            lblMensaje.setText("Selecciona un tipo de reporte.");
            return;
        }

        FileChooser selector = new FileChooser();
        selector.setTitle("Guardar reporte TXT");
        selector.setInitialFileName("reporte-cineuq.txt");

        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Archivo de texto", "*.txt"
                )
        );

        Stage stage = (Stage) lblMensaje.getScene().getWindow();
        File archivo = selector.showSaveDialog(stage);

        if (archivo == null) {
            return;
        }

        try {
            String contenido = reporteController.generarContenido(tipo);

            reporteController.exportarTXT(archivo, contenido);

            lblMensaje.setText("Reporte TXT exportado correctamente.");

        } catch (IOException e) {
            lblMensaje.setText("Error al exportar el reporte TXT.");
            e.printStackTrace();
        }
    }

    @FXML
    public void onExportarPDF() {

        String tipo = cbTipoReporte.getValue();

        if (tipo == null) {
            lblMensaje.setText("Selecciona un tipo de reporte.");
            return;
        }

        FileChooser selector = new FileChooser();
        selector.setTitle("Guardar reporte PDF");
        selector.setInitialFileName("reporte-cineuq.pdf");

        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Archivo PDF", "*.pdf"
                )
        );

        Stage stage = (Stage) lblMensaje.getScene().getWindow();
        File archivo = selector.showSaveDialog(stage);

        if (archivo == null) {
            return;
        }

        try {
            String contenido = reporteController.generarContenido(tipo);

            reporteController.exportarPDF(archivo, contenido);

            lblMensaje.setText("Reporte PDF exportado correctamente.");

        } catch (Exception e) {
            lblMensaje.setText("Error al exportar el reporte PDF.");
            e.printStackTrace();
        }
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

            Stage stage = (Stage) lblMensaje.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CinemaUQ - Administración");

        } catch (IOException e) {
            lblMensaje.setText("No se pudo regresar al panel.");
            e.printStackTrace();
        }
    }
}

