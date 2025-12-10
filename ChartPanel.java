// draws price line chart

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

public class ChartPanel extends JPanel {

    private List<Double> priceHistory;

    public void setPriceHistory(List<Double> priceHistory) {
        this.priceHistory = priceHistory;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (priceHistory == null || priceHistory.size() < 2) return;

        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (double p : priceHistory) {
            if (p < min) min = p;
            if (p > max) max = p;
        }

        if (max == min) max = min + 1;

        int pad = 30;
        int usableW = w - 2 * pad;
        int usableH = h - 2 * pad;

        g2.setColor(Color.BLUE);

        int prevX = pad;
        int prevY = toY(priceHistory.get(0), min, max, h, pad, usableH);

        for (int i = 1; i < priceHistory.size(); i++) {
            int x = pad + (i * usableW) / (priceHistory.size() - 1);
            int y = toY(priceHistory.get(i), min, max, h, pad, usableH);
            g2.drawLine(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
        }
    }

    private int toY(double price, double min, double max, int h, int pad, int usableH) {
        double n = (price - min) / (max - min);
        return h - pad - (int)(n * usableH);
    }
}
