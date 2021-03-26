package datamodels;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class creates an InvestorStockQuote object
 */
public class InvestorStockQuote implements Serializable {

    private StockQuote stock;
    private int shares=10;

    /**
     * no-arg constructor
     */
    public InvestorStockQuote() {
    }

    /**
     * This constructor takes the stock and shares
     *
     * @param stock
     * @param shares
     */
	public InvestorStockQuote(StockQuote stock, int shares) {
		this.stock = stock;
		this.shares = shares;
	}
	
    /**
     * Returns the shares of the InvestorStockQuote object
     */
	public int getShares() {
		return this.shares;
	}
	
	/**
	 * sets the shares of the name of the InvestorStockQuote object
	 * @param shares
	 */
	public void setShares(int shares) {
		this.shares=shares;
	}
	
    /**
     * Returns the stock of the InvestorStockQuote object
     */
	public StockQuote getStock() {
		return this.stock;
	}
	
	/**
	 * sets the stock of the name of the InvestorStockQuote object
	 * @param stock
	 */
	public void setStock(StockQuote stock) {
		this.stock = stock;
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.stock);
        hash = 41 * hash + this.shares;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InvestorStockQuote other = (InvestorStockQuote) obj;
        if (this.shares != other.shares) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "InvestorStockQuote{" + "stock=" + this.getStock() + ", shares=" + this.getShares() + '}';
    }
    
    

}
