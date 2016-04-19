import java.time.LocalDate;
import java.util.Map;

/**
 * Created by jmarshall on 4/17/16.
 */
public class Stock {
    private String ticker;
    private Map<LocalDate, Double> prices;
    private Map<LocalDate, Double> returns;

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
}
