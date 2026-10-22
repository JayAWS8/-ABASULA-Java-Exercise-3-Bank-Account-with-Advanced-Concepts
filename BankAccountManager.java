import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

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
