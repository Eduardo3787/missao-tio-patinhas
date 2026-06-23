import java.time.LocalDate;

// Posição de investimento de um criptoativo na carteira.

public class Investment {

    private Long id;
    private Long walletId;
    private Long cryptoAssetId;
    private Wallet wallet; // One Wallet para um Investment
    private CryptoAsset cryptoAsset; // One CryptoAsset para um Investment
    private double quantity;
    private double averagePurchasePrice;
    private LocalDate startDate;
    private double totalInvested;


    public Investment(Long id, Long walletId, Long cryptoAssetId, CryptoAsset cryptoAsset,
                      double quantity, double averagePurchasePrice) {
        this.id = id;
        this.walletId = walletId;
        this.cryptoAssetId = cryptoAssetId;
        this.cryptoAsset = cryptoAsset;
        this.quantity = quantity;
        this.averagePurchasePrice = averagePurchasePrice;
        this.startDate = LocalDate.now();
        this.totalInvested = quantity * averagePurchasePrice;
    }

    public double calculateCurrentValue() {
        return this.quantity * this.cryptoAsset.getCurrentPrice();
    }

    public double calculateProfitOrLoss() {
        return calculateCurrentValue() - this.totalInvested;
    }

    public double calculateReturnPercentage() {
        if (this.totalInvested == 0) return 0;
        return ((calculateCurrentValue() - this.totalInvested) / this.totalInvested) * 100;
    }

    public boolean isProfitable() {
        return calculateProfitOrLoss() > 0;
    }

    public void addQuantity(double newQuantity, double purchasePrice) {
        double previousValue = this.quantity * this.averagePurchasePrice;
        double newValue = newQuantity * purchasePrice;
        this.quantity += newQuantity;
        this.averagePurchasePrice = (previousValue + newValue) / this.quantity;
        this.totalInvested += newValue;
    }

    public void reduceQuantity(double soldQuantity) {
        if (soldQuantity <= this.quantity) {
            double soldProportion = soldQuantity / this.quantity;
            this.totalInvested -= this.totalInvested * soldProportion;
            this.quantity -= soldQuantity;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getCryptoAssetId() {
        return cryptoAssetId;
    }

    public void setCryptoAssetId(Long cryptoAssetId) {
        this.cryptoAssetId = cryptoAssetId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        if (wallet != null) {
            this.walletId = wallet.getId();
        }
    }

    public CryptoAsset getCryptoAsset() {
        return cryptoAsset;
    }

    public void setCryptoAsset(CryptoAsset cryptoAsset) {
        this.cryptoAsset = cryptoAsset;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    public void setAveragePurchasePrice(double averagePurchasePrice) {
        this.averagePurchasePrice = averagePurchasePrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }

    // @Override toString
    @Override
    public String toString() {
        return "Investment{" +
                "cryptoAsset=" + cryptoAsset.getSymbol() +
                ", quantity=" + String.format("%.6f", quantity) +
                ", averagePurchasePrice=R$" + String.format("%.2f", averagePurchasePrice) +
                ", currentValue=R$" + String.format("%.2f", calculateCurrentValue()) +
                ", profitOrLoss=R$" + String.format("%.2f", calculateProfitOrLoss()) +
                ", return=" + String.format("%.2f", calculateReturnPercentage()) + "%" +
                '}';
    }
}
