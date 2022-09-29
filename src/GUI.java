import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class GUI {

    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15, label16;
    JLabel score;
    JFrame frame;
    JPanel gamePanel;
    static JPanel gameOver;

    Color two = new Color(238, 228, 218);
    Color four = new Color(238, 225, 201);
    Color eight = new Color(242, 178, 121);
    Color sixteen = new Color(246, 150, 100);
    Color thirtyTwo = new Color(247, 123, 94);
    Color sixtyFour = new Color(247, 94, 60);
    Color oneTwentyEight = new Color(237, 206, 113);
    Color twoFiftySix = new Color(237, 204, 97);
    Color fiveTwelve = new Color(237, 200, 83);
    Color tenTwentyFour = new Color(237, 197, 63);
    Color twentyFortyEight = new Color(237, 195, 49);
    Color emptyCell = new Color(205, 193, 180);
    Color border = new Color(187, 173, 160);
    Color textColor = new Color(119, 110, 101);
    Border borderFactory = BorderFactory.createLineBorder(border, 5, true);

    public GUI() {
        frame = new JFrame();
        gamePanel = new JPanel(new GridLayout(4, 4)) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = (Math.min(w, h));
                return new Dimension(s,s);
            }
        };
        JPanel mainPanel = new JPanel();
        score = new JLabel("Score: 0");

        label1 = new JLabel("2", SwingConstants.CENTER);
        setLabel(label1);
        label2 = new JLabel("4", SwingConstants.CENTER);
        setLabel(label2);
        label3 = new JLabel("8", SwingConstants.CENTER);
        setLabel(label3);
        label4 = new JLabel("16", SwingConstants.CENTER);
        setLabel(label4);
        label5 = new JLabel("32", SwingConstants.CENTER);
        setLabel(label5);
        label6 = new JLabel("64", SwingConstants.CENTER);
        setLabel(label6);
        label7 = new JLabel("128", SwingConstants.CENTER);
        setLabel(label7);
        label8 = new JLabel("256", SwingConstants.CENTER);
        setLabel(label8);
        label9 = new JLabel("512", SwingConstants.CENTER);
        setLabel(label9);
        label10 = new JLabel("1024", SwingConstants.CENTER);
        setLabel(label10);
        label11 = new JLabel("2048", SwingConstants.CENTER);
        setLabel(label11);
        label12 = new JLabel("5096", SwingConstants.CENTER);
        setLabel(label12);
        label13 = new JLabel("10192", SwingConstants.CENTER);
        setLabel(label13);
        label14 = new JLabel("20384", SwingConstants.CENTER);
        setLabel(label14);
        label15 = new JLabel("40768", SwingConstants.CENTER);
        setLabel(label15);
        label16 = new JLabel("81536", SwingConstants.CENTER);
        setLabel(label16);

        gamePanel.setPreferredSize(new Dimension(400, 400));

        gameOver = createPopupPanel(gamePanel);
        gameOver.setVisible(false);

        mainPanel.add(gameOver);
        mainPanel.add(score);
        mainPanel.add(gamePanel);

        mainPanel.setPreferredSize(new Dimension(600, 700));

        var imgURL = Main.class.getResource("2048.png");
        frame.setTitle("2048");
        if (imgURL != null) frame.setIconImage(new ImageIcon(imgURL).getImage());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    void propertyChangeListener(PropertyChangeEvent evt, JLabel label) {
        var num = evt.getNewValue().toString();
        switch (num) {
            case "" -> label.setBackground(emptyCell);
            case "2" -> label.setBackground(two);
            case "4" -> label.setBackground(four);
            case "8" -> label.setBackground(eight);
            case "16" -> label.setBackground(sixteen);
            case "32" -> label.setBackground(thirtyTwo);
            case "64" -> label.setBackground(sixtyFour);
            case "128" -> label.setBackground(oneTwentyEight);
            case "256" -> label.setBackground(twoFiftySix);
            case "512" -> label.setBackground(fiveTwelve);
            case "1024" -> label.setBackground(tenTwentyFour);
            case "2048" -> label.setBackground(twentyFortyEight);
        }
    }

    void setLabel(JLabel label) {
        label.setSize(48, 48);
        label.setBounds(48, 48, 48, 48);
        label.setForeground(textColor);
        label.setBackground(emptyCell);
        label.setOpaque(true);
        label.setBorder(borderFactory);
        label.setFont(new Font("Clear Sans", Font.BOLD, 48));
        label.addPropertyChangeListener(evt -> propertyChangeListener(evt, label));
        gamePanel.add(label);
    }

    private static JPanel createPopupPanel(JComponent overlapComponent) {
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        gameOverPanel.setOpaque(false);
        gameOverPanel.setMaximumSize(new Dimension(150, 70));
        gameOverPanel.setBorder(new LineBorder(Color.gray));
        gameOverPanel.setVisible(false);

        JLabel label = new JLabel("Game Over!");
        gameOverPanel.add(wrapInPanel(label), BorderLayout.CENTER);

        JButton gameOverRestartButton = new JButton("Restart");
        gameOverPanel.add(wrapInPanel(gameOverRestartButton), BorderLayout.SOUTH);

        gameOverRestartButton.addActionListener(e -> {
            overlapComponent.setEnabled(true);
            gameOverPanel.setVisible(false);
            Main.restart();
            gameOver.setVisible(false);
        });

        return gameOverPanel;
    }

    private static JPanel wrapInPanel(JComponent component) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(50, 210, 250, 150));
        jPanel.add(component);
        return jPanel;
    }
}
