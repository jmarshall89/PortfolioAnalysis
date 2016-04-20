import java.io.File;

/**
 * Created by jmarshall on 4/19/16.
 * <p>
 *     Stateless class, which builds and returns a portfolio.
 *     Contains static methods for calculating necessary calcs
 *     for analysis.
 */
public final class PortfolioBuilder {

    public static Portfolio buildPortfolioFromFiles() {
        Portfolio portfolio = new Portfolio();
        String path = CSVReader.getCSVsPath();
        File folder = new File(path);
        if (!folder.isDirectory()) {
            //todo make exception
            System.out.println("The following path is not a directory:");
            System.out.println(path);
            return null;
        }
        for (File stockCSV : folder.listFiles()) {
            if (stockCSV.getName().substring(0,1).compareTo(".") == 0) continue; // don't want hidden files
            String ticker = stockCSV.getName().split("[.csv]")[0]; //want to remove the .csv from name for ticker
            Stock stock = new Stock(ticker);
            stock.setPrices(CSVReader.getAdjClose(ticker));
            portfolio.addStock(stock);
            StockInitalizer.populateReturns(stock);
        }
        return portfolio;
    }
    //todo next build the market, and calc a beta







}
