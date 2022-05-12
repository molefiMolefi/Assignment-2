module com.example.molefi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.molefi to javafx.fxml;
    exports com.example.molefi;
}