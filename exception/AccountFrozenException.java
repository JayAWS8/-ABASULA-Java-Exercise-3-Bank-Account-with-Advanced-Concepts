package exception;

/**
 * Indicates that an operation was attempted on a frozen bank account.
 */
public class AccountFrozenException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates an exception for an operation blocked by a frozen account.
   */
  public AccountFrozenException() {
    super("Account is FROZEN. Unable to transact.");
  }
}
