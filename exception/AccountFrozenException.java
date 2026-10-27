package exception;

/**
 * Exception thrown when an operation is attempted on a frozen bank account.
 */
public class AccountFrozenException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String message;

  /**
   * Constructs an AccountFrozenException with the specified detail message.
   *
   * @param message the detail message
   */
  public AccountFrozenException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}