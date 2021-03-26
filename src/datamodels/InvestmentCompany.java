package datamodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptionhandlers.MyFileException;

public class InvestmentCompany implements Serializable {

    private String companyName;
    private List<Broker> listOfBrokers = new ArrayList<>();

    /**
     * no-arg constructor
     */
    public InvestmentCompany() {
    }

    /**
     * This constructor takes the companyName  and listOfBrokers
     *
     * @param companyName The name of the Investment Company
     * @param listOfBrokers The listOfBrokers for the Investment Company
     */
    public InvestmentCompany(String companyName, List<Broker> listOfBrokers) throws MyFileException {
        if (companyName.isEmpty())  {
            throw new MyFileException("Creating investment compnay failed, company name not specified");
        }
        this.companyName = companyName;
        this.listOfBrokers = listOfBrokers;
    }

    /**
     * This constructor takes the companyName
     *
     * @param companyName The name of the Investment Company
     */
    public InvestmentCompany(String companyName) throws MyFileException {
        if (companyName.isEmpty())  {
            throw new MyFileException("Creating investment compnay failed, company name not specified");
        }
        this.companyName = companyName;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) throws MyFileException {
        if (companyName.isEmpty())  {
            throw new MyFileException("Setting investment compnay name failed, company name not specified");
        } else {
            this.companyName = companyName;
        }
    }

    public List<Broker> getListOfBrokers() {
        return this.listOfBrokers;
    }

    public void addBroker(Broker newBroker) {
        this.listOfBrokers.add(newBroker);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.companyName);
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
        final InvestmentCompany other = (InvestmentCompany) obj;
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return   "Investment Company : " + this.companyName + "\n"
                + "List Of Brokers\n" 
                + "---------------\n"
                + this.getFormattedListOfBrokerNames() + "\n";
    }

    private String getFormattedListOfBrokerNames() {
        String formattedListOfBrokers = "";
        for (Broker broker : this.listOfBrokers) {
            formattedListOfBrokers += broker.getName() + "\n";
        }
        return formattedListOfBrokers;
    }
}