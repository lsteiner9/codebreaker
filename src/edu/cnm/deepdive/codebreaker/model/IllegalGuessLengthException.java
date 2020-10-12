package edu.cnm.deepdive.codebreaker.model;

/**
 * Indicates that the evaluated guess was the wrong length.
 */
public class IllegalGuessLengthException extends IllegalArgumentException {

  /**
   * Constructs an exception reporting that a guess was the wrong length, with no detail message.
   */
  public IllegalGuessLengthException() {
  }

  /**
   * Constructs an exception reporting that a guess was the wrong length, with a detail message.
   */
  public IllegalGuessLengthException(String message) {
    super(message);
  }

  /**
   * Constructs an exception reporting that a guess was the wrong length, with a detail message and
   * an underlying cause.
   */
  public IllegalGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs an exception reporting that a guess was the wrong length, with an underlying cause.
   */
  public IllegalGuessLengthException(Throwable cause) {
    super(cause);
  }
}
