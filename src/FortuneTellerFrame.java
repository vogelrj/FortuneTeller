import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    private JTextArea textArea;
    private ArrayList<String> fortunes;
    private int lastFortuneIndex = -1;

    public FortuneTellerFrame() {

        setupFrame();

        JPanel topPanel = createTopPanel();
        JScrollPane middlePanel = createMiddlePanel();
        JPanel bottomPanel = createBottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        fortunes = new ArrayList<>();
        addFortunes();

        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set Frame Size (3/4 of Screen) and Centered
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = (int) (screenWidth * 0.75);
        int frameHeight = (int) (screenHeight * 0.75);
        setSize(frameWidth, frameHeight);
        setLocation(screenWidth / 8, screenHeight / 8);

        getContentPane().setBackground(new Color(100, 149, 237));
    }

    /** Creates top panel with the title and image */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        // Load Icon and resize
        ImageIcon originalIcon = new ImageIcon("FortuneTeller.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);

        // JLabel with Text Above Image
        JLabel titleLabel = new JLabel("Fortune Teller", icon, JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));

        topPanel.setOpaque(false);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return topPanel;
    }

    /** Creates middle panel with scrollable fortune text area */
    private JScrollPane createMiddlePanel() {
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(textArea);
        return scrollPane;
    }

    /** Creates bottom Panel with Fortune and Quit buttons */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);

        JButton readFortuneButton = new JButton("Read My Fortune!");
        JButton quitButton = new JButton("Quit");

        readFortuneButton.setFont(new Font("Serif", Font.BOLD, 20));
        quitButton.setFont(new Font("Serif", Font.BOLD, 20));

        readFortuneButton.addActionListener((ActionEvent e) -> displayRandomFortune());
        quitButton.addActionListener((ActionEvent e) -> System.exit(0));

        bottomPanel.add(readFortuneButton);
        bottomPanel.add(quitButton);

        return bottomPanel;
    }

    /** Fortune List */
    private void addFortunes() {
        fortunes.add("Hard work pays off in the future, laziness pays off now.");
        fortunes.add("A foolish man listens to his heart, a wise man listens to an intermediate Java app.");
        fortunes.add("Regardless of bad luck, not walking under ladders is just good advice.");
        fortunes.add("Congrats! You are not illiterate.");
        fortunes.add("Don't win the lottery, it's just a trap to catch time travelers.");
        fortunes.add("There is no mistake that running away to join the French Foreign Legion under an assumed identity cannot fix.");
        fortunes.add("People can be forgotten, but those responsible for new required safety briefings live forever(-ish).");
        fortunes.add("Calories from food eaten in front of an open fridge at 3am do not count.");
        fortunes.add("Yeah, more caffeine ought to do it.");
        fortunes.add("Do onto others as you do onto you or something.");
        fortunes.add("Ignore the previous fortune, I was in a really bad space then.");
        fortunes.add("My Golden Rule is that life is too short to hold grudges. My father held grudges and I always hated him for that.");
    }

    /** Displays a random fortune that does not repeat */
    private void displayRandomFortune() {
        if (fortunes.isEmpty()) return;

        Random rand = new Random();
        int newIndex;

        do {
            newIndex = rand.nextInt(fortunes.size());
        } while (newIndex == lastFortuneIndex);

        lastFortuneIndex = newIndex;
        textArea.append(fortunes.get(newIndex) + "\n");
    }
}
