import java.time.LocalDate;

// Entidade associativa entre investidor experiente e empresa.

public class InvestorCompany {

    private Long id;
    private Long userId;
    private User investor; // One User para um InvestorCompany
    private String companyName;
    private String companyCnpj;
    private LocalDate associationDate;
    private double participationPercentage;

    public InvestorCompany(Long id, User investor, String companyName, String companyCnpj) {
        this.id = id;
        this.investor = investor;
        this.userId = investor != null ? investor.getId() : null;
        this.companyName = companyName;
        this.companyCnpj = companyCnpj;
        this.associationDate = LocalDate.now();
        this.participationPercentage = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getInvestor() {
        return investor;
    }

    public void setInvestor(User investor) {
        this.investor = investor;
        if (investor != null) {
            this.userId = investor.getId();
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCnpj() {
        return companyCnpj;
    }

    public void setCompanyCnpj(String companyCnpj) {
        this.companyCnpj = companyCnpj;
    }

    public LocalDate getAssociationDate() {
        return associationDate;
    }

    public void setAssociationDate(LocalDate associationDate) {
        this.associationDate = associationDate;
    }

    public double getParticipationPercentage() {
        return participationPercentage;
    }

    public void setParticipationPercentage(double participationPercentage) {
        this.participationPercentage = participationPercentage;
    }

    // @Override toString
    @Override
    public String toString() {
        return "InvestorCompany{" +
                "id=" + id +
                ", userId=" + userId +
                ", companyName='" + companyName + '\'' +
                ", associationDate=" + associationDate +
                '}';
    }
}
