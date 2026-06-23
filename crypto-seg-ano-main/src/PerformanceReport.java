import java.time.LocalDateTime;

// Relatório de desempenho e retorno consolidado da carteira. Encapsulamento

public class PerformanceReport {

    private Long id;
    private Wallet wallet; // One Wallet para um PerformanceReport
    private LocalDateTime generationDate;
    private double totalWalletValue;
    private double totalReturn;
    private double returnPercentage;
    private int totalAssets;
    private int totalTransactions;


    public PerformanceReport(Long id, Wallet wallet) {
        this.id = id;
        this.wallet = wallet;
        this.generationDate = LocalDateTime.now();
        generateReport();
    }

    public void generateReport() {
        this.totalWalletValue = wallet.calculateTotalWalletValue();
        this.totalReturn = wallet.calculateTotalReturn();
        this.totalAssets = wallet.getTotalInvestments();
        this.totalTransactions = wallet.getTransactionHistory().size();

        double totalInvested = 0;
        for (Investment inv : wallet.getInvestments()) {
            totalInvested += inv.getTotalInvested();
        }
        this.returnPercentage = totalInvested > 0
                ? (totalReturn / totalInvested) * 100
                : 0;
    }

    // overload: generateSummary sem contagem de transações
    public String generateSummary() {
        return generateSummary(false);
    }

    // overload: generateSummary com opção de incluir transações
    public String generateSummary(boolean includeTransactionCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("========== PERFORMANCE REPORT ==========\n");
        sb.append("User: ").append(wallet.getUser().getName()).append("\n");
        sb.append("Date: ").append(generationDate).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("Total Wallet Value: R$").append(String.format("%.2f", totalWalletValue)).append("\n");
        sb.append("Total Return: R$").append(String.format("%.2f", totalReturn)).append("\n");
        sb.append("Return Percentage: ").append(String.format("%.2f", returnPercentage)).append("%\n");
        sb.append("Total Assets: ").append(totalAssets).append("\n");
        if (includeTransactionCount) {
            sb.append("Total Transactions: ").append(totalTransactions).append("\n");
        }
        sb.append("----------------------------------------\n");

        sb.append("BREAKDOWN BY ASSET:\n");
        for (Investment inv : wallet.getInvestments()) {
            sb.append("  - ").append(inv.getCryptoAsset().getSymbol())
              .append(": Qty=").append(String.format("%.6f", inv.getQuantity()))
              .append(" | Value=R$").append(String.format("%.2f", inv.calculateCurrentValue()))
              .append(" | Profit=R$").append(String.format("%.2f", inv.calculateProfitOrLoss()))
              .append("\n");
        }
        sb.append("========================================\n");

        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDateTime generationDate) {
        this.generationDate = generationDate;
    }

    public double getTotalWalletValue() {
        return totalWalletValue;
    }

    public void setTotalWalletValue(double totalWalletValue) {
        this.totalWalletValue = totalWalletValue;
    }

    public double getTotalReturn() {
        return totalReturn;
    }

    public void setTotalReturn(double totalReturn) {
        this.totalReturn = totalReturn;
    }

    public double getReturnPercentage() {
        return returnPercentage;
    }

    public void setReturnPercentage(double returnPercentage) {
        this.returnPercentage = returnPercentage;
    }

    public int getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(int totalAssets) {
        this.totalAssets = totalAssets;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    // @Override toString
    @Override
    public String toString() {
        return "PerformanceReport{" +
                "user=" + wallet.getUser().getName() +
                ", totalValue=R$" + String.format("%.2f", totalWalletValue) +
                ", return=" + String.format("%.2f", returnPercentage) + "%" +
                ", totalAssets=" + totalAssets +
                '}';
    }
}
