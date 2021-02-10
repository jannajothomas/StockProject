package datamodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		return companyName;
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
		return listOfBrokers;
	}
	
	
	/**
	 * Adds an individual broker to the InvestmentCompany object
	 * @param newBroker
	 */
	
	public void addBroker(Broker newBroker) {
		listOfBrokers.add(newBroker);
	}
	
    /**
     * These methods compares two InvestmentCompany objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
        //TODO: Is this the proper hash code for a string and a list???
        hash = 67 * hash + Objects.hashCode(this.companyName);
        hash = 67 * hash + Objects.hashCode(this.listOfBrokers);
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
        
        final InvestmentCompany other = (InvestmentCompany) obj;
        
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.listOfBrokers, other.listOfBrokers)) {
            return false;
        }
        return true;
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
}