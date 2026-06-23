import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== COLEÇÕES E PERSISTÊNCIA EM ARQUIVO ===\n");

        CryptoAsset bitcoin = new CryptoAsset(1L, "Bitcoin", "BTC",
                "Primeira e maior criptomoeda do mundo", 350000.00, 6800000000000.00);
        CryptoAsset ethereum = new CryptoAsset(2L, "Ethereum", "ETH",
                "Plataforma de contratos inteligentes", 18000.00, 2100000000000.00);
        CryptoAsset solana = new CryptoAsset(3L, "Solana", "SOL",
                "Blockchain de alta performance", 800.00, 340000000000.00);

        ExperiencedInvestor expInvestor = new ExperiencedInvestor(
                1L, "FIAP", "FIAP@empresa.com",
                "111.222.333-44", "(11) 99999-1234",
                "12.345.678/0001-00", 15
        );
        BeginnerInvestor begInvestor = new BeginnerInvestor(
                2L, "Hugo", "hugo@email.com",
                "555.666.777-88", "(11) 98888-5678"
        );

        ArrayList<CryptoAsset> cryptoAssets = new ArrayList<>();
        cryptoAssets.add(bitcoin);
        cryptoAssets.add(ethereum);
        cryptoAssets.add(solana);

        ArrayList<User> investors = new ArrayList<>();
        investors.add(expInvestor);
        investors.add(begInvestor);

        System.out.println("ArrayList de criptoativos (" + cryptoAssets.size() + "):");
        for (CryptoAsset asset : cryptoAssets) {
            System.out.println("  " + asset);
        }
        System.out.println("ArrayList de investidores (" + investors.size() + "):");
        for (User investor : investors) {
            System.out.println("  " + investor);
        }
        System.out.println();

        HashMap<String, CryptoAsset> cryptoByTicker = new HashMap<>();
        for (CryptoAsset asset : cryptoAssets) {
            cryptoByTicker.put(asset.getSymbol(), asset);
        }

        HashMap<Long, User> userById = new HashMap<>();
        for (User investor : investors) {
            userById.put(investor.getId(), investor);
        }

        System.out.println("HashMap de criptoativos por ticker:");
        for (Map.Entry<String, CryptoAsset> entry : cryptoByTicker.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("HashMap de investidores por id:");
        for (Map.Entry<Long, User> entry : userById.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();

        String arquivo = "relatorio_dados.txt";

        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.write("=== RELATORIO DE DADOS - MISSAO TIO PATINHAS ===\n\n");
            writer.write("CRIPTOATIVOS (ArrayList)\n");
            for (CryptoAsset asset : cryptoAssets) {
                writer.write("Id: " + asset.getId()
                        + " | Nome: " + asset.getName()
                        + " | Ticker: " + asset.getSymbol()
                        + " | Preco: R$" + String.format("%.2f", asset.getCurrentPrice())
                        + " | Variacao: " + String.format("%.2f", asset.getdailyVariation()) + "%"
                        + " | MarketCap: R$" + String.format("%.2f", asset.getMarketCap()) + "\n");
            }
            writer.write("\nINVESTIDORES (ArrayList)\n");
            for (User investor : investors) {
                writer.write("Id: " + investor.getId()
                        + " | Nome: " + investor.getName()
                        + " | Email: " + investor.getEmail()
                        + " | Documento: " + investor.getDocument()
                        + " | Perfil: " + investor.getInvestorProfile()
                        + " | Limite: R$" + String.format("%.2f", investor.getInvestmentLimit()) + "\n");
            }
            System.out.println("Arquivo criado: " + arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(arquivo, true)) {
            writer.write("\n=== ATUALIZACAO A PARTIR DOS HASHMAPS ===\n\n");
            writer.write("CRIPTOATIVOS POR TICKER (HashMap)\n");
            for (Map.Entry<String, CryptoAsset> entry : cryptoByTicker.entrySet()) {
                CryptoAsset asset = entry.getValue();
                writer.write(entry.getKey()
                        + " -> " + asset.getName()
                        + " | Preco: R$" + String.format("%.2f", asset.getCurrentPrice())
                        + " | Volume: R$" + String.format("%.2f", asset.getdailyTradedVolume()) + "\n");
            }
            writer.write("\nINVESTIDORES POR ID (HashMap)\n");
            for (Map.Entry<Long, User> entry : userById.entrySet()) {
                User investor = entry.getValue();
                writer.write(entry.getKey()
                        + " -> " + investor.getName()
                        + " | Perfil: " + investor.getInvestorProfile()
                        + " | Cadastro: " + investor.getRegistrationDate() + "\n");
            }
            System.out.println("Arquivo atualizado: " + arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao atualizar arquivo: " + e.getMessage());
        }
    }
}
