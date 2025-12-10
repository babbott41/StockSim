// simple auto trading logic

public class Strategy {

    private boolean enabled;
    private double buyDip = -0.03;
    private double sellRise = 0.05;
    private Double lastPrice;

    public void setEnabled(boolean b) {
        enabled = b;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int decide(double price) {
        if (!enabled) return 0;
        if (lastPrice == null) {
            lastPrice = price;
            return 0;
        }

        double pct = (price - lastPrice) / lastPrice;
        int action = 0;

        if (pct <= buyDip) action = 5;
        else if (pct >= sellRise) action = -5;

        lastPrice = price;
        return action;
    }

    public void reset() {
        lastPrice = null;
    }
}