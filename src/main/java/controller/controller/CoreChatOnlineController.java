package controller.controller;

import server.Connection;

public class CoreChatOnlineController {
    private final Connection connection;
    private final CoreChatMenuController controller;

    public CoreChatOnlineController(Connection connection) {
        this.connection = connection;
        controller = new CoreChatMenuController();
    }
}
