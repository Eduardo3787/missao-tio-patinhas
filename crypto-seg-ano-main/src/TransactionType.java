// Tipos de transação suportados (compra e venda).

public enum TransactionType {

    BUY("Buy"),
    SELL("Sell");

    private String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
