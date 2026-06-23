import java.util.ArrayList;
import java.util.List;

// Investidor experiente com maior limite e vínculo a empresas. Herança User 

public class ExperiencedInvestor extends User {

    private String cnpj;
    private List<String> companies; // Many Companies para um Experienced Investor
    private List<InvestorCompany> investorCompanies; // Many InvestorCompanies para um Experienced Investor
    private double totalVolumeInvested;
    private int yearsOfExperience;
    private double investmentLimit;

    public ExperiencedInvestor(Long id, String name, String email, String document,
                                String phone, String cnpj, int yearsOfExperience) {
        super(id, name, email, document, phone);
        this.cnpj = cnpj;
        this.companies = new ArrayList<>();
        this.investorCompanies = new ArrayList<>();
        this.totalVolumeInvested = 0.0;
        this.yearsOfExperience = yearsOfExperience;
        this.investmentLimit = 1000000.00;
    }

    // Polimorfismo implementação específica do método abstrato da superclasse User
    @Override
    public String getInvestorProfile() {
        return "Experienced";
    }

    // Polimorfismo método override
    @Override
    public double getInvestmentLimit() {
        return investmentLimit;
    }

    // overload: addCompany com um parâmetro
    public void addCompany(String companyName) {
        this.companies.add(companyName);
        InvestorCompany association = new InvestorCompany(
                (long) (investorCompanies.size() + 1),
                this,
                companyName,
                null
        );
        investorCompanies.add(association);
    }

    // overload: addCompany com nome e CNPJ da empresa
    public void addCompany(String companyName, String companyCnpj) {
        addCompany(companyName);
        if (!investorCompanies.isEmpty()) {
            investorCompanies.get(investorCompanies.size() - 1).setCompanyCnpj(companyCnpj);
        }
    }

    public void removeCompany(String companyName) {
        this.companies.remove(companyName);
        investorCompanies.removeIf(ic -> ic.getCompanyName().equals(companyName));
    }

    public void registerInvestedVolume(double amount) {
        this.totalVolumeInvested += amount;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<String> getCompanies() {
        return companies;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

    public List<InvestorCompany> getInvestorCompanies() {
        return investorCompanies;
    }

    public void setInvestorCompanies(List<InvestorCompany> investorCompanies) {
        this.investorCompanies = investorCompanies;
    }

    public double getTotalVolumeInvested() {
        return totalVolumeInvested;
    }

    public void setTotalVolumeInvested(double totalVolumeInvested) {
        this.totalVolumeInvested = totalVolumeInvested;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setInvestmentLimit(double investmentLimit) {
        this.investmentLimit = investmentLimit;
    }
    
    // @OverridePolimorfismo de getInvestorProfile() e getInvestmentLimit().
    @Override
    public String toString() {
        return "ExperiencedInvestor{" +
                "name='" + getName() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", companies=" + companies +
                ", totalVolumeInvested=" + totalVolumeInvested +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
