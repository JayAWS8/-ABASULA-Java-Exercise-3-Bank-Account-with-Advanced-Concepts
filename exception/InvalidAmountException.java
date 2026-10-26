package exception;

/**
 * Indicates that a deposit or withdrawal amount is invalid.
 */
public class InvalidAmountException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates an exception for a non-positive or non-finite amount.
   */
  public InvalidAmountException() {
    super("Invalid amount. Must be greater than 0");
  }
}
