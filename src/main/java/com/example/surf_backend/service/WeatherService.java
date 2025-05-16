package com.example.surf_backend.service;

import com.example.surf_backend.dto.WeatherDTO;
import com.example.surf_backend.model.Spot;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherDTO getClima(Spot spot) {
        String url = String.format("%s?lat=%.4f&lon=%.4f&appid=%s&units=metric&lang=pt_br",
                baseUrl, spot.getLatitude(), spot.getLongitude(), apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject json = new JSONObject(response.getBody());

        String descricao = json.getJSONArray("weather").getJSONObject(0).getString("description");
        String temperatura = json.getJSONObject("main").getDouble("temp") + "°C";
        String vento = json.getJSONObject("wind").getDouble("speed") + " km/h";
        String umidade = json.getJSONObject("main").getInt("humidity") + "%";

        return new WeatherDTO(spot.getNome(), descricao, temperatura, vento, umidade);
    }
}


