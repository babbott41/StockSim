Blake Abbott
CTIS 210

Project Description
The goal of my final project was to create a complete Java application that simulates stock price movements and allows a user to interact with those movements through trading, visualization, and automated decision-making. The result is a stock pattern simulator that generates continually changing prices, displays those prices in a live-updating chart, and gives users tools for making trading decisions, both manual and automated. The project helped me understand not only how individual Java components work, but also how algorithmic structures guide the behavior of interactive systems.
Functions of the Program
Price Simulation
The stock price behavior is based on a random-walk model. Each update applies two influences:
A random shock, generated from a Gaussian distribution, which represents the unpredictable nature of markets.
A fixed trend value, chosen by the user, which can push the price slightly upward or downward over time.
The combination of randomness and trend produces price movements that feel natural: sometimes rising steadily, other times falling or drifting sideways. Volatility is adjustable through a slider in the interface, allowing the user to experiment with how stable or chaotic the market should look. A higher volatility value creates larger and more dramatic swings, while a lower value makes the price behave more smoothly.
Graphical Visualization
The core visual element of the program is a live line chart. Each time the price updates, the program stores the new value in a list and repaints the ChartPanel. The panel rescales the values to fit the height and width of the window, draws a polyline through all recorded prices, and updates continuously in real time. This visual feedback is crucial in helping the user understand market behavior and recognize patterns. Because the program updates the chart on a timer, the graph behaves like a miniature version of a live stock ticker.
Manual Trading System
The manual trading system allows the user to buy or sell shares at any time. A Trader class maintains the user’s:
->  cash balance
->  number of shares owned
->  trade history
->  total net worth


When the user clicks “Buy” or “Sell,” the program checks whether the user has enough cash or shares to complete the trade. If the trade is valid, it adjusts the internal values and logs the transaction in a scrolling text area. This provides an incremental record of decisions over time, helping the user reflect on how price changes influenced their behavior.
Automated Trading Strategy
The program also includes a simple rule-based auto-strategy. When enabled, it evaluates the percentage change between the current price and the previous price:
If the price drops beyond a chosen threshold, the strategy automatically buys a fixed number of shares (“buying the dip”).
If the price rises beyond a threshold, it automatically sells shares to capture gains.
This feature demonstrates how algorithmic logic can be used to make decisions without user input. Even though the strategy is very simple, it introduces ideas related to automated trading, decision rules, and reactive algorithms.
How to Use the Program
The program is designed to be intuitive even for users unfamiliar with stock markets.
Starting the Simulation
Upon launching the program, the user can begin generating price updates by clicking Start. The simulation runs automatically on a timer, but the user may pause it with Stop or advance it one update at a time using Step.
Understanding the Chart
The chart displays all price data collected since the last reset. The line moves from left to right, and each tick represents one new price. As the simulation continues, the user can observe short-term fluctuations, long-term trends, and volatility effects.
Trading Shares
To trade, the user enters a quantity into the input box and clicks Buy or Sell. The account summary labels update to show:
->  current cash
->  number of shares
-> total net worth
The trade log records every action.
Using the Auto-Strategy
The auto-strategy can be turned on with a checkbox. Once active, it automatically trades during each update cycle. All automated decisions also appear in the trade log.
Configuring the Market
On the right side of the interface, the user can modify:
-> starting price
-> starting cash
-> volatility
-> upward or downward trend
After adjusting these settings, clicking Reset restarts the entire simulation with the new parameters.
Challenges Presented
One of the most significant challenges was learning how to draw dynamic graphics in Java Swing. Understanding how paintComponent() worked, and how to correctly scale price values into screen coordinates, required experimentation. Another difficulty involved keeping the program responsive while updating frequently. Swing’s Timer simplified this, but coordinating repainting, price updates, and strategy evaluation still required careful ordering.
Managing multiple interacting classes was also a challenge. The program includes distinct classes for simulation, trading, strategy, charting, and GUI control. Ensuring each class had a clear responsibility helped avoid confusion. For example, the StockSimulator should never modify the user’s money, and the Trader should never modify the chart; separating these concerns made the program more reliable.
Developing the auto-strategy raised conceptual questions similar to those discussed in class about algorithms and fairness. Even a simple rule-based system can make aggressive trades if volatility is high, sometimes helping the user significantly and sometimes harming them. This highlighted the broader challenge in algorithm design: deterministic rules behave unpredictably when paired with random environments. This connects directly to discussions from class about how algorithms operate under conditions of uncertainty, and how they must be evaluated not only by intent but by the structure of their inputs.
Conclusion
This project was an opportunity to combine many ideas from the course into a single functional application: GUI design, event-driven programming, algorithmic behavior, and object-oriented architecture. By building a stock simulator, I learned how to organize interacting components, how to visualize data in real time, and how to design simple automated decision systems. Although the project could be extended dramatically – with multiple stocks, improved chart types, or more advanced trading strategies – the current version already demonstrates the power and flexibility of the concepts learned during the semester. Completing it gave me a deeper understanding of how algorithms guide dynamic systems, and how user interaction shapes the overall structure of a program.

