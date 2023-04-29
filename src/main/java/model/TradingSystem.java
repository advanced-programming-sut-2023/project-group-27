package model;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class TradingSystem {
    private int idCounter = 1;
    private final User systemOwner;
    private final List<Trade> newTrades = new ArrayList<>();
    private final List<Trade> awaitingTrades = new ArrayList<>();
    private final List<Trade> history = new ArrayList<>();

    public TradingSystem(User systemOwner) {
        this.systemOwner = systemOwner;
    }

    private int generateId() {
        return 1000 + idCounter++;
    }
    public void addTrade(User owner, GoodsType type, int amount, int price, String comment) {
        Trade trade = new Trade(generateId(), owner, type, amount, price, comment);
        owner.getMonarchy().getTradingSystem().addToHistory(trade);
        newTrades.add(trade);
    }

    private void addToHistory(Trade trade) {
        history.add(trade);
    }

    public String notifications() {
        StringBuilder result = new StringBuilder();
        for (Trade trade : newTrades) {
            result.append(trade.toString()).append("\n");
            awaitingTrades.add(trade);
        }
        newTrades.clear();
        return result.toString();
    }

    public String acceptTrade(int id) {
        Trade trade = null;
        for (Trade awaitingTrade : awaitingTrades) {
            if (awaitingTrade.getId() == id) {
                trade = awaitingTrade;
                break;
            }
        }
        if (trade == null) {
            return "no such trade\n";
        }
        Monarchy systemOwnerMonarchy = systemOwner.getMonarchy();
        if (systemOwnerMonarchy.getStorage().getOrDefault(trade.getType(), 0) < trade.getAmount()) {
            return "not enough goods to accept this trade\n";
        }
        Monarchy tradeOwnerMonarchy = trade.getOwner().getMonarchy();
        if (tradeOwnerMonarchy.getGold() < trade.getPrice()) {
            return "trade owner does not have enough gold to trade with you\n";
        }
        trade.setStateToDone();
        tradeOwnerMonarchy.changeGold(-trade.getPrice());
        systemOwnerMonarchy.changeGold(trade.getPrice());
        tradeOwnerMonarchy.getStorage().put(trade.getType(),
                tradeOwnerMonarchy.getStorage().getOrDefault(trade.getType(), 0)
                        + trade.getAmount());
        systemOwnerMonarchy.getStorage().put(trade.getType(),
                systemOwnerMonarchy.getStorage().getOrDefault(trade.getType(), 0)
                        - trade.getAmount());
        awaitingTrades.remove(trade);
        history.add(trade);
        return "trade accepted\n";
    }

    public String showHistory(){
        StringBuilder result = new StringBuilder();
        for (Trade trade : history) {
            result.append(trade.toString()).append("\n");
        }
        return result.toString();
    }

    public String showAwaitingTrades(){
        StringBuilder result = new StringBuilder();
        for (Trade trade : awaitingTrades) {
            result.append(trade.toString()).append("\n");
        }
        return result.toString();
    }
}
