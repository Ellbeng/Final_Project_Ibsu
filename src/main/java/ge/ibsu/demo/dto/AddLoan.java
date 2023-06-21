package ge.ibsu.demo.dto;

public class AddLoan {
    private long amount;
    private String currency;
    private long loanPeriod;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(long loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
}
