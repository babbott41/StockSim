// tracks cash shares and trades

import java.util.ArrayList;
import java.util.List;

public class Trader {

    private double cashBalance;
    private int sharesHeld;
    private final List<Trade> trades;

    public Trader(double startingCash) {
        this.cashBalance = startingCash;
        this.sharesHeld = 0;
        this.trades = new ArrayList<>();
    }

    public boolean buy(int qty, double price) {
        if (qty <= 0) return false;
        double cost = qty * price;
        if (cost > cashBalance) return false;
        cashBalance -= cost;
        sharesHeld += qty;
        trades.add(new Trade(Trade.Type.BUY, qty, price));
        return true;
    }

    public boolean sell(int qty, double price) {
        if (qty <= 0) return false;
        if (qty > sharesHeld) return false;
        double money = qty * price;
        cashBalance += money;
        sharesHeld -= qty;
        trades.add(new Trade(Trade.Type.SELL, qty, price));
        return true;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public int getSharesHeld() {
        return sharesHeld;
    }

    public double getNetWorth(double price) {
        return cashBalance + sharesHeld * price;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void reset(double startingCash) {
        cashBalance = startingCash;
        sharesHeld = 0;
        trades.clear();
    }
}