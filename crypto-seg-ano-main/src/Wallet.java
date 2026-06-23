import java.util.ArrayList;
import java.util.List;

// Carteira digital do usuário com saldo, investimentos e transações.

public class Wallet {

    private Long id;
    private User user; // One User para uma Wallet
    private List<Investment> investments; // Many Investment para uma Wallet
    private List<Transaction> transactionHistory; // Many Transaction para uma Wallet
    private double availableBalance;

    private static Long idCounter = 1L;


    // overload: construtor com User
    public Wallet(User user) {
        this.id = idCounter++;
        this.user = user;
        this.investments = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        this.availableBalance = 0.0;
    }

    // overload: deposit com valor
    public void deposit(double amount) {
        if (amount > 0) {
            this.availableBalance += amount;
        }
    }

    // overload: deposit com valor e descrição
    public void deposit(double amount, String description) {
        deposit(amount);
        System.out.println("Depósito registrado: " + description + " | R$" + String.format("%.2f", amount));
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.availableBalance) {
            this.availableBalance -= amount;
            return true;
        }
        return false;
    }

    public Transaction buyCryptoAsset(CryptoAsset cryptoAsset, double quantity) {
        double totalValue = cryptoAsset.getCurrentPrice() * quantity;
        double fee = totalValue * 0.005;
        double totalCost = totalValue + fee;

        if (totalCost > this.availableBalance) {
            return null;
        }

        this.availableBalance -= totalCost;

        Transaction transaction = new Transaction(
                (long) (transactionHistory.size() + 1),
                TransactionType.BUY,
                cryptoAsset,
                quantity,
                cryptoAsset.getCurrentPrice()
        );
        transactionHistory.add(transaction);

        Investment existingInvestment = findInvestmentByCryptoAsset(cryptoAsset);
        if (existingInvestment != null) {
            existingInvestment.addQuantity(quantity, cryptoAsset.getCurrentPrice());
        } else {
            Investment newInvestment = new Investment(
                    (long) (investments.size() + 1),
                    this.id,
                    cryptoAsset.getId(),
                    cryptoAsset,
                    quantity,
                    cryptoAsset.getCurrentPrice()
            );
            newInvestment.setWallet(this);
            investments.add(newInvestment);
        }

        return transaction;
    }

    public Transaction sellCryptoAsset(CryptoAsset cryptoAsset, double quantity) {
        Investment investment = findInvestmentByCryptoAsset(cryptoAsset);
        if (investment == null || investment.getQuantity() < quantity) {
            return null;
        }

        double saleValue = cryptoAsset.getCurrentPrice() * quantity;
        double fee = saleValue * 0.005;
        this.availableBalance += (saleValue - fee);

        investment.reduceQuantity(quantity);

        if (investment.getQuantity() <= 0) {
            investments.remove(investment);
        }

        Transaction transaction = new Transaction(
                (long) (transactionHistory.size() + 1),
                TransactionType.SELL,
                cryptoAsset,
                quantity,
                cryptoAsset.getCurrentPrice()
        );
        transactionHistory.add(transaction);

        return transaction;
    }

    public Investment findInvestmentByCryptoAsset(CryptoAsset cryptoAsset) {
        for (Investment inv : investments) {
            if (inv.getCryptoAsset().getSymbol().equals(cryptoAsset.getSymbol())) {
                return inv;
            }
        }
        return null;
    }

    public double calculateTotalWalletValue() {
        double total = this.availableBalance;
        for (Investment inv : investments) {
            total += inv.calculateCurrentValue();
        }
        return total;
    }

    public double calculateTotalReturn() {
        double totalReturn = 0;
        for (Investment inv : investments) {
            totalReturn += inv.calculateProfitOrLoss();
        }
        return totalReturn;
    }

    public int getTotalInvestments() {
        return investments.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    // @Override toString
    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", user=" + (user != null ? user.getName() : "N/A") +
                ", totalInvestments=" + investments.size() +
                ", availableBalance=R$" + String.format("%.2f", availableBalance) +
                ", totalValue=R$" + String.format("%.2f", calculateTotalWalletValue()) +
                '}';
    }
}
