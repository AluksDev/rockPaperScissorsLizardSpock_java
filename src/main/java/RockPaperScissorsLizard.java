import java.awt.*;
import java.util.Random;

//backend
public class RockPaperScissorsLizard {
    private static final String[] computerChoices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
    private String computerChoice;

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public String getComputerChoice() {
        return computerChoice;
    }

    private int computerScore, playerScore;

    private Random random;
    public RockPaperScissorsLizard(){
        random = new Random();
    }

    public String play(String playerChoice){
        computerChoice = computerChoices[random.nextInt(computerChoices.length)];
        String result;
        if (computerChoice.equals(playerChoice)) {
            result = "Draw";
        } else if (computerChoice.equals("Rock")) {
            if (playerChoice.equals("Scissors")) {
                computerScore++;
                result = "Computer wins: Rock crushes Scissors";
            } else if (playerChoice.equals("Lizard")) {
                computerScore++;
                result = "Computer wins: Rock crushes Lizard";
            } else {
                playerScore++;
                result = "Player wins: " + (playerChoice.equals("Paper") ? "Paper covers Rock" : "Spock vaporizes Rock");
            }
        } else if (computerChoice.equals("Paper")) {
            if (playerChoice.equals("Rock")) {
                computerScore++;
                result = "Computer wins: Paper covers Rock";
            } else if (playerChoice.equals("Spock")) {
                computerScore++;
                result = "Computer wins: Paper disproves Spock";
            } else {
                playerScore++;
                result = "Player wins: " + (playerChoice.equals("Scissors") ? "Scissors cuts Paper" : "Lizard eats Paper");
            }
        } else if (computerChoice.equals("Scissors")) {
            if (playerChoice.equals("Paper")) {
                computerScore++;
                result = "Computer wins: Scissors cuts Paper";
            } else if (playerChoice.equals("Lizard")) {
                computerScore++;
                result = "Computer wins: Scissors decapitates Lizard";
            } else {
                playerScore++;
                result = "Player wins: " + (playerChoice.equals("Rock") ? "Rock crushes Scissors" : "Spock smashes Scissors");
            }
        } else if (computerChoice.equals("Lizard")) {
            if (playerChoice.equals("Spock")) {
                computerScore++;
                result = "Computer wins: Lizard poisons Spock";
            } else if (playerChoice.equals("Paper")) {
                computerScore++;
                result = "Computer wins: Lizard eats Paper";
            } else {
                playerScore++;
                result = "Player wins: " + (playerChoice.equals("Rock") ? "Rock crushes Lizard" : "Scissors decapitates Lizard");
            }
        } else { // Spock
            if (playerChoice.equals("Scissors")) {
                computerScore++;
                result = "Computer wins: Spock smashes Scissors";
            } else if (playerChoice.equals("Rock")) {
                computerScore++;
                result = "Computer wins: Spock vaporizes Rock";
            } else {
                playerScore++;
                result = "Player wins: " + (playerChoice.equals("Paper") ? "Paper disproves Spock" : "Lizard poisons Spock");
            }
        }
        return result;
    }
}
