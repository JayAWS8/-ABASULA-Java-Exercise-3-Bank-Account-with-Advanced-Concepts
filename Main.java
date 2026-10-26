import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

/**
 * Demonstrates valid and rejected deposits and withdrawals, frozen-account
 * behavior, transaction history, and bank account management.
 */
public class Main {
  /**
   * Runs the bank account transaction and management demonstration.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    BankAccount acc1 = new SavingsAccount("Test0");
    System.out.println("----------------------------------------");
    System.out.println("Java Exercise 3: Bank Account with Advanced Concepts");
    System.out.println("----------------------------------------");
    System.out.println("Account Owner: " + ((SavingsAccount) acc1).getOwnerName());

    // Set up the balance required by the test cases.
    performTransaction(() -> acc1.deposit(1000.125));
    performTransaction(() -> acc1.withdraw(500));

    // Test 1: deposit a negative amount.
    System.out.println("\n<<< TEST 1: Deposit with negative amount >>>");
    performTransaction(() -> acc1.deposit(-500));

    // Test 2: deposit while the account is frozen.
    System.out.println("\n<<< TEST 2: Deposit when account is frozen >>>");
    acc1.freezeAccount();
    performTransaction(() -> acc1.deposit(11500));
    acc1.unfreezeAccount();

    // Test 3: withdraw a negative amount.
    System.out.println("\n<<< TEST 3: Withdraw with negative amount >>>");
    performTransaction(() -> acc1.withdraw(-100));

    // Test 4: withdraw more than the available balance.
    System.out.println("\n<<< TEST 4: Withdraw with insufficient funds >>>");
    performTransaction(() -> acc1.withdraw(1500));

    // Test 5: withdraw while the account is frozen.
    System.out.println("\n<<< TEST 5: Withdraw when account is frozen >>>");
    acc1.freezeAccount();
    performTransaction(() -> acc1.withdraw(500));
    acc1.unfreezeAccount();

    // Preserve the 100.00 withdrawal for the transaction history.
    performTransaction(() -> acc1.withdraw(100));

    // Display the transaction history
    System.out.println("\n----------------------------------------");
    System.out.println("Transaction history:");
    System.out.println(acc1.getTransactionHistory());
    System.out.println("----------------------------------------");

    // Filter and sort transactions
    BankAccountManager accM1 = new BankAccountManager();
    System.out.println("\n----------------------------------------");
    System.out.println("<<< TEST 6: Filter transactions at or above 200 >>>");
    accM1.filterTransactionsAtOrAbove(200, acc1.getTransactionHistory());
    System.out.println("----------------------------------------");

    // Sort transactions by amount
    System.out.println("\n----------------------------------------");
    System.out.println("<<< TEST 7: Sort transactions by amount >>>");
    accM1.sortTransactionsByAmount(acc1.getTransactionHistory());
    System.out.println("----------------------------------------");

    System.out.println("\n----------------------------------------");
    accM1.addAccount(acc1);
    accM1.addAccount(new SavingsAccount("Test1"));
    System.out.println("----------------------------------------");

    System.out.println("\n----------------------------------------");
    System.out.println("Bank accounts:");
    accM1.listAccounts();
    System.out.println("----------------------------------------");
  }

  /**
   * Runs one transaction and displays any supported account exception.
   *
   * @param transaction the transaction operation to run
   */
  private static void performTransaction(Runnable transaction) {
    try {
      transaction.run();
    } catch (AccountFrozenException | InvalidAmountException
        | InsufficientFundsException exception) {
      System.out.println(exception.getMessage());
    }
  }
}