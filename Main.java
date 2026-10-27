import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

/**
 * Demonstrates valid and rejected deposits and withdrawals, frozen-account
 * behavior, transaction history, and bank account management[cite: 4].
 */
public class Main {

  /**
   * Functional interface for bank transaction actions that throw checked exceptions.
   */
  @FunctionalInterface
  private interface TransactionAction {
    void execute() throws Exception;
  }

  /**
   * Runs the bank account transaction and management demonstration[cite: 4].
   *
   * @param args command-line arguments (not used)[cite: 4]
   */
  public static void main(String[] args) {
    BankAccount acc1 = new SavingsAccount("Test0");
    System.out.println("----------------------------------------");
    System.out.println("Java Exercise 3: Bank Account with Advanced Concepts");
    System.out.println("----------------------------------------");
    System.out.println("Account Owner: " + ((SavingsAccount) acc1).getOwnerName());

    // Set up the initial balance required by the test cases[cite: 4].
    performTransaction(() -> acc1.deposit(1000.125));
    performTransaction(() -> acc1.withdraw(500));

    // Test 1: deposit a negative amount[cite: 4].
    System.out.println("\n<<< TEST 1: Deposit with negative amount >>>");
    performTransaction(() -> acc1.deposit(-500));

    // Test 2: deposit while the account is frozen[cite: 4].
    System.out.println("\n<<< TEST 2: Deposit when account is frozen >>>");
    acc1.freezeAccount();
    performTransaction(() -> acc1.deposit(11500));
    acc1.unfreezeAccount();

    // Test 3: withdraw a negative amount[cite: 4].
    System.out.println("\n<<< TEST 3: Withdraw with negative amount >>>");
    performTransaction(() -> acc1.withdraw(-100));

    // Test 4: withdraw more than the available balance[cite: 4].
    System.out.println("\n<<< TEST 4: Withdraw with insufficient funds >>>");
    performTransaction(() -> acc1.withdraw(1500));

    // Test 5: withdraw while the account is frozen[cite: 4].
    System.out.println("\n<<< TEST 5: Withdraw when account is frozen >>>");
    acc1.freezeAccount();
    performTransaction(() -> acc1.withdraw(500));
    acc1.unfreezeAccount();

    // Preserve the 100.00 withdrawal for the transaction history[cite: 4].
    performTransaction(() -> acc1.withdraw(100));

    // Display the transaction history[cite: 4]
    System.out.println("\n----------------------------------------");
    System.out.println("Transaction history:");
    acc1.getTransactionHistory().forEach(System.out::println);
    System.out.println("----------------------------------------");

    // Filter and sort transactions[cite: 4]
    BankAccountManager accM1 = new BankAccountManager();
    System.out.println("\n----------------------------------------");
    System.out.println("<<< TEST 6: Filter transactions at or above 200 >>>");
    accM1.filterTransactionsAtOrAbove(200, acc1.getTransactionHistory());
    System.out.println("----------------------------------------");

    // Sort transactions by amount[cite: 4]
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
   * Runs one transaction and displays any supported account exception[cite: 4].
   *
   * @param transaction the transaction operation to run[cite: 4]
   */
  private static void performTransaction(TransactionAction transaction) {
    try {
      transaction.execute();
    } catch (AccountFrozenException | InvalidAmountException
        | InsufficientFundsException exception) {
      System.out.println(exception.getMessage());
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
}