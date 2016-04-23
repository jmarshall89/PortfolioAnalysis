import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jmarshall on 4/17/16.
 */
public class Stock {
    private String ticker;
    private Map<LocalDate, Double> prices;
    private Map<LocalDate, Double> returns;
    private Map<Market, Double> beta = new TreeMap<>(); // made a map, as there could be multiple betas for different market proxies

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public Map<LocalDate, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<LocalDate, Double> prices) {
        this.prices = prices;
    }

    public Map<LocalDate, Double> getReturns() {
        return returns;
    }

    public void setReturns(Map<LocalDate, Double> returns) {
        this.returns = returns;
    }

    public String getTicker() {
        return ticker;
    }

    public void addBeta(Market market, Double beta) {
        this.beta.put(market, beta);
    }

    public Double getbeta(Market market) {
        return beta.get(market);
    }

}
