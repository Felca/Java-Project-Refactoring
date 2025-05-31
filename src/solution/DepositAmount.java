package solution;

import java.util.List;
import java.util.Scanner;

public class DepositAmount extends BalanceAmount {

    public DepositAmount(List<Account> accounts) {
        super(accounts); 
    }

    public void depositAmount(int accountNumber) {
        if (isAccountExist(accountNumber)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter amount to be deposited: ");
            int amount = scanner.nextInt();
            BalanceAmount(accountNumber, amount, "Amount deposited");
        } else {
            System.out.println("Account not found!");
        }
    }
}