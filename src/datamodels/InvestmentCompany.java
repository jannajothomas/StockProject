package datamodels;

import java.util.List;
import java.util.Objects;

//import utilities.DateFunctions;

public class InvestmentCompany {
	private String companyName;
	private List<Broker> listOfBrokers;
	
	//TODO: Implement This
	public InvestmentCompany(String companyName, List<Broker> listOfBrokers) {
		this.companyName=companyName;
		this.listOfBrokers=listOfBrokers;
		
	}
	
	//TODO: Implement This
	public InvestmentCompany() {
	}
	
	//TODO:
	public InvestmentCompany(String companyName) {
		this.companyName=companyName;
		this.listOfBrokers=null;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName=companyName;
	}
	
	public List<Broker> getListOfBrokers(){
		return listOfBrokers;
	}
	
	//TODO: Implement This
	public void addBroker(Broker newBroker) {
		
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
        
        //TODO: What does this do?
        final InvestmentCompany other = (InvestmentCompany) obj;
        //Probabaly should check Person  variables too
        //check classes variable by variable
        
        
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.listOfBrokers, other.listOfBrokers)) {
            return false;
        }
        return true;
    }
	
    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
        return "{\"InvestmentCompany\":{"
                + "\"companyName\":\"" + this.companyName + "\""
                + ", \"listOfBrokers\":\"" + this.listOfBrokers + "\""
                + "}}";
    }
}