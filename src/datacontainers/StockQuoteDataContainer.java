/*
 *  This Class contains several containers which can hold classroom objects 
 *  created in the UI
 */
package datacontainers;

import datamodels.StockQuote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StockQuoteDataContainer  {

    /** Simple container that stores elements as a list, can contain duplicates 
     *  Stores in FIFO order
     */
    private List<StockQuote> stockQuoteList = new ArrayList<>();
    
    /** Container that stores elements as a set of unique elements
     *  Random ordering
     */
    private Set stockQuoteSet = new HashSet();

    /** Container that stores elements as a map, can contain duplicates
     *  Order not enforced
     */
    private Map stockQuoteMap = new HashMap();

    
    
    public List<StockQuote> getStockQuoteList() {
        return stockQuoteList;
    }

    public void setStockQuoteList(List<StockQuote> stockQuoteList) {
        this.stockQuoteList = stockQuoteList;
    }

    public Set getStockQuoteSet() {
        return stockQuoteSet;
    }

    public void setStockQuoteSet(Set stockQuoteSet) {
        this.stockQuoteSet = stockQuoteSet;
    }

    public Map getStockQuoteMap() {
        return stockQuoteMap;
    }

    public void setStockQuoteMap(Map stockQuoteMap) {
        this.stockQuoteMap = stockQuoteMap;
    }
}