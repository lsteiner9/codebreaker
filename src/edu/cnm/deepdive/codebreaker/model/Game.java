package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Implements a Game object, which provides the behind-the-scenes action of a Codebreaker game. This
 * object initiates the secret code and creates a list of guesses. The guess method validates
 * characters and length of guess text, and for each valid guess, creates a {@link Guess} object and
 * adds each guess to the list.
 */
public class Game {

  private static final String BAD_GUESS_PATTERN_FORMAT = "^.*[^%s].*$";
  private static final String ILLEGAL_LENGTH_MESSAGE =
      "Invalid guess length. required=%d; provided=%d";
  private static final String ILLEGAL_CHARACTER_MESSAGE =
      "Guess includes invalid characters: required=%s; provided=%s.";

  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String badGuessPattern;

  /**
   * Constructs a Codebreaker Game object. Sets up an instance of {@link Code}, creates a list of
   * guesses, and sets up a pattern to match invalid guesses.
   *
   * @param pool   The letters available to be used.
   * @param length The length of the secret code.
   * @param rng    A source of randomness.
   */
  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    badGuessPattern = String.format(BAD_GUESS_PATTERN_FORMAT, pool);
  }

  /**
   * Returns the secret {@link Code}.
   */
  public Code getCode() {
    return code;
  }

  /**
   * Returns an unmodifiable list of {@link Guess} objects.
   */
  public List<Guess> getGuesses() {
    return Collections.unmodifiableList(guesses);
  }

  /**
   * Returns the pool of available characters.
   */
  public String getPool() {
    return pool;
  }

  /**
   * Returns the length of the code.
   */
  public int getLength() {
    return length;
  }

  /**
   * Returns the number of guesses made.
   */
  public int getGuessCount() {
    return guesses.size();
  }

  /**
   * Checks user input for valid length and characters. If valid, creates a {@link Guess} object,
   * adds it to the list of guesses, and returns it.
   *
   * @param text A string of user-inputted text.
   * @return A {@link Guess} object with the valid input text.
   * @throws IllegalGuessLengthException    The text length was longer or shorter than specified.
   * @throws IllegalGuessCharacterException The text contained characters that are not specified.
   */
  public Guess guess(String text)
      throws IllegalGuessLengthException, IllegalGuessCharacterException {
    if (text.length() != length) {
      throw new IllegalGuessLengthException(String.format(
          ILLEGAL_LENGTH_MESSAGE, length, text.length()));
    }
    if (text.matches(badGuessPattern)) {
      throw new IllegalGuessCharacterException(String.format(
          ILLEGAL_CHARACTER_MESSAGE, pool, text));
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;
  }

  /**
   * Clears all guesses from the guess list.
   */
  public void restart() {
    guesses.clear();
  }

}
