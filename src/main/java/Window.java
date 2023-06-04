import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width;
    private int height;
    private String title;
    private static Window window = null;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Hello World";

    }

    public static Window get() {
        if (Window.window == null)
            Window.window = new Window();
        return Window.window;
    }

    public void run() {
        System.out.println("Hello LWJGL" + Version.getVersion());

        init();
        loop();
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException();
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE , GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED , GLFW_TRUE);
    }

    public void loop() {

    }
}
