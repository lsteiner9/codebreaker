package edu.cnm.deepdive.codebreaker.model;

/**
 * Indicates that the evaluated guess contained invalid characters.
 */
public class IllegalGuessCharacterException extends IllegalArgumentException {

  /**
   * Constructs an exception reporting that a guess contained invalid characters, with no detail
   * message.
   */
  public IllegalGuessCharacterException() {
  }

  /**
   * Constructs an exception reporting that a guess contained invalid characters, with a detail
   * message.
   */
  public IllegalGuessCharacterException(String message) {
    super(message);
  }

  /**
   * Constructs an exception reporting that a guess contained invalid characters, with a detail
   * message and an underlying cause.
   */
  public IllegalGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs an exception reporting that a guess contained invalid characters, with an underlying
   * cause.
   */
  public IllegalGuessCharacterException(Throwable cause) {
    super(cause);
  }
}
