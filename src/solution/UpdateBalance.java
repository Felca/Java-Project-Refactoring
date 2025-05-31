package solution;

import java.util.List;
import java.util.Scanner;

public class UpdateBalanceAmount extends BalanceAmount {

    public UpdateBalanceAmount(List<Account> accounts) {
        super(accounts);
    }

    public void updateBalance(int accountNumber) {
        if (isAccountExist(accountNumber)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter amount to update balance (positive or negative): ");
            int amount = scanner.nextInt();
            BalanceAmount(accountNumber, amount, "Balance updated");
        } else {
            System.out.println("Account not found!");
        }
    }
}
