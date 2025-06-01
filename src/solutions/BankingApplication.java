package solutions;

public class BankingApplication {
    private AccountService accountService;
    
    public BankingApplication() {
        this.accountService = new AccountService();
    }
    
    public boolean isAccountNumberUnique(int accountNumber) {
        return accountService.isAccountNumberUnique(accountNumber);
    }
    
    public void createAccount(int minBalanceToOpen, int minBalanceRemainAfterWithdraw) {
        accountService.createAccount(minBalanceToOpen);
    }
    
    public void displayAllAccounts() {
        accountService.displayAllAccounts();
    }
    
    public void updateAccountBalance(int accountNumber) {
        accountService.updateAccountBalance(accountNumber);
    }
    
    public void deleteAccount(int accountNumber) {
        accountService.deleteAccount(accountNumber);
    }
    
    public void depositAmount(int accountNumber) {
        accountService.depositAmount(accountNumber);
    }
    
    public void withdrawAmount(int accountNumber, int minBalanceRemainAfterWithdraw) {
        accountService.withdrawAmount(accountNumber, minBalanceRemainAfterWithdraw);
    }
    
    public void searchAccount(int accountNumber) {
        accountService.searchAccount(accountNumber);
    }
}
