package exception;

/**
 * Exception thrown when a withdrawal exceeds the available account balance.
 */
public class InsufficientFundsException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String message;

  /**
   * Constructs an InsufficientFundsException with the specified detail message.
   *
   * @param message the detail message
   */
  public InsufficientFundsException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}