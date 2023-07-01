package model.chat;

import graphics_view.view.ProfileMenu;
import javafx.scene.image.Image;
import model.User;

import java.util.HashMap;


public enum Reactions {
    LAUGH("laugh" , "/assets/reactions/Laugh.jpg"),
    LOVE("love" , "/assets/reactions/Love.jpg"),
    ANGRY("angry" , "/assets/reactions/Angry.jpg"),
    LIKE("like" , "/assets/reactions/Like.png"),
    DISLIKE("dislike" , "/assets/reactions/Dislike.png"),
    SAD("sad" , "/assets/reactions/Sad.jpg");

    private final String name;
    private final String imagePath;
    private static final HashMap<String , Reactions> allReactions = new HashMap<>();
    Reactions(String name , String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
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
        return new Image(Reactions.class.getResource(imagePath).toExternalForm());
    }

    public static Reactions getTypeByName(String name) {
        return allReactions.get(name);
    }
}
