import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //instantiate a RockPaperScissorsLizardSpockGUI obj
                RockPaperScissorsLizardSpockGUI rockPaperScissorsLizardSpockGUI = new RockPaperScissorsLizardSpockGUI();
                rockPaperScissorsLizardSpockGUI.setVisible(true);
            }
        });
    }
}
