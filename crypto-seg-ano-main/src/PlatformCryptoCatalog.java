import java.time.LocalDate;

// Entidade associativa de criptoativos listados na plataforma.

public class PlatformCryptoCatalog {

    private Long id;
    private Long platformId;
    private Long cryptoAssetId;
    private Platform platform; // One Platform para um PlatformCryptoCatalog
    private CryptoAsset cryptoAsset; // One CryptoAsset para um PlatformCryptoCatalog
    private LocalDate listedSince;
    private boolean availableForTrading;



    // overload: construtor com Platform e CryptoAsset
    public PlatformCryptoCatalog(Long id, Platform platform, CryptoAsset cryptoAsset) {
        this.id = id;
        this.platform = platform;
        this.cryptoAsset = cryptoAsset;
        this.platformId = 1L;
        this.cryptoAssetId = cryptoAsset != null ? cryptoAsset.getId() : null;
        this.listedSince = LocalDate.now();
        this.availableForTrading = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getCryptoAssetId() {
        return cryptoAssetId;
    }

    public void setCryptoAssetId(Long cryptoAssetId) {
        this.cryptoAssetId = cryptoAssetId;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public CryptoAsset getCryptoAsset() {
        return cryptoAsset;
    }

    public void setCryptoAsset(CryptoAsset cryptoAsset) {
        this.cryptoAsset = cryptoAsset;
        if (cryptoAsset != null) {
            this.cryptoAssetId = cryptoAsset.getId();
        }
    }

    public LocalDate getListedSince() {
        return listedSince;
    }

    public void setListedSince(LocalDate listedSince) {
        this.listedSince = listedSince;
    }

    public boolean isAvailableForTrading() {
        return availableForTrading;
    }

    public void setAvailableForTrading(boolean availableForTrading) {
        this.availableForTrading = availableForTrading;
    }

    // @Override toString
    @Override
    public String toString() {
        return "PlatformCryptoCatalog{" +
                "id=" + id +
                ", cryptoAsset=" + (cryptoAsset != null ? cryptoAsset.getSymbol() : cryptoAssetId) +
                ", listedSince=" + listedSince +
                ", available=" + availableForTrading +
                '}';
    }
}
