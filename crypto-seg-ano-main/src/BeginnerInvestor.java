// Investidor iniciante com limite reduzido. Herança User

public class BeginnerInvestor extends User {

    private String profileLevel;
    private double investmentLimit;

    public BeginnerInvestor(Long id, String name, String email, String document, String phone) {
        super(id, name, email, document, phone);
        this.profileLevel = "Basic";
        this.investmentLimit = 10000.00;
    }

    // Polimorfismo implementação específica do método abstrato da superclasse User
    @Override
    public String getInvestorProfile() {
        return "Beginner";
    }

    // Polimorfismo método override
    @Override
    public double getInvestmentLimit() {
        return investmentLimit;
    }

    public void updateProfileLevel(String newLevel) {
        this.profileLevel = newLevel;
    }

    public String getProfileLevel() {
        return profileLevel;
    }

    public void setProfileLevel(String profileLevel) {
        this.profileLevel = profileLevel;
    }

    public void setInvestmentLimit(double investmentLimit) {
        this.investmentLimit = investmentLimit;
    }

    // @OverridePolimorfismo de getInvestorProfile() e getInvestmentLimit().
    @Override
    public String toString() {
        return "BeginnerInvestor{" +
                "name='" + getName() + '\'' +
                ", profileLevel='" + profileLevel + '\'' +
                ", investmentLimit=" + investmentLimit +
                '}';
    }
}
