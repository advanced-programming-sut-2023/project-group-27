package graphics_view.graphical_controller;

import server.Connection;

public class CommandListener extends Thread{
    private Connection connection;

    public CommandListener(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            String command = null;
            try {
                // TODO mapEditOnline -> yousef
                command = connection.receive();
                System.out.println(command);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
