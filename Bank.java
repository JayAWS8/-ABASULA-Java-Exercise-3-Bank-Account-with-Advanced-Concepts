/**
 * Defines account-management operations supported by a bank.
 */
public interface Bank {
  /** Adds an account to the bank. */
  void addAccount(BankAccount account);

  /**
   * Returns the account with the specified ID.
   *
   * @param id the account ID
   * @return the matching account
   */
  BankAccount getAccount(int id);
}
