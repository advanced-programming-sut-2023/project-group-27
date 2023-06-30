package model.chat;

import graphics_view.view.ProfileMenu;
import javafx.scene.image.Image;
import model.User;

import java.util.HashMap;


public enum Reactions {
    LAUGH("laugh" , new Image(Reactions.class.getResource(
            "/assets/reactions/Laugh.jpg").toExternalForm())),
    LOVE("love" , new Image(Reactions.class.getResource(
            "/assets/reactions/Love.jpg").toExternalForm())),
    ANGRY("angry" , new Image(Reactions.class.getResource(
            "/assets/reactions/Angry.jpg").toExternalForm())),
    LIKE("like" , new Image(Reactions.class.getResource(
            "/assets/reactions/Like.png").toExternalForm())),
    DISLIKE("dislike" , new Image(Reactions.class.getResource(
            "/assets/reactions/Dislike.png").toExternalForm())),
    SAD("sad" , new Image(Reactions.class.getResource(
            "/assets/reactions/Sad.jpg").toExternalForm()));

    private final String name;
    private final Image image;
    private static final HashMap<String , Reactions> allReactions = new HashMap<>();
    Reactions(String name , Image image) {
        this.name = name;
        this.image = image;
    }

    public static void init() {
        for (Reactions reaction : Reactions.values()) {
            allReactions.put(reaction.name , reaction);
        }
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public static Reactions getTypeByName(String name) {
        return allReactions.get(name);
    }
}
