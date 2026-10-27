package exception;

/**
 * Exception thrown when an invalid deposit or withdrawal amount is provided.
 */
public class InvalidAmountException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String message;

  /**
   * Constructs an InvalidAmountException with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidAmountException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}