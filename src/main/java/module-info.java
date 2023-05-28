module APGameAA {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires com.google.common;
    requires java.desktop;
    requires commons.lang3;


    exports graphics_view.view;
    exports graphics_view.controller;
    exports model;
    exports controller;
    opens model to com.google.gson;
    opens graphics_view to com.google.gson, javafx.fxml;
    opens graphics_view.controller to javafx.fxml;
}