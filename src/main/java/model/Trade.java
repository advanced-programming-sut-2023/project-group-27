package model;

import java.util.List;

public class Trade {
    private int id;
    private User owner;
    private int amount;
    private int price;
    private String comment;
    private GoodsType type;
    private String state;

    public Trade(int id, User owner, GoodsType type, int amount, int price, String comment) {
        this.id = id;
        this.owner = owner;
        this.amount = amount;
        this.price = price;
        this.comment = comment;
        this.type = type;
        this.state = "awaiting";
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public GoodsType getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public void setStateToDone() {
        state = "done";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id: ").append(id).append("\n");
        builder.append("Owner: ").append(owner.getUsername()).append("\n");
        builder.append("Type: ").append(type.getName()).append("\n");
        builder.append("Amount: ").append(amount).append("\n");
        builder.append("Price: ").append(price).append("\n");
        builder.append("Comment: ").append(comment).append("\n");
        builder.append("State: ").append(state).append("\n");
        return builder.toString();
    }
}
