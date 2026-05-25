module com.example.allatokgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.allatokgui to javafx.fxml;
    exports com.example.allatokgui;
}