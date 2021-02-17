/*
 *  This Class contains the data container which can hold classroom objects 
 *  created in the UI
 */
package datacontainers;

import datamodels.StockQuote;
import java.util.ArrayList;
import java.util.List;

public class StockQuoteDataContainer  {

    /** Simple container that stores elements as a list, can contain duplicates 
     *  Stores in FIFO order
     */
    private List<StockQuote> stockQuoteList = new ArrayList<>();

    public StockQuoteDataContainer() {
    }    
    
    public List<StockQuote> getStockQuoteList() {
        return stockQuoteList;
    }

    public void setStockQuoteList(List<StockQuote> stockQuoteList) {
        this.stockQuoteList = stockQuoteList;
    }
  
}
