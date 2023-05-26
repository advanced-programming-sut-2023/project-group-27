module APGameAA {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires com.google.common;
    requires java.desktop;
    requires commons.lang3;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;


    exports graphics_view;
    exports model;
    exports controller;
    opens model to com.google.gson;
    opens graphics_view to com.google.gson, javafx.fxml;
}