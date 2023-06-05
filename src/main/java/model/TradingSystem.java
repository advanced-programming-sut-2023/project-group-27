package model;

import java.util.ArrayList;
import java.util.List;

public class TradingSystem {
    private int idCounter = 1;
    private final User systemOwner;
    private final List<Trade> newTrades = new ArrayList<>();
    private final List<Trade> incomingTrades = new ArrayList<>();
    private final List<Trade> outgoingTrades = new ArrayList<>();

    public TradingSystem(User systemOwner) {
        this.systemOwner = systemOwner;
    }

    private int generateId() {
        return 1000 + idCounter++;
    }
    public void addTrade(User otherUser, User owner, GoodsType type, int amount, int price, String comment) {
        Trade trade = new Trade(generateId(), otherUser, owner, type, amount, price, comment);
        owner.getMonarchy().getTradingSystem().addToHistory(trade);
        newTrades.add(trade);
    }

    private void addToHistory(Trade trade) {
        outgoingTrades.add(trade);
    }

    public String notifications() {
        StringBuilder result = new StringBuilder();
        for (Trade trade : newTrades) {
            result.append(trade.toString()).append("\n");
            incomingTrades.add(trade);
        }
        newTrades.clear();
        return result.toString();
    }

    public String acceptTrade(int id) {
        Trade trade = null;
        for (Trade awaitingTrade : incomingTrades) {
            if (awaitingTrade.getId() == id) {
                trade = awaitingTrade;
                break;
            }
        }
        if (trade == null) {
            return "no such trade\n";
        }
        Monarchy systemOwnerMonarchy = systemOwner.getMonarchy();
        if (systemOwnerMonarchy.getGood(trade.getType()) < trade.getAmount()) {
            return "not enough goods to accept this trade\n";
        }
        Monarchy tradeOwnerMonarchy = trade.getOwner().getMonarchy();
        if (tradeOwnerMonarchy.getGold() < trade.getPrice()) {
            return "trade owner does not have enough gold to trade with you\n";
        }
        trade.setStateToDone();
        tradeOwnerMonarchy.changeGold(-trade.getPrice());
        systemOwnerMonarchy.changeGold(trade.getPrice());
        tradeOwnerMonarchy.putGood(trade.getType(),
                tradeOwnerMonarchy.getGood(trade.getType())
                        + trade.getAmount());
        systemOwnerMonarchy.putGood(trade.getType(),
                systemOwnerMonarchy.getGood(trade.getType())
                        - trade.getAmount());
        return "trade accepted\n";
    }

    public String showHistory(){
        StringBuilder result = new StringBuilder();
        for (Trade trade : outgoingTrades) {
            result.append(trade.toString()).append("\n");
        }
        return result.toString();
    }

    public String showAwaitingTrades(){
        StringBuilder result = new StringBuilder();
        for (Trade trade : incomingTrades) {
            result.append(trade.toString()).append("\n");
        }
        return result.toString();
    }

    public List<Trade> getIncomingTrades() {
        return incomingTrades;
    }

    public List<Trade> getOutgoingTrades() {
        return outgoingTrades;
    }
}
