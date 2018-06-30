package pl.task.stock.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.task.stock.model.Stock;
import pl.task.stock.util.PropertiesUtil;

import java.util.Optional;

public class AlphaVentageServiceImpl implements AlphaVentageService {

    private static final String FULL = "full";
    private static final String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";

    private String ENDPOINT;
    private RestTemplate restTemplate;

    public AlphaVentageServiceImpl() {
        restTemplate = new RestTemplate();
        this.ENDPOINT = PropertiesUtil.getUrlFromProperties();
    }

    public AlphaVentageServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Stock> getStockData(String companyName, String apiKey) {
        String url = prepareURI(companyName, apiKey);
        Stock stock = restTemplate.getForObject(url, Stock.class);
        return Optional.of(stock);
    }

    private String prepareURI(String companyName, String apiKey) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ENDPOINT)
                .queryParam("function", TIME_SERIES_DAILY)
                .queryParam("symbol", companyName)
                .queryParam("outputsize", FULL)
                .queryParam("apikey", apiKey);

        return builder.toUriString();

    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
