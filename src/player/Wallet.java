package player;

public class Wallet {
    private int balance;

    public Wallet(int balance) {
        this.setBalance(balance);
    }

    public void receive(int amount) {
        this.setBalance(this.getBalance() + amount);
    }

    public int spend(int amount) {
        if (amount > this.getBalance()) return -1;
        this.setBalance(this.getBalance() - amount);
        return 1;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) balance = 0;
        this.balance = balance;
    }
}
