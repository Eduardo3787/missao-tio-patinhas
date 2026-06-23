
public class Main_fase_3 {

    public static void main(String[] args) {
        System.out.println("=== MISSAO TIO PATINHAS - FASE 3 ===\n");

        Platform platform = null;
        CryptoAsset bitcoin = null;
        CryptoAsset ethereum = null;
        CryptoAsset solana = null;
        ExperiencedInvestor expInvestor = null;
        BeginnerInvestor begInvestor = null;

        try {
            platform = new Platform("Tio Patinhas - Crypto", "3.0");

            bitcoin = new CryptoAsset(1L, "Bitcoin", "BTC",
                    "Primeira e maior criptomoeda do mundo", 350000.00, 6800000000000.00);
            ethereum = new CryptoAsset(2L, "Ethereum", "ETH",
                    "Plataforma de contratos inteligentes", 18000.00, 2100000000000.00);
            solana = new CryptoAsset(3L, "Solana", "SOL",
                    "Blockchain de alta performance", 800.00, 340000000000.00);

            platform.addCryptoAsset(bitcoin);
            platform.addCryptoAsset(ethereum);
            platform.addCryptoAsset(solana);

            System.out.println("Criptoativos disponíveis (entidade associativa PlatformCryptoCatalog)");
            for (PlatformCryptoCatalog entry : platform.getCryptoCatalog()) {
                System.out.println(entry);
            }
            System.out.println();

            expInvestor = new ExperiencedInvestor(
                    1L, "FIAP", "FIAP@empresa.com",
                    "111.222.333-44", "(11) 99999-1234",
                    "12.345.678/0001-00", 15
            );
            expInvestor.addCompany("CIA Holdings", "11.111.111/0001-11");
            expInvestor.addCompany("Digital Mining");
            platform.registerUser(expInvestor);

            begInvestor = new BeginnerInvestor(
                    2L, "Hugo", "hugo@email.com",
                    "555.666.777-88", "(11) 98888-5678"
            );
            platform.registerUser(begInvestor);

            System.out.println("Registros Usuario-Plataforma (entidade associativa UserPlatform)");
            for (UserPlatform registration : platform.getUserRegistrations()) {
                System.out.println(registration);
            }
            System.out.println();

            System.out.println("Usuários cadastrados (polimorfismo dinâmico - override)");
            exibirPerfilInvestidor(expInvestor);
            exibirPerfilInvestidor(begInvestor);
            System.out.println();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao instanciar objetos: " + e.getMessage());
            return;
        } catch (NullPointerException e) {
            System.err.println("Referência nula ao popular objetos: " + e.getMessage());
            return;
        }

        try {
            System.out.println("OPERAÇÕES DO INVESTIDOR EXPERIENTE\n");

            Wallet expWallet = expInvestor.getWallet();
            expWallet.deposit(500000.00, "Aporte inicial corporativo");

            Transaction buy1 = expWallet.buyCryptoAsset(bitcoin, 0.5);
            if (buy1 == null) {
                throw new IllegalStateException("Compra de Bitcoin não realizada.");
            }
            System.out.println("Compra realizada: " + buy1);

            Transaction buy2 = expWallet.buyCryptoAsset(ethereum, 5.0);
            System.out.println("Compra realizada: " + buy2);

            Transaction buy3 = expWallet.buyCryptoAsset(solana, 100.0);
            System.out.println("Compra realizada: " + buy3);

            System.out.println("\nCarteira: " + expWallet);
            System.out.println("Empresas vinculadas (entidade associativa InvestorCompany):");
            for (InvestorCompany company : expInvestor.getInvestorCompanies()) {
                System.out.println("  " + company);
            }
            System.out.println();

            bitcoin.updatePrice(380000.00, 1500000.00);
            ethereum.updatePrice(20000.00);
            solana.updatePrice(750.00, 800000.00);

            System.out.println("Após variação de preço (overload updatePrice)");
            System.out.println(bitcoin);
            System.out.println(ethereum);
            System.out.println(solana);
            System.out.println();

            PerformanceReport expReport = new PerformanceReport(1L, expWallet);
            System.out.println(expReport.generateSummary());
            System.out.println(expReport.generateSummary(true));

        } catch (IllegalStateException e) {
            System.err.println("Erro nas operações do investidor experiente: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Erro de cálculo financeiro: " + e.getMessage());
        }

        try {
            System.out.println("OPERAÇÕES DO INVESTIDOR INICIANTE\n");

            Wallet begWallet = begInvestor.getWallet();
            begWallet.deposit(5000.00);

            Transaction buy4 = begWallet.buyCryptoAsset(bitcoin, 0.01);
            System.out.println("Compra realizada: " + buy4);

            Transaction buy5 = begWallet.buyCryptoAsset(ethereum, 0.1);
            if (buy5 != null) {
                System.out.println("Compra realizada: " + buy5);
            } else {
                System.out.println("Compra de Ethereum não realizada: saldo insuficiente.");
            }

            System.out.println("\nCarteira: " + begWallet);
            System.out.println();

            PerformanceReport begReport = new PerformanceReport(2L, begWallet);
            System.out.println(begReport.generateSummary(true));

        } catch (IllegalStateException e) {
            System.err.println("Erro nas operações do investidor iniciante: " + e.getMessage());
        }

        try {
            System.out.println("VENDA PARCIAL - INVESTIDOR EXPERIENTE\n");
            Wallet expWallet = expInvestor.getWallet();
            Transaction sale1 = expWallet.sellCryptoAsset(bitcoin, 0.2);
            if (sale1 == null) {
                throw new IllegalStateException("Venda não concluída.");
            }
            System.out.println("Venda realizada: " + sale1);
            System.out.println("Saldo após venda: R$" + String.format("%.2f", expWallet.getAvailableBalance()));
            System.out.println();

            platform.updateQuotes();
            System.out.println("Cotações registradas:");
            for (Quote quote : platform.getQuoteHistory()) {
                System.out.println(quote);
            }
            System.out.println();

            System.out.println("RESUMO DA PLATAFORMA");
            System.out.println(platform);
            System.out.println("Total de usuários: " + platform.getTotalUsers());
            System.out.println("Total de criptoativos: " + platform.getTotalCryptoAssets());
            System.out.println("Criptoativos em valorização: " + platform.listAppreciatingCryptoAssets().size());

        } catch (Exception e) {
            System.err.println("Erro ao finalizar operações: " + e.getMessage());
        }
    }

    // Polimorfismo parâmetro User aceita BeginnerInvestor ou ExperiencedInvestor
    private static void exibirPerfilInvestidor(User user) {
        System.out.println(user);
        System.out.println("  Perfil: " + user.getInvestorProfile()
                + " | Limite: R$" + String.format("%.2f", user.getInvestmentLimit()));
    }
}
