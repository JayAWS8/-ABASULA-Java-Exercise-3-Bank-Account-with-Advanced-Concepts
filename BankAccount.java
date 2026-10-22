import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account with a balance and the ability to freeze transactions.
 *
 * <p>Deposits and withdrawals are accepted only when the account is unfrozen and
 * the requested amount is finite and greater than zero.
 */
public class BankAccount {
  private final String ownerName;
  private double balance;
  private boolean isFrozen;
  private final List<Transaction> transactionHistory;

  /**
   * Creates an unfrozen bank account with a zero balance and unspecified owner.
   */
  public BankAccount() {
    this("Unknown");
  }

  /**
   * Creates an unfrozen bank account for the specified owner.
   *
   * @param ownerName the account owner's name
   */
  public BankAccount(String ownerName) {
    this.ownerName = ownerName;
    this.balance = 0.0;
    this.isFrozen = false;
    this.transactionHistory = new ArrayList<>();
  }

  /**
   * Returns the name of this account's owner.
   *
   * @return the owner's name
   */
  public String getOwnerName() {
    return ownerName;
  }

  /**
   * Deposits a positive, finite amount into this account.
   *
   * @param amount the amount to deposit; must be greater than zero and finite
   */
  public void deposit(double amount) {
    if (isFrozen) {
      System.out.println("Account is FROZEN. Unable to transact.");
      return;
    }

    System.out.printf("Amount deposited: %.2f%n", amount);
    if (!isValidAmount(amount)) {
      System.out.println("Invalid amount. Must be greater than 0");
      return;
    }

    balance += amount;
    transactionHistory.add(new Transaction("Deposit", amount));
    System.out.printf("Current balance: %.2f%n", balance);
  }

  /**
   * Withdraws a positive, finite amount if the account has sufficient funds.
   *
   * @param amount the amount to withdraw; must be greater than zero and finite
   */
  public void withdraw(double amount) {
    if (isFrozen) {
      System.out.println("Account is FROZEN. Unable to transact.");
      return;
    }

    System.out.printf("Amount withdrawn: %.2f%n", amount);
    if (!isValidAmount(amount)) {
      System.out.println("Invalid amount. Must be greater than 0");
      return;
    }

    if (amount > balance) {
      System.out.printf("Insufficient balance. Current balance: %.2f%n", balance);
      return;
    }

    balance -= amount;
    transactionHistory.add(new Transaction("Withdraw", amount));
    System.out.printf("Current balance: %.2f%n", balance);
  }

  /**
   * Returns the transactions made on this account.
   *
   * @return the transaction history
   */
  public List<Transaction> getTransactionHistory() {
    return new ArrayList<>(transactionHistory);
  }

  /**
   * Returns the current account balance.
   *
   * @return the balance
   */
  public double getBalance() {
    return balance;
  }

  /** Prevents deposits and withdrawals on this account. */
  public void freezeAccount() {
    this.isFrozen = true;
  }

  /** Allows deposits and withdrawals on this account again. */
  public void unfreezeAccount() {
    this.isFrozen = false;
  }

  /**
   * Reports whether this account is frozen.
   *
   * @return {@code true} if the account is frozen; otherwise {@code false}
   */
  public boolean isFrozen() {
    return isFrozen;
  }

  /**
   * Checks whether an amount is a finite value greater than zero.
   *
   * @param amount the amount to validate
   * @return {@code true} if the amount is finite and positive
   */
  private boolean isValidAmount(double amount) {
    return Double.isFinite(amount) && amount > 0;
  }
}
