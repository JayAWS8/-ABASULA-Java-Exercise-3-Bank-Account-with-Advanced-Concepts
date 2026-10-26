import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Stores bank accounts and assigns each account a sequential ID.
 */
public class BankAccountManager implements Bank {
  private final Map<Integer, BankAccount> accounts;
  private int nextAccountId;

  /** Creates an empty bank account manager. */
  public BankAccountManager() {
    this.accounts = new LinkedHashMap<>();
    this.nextAccountId = 0;
  }

  /**
   * Adds an account and assigns it the next available ID.
   *
   * @param account the account to add
   * @throws NullPointerException if {@code account} is {@code null}
   */
  @Override
  public void addAccount(BankAccount account) {
    Objects.requireNonNull(account, "account must not be null");
    int accountId = nextAccountId++;
    accounts.put(accountId, account);
    System.out.printf("Account added successfully with ID: %d%n", accountId);
  }

  /**
   * Returns the account with the specified ID.
   *
   * @param id the account ID
   * @return the matching account, or {@code null} if no account has that ID
   */
  @Override
  public BankAccount getAccount(int id) {
    return accounts.get(id);
  }

  /**
   * Prints and returns transactions whose amounts meet the specified minimum.
   *
   * @param minimumAmount the minimum transaction amount, inclusive
   * @param transactions the transactions to filter
   * @return the matching transactions in their original order
   * @throws NullPointerException if {@code transactions} is {@code null}
   */
  public List<Transaction> filterTransactionsAtOrAbove(
      double minimumAmount, List<Transaction> transactions) {
    Objects.requireNonNull(transactions, "transactions must not be null");
    String minimumAmountText =
        minimumAmount == Math.rint(minimumAmount)
            ? String.format("%.0f", minimumAmount)
            : Double.toString(minimumAmount);
    System.out.printf("Filtering transactions at or above %s...%n", minimumAmountText);
    List<Transaction> filteredTransactions =
        transactions.stream()
            .filter(transaction -> transaction.getAmount() >= minimumAmount)
            .collect(Collectors.toList());
    System.out.println(filteredTransactions);
    return filteredTransactions;
  }

  /**
   * Prints and returns transactions sorted by amount in ascending order.
   *
   * @param transactions the transactions to sort
   * @return a sorted copy of the transactions
   * @throws NullPointerException if {@code transactions} is {@code null}
   */
  public List<Transaction> sortTransactionsByAmount(List<Transaction> transactions) {
    Objects.requireNonNull(transactions, "transactions must not be null");
    System.out.println("Sorting transactions by amount...");
    List<Transaction> sortedTransactions =
        transactions.stream()
            .sorted(Comparator.comparingDouble(transaction -> transaction.getAmount()))
            .collect(Collectors.toList());
    System.out.println(sortedTransactions);
    return sortedTransactions;
  }

  /** Displays each account's ID, owner name, and current balance. */
  public void listAccounts() {
    for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
      BankAccount account = entry.getValue();
      System.out.printf(
          "Account ID: %d, Account Name: %s, Balance: %.2f%n",
          entry.getKey(),
          account.getOwnerName(),
          account.getBalance());
    }
  }
}
