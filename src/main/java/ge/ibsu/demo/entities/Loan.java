package ge.ibsu.demo.entities;

import ge.ibsu.demo.entities.enums.LoanType;
import ge.ibsu.demo.entities.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name="Loan")
public class Loan {
    @Id
    @SequenceGenerator(name="loan_loan_id_seq", sequenceName = "loan_loan_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="loan_loan_id_seq")
    @Column(name="loan_id")
    private long loanId;
    @Column(name="amount")
    private long amount;

    @Column(name="currency")
    private String currency;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name="loan_type")
    private LoanType loanType;

    @Column(name="status")
    private Status status;
    @Column(name="loan_period")
    private long loanPeriod;
    public long getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(long loanPeriod) {
        this.loanPeriod = loanPeriod;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
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
}