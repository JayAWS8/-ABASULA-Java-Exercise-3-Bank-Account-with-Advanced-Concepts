/**
 * A bank account associated with a named owner.
 *
 * <p>This account inherits balance management and transaction controls from
 * {@link BankAccount}.
 */
public class SavingsAccount extends BankAccount {
  /**
   * Creates a savings account for the specified owner.
   *
   * @param ownerName the name of the account owner
   */
  public SavingsAccount(String ownerName) {
    super(ownerName);
  }
}
