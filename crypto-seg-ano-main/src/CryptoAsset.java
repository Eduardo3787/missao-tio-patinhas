// Criptoativo negociável com preço, símbolo e dados de mercado. 

public class CryptoAsset {

    private Long id;
    private String name;
    private String symbol;
    private String description;
    private double currentPrice;
    private double dailyVariation;
    private double marketCap;
    private double dailyTradedVolume;

    public CryptoAsset(Long id, String name, String symbol, String description,
                        double currentPrice, double marketCap) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.currentPrice = currentPrice;
        this.dailyVariation = 0.0;
        this.marketCap = marketCap;
        this.dailyTradedVolume = 0.0;
    }
  
    // overload: updatePrice com novo preço
    public void updatePrice(double newPrice) {
        if (this.currentPrice > 0) {
            this.dailyVariation = ((newPrice - this.currentPrice) / this.currentPrice) * 100;
        }
        this.currentPrice = newPrice;
    }
   
    // overload: updatePrice com preço e volume negociado
    public void updatePrice(double newPrice, double tradedVolume) {
        updatePrice(newPrice);
        this.dailyTradedVolume = tradedVolume;
    }

    public boolean isAppreciating() {
        return this.dailyVariation > 0;
    }

    public double calculateValueForQuantity(double quantity) {
        return this.currentPrice * quantity;
    }

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getdailyVariation() {
        return dailyVariation;
    }

    public void setdailyVariation(double dailyVariation) {
        this.dailyVariation = dailyVariation;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getdailyTradedVolume() {
        return dailyTradedVolume;
    }

    public void setdailyTradedVolume(double dailyTradedVolume) {
        this.dailyTradedVolume = dailyTradedVolume;
    }

    // @Override toString
    @Override
    public String toString() {
        return "CryptoAsset{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", currentPrice=R$" + String.format("%.2f", currentPrice) +
                ", dailyVariation=" + String.format("%.2f", dailyVariation) + "%" +
                '}';
    }
}
