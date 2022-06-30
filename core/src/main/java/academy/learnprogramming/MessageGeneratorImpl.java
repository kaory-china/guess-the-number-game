package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static java.lang.String.format;

public class MessageGeneratorImpl implements MessageGenerator {

    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    @Autowired
    private Game game;

    @PostConstruct
    public void init() {
        log.info("game was injected, value is {}", game);
    }

    @Override
    public String getMainMessage() {
        return "Number is between " + game.getSmallest() + " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {

        if (game.isGameWon()) {
            return "You guessed it! The number was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "Not this time...the number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "Invalid number range...try again";
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return "What's your first guess?";
        } else {
            String direction = "Lower";

            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }

            return direction + "! You have " + game.getRemainingGuesses() + " guesses left.";
        }

    }
}
