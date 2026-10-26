package exception;

/**
 * Indicates that a withdrawal exceeds the account's available balance.
 */
public class InsufficientFundsException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates an exception that reports the account's current balance.
   *
   * @param currentBalance the balance available when the withdrawal was attempted
   */
  public InsufficientFundsException(double currentBalance) {
    super(String.format(
        "Insufficient balance.%nCurrent balance: %.2f", currentBalance));
  }
}
