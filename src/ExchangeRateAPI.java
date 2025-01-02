import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import org.json.JSONObject;


public class ExchangeRateAPI {
    private final String API_KEY = "9249d199c64322a6d395949f";

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;

        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Error al conectar con la API: Código " + responseCode);
            }

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder inline = new StringBuilder();
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject json = new JSONObject(inline.toString());
            return json.getDouble("conversion_rate");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
}

