package model.chat;

import javafx.scene.image.Image;
import model.User;


public enum Reactions {
    LAUGH("laugh" , null),
    LOVE("love" , null),
    ANGRY("angry" , null),
    LIKE("like" , null),
    DISLIKE("dislike" , null),
    SAD("sad" , null);

    private final String name;
    private final Image image;
    Reactions(String name , Image image) {
        this.name = name;
        this.image = image;
    }
}
