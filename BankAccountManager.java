import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Stores bank accounts and provides filtering and sorting on transaction lists.
 */
public class BankAccountManager implements Bank {
  private static final int MAX_ACCOUNTS = 100;
  private final Map<Integer, BankAccount> accounts;
  private int nextAccountId;

  public BankAccountManager() {
    this.accounts = new LinkedHashMap<>();
    this.nextAccountId = 0;
  }

  @Override
  public void addAccount(BankAccount account) {
    Objects.requireNonNull(account, "account must not be null");
    if (accounts.size() >= MAX_ACCOUNTS) {
      throw new IllegalStateException("Maximum number of accounts reached");
    }

    int accountId = nextAccountId++;
    accounts.put(accountId, account);
    System.out.printf("Account added successfully with ID: %d%n", accountId);
  }

  @Override
  public BankAccount getAccount(int id) {
    return accounts.get(id);
  }

  /**
   * Filters transactions above or equal to a minimum amount using a lambda expression.
   *
   * @param amount the minimum threshold amount
   * @param txList the list of transactions to filter
   * @return filtered list of transactions
   */
  public List<Transaction> filterTransactionsAtOrAbove(double amount, List<Transaction> txList) {
    Objects.requireNonNull(txList, "txList must not be null");
    String amountText =
        amount == Math.rint(amount)
            ? String.format("%.0f", amount)
            : Double.toString(amount);
    System.out.printf("Filtering transactions at or above %s...%n", amountText);

    List<Transaction> filteredTransactions = txList.stream()
        .filter(tx -> tx.getAmount() >= amount)
        .collect(Collectors.toList());

    filteredTransactions.forEach(System.out::println);
    return filteredTransactions;
  }

  /**
   * Sorts transactions by amount in ascending order using a lambda expression.
   *
   * @param txList the list of transactions to sort
   * @return sorted list of transactions
   */
  public List<Transaction> sortTransactionsByAmount(List<Transaction> txList) {
    Objects.requireNonNull(txList, "txList must not be null");
    System.out.println("Sorting transactions by amount...");

    List<Transaction> sortedTransactions = txList.stream()
        .sorted(Comparator.comparingDouble(tx -> tx.getAmount()))
        .collect(Collectors.toList());

    sortedTransactions.forEach(System.out::println);
    return sortedTransactions;
  }

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