package datamodels;

import java.util.Calendar;
import java.util.Objects;

//import utilities.DateFunctions;

public class InvestorStockQuote {

	private StockQuote stock;
	private int shares;
	
	//TODO: Add  implementation of this
	public InvestorStockQuote() {
		
	}
	
	public InvestorStockQuote(StockQuote stock, int shares) {
		this.stock = stock;
		this.shares = shares;
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
    	//The item you are comparing to is the exact object
        if (this == obj) {
            return true;
        }
        //The object is null.  This object is initialize and can't be null
        if (obj == null) {
            return false;
        }
        //If the objects are not the same class, they cannot be equal
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final InvestorStockQuote other = (InvestorStockQuote) obj;
        //check classes variable by variable
        if(!Objects.equals(this.stock, other.stock)) {
        	return false;
        }
        if(!Objects.equals(this.shares, other.shares)) {
        	return false;
        }
 
        return true;
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
