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
    System.out.println("Java Exercise 1: Bank Account with OOP Concepts");
    System.out.println("----------------------------------------");
    System.out.println("Account Owner: " + ((SavingsAccount) acc1).getOwnerName());

    acc1.deposit(1000.125);
    acc1.deposit(0);
    acc1.deposit(-500);

    acc1.withdraw(500);
    acc1.withdraw(1500);
    acc1.withdraw(-100);

    acc1.freezeAccount();
    acc1.deposit(11500);
    acc1.withdraw(500);

    acc1.unfreezeAccount();
    acc1.withdraw(100);
    System.out.println("----------------------------------------");
    System.out.println(" ");

    System.out.println("----------------------------------------");
    System.out.println("Java Exercise 2: Bank Account with Java Collections and Package");
    System.out.println("----------------------------------------");
    System.out.println("Transaction history:");
    System.out.println(acc1.getTransactionHistory());

    BankAccountManager accM1 = new BankAccountManager();
    accM1.addAccount(acc1);
    accM1.addAccount(new SavingsAccount("Test1"));

    System.out.println("Bank accounts:");
    accM1.listAccounts();
    System.out.println("----------------------------------------");
  }
}