// handles price simulation

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockSimulator {

    private final List<Double> priceHistory;
    private double currentPrice;
    private double volatility;
    private double trend;
    private final Random random;

    public StockSimulator(double startingPrice, double volatility, double trend) {
        this.currentPrice = startingPrice;
        this.volatility = volatility;
        this.trend = trend;
        this.priceHistory = new ArrayList<>();
        this.priceHistory.add(startingPrice);
        this.random = new Random();
    }

    public void updatePrice() {
        double shock = random.nextGaussian() * volatility;
        double pctChange = trend + shock;
        currentPrice *= (1.0 + pctChange);
        if (currentPrice < 0.01) {
            currentPrice = 0.01;
        }
        priceHistory.add(currentPrice);
    }

    public List<Double> getPriceHistory() {
        return priceHistory;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void reset(double startingPrice) {
        priceHistory.clear();
        this.currentPrice = startingPrice;
        priceHistory.add(startingPrice);
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public void setTrend(double trend) {
        this.trend = trend;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getTrend() {
        return trend;
    }
}