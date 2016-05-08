package com.portfolio.builder;

import com.portfolio.builder.tseries.AbstractTSeries;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jmarshall on 4/17/16.
 */
public class Stock {
    private String ticker;
    private AbstractTSeries prices;
    private AbstractTSeries returns;
    private Map<Stock, Double> beta = new TreeMap<>(); // made a map, as there could be multiple betas for different market proxies
    private LocalDate start = null;
    private LocalDate end = null;

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public AbstractTSeries getPrices() {
        return prices;
    }

    public void setPrices(AbstractTSeries prices) {
        this.prices = prices;
    }

    public AbstractTSeries getReturns() {
        return returns;
    }

    public void setReturns(AbstractTSeries returns) {
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Method for determing if a given date is within the range of available returns.
     * @param date date to test
     * @return true if date falls within available returns
     */
    public boolean validateDate(LocalDate date) {
        return (end != null && !date.isBefore(end));
    }

    public static Comparator<Stock> END_COMP() {
        return (a, b) -> a.getEnd().isBefore(b.getEnd()) ? -1 : 1;
    }

    public static Comparator<Stock> EARLIEST_START() {
        return (a, b) -> a.getStart().isBefore(b.getStart()) ? -1 : 1;
    }

}