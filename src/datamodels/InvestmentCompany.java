package datamodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import exceptionhandlers.InvalidDataException;

public class InvestmentCompany {
	private String companyName;
	private List<Broker> listOfBrokers;
	
	/**
	 * no-arg constuctor
	 */
	public InvestmentCompany() {
		this.listOfBrokers = new ArrayList<Broker>();
	}
	
    /**
     * This constructor takes the companyName  and listOfBrokers
     *
     * @param companyName The name of the Investment Company
     * @param listOfBrokers The listOfBrokers for the Investment Company
     */
	public InvestmentCompany(String companyName, List<Broker> listOfBrokers) {
		this.companyName=companyName;
		this.listOfBrokers=listOfBrokers;
	}
	
    /**
     * This constructor takes the companyName
     *
     * @param companyName The name of the Investment Company
     */
	public InvestmentCompany(String companyName) {
		this.companyName=companyName;
		this.listOfBrokers = new ArrayList<Broker>();
	}
	
    /**
     * Returns the company name of the InvestmentCompany object
     */
	public String getCompanyName() {
		return this.companyName;
	}
	
	/**
	 * sets the value of the memberSince date of the InvestmentCompany object
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName=companyName;
	}
	
    /**
     * Returns the listOfBrokers for the InvestmentCompany object
     */
	public List<Broker> getListOfBrokers(){
		return this.listOfBrokers;
	}
	
	
	/**
	 * Adds an individual broker to the InvestmentCompany object
	 * @param newBroker
	 */
	
	public void addBroker(Broker newBroker) {
		this.listOfBrokers.add(newBroker);
	}
	
    /**
     * Returns a String representation of the Investment Company object 
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
        builder.append( "\nInvestmentCompany : " +  this.companyName + "\n\n");
        builder.append("List Of Brokers\n---------------\n");
        for(Broker broker: listOfBrokers) {
        	builder.append(broker.getName());
        }
        return builder.toString();
              
    }

	@Override
	public int hashCode() {
		return Objects.hash(companyName, listOfBrokers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentCompany other = (InvestmentCompany) obj;
		return Objects.equals(companyName, other.companyName) && Objects.equals(listOfBrokers, other.listOfBrokers);
	}
}