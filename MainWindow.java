// main gui and logic

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final StockSimulator simulator;
    private final Trader trader;
    private final Strategy strategy;
    private final ChartPanel chart;

    private final Timer timer;

    private final JTextArea log;
    private final JTextField qty;
    private final JTextField startP;
    private final JTextField startC;
    private final JSlider vol;
    private final JSlider tr;

    private final JLabel priceL;
    private final JLabel cashL;
    private final JLabel sharesL;
    private final JLabel worthL;

    public MainWindow() {
        super("stock sim");

        simulator = new StockSimulator(100, 0.02, 0.001);
        trader = new Trader(10000);
        strategy = new Strategy();

        chart = new ChartPanel();
        chart.setPreferredSize(new Dimension(700, 350));

        qty = new JTextField("10", 4);
        startP = new JTextField("100", 5);
        startC = new JTextField("10000", 7);

        vol = new JSlider(0, 100, 20);
        tr = new JSlider(-20, 20, 1);

        priceL = new JLabel();
        cashL = new JLabel();
        sharesL = new JLabel();
        worthL = new JLabel();

        log = new JTextArea(8, 40);
        log.setEditable(false);

        updateLabels();
        chart.setPriceHistory(simulator.getPriceHistory());

        timer = new Timer(200, e -> tick());

        buildUI();
        wire();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void buildUI() {

        setLayout(new BorderLayout());
        add(chart, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        JButton start = new JButton("start");
        JButton stop = new JButton("stop");
        JButton step = new JButton("step");
        JButton reset = new JButton("reset");
        JButton buy = new JButton("buy");
        JButton sell = new JButton("sell");
        JCheckBox auto = new JCheckBox("auto");

        controls.add(start);
        controls.add(stop);
        controls.add(step);
        controls.add(reset);
        controls.add(new JLabel("qty"));
        controls.add(qty);
        controls.add(buy);
        controls.add(sell);
        controls.add(auto);
        controls.add(priceL);
        controls.add(cashL);
        controls.add(sharesL);
        controls.add(worthL);

        add(controls, BorderLayout.SOUTH);

        JPanel right = new JPanel(new BorderLayout());
        JPanel cfg = new JPanel(new GridLayout(0,2));

        cfg.add(new JLabel("start price"));
        cfg.add(startP);
        cfg.add(new JLabel("start cash"));
        cfg.add(startC);
        cfg.add(new JLabel("vol"));
        cfg.add(vol);
        cfg.add(new JLabel("trend"));
        cfg.add(tr);

        right.add(cfg, BorderLayout.NORTH);

        JScrollPane sp = new JScrollPane(log);
        right.add(sp, BorderLayout.CENTER);

        add(right, BorderLayout.EAST);

        start.addActionListener(e -> timer.start());
        stop.addActionListener(e -> timer.stop());
        step.addActionListener(e -> tick());
        auto.addActionListener(e -> strategy.setEnabled(auto.isSelected()));
        buy.addActionListener(e -> doBuy());
        sell.addActionListener(e -> doSell());
        reset.addActionListener(e -> doReset());
    }

    private void wire() {
        vol.addChangeListener(e -> simulator.setVolatility(vol.getValue() / 100.0));
        tr.addChangeListener(e -> simulator.setTrend(tr.getValue() / 1000.0));
    }

    private void tick() {
        simulator.updatePrice();
        chart.setPriceHistory(simulator.getPriceHistory());
        applyStrategy();
        updateLabels();
    }

    private void applyStrategy() {
        int action = strategy.decide(simulator.getCurrentPrice());
        if (action > 0) trader.buy(action, simulator.getCurrentPrice());
        else if (action < 0) trader.sell(-action, simulator.getCurrentPrice());
    }

    private void doBuy() {
        try {
            int q = Integer.parseInt(qty.getText());
            if (trader.buy(q, simulator.getCurrentPrice())) {
                log.append("buy " + q + "\n");
                updateLabels();
            }
        } catch (Exception ignored) {}
    }

    private void doSell() {
        try {
            int q = Integer.parseInt(qty.getText());
            if (trader.sell(q, simulator.getCurrentPrice())) {
                log.append("sell " + q + "\n");
                updateLabels();
            }
        } catch (Exception ignored) {}
    }

    private void doReset() {
        try {
            simulator.reset(Double.parseDouble(startP.getText()));
            trader.reset(Double.parseDouble(startC.getText()));
            strategy.reset();
            log.setText("");
            chart.setPriceHistory(simulator.getPriceHistory());
            updateLabels();
        } catch (Exception ignored) {}
    }

    private void updateLabels() {
        double p = simulator.getCurrentPrice();
        priceL.setText("price " + String.format("%.2f", p));
        cashL.setText("cash " + String.format("%.2f", trader.getCashBalance()));
        sharesL.setText("shares " + trader.getSharesHeld());
        worthL.setText("worth " + String.format("%.2f", trader.getNetWorth(p)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}