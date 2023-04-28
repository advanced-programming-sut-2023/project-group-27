package model;

public enum TreeType {
    DESSERTSHRUB("Dessert Palm", "\\u001B[38;5;m"),
    CHERRYPALM("Cherry Palm", "\\u001B[31m"),
    OLIVETREE("Olive Tree", "\\u001B[38;5;178m"),
    COCONUTPALM("Coconut Palm", "\\u001B[37m"),
    DATESPALM("Dates Palm", "\\u001B[38;5;237m");

    private String treeName;
    private String ANSI_COLOR;

    TreeType(String treeName, String ANSI_COLOR) {
        this.treeName = treeName;
        this.ANSI_COLOR = ANSI_COLOR;
    }

    public String getANSI_COLOR() {
        return ANSI_COLOR;
    }

    public String getTreeName() {
        return treeName;
    }
}
