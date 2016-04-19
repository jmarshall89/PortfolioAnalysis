import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarshall on 4/19/16.
 * Contains a list of stocks
 */
public class Portfolio {

    List<Stock> stocks = new ArrayList<>();
    Double beta;

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }






    //getters and setters
    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
