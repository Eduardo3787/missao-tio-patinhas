import java.time.LocalDate;

// Classe abstrata para perfil de usuários investidores. Herança superclasse de BeginnerInvestor e ExperiencedInvestor

public abstract class User {

    private Long id;
    private String name;
    private String email;
    private String document;
    private String phone;
    private LocalDate registrationDate;
    private Wallet wallet; // One Wallet para um User

    public User(Long id, String name, String email, String document, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.phone = phone;
        this.registrationDate = LocalDate.now();
        this.wallet = new Wallet(this);
    }
 
    // Polimorfismo contrato abstrato redefinido em BeginnerInvestor e ExperiencedInvestor
    public abstract String getInvestorProfile();

    // Polimorfismo limite de investimento específico por tipo de investidor
    public abstract double getInvestmentLimit();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    // @OverridePolimorfismo toString usa getInvestorProfile() dinamicamente
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", document='" + document + '\'' +
                ", profile='" + getInvestorProfile() + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
