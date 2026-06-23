import java.time.LocalDateTime;

// Cotação histórica de um criptoativo.

public class Quote {

    private Long id;
    private CryptoAsset cryptoAsset; // One CryptoAsset para uma Quote
    private double price;
    private double variation;
    private LocalDateTime dateTime;
    private double tradedVolume;


    public Quote(Long id, CryptoAsset cryptoAsset, double price, double variation) {
        this.id = id;
        this.cryptoAsset = cryptoAsset;
        this.price = price;
        this.variation = variation;
        this.dateTime = LocalDateTime.now();
        this.tradedVolume = 0.0;
    }

    public boolean isPositive() {
        return this.variation > 0;
    }

    public boolean isNegative() {
        return this.variation < 0;
    }

    public boolean isStable() {
        return Math.abs(this.variation) < 0.5;
    }

    public String getVariationStatus() {
        if (isPositive()) return "Rising";
        if (isNegative()) return "Falling";
        return "Stable";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CryptoAsset getCryptoAsset() {
        return cryptoAsset;
    }

    public void setCryptoAsset(CryptoAsset cryptoAsset) {
        this.cryptoAsset = cryptoAsset;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getTradedVolume() {
        return tradedVolume;
    }

    public void setTradedVolume(double tradedVolume) {
        this.tradedVolume = tradedVolume;
    }

    // @Override toString
    @Override
    public String toString() {
        return "Quote{" +
                "cryptoAsset=" + cryptoAsset.getSymbol() +
                ", price=R$" + String.format("%.2f", price) +
                ", variation=" + String.format("%.2f", variation) + "%" +
                ", status=" + getVariationStatus() +
                ", dateTime=" + dateTime +
                '}';
    }
}
