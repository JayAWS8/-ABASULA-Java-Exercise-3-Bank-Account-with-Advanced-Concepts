import java.util.ArrayList;
import java.util.List;

import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

/**
 * Represents a bank account with balance management and frozen transaction controls.
 */
public class BankAccount {
  private final String ownerName;
  private double balance;
  private boolean isFrozen;
  private final List<Transaction> transactionHistory;

  /**
   * Creates an unfrozen bank account with a zero balance and default owner name.
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

  public String getOwnerName() {
    return ownerName;
  }

  /**
   * Deposits funds into this account.
   *
   * @param amount the amount to deposit
   * @throws AccountFrozenException if the account is frozen
   * @throws InvalidAmountException if the amount is less than or equal to zero
   */
  public void deposit(double amount) throws AccountFrozenException, InvalidAmountException {
    if (isFrozen) {
      throw new AccountFrozenException("Account is FROZEN. Unable to transact.");
    }

    System.out.printf("Amount deposited: %.2f%n", amount);
    if (!isValidAmount(amount)) {
      throw new InvalidAmountException("Invalid amount. Must be greater than 0");
    }

    balance += amount;
    transactionHistory.add(new Transaction("Deposit", amount));
  }

  /**
   * Withdraws funds from this account.
   *
   * @param amount the amount to withdraw
   * @throws AccountFrozenException if the account is frozen
   * @throws InvalidAmountException if the amount is less than or equal to zero
   * @throws InsufficientFundsException if the amount exceeds current balance
   */
  public void withdraw(double amount)
      throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
    if (isFrozen) {
      throw new AccountFrozenException("Account is FROZEN. Unable to transact.");
    }

    System.out.printf("Amount withdrawn: %.2f%n", amount);
    if (!isValidAmount(amount)) {
      throw new InvalidAmountException("Invalid amount. Must be greater than 0");
    }

    if (amount > balance) {
      throw new InsufficientFundsException(
          String.format("Insufficient balance.%nCurrent balance: %.2f", balance));
    }

    balance -= amount;
    transactionHistory.add(new Transaction("Withdraw", amount));
  }

  public List<Transaction> getTransactionHistory() {
    return new ArrayList<>(transactionHistory);
  }

  public double getBalance() {
    return balance;
  }

  public void freezeAccount() {
    this.isFrozen = true;
  }

  public void unfreezeAccount() {
    this.isFrozen = false;
  }

  public boolean isFrozen() {
    return isFrozen;
  }

  private boolean isValidAmount(double amount) {
    return Double.isFinite(amount) && amount > 0;
  }
}