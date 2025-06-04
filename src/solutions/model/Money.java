package solutions.model;

public class Money {
    private int amount;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        if (this.amount < other.amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        return new Money(this.amount - other.amount);
    }
    
    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
