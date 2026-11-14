
module co.edu.uniquindio.cineuq {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.desktop;

    // Dependencia para generar archivos PDF
    requires itextpdf;

    opens co.edu.uniquindio.cineuq to javafx.fxml;
    opens co.edu.uniquindio.cineuq.viewController to javafx.fxml;

    exports co.edu.uniquindio.cineuq;
    exports co.edu.uniquindio.cineuq.model;
    exports co.edu.uniquindio.cineuq.controller;
    exports co.edu.uniquindio.cineuq.viewController;
}
