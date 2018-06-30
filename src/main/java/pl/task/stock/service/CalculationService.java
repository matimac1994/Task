package pl.task.stock.service;

import pl.task.stock.model.StockData;

import java.util.List;

public interface CalculationService {
    Double calculateStandardDeviation(List<StockData> stockData);
}
