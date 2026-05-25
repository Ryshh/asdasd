module com.example.nevnapgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nevnapgui to javafx.fxml;
    exports com.example.nevnapgui;
}