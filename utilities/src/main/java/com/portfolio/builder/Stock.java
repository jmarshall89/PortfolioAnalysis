package com.portfolio.builder;

import com.portfolio.builder.tseries.BaseTSeries;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jmarshall on 4/17/16.
 */
public class Stock {
    private String ticker;
    private BaseTSeries prices;
    private BaseTSeries returns;
    private Map<Stock, Double> beta = new TreeMap<>(); // made a map, as there could be multiple betas for different market proxies

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public BaseTSeries getPrices() {
        return prices;
    }

    public void setPrices(BaseTSeries prices) {
        this.prices = prices;
    }

    public BaseTSeries getReturns() {
        return returns;
    }

    public void setReturns(BaseTSeries returns) {
        this.returns = returns;
    }

    public String getTicker() {
        return ticker;
    }

    public void addBeta(Stock market, Double beta) {
        this.beta.put(market, beta);
    }

    public Double getbeta(Market market) {
        return beta.get(market);
    }

    public Double getTotalReturn(LocalDate start, LocalDate end) {
        return this.returns.totalReturn(start, end);
    }

}
