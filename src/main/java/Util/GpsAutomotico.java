package Util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GpsAutomotico {
    public static double[] capturarCoordenadas() {
        try {
            // API gratuita que localiza seu computador pelo sinal da internet
            String url = "http://ip-api.com/json/"; 
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            
            double lat = json.get("lat").getAsDouble();
            double lon = json.get("lon").getAsDouble();
            
            return new double[]{lat, lon};
        } catch (Exception e) {
            // Se falhar, usa o centro de Patos como padrão
            return new double[]{-7.0247, -37.2731};
        }
    }
}