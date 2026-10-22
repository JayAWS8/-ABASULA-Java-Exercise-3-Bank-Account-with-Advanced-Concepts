import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deposit or withdrawal made on a bank account.
 */
public class Transaction {
  private final String type;
  private final double amount;
  private final LocalDateTime timestamp;

  /**
   * Creates a transaction with the specified type and amount.
   *
   * @param type   the transaction type, such as {@code "Deposit"} or
   *               {@code "Withdrawal"}
   * @param amount the transaction amount
   */
  public Transaction(String type, double amount) {
    this.type = type;
    this.amount = amount;
    this.timestamp = LocalDateTime.now();
  }

  /**
   * Returns the transaction type.
   *
   * @return the transaction type
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the transaction amount.
   *
   * @return the transaction amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Returns when this transaction was created.
   *
   * @return the transaction timestamp
   */
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return String.format(
        "Transaction [type=%s, amount=%s, time_stamp=%s]",
        type,
        Double.toString(amount),
        timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
  }
}
