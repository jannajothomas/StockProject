package testclasses;

import java.util.Calendar;

import datamodels.Broker;
import datamodels.InvestmentCompany;
import datamodels.Investor;
import datamodels.InvestorStockQuote;
import datamodels.StockQuote;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;

public class TestAllClassesClassCreationAssign2 {

    public static void main(String[] args) throws InvalidDataException, MyFileException {
        
        // Create a date which will be used in all test methods
        Calendar testDate = Calendar.getInstance();
        
        // Create a stock
        StockQuote stock = new StockQuote();   
        stock.setQuoteDate(testDate);
        stock.setTickerSymbol("AAPL");
        stock.setValue(300.00);
        System.out.println(stock.toString());
        System.out.println();
        
        // Create an investor1 stock
        InvestorStockQuote investorStock1 = new InvestorStockQuote();
        investorStock1.setStock(stock);
        investorStock1.setShares(50);
        
        // Create an investor1 stock
        InvestorStockQuote investorStock2 = new InvestorStockQuote();
        investorStock2.setStock(stock);
        investorStock2.setShares(100);
        
        // Create an Investor
        Investor investor1 = new Investor();  
        investor1.setName("Tom Jones");
        investor1.setAddress("100 University Ave");
        investor1.setDateOfBirth(testDate);
        investor1.setId(136900);
        investor1.setMemberSince(testDate);
        investor1.addStock(investorStock1);
        investor1.addStock(investorStock2);
        System.out.println(investor1.toString());
        
        // Create an Investor
        Investor investor2 = new Investor();  
        investor2.setName("Sam Smith");
        investor2.setAddress("50 University Ave");
        investor2.setDateOfBirth(testDate);
        investor2.setId(137200);
        investor2.setMemberSince(testDate);
        investor2.addStock(investorStock1);
        System.out.println(investor2.toString());
        
        // Create a broker
        Broker broker = new Broker();   
        broker.setName("Alex Jones");
        broker.setAddress("300 University Ave");
        broker.setDateOfBirth(testDate);
        broker.setDateOfHire(testDate);
        broker.setDateOfTermination(testDate);
        broker.setId(1234567);
        broker.setSalary(75000.00);
        broker.setStatus("Fulltime");
        broker.addClient(investor1);
        broker.addClient(investor2);
        System.out.println(broker.toString());
       
        // Create an investment company
        InvestmentCompany company = new InvestmentCompany();
        company.setCompanyName("Fidelity Investments");
        company.addBroker(broker);
        System.out.println(company.toString());
    }        
}
