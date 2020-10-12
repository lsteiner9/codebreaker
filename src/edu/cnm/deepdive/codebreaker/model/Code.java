package edu.cnm.deepdive.codebreaker.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Creates an instance of a randomly-generated secret code for a Codebreaker game.
 */
public class Code {

  private final char[] secret;

  /**
   * Creates a character array holding the secret code. Uses {@code rng} to select {@code length}
   * characters from {@code pool}.
   *
   * @param pool   The letters available to be used.
   * @param length The length of the secret code.
   * @param rng    A source of randomness.
   */
  public Code(String pool, int length, Random rng) {
    secret = new char[length];
    for (int i = 0; i < length; i++) {
      secret[i] = pool.charAt(rng.nextInt(pool.length()));
    }
  }

  @Override
  public String toString() {
    return new String(secret);
  }

  /**
   * Implements a comparison between user-submitted input and {@link Code}.
   *
   */
  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s\", correct: %d, close: %d}";
    private final String text;
    private final int correct;
    private final int close;

    /**
     * Compares the user-submitted input and the secret code.
     * @param text Guess content.
     */
    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;

      Map<Character, Set<Integer>> letterMap = getLetterMap(text);

      char[] work = Arrays.copyOf(secret, secret.length);
      for (int i = 0; i < work.length; i++) {
        char letter = work[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
        if (positions.contains(i)) {
          correct++;
          positions.remove(i);
          work[i] = 0;
        }
      }
      for (char letter : work) {
        if (letter != 0) {
          Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
          if (positions.size() > 0) {
            close++;
            Iterator<Integer> iter = positions.iterator();
            iter.next();
            iter.remove();
          }
        }
      }
      this.correct = correct;
      this.close = close;
    }

    private Map<Character, Set<Integer>> getLetterMap(String text) {
      Map<Character, Set<Integer>> letterMap = new HashMap<>();
      char[] letters = text.toCharArray();
      for (int i = 0; i < letters.length; i++) {
        char letter = letters[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, new HashSet<>());
        positions.add(i); //this also updates it within letterMap, since positions is a pointer
        letterMap.putIfAbsent(letter, positions);
      }
      return letterMap;
    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }

    /**
     * Returns the text of this guess.
     */
    public String getText() {
      return text;
    }

    /**
     * Returns the number of characters that are in the correct position in this guess.
     */
    public int getCorrect() {
      return correct;
    }

    /**
     * Returns the number of characters that are correct but in the wrong position in this
     * guess.
     */
    public int getClose() {
      return close;
    }
  }

}
