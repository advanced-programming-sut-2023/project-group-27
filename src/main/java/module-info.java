module APGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires com.google.common;
    requires java.desktop;
    requires commons.lang3;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;


    exports graphics_view.view;
    exports graphics_view.graphical_controller;
    exports model;
    exports controller;
    opens model to com.google.gson;
    opens model.database to com.google.gson;
    opens server to com.google.gson;
    opens model.building to com.google.gson;
    opens model.man to com.google.gson;
    opens graphics_view.graphical_controller to javafx.fxml;
    opens graphics_view.view to com.google.gson, javafx.fxml;
}