package pl.task.stock.service;

import org.springframework.web.client.RestTemplate;
import pl.task.stock.model.Stock;

import java.util.Optional;

public interface AlphaVentageService {
    public RestTemplate getRestTemplate();
    Optional<Stock> getStockData(String companyName, String apiKey);
}
