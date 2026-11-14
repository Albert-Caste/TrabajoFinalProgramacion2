
package co.edu.uniquindio.cineuq.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ReporteController {

    // Datos temporales de demostración
    public int obtenerTotalCompras() {
        return 320;
    }

    public double obtenerIngresos() {
        return 8500000;
    }

    public int obtenerTotalClientes() {
        return 150;
    }

    public int obtenerTotalEntradas() {
        return 520;
    }

    public String generarContenido(String tipoReporte) {

        StringBuilder contenido = new StringBuilder();

        contenido.append("CINEMAUQ - REPORTE ADMINISTRATIVO\n");
        contenido.append("====================================\n\n");

        contenido.append("Tipo de reporte: ")
                .append(tipoReporte).append("\n");

        contenido.append("Total de compras: ")
                .append(obtenerTotalCompras()).append("\n");

        contenido.append("Ingresos registrados: $")
                .append(String.format("%.0f", obtenerIngresos()))
                .append("\n");

        contenido.append("Clientes registrados: ")
                .append(obtenerTotalClientes()).append("\n");

        contenido.append("Entradas vendidas: ")
                .append(obtenerTotalEntradas()).append("\n\n");

        contenido.append("Nota: estos valores son datos de demostración.");

        return contenido.toString();
    }

    public void exportarTXT(File archivo, String contenido)
            throws IOException {

        Files.write(
                archivo.toPath(),
                contenido.getBytes(StandardCharsets.UTF_8)
        );
    }

    public void exportarPDF(File archivo, String contenido)
            throws Exception {

        Document documento = new Document();

        try {
            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(archivo)
            );

            documento.open();

            documento.add(new Paragraph("CINEMAUQ"));
            documento.add(new Paragraph("Reporte administrativo"));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(contenido));

        } finally {
            documento.close();
        }
    }
}
