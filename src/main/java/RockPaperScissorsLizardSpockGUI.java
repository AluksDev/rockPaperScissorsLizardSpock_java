import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.sql.Time;

//frontend
public class RockPaperScissorsLizardSpockGUI extends JFrame implements ActionListener {
    //player buttons
    JButton rockButton, paperButton, scissorsButton, lizardButton, spockButton;

    JLabel computerChoice;
    JLabel computerScoreLabel, playerScoreLabel;
    JLabel delayLabel;
    JProgressBar loadingBar;

    RockPaperScissorsLizard backEnd;

    public RockPaperScissorsLizardSpockGUI(){
        //invoke JFrame constructor and gives title
        super("Rock Paper Scissors Lizard Spock");

        //set size of GUI
        setSize(490, 640);

        setLayout(null);
        //terminate java machine when closing app
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        backEnd = new RockPaperScissorsLizard();

        //add GUI components
        addGuiComponents();
    }
    private void addGuiComponents(){
        //create computer label
        computerScoreLabel = new JLabel("Computer: 0");

        //set x, y, coordinates and width/heigh values, centers it and sets fonts
        computerScoreLabel.setBounds(0, 40, 490, 30);
        computerScoreLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(computerScoreLabel);

        //create computer choice
        computerChoice = new JLabel("?");
        computerChoice.setBounds(195, 120, 100, 81);
        computerChoice.setFont(new Font("Monospaced", Font.PLAIN, 18));
        computerChoice.setHorizontalAlignment(SwingConstants.CENTER);
        computerChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(computerChoice);

        //create delay text
        delayLabel = new JLabel("Rock Paper Scissors Lizard Spock");
        delayLabel.setBounds(0, 250, 490, 30);
        delayLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
        delayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        delayLabel.setVisible(false);
        add(delayLabel);

        loadingBar = new JProgressBar(0, 100);
        loadingBar.setBounds(95, 300, 300, 20);
        loadingBar.setStringPainted(true);
        loadingBar.setVisible(false);
        add(loadingBar);


        //create player score label
        playerScoreLabel = new JLabel("Player: 0");
        playerScoreLabel.setBounds(0, 320, 490, 30);
        playerScoreLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerScoreLabel);

        //create player buttons

        rockButton = new JButton("Rock");
        rockButton.setBounds(40, 400, 120, 80);
        rockButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        rockButton.addActionListener(this);
        add(rockButton);

        paperButton = new JButton("Paper");
        paperButton.setBounds(180, 400, 120, 80);
        paperButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        paperButton.addActionListener(this);
        add(paperButton);

        scissorsButton = new JButton("Scissors");
        scissorsButton.setBounds(320, 400, 120, 80);
        scissorsButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        scissorsButton.addActionListener(this);
        add(scissorsButton);

        lizardButton = new JButton("Lizard");
        lizardButton.setBounds(100, 500, 120, 80);
        lizardButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        lizardButton.addActionListener(this);
        add(lizardButton);

        spockButton = new JButton("Spock");
        spockButton.setBounds(240, 500, 120, 80);
        spockButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        spockButton.addActionListener(this);
        add(spockButton);

    }

    private void showDialog (String message){
        JDialog resultDialog = new JDialog(this, "Result", true);
        resultDialog.setSize(490, 150);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        JLabel resultMessage = new JLabel(message);
        resultMessage.setFont(new Font("Monospaced", Font.BOLD, 18));
        resultMessage.setHorizontalAlignment((SwingConstants.CENTER));
        resultDialog.add(resultMessage, BorderLayout.CENTER);

        JButton tryAgainButton = new JButton("Try Again");
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset computer choice
                computerChoice.setText("?");
                resultDialog.dispose();
            }
        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();
        setButtonsEnabled(false);
        delayLabel.setVisible(true);
        loadingBar.setValue(0);
        loadingBar.setVisible(true);

        Timer timer = new Timer(20, null); // updates every 25ms (~40 steps in 1 second)
        timer.addActionListener(new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent evt) {
                progress += 2; // increment progress by 2%
                loadingBar.setValue(progress);
                if (progress >= 100) {
                    timer.stop();
                    loadingBar.setVisible(false);

                    // Run your game logic here after loading finishes
                    String result = backEnd.play(playerChoice);
                    computerChoice.setText(backEnd.getComputerChoice());
                    computerScoreLabel.setText("Computer: " + backEnd.getComputerScore());
                    playerScoreLabel.setText("Player: " + backEnd.getPlayerScore());
                    showDialog(result);
                    delayLabel.setVisible(false);
                    setButtonsEnabled(true);
                }
            }
        });
        timer.start();
    }

    private void setButtonsEnabled(boolean enabled) {
        rockButton.setEnabled(enabled);
        paperButton.setEnabled(enabled);
        scissorsButton.setEnabled(enabled);
        lizardButton.setEnabled(enabled);
        spockButton.setEnabled(enabled);
    }
}
