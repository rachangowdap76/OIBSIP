import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class GuessQuest extends JFrame {

    final Color BG = new Color(9,7,24), PANEL = new Color(22,17,45);
    final Color PURPLE = new Color(170,90,255), CYAN = new Color(0,235,255);
    final Color PINK = new Color(255,70,180), WHITE = new Color(245,245,255);
    final Color GRAY = new Color(165,160,190), GREEN = new Color(70,255,160);
    
    Random random = new Random();
    int number, attempts, maxAttempts, score = 0, round = 0, max = 100;
    
    JLabel feedback, attemptsLabel, scoreLabel, roundLabel, rangeLabel;
    JTextField input;
    JTextArea history;
    JComboBox<String> difficulty;
    JProgressBar progress;

    public GuessQuest() {
        setTitle("GuessQuest - Neon Number Challenge");
        setSize(1000, 680);
        setMinimumSize(new Dimension(900,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout(20,20));
        main.setBackground(BG);
        main.setBorder(new EmptyBorder(25,35,25,35));

        // HEADER
        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);

        JLabel title = new JLabel("GUESSQUEST");
        title.setFont(new Font("SansSerif",Font.BOLD,38));
        title.setForeground(CYAN);

        JLabel sub = new JLabel("⚡ NEON NUMBER CHALLENGE");
        sub.setForeground(PINK);
        sub.setFont(new Font("SansSerif",Font.BOLD,12));

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles,BoxLayout.Y_AXIS));
        titles.add(title);
        titles.add(sub);

        difficulty = new JComboBox<>(new String[]{"Easy","Medium","Hard"});
        difficulty.setSelectedItem("Medium");
        difficulty.addActionListener(e -> newRound());

        head.add(titles,BorderLayout.WEST);
        head.add(difficulty,BorderLayout.EAST);
        main.add(head,BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new GridLayout(1,2,20,0));
        center.setOpaque(false);
        center.add(gamePanel());
        center.add(historyPanel());
        main.add(center,BorderLayout.CENTER);

        JLabel footer = new JLabel(
            "◆ Guess smart  •  Beat the challenge  •  Become the champion ◆",
            SwingConstants.CENTER);
        footer.setForeground(GRAY);
        main.add(footer,BorderLayout.SOUTH);

        add(main);
        newRound();
    }

    JPanel gamePanel() {
        JPanel p = panel();
        p.setLayout(new BorderLayout(15,15));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        roundLabel = label("ROUND 01",PURPLE,13);
        scoreLabel = label("SCORE 0",CYAN,13);
        top.add(roundLabel,BorderLayout.WEST);
        top.add(scoreLabel,BorderLayout.EAST);
        p.add(top,BorderLayout.NORTH);

        JPanel area = new JPanel();
        area.setOpaque(false);
        area.setLayout(new BoxLayout(area,BoxLayout.Y_AXIS));

        JLabel question = label("GUESS THE SECRET NUMBER",WHITE,22);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        rangeLabel = label("",GRAY,14);
        rangeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        input = new JTextField();
        input.setMaximumSize(new Dimension(280,58));
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setFont(new Font("SansSerif",Font.BOLD,25));
        input.setForeground(WHITE);
        input.setBackground(new Color(31,24,60));
        input.setCaretColor(CYAN);
        input.setBorder(BorderFactory.createLineBorder(PURPLE,2));
        input.addActionListener(e -> guess());

        JButton guess = button("⚡ MAKE GUESS",PURPLE);
        guess.addActionListener(e -> guess());

        feedback = label("Enter your first guess!",GRAY,15);
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);

        area.add(Box.createVerticalStrut(20));
        area.add(question);
        area.add(Box.createVerticalStrut(7));
        area.add(rangeLabel);
        area.add(Box.createVerticalStrut(25));
        area.add(input);
        area.add(Box.createVerticalStrut(18));
        area.add(guess);
        area.add(Box.createVerticalStrut(20));
        area.add(feedback);

        p.add(area,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));

        attemptsLabel = label("",GRAY,12);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        progress = new JProgressBar();
        progress.setMaximumSize(new Dimension(350,12));
        progress.setForeground(CYAN);
        progress.setBorderPainted(false);

        JButton again = button("↻ NEW ROUND",CYAN);
        again.setForeground(Color.BLACK);
        again.addActionListener(e -> newRound());

        bottom.add(attemptsLabel);
        bottom.add(Box.createVerticalStrut(8));
        bottom.add(progress);
        bottom.add(Box.createVerticalStrut(18));
        bottom.add(again);

        p.add(bottom,BorderLayout.SOUTH);
        return p;
    }

    JPanel historyPanel() {
        JPanel p = panel();
        p.setLayout(new BorderLayout(10,10));

        JLabel t = label("⚡ ROUND HISTORY",WHITE,18);
        p.add(t,BorderLayout.NORTH);

        history = new JTextArea("No completed rounds yet...\n\nStart playing to see your results.");
        history.setEditable(false);
        history.setBackground(PANEL);
        history.setForeground(GRAY);
        history.setFont(new Font("Monospaced",Font.PLAIN,13));

        p.add(new JScrollPane(history),BorderLayout.CENTER);

        JLabel tip = new JLabel("<html><b>TIP:</b> Use the hints wisely!</html>");
        tip.setForeground(PINK);
        p.add(tip,BorderLayout.SOUTH);

        return p;
    }

    void newRound() {
        String level = (String)difficulty.getSelectedItem();

        if(level.equals("Easy")) { max=50; maxAttempts=10; }
        else if(level.equals("Hard")) { max=200; maxAttempts=5; }
        else { max=100; maxAttempts=7; }

        number = random.nextInt(max)+1;
        attempts = 0;
        round = round % 99 + 1;

        rangeLabel.setText("Secret number: 1 - " + max);
        roundLabel.setText(String.format("ROUND %02d",round));
        attemptsLabel.setText("Attempts: 0 / " + maxAttempts);
        progress.setMaximum(maxAttempts);
        progress.setValue(0);
        feedback.setText("Enter your first guess!");
        feedback.setForeground(GRAY);
        input.setText("");
        input.setEnabled(true);
        input.requestFocus();
    }

    void guess() {
        try {
            int g = Integer.parseInt(input.getText().trim());

            if(g < 1 || g > max) {
                feedback.setText("⚠ NUMBER MUST BE BETWEEN 1 AND " + max);
                feedback.setForeground(Color.RED);
                return;
            }

            attempts++;
            attemptsLabel.setText("Attempts: " + attempts + " / " + maxAttempts);
            progress.setValue(attempts);

            if(g == number) win();
            else if(attempts >= maxAttempts) lose();
            else if(g < number) message("⬆ TOO LOW! GO HIGHER!",CYAN);
            else message("⬇ TOO HIGH! GO LOWER!",PINK);

            input.selectAll();

        } catch(Exception e) {
            message("⚠ ENTER A VALID NUMBER!",Color.RED);
        }
    }

    void win() {
        int points = Math.max(20,
            (difficulty.getSelectedItem().equals("Hard") ? 200 :
            difficulty.getSelectedItem().equals("Easy") ? 100 : 150)
            + (maxAttempts-attempts)*15);

        score += points;
        scoreLabel.setText("SCORE " + score);
        message("★ CORRECT! NUMBER FOUND!",GREEN);
        history.setText("★ WIN | " + difficulty.getSelectedItem() +
            " | Number: " + number + " | +" + points + " pts\n" +
            "────────────────────\n" + history.getText());

        input.setEnabled(false);
        JOptionPane.showMessageDialog(this,
            "★ AMAZING!\n\nYou found: " + number +
            "\nAttempts: " + attempts +
            "\nScore: +" + points,
            "YOU WIN!",JOptionPane.INFORMATION_MESSAGE);
    }

    void lose() {
        message("✖ GAME OVER! NUMBER WAS " + number,Color.RED);
        history.setText("✖ LOSS | " + difficulty.getSelectedItem() +
            " | Number: " + number + "\n────────────────────\n" +
            history.getText());

        input.setEnabled(false);
        JOptionPane.showMessageDialog(this,
            "GAME OVER!\n\nThe secret number was: " + number +
            "\n\nTry another round!",
            "ROUND OVER",JOptionPane.WARNING_MESSAGE);
    }

    void message(String text,Color c) {
        feedback.setText(text);
        feedback.setForeground(c);
    }

    JPanel panel() {
        JPanel p = new JPanel();
        p.setBackground(PANEL);
        p.setBorder(new EmptyBorder(25,25,25,25));
        return p;
    }

    JLabel label(String text,Color c,int size) {
        JLabel l = new JLabel(text);
        l.setForeground(c);
        l.setFont(new Font("SansSerif",Font.BOLD,size));
        return l;
    }

    JButton button(String text,Color c) {
        JButton b = new JButton(text);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif",Font.BOLD,13));
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(13,25,13,25));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessQuest().setVisible(true));
    }
}