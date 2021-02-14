package datamodels;

import java.util.Objects;

public class InvestorStockQuote {

	private StockQuote stock;
	private int shares;
	
	/**
	 * no-arg constuctor
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
	
    /**
     * Returns a String representation of the InvestorStockQuote object
     */
    @Override
    public String toString() {
        return "{\"InvestorStockQuote\":{"
                + "\"stock\":\"" + this.stock + "\""
                + ", \"shares\":\"" + this.shares + "\""
                + "}}";
    }

	@Override
	public int hashCode() {
		return Objects.hash(shares, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestorStockQuote other = (InvestorStockQuote) obj;
		return shares == other.shares && Objects.equals(stock, other.stock);
	}
	
}
