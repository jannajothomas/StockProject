package datamodels;

import java.util.Objects;

//import utilities.DateFunctions;

public class InvestorStockQuote {

	private StockQuote stock;
	private int shares;
	
	//TODO: Add  implementation of this
	public InvestorStockQuote() {
		
	}
	
	//TODO: Add implementation of this
	public InvestorStockQuote(StockQuote stock, int shares) {
		
	}
	
	public int getShares() {
		return shares;
	}
	
	public void setShares(int shares) {
		this.shares=shares;
	}
	
	public StockQuote getStock() {
		return stock;
	}
	
	public void setStock(StockQuote stock) {
		this.stock = stock;
	}
	
    /**
     * These methods compares two InvestorStockQuote objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.stock);
        //TODO: Is this the proper hash code for an int???
        hash = 67 * hash + Objects.hashCode(this.shares);
        return hash;
    }
    
    @Override
	public boolean equals(Object obj) {
		return false;
		//TODO  Implement this
	}
	
	
    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
        return "{\"InvestorStockQuote\":{"
                + "\"stock\":\"" + this.stock + "\""
                + ", \"shares\":\"" + this.shares + "\""
                + "}}";
    }
	
}
