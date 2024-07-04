module com.example.projectjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.projectjava to javafx.fxml;
    exports com.example.projectjava;
}