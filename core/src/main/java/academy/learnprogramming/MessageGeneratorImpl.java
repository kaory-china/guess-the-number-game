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
    private int guessCount = 10;

    @PostConstruct
    public void gameValue() {
        log.info("game was injected, value is {}", game);
    }

    @Override
    public String getMainMessage() {
        return format("Number is between {} and {}. Can you guess it?", game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {

        if (game.isGameWon()) {
            return format("You guessed it! The number was {}!", game.getNumber());
        } else if (game.isGameLost()) {
            return format("Not this time...the number was {}!", game.getNumber());
        } else if (game.isValidNumberRange()) {
            return "Invalid number range...try again";
        } else if (game.getRemainingGuesses() == guessCount) {
            return "What's your first guess?";
        } else {
            String direction = "Lower";

            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }

            return format("{}! You have {} guesses left!", direction, game.getRemainingGuesses());
        }

    }
}
