package kd;

public class Transaction {
    public String transactionId;
    public String accountId;
    public String merchant;
    public double amount;
    public String currency;
    public long timestamp;
    public String type;     // "debit" or "credit"
    public String status;   // "completed", "pending", "failed"

    public Transaction(String transactionId, String accountId, String merchant, double amount,
                       String currency, long timestamp, String type, String status) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.merchant = merchant;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.type = type;
        this.status = status;
    }
}
