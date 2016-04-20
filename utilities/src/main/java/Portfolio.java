import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jmarshall on 4/19/16.
 * Contains a list of stocks
 */
public class Portfolio {

    List<Stock> stocks = new ArrayList<>();
    Optional<Double> beta = Optional.empty();

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }







//    //getters and setters
//    public Double getorCalcBeta() {
//        if (beta.isPresent()) {
//            return beta;
//        }
//        else //todo calc beta
//    }

    public void setBeta(Double beta) {
        this.beta = Optional.of(beta);
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
