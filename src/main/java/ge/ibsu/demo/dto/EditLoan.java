package ge.ibsu.demo.dto;

import ge.ibsu.demo.entities.enums.LoanType;
import ge.ibsu.demo.entities.enums.Status;

public class EditLoan {

    private long amount;


    private String currency;

    private LoanType loanType;


    private Status status;

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

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(long loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
}
