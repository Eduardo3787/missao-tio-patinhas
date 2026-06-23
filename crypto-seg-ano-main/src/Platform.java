import java.util.ArrayList;
import java.util.List;

// Plataforma de investimento em criptoativos.

public class Platform {

    private String name;
    private String version;
    private Long id;
    private List<User> users; // Many User para uma Platform
    private List<CryptoAsset> availableCryptoAssets; // Many CryptoAsset para uma Platform
    private List<Quote> quoteHistory; // Many Quote para uma Platform
    private List<UserPlatform> userRegistrations; // Many UserPlatform para uma Platform
    private List<PlatformCryptoCatalog> cryptoCatalog; // Many PlatformCryptoCatalog para uma Platform

    private static Long idCounter = 1L;


    // overload: construtor com nome e versão
    public Platform(String name, String version) {
        this.id = idCounter++;
        this.name = name;
        this.version = version;
        this.users = new ArrayList<>();
        this.availableCryptoAssets = new ArrayList<>();
        this.quoteHistory = new ArrayList<>();
        this.userRegistrations = new ArrayList<>();
        this.cryptoCatalog = new ArrayList<>();
    }

    public void registerUser(User user) {
        this.users.add(user);
        UserPlatform registration = new UserPlatform(
                (long) (userRegistrations.size() + 1),
                user,
                this
        );
        registration.setPlatformId(this.id);
        userRegistrations.add(registration);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public User findUserByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public User findUserByDocument(String document) {
        for (User u : users) {
            if (u.getDocument().equals(document)) {
                return u;
            }
        }
        return null;
    }

    public void addCryptoAsset(CryptoAsset cryptoAsset) {
        this.availableCryptoAssets.add(cryptoAsset);
        PlatformCryptoCatalog catalogEntry = new PlatformCryptoCatalog(
                (long) (cryptoCatalog.size() + 1),
                this,
                cryptoAsset
        );
        catalogEntry.setPlatformId(this.id);
        cryptoCatalog.add(catalogEntry);
    }

    public CryptoAsset findCryptoAssetBySymbol(String symbol) {
        for (CryptoAsset c : availableCryptoAssets) {
            if (c.getSymbol().equalsIgnoreCase(symbol)) {
                return c;
            }
        }
        return null;
    }

    public void registerQuote(Quote quote) {
        this.quoteHistory.add(quote);
    }

    public void updateQuotes() {
        for (CryptoAsset crypto : availableCryptoAssets) {
            Quote quote = new Quote(
                    (long) (quoteHistory.size() + 1),
                    crypto,
                    crypto.getCurrentPrice(),
                    crypto.getdailyVariation()
            );
            quoteHistory.add(quote);
        }
    }

    public List<CryptoAsset> listAppreciatingCryptoAssets() {
        List<CryptoAsset> appreciating = new ArrayList<>();
        for (CryptoAsset c : availableCryptoAssets) {
            if (c.isAppreciating()) {
                appreciating.add(c);
            }
        }
        return appreciating;
    }

    public int getTotalUsers() {
        return users.size();
    }

    public int getTotalCryptoAssets() {
        return availableCryptoAssets.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserPlatform> getUserRegistrations() {
        return userRegistrations;
    }

    public void setUserRegistrations(List<UserPlatform> userRegistrations) {
        this.userRegistrations = userRegistrations;
    }

    public List<PlatformCryptoCatalog> getCryptoCatalog() {
        return cryptoCatalog;
    }

    public void setCryptoCatalog(List<PlatformCryptoCatalog> cryptoCatalog) {
        this.cryptoCatalog = cryptoCatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<CryptoAsset> getAvailableCryptoAssets() {
        return availableCryptoAssets;
    }

    public void setAvailableCryptoAssets(List<CryptoAsset> availableCryptoAssets) {
        this.availableCryptoAssets = availableCryptoAssets;
    }

    public List<Quote> getQuoteHistory() {
        return quoteHistory;
    }

    public void setQuoteHistory(List<Quote> quoteHistory) {
        this.quoteHistory = quoteHistory;
    }

    // @Override toString
    @Override
    public String toString() {
        return "Platform{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", totalUsers=" + users.size() +
                ", totalCryptoAssets=" + availableCryptoAssets.size() +
                '}';
    }
}
