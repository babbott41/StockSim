// simple trade record

import java.time.LocalDateTime;

public class Trade {
    public enum Type { BUY, SELL }

    private final Type type;
    private final int quantity;
    private final double price;
    private final LocalDateTime timestamp;

    public Trade(Type type, int quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public Type getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return timestamp + " - " + type + " " + quantity + " @ " + String.format("%.2f", price);
    }
}