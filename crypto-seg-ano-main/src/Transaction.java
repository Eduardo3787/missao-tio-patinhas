import java.time.LocalDateTime;

// Registro de operação de compra ou venda na carteira.

public class Transaction {

    private Long id;
    private TransactionType type;
    private CryptoAsset cryptoAsset; // One CryptoAsset para uma Transaction
    private double quantity;
    private double unitPrice;
    private double totalValue;
    private LocalDateTime dateTime;
    private double operationFee;


    // overload: construtor sem data/hora explícita
    public Transaction(Long id, TransactionType type, CryptoAsset cryptoAsset,
                       double quantity, double unitPrice) {
        this.id = id;
        this.type = type;
        this.cryptoAsset = cryptoAsset;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalValue = quantity * unitPrice;
        this.dateTime = LocalDateTime.now();
        this.operationFee = calculateFee();
    }

    // overload: construtor com data/hora informada
    public Transaction(Long id, TransactionType type, CryptoAsset cryptoAsset,
                       double quantity, double unitPrice, LocalDateTime dateTime) {
        this.id = id;
        this.type = type;
        this.cryptoAsset = cryptoAsset;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalValue = quantity * unitPrice;
        this.dateTime = dateTime;
        this.operationFee = calculateFee();
    }

    // Encapsulamento
    private double calculateFee() {
        double feePercentage = 0.005;
        return this.totalValue * feePercentage;
    }

    public double getTotalValueWithFee() {
        return this.totalValue + this.operationFee;
    }

    public boolean isBuy() {
        return this.type == TransactionType.BUY;
    }

    public boolean isSell() {
        return this.type == TransactionType.SELL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getOperationFee() {
        return operationFee;
    }

    public void setOperationFee(double operationFee) {
        this.operationFee = operationFee;
    }

    // @Override toString
    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type.getDescription() +
                ", cryptoAsset=" + cryptoAsset.getSymbol() +
                ", quantity=" + quantity +
                ", unitPrice=R$" + String.format("%.2f", unitPrice) +
                ", totalValue=R$" + String.format("%.2f", totalValue) +
                ", dateTime=" + dateTime +
                '}';
    }
}
