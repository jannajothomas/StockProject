package testclasses;

import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.InvestorDataContainer;
import datacontainers.StockQuoteDataContainer;
import datamodels.InvestmentCompany;
import datamodels.StockQuote;
import datamodels.Broker;
import datamodels.Investor;
import datamodels.InvestorStockQuote;
import java.util.Calendar;

public class TestAllClassesClassCreation {

    public static void main(String[] args) {

        // Create a date which will be used in all test methods
        Calendar testDate = Calendar.getInstance();
        double value = 12.34;

        // Create data containers
        StockQuoteDataContainer stockQuoteContainer = new StockQuoteDataContainer();
        InvestmentCompanyDataContainer investmentCompanyContainer = new InvestmentCompanyDataContainer();
        InvestorDataContainer investorDataContainer = new InvestorDataContainer();
        BrokerDataContainer brokerDataContainer = new BrokerDataContainer();

        // Create some stocks and store in the container  
        for (int i = 0; i < 10; i++) {
            // Create a stock object
            StockQuote stock = new StockQuote();
            stock.setQuoteDate(Calendar.getInstance());
            stock.setTickerSymbol("AAPL");
            value = value + 0.34;
            stock.setValue(value);

            // Store in the list in the data container
            stockQuoteContainer.getStockQuoteList().add(stock);

        }

        // Verify the container values
        for (int i = 0; i < 10; i++) {
            System.out.println(stockQuoteContainer.getStockQuoteList().get(i));
        }

        // Create some investor stocks        
        InvestorStockQuote investorStock1 = new InvestorStockQuote();
        investorStock1.setStock(stockQuoteContainer.getStockQuoteList().get(0));
        investorStock1.setShares(50);

        InvestorStockQuote investorStock2 = new InvestorStockQuote();
        investorStock2.setStock(stockQuoteContainer.getStockQuoteList().get(1));
        investorStock2.setShares(50);

        // Create some Investors
        Investor investor1 = new Investor();
        investor1.setName("Obi Wan Kenobi");
        investor1.setAddress("100 University Ave");
        investor1.setDateOfBirth(testDate);
        investor1.setId(136900);
        investor1.setMemberSince(testDate);
        investor1.addStock(investorStock1);
        investor1.addStock(investorStock2);
        // Store in the data container
        investorDataContainer.getInvestorList().add(investor1);

        // Create an Investor
        Investor investor2 = new Investor();
        investor2.setName("Luke Skywalker");
        investor2.setAddress("150 University Ave");
        investor2.setDateOfBirth(testDate);
        investor2.setId(137200);
        investor2.setMemberSince(testDate);
        investor2.addStock(investorStock1);
        investor1.addStock(investorStock2);
        // Store in the data container
        investorDataContainer.getInvestorList().add(investor2);

        // Create an Investor
        Investor investor3 = new Investor();
        investor3.setName("Princess Leia");
        investor3.setAddress("250 University Ave");
        investor3.setDateOfBirth(testDate);
        investor3.setId(137200);
        investor3.setMemberSince(testDate);
        investor3.addStock(investorStock1);
        investor1.addStock(investorStock2);
        // Store in the data container
        investorDataContainer.getInvestorList().add(investor3);

        // Create an Investor
        Investor investor4 = new Investor();
        investor4.setName("Mon Mothra");
        investor4.setAddress("350 University Ave");
        investor4.setDateOfBirth(testDate);
        investor4.setId(137200);
        investor4.setMemberSince(testDate);
        investor4.addStock(investorStock1);
        investor1.addStock(investorStock2);
        // Store in the data container
        investorDataContainer.getInvestorList().add(investor4);

        // Verify the container values
        for (int i = 0; i < 4; i++) {
            System.out.println(investorDataContainer.getInvestorList().get(i));
        }

        // Create some brokers
        Broker broker1 = new Broker();
        broker1.setName("Moff Tarkin");
        broker1.setAddress("400 University Ave");
        broker1.setDateOfBirth(testDate);
        broker1.setDateOfHire(testDate);
        broker1.setDateOfTermination(testDate);
        broker1.setId(1234567);
        broker1.setSalary(75000.00);
        broker1.setStatus("Fulltime");
        broker1.addClient(investor1);
        broker1.addClient(investor3);
        // Store in the data container
        brokerDataContainer.getBrokerList().add(broker1);

        Broker broker2 = new Broker();
        broker2.setName("Kylo Ren");
        broker2.setAddress("450 University Ave");
        broker2.setDateOfBirth(testDate);
        broker2.setDateOfHire(testDate);
        broker2.setDateOfTermination(testDate);
        broker2.setId(9876543);
        broker2.setSalary(175000.00);
        broker2.setStatus("Parttime");
        broker2.addClient(investor2);
        broker2.addClient(investor4);
        // Store in the data container
        brokerDataContainer.getBrokerList().add(broker2);

        // Verify the container values
        for (int i = 0; i < 2; i++) {
            System.out.println(brokerDataContainer.getBrokerList().get(i));
        }

        // Create some investment companies
        InvestmentCompany company1 = new InvestmentCompany();
        company1.setCompanyName("Fidelity Investments");
        company1.addBroker(broker1);
        // Store in the data container
        investmentCompanyContainer.getCompanyList().add(company1);

        InvestmentCompany company2 = new InvestmentCompany();
        company2.setCompanyName("Charles Schwab");
        company2.addBroker(broker2);
        // Store in the data container
        investmentCompanyContainer.getCompanyList().add(company2);

        // Verify the container values
        for (int i = 0; i < 2; i++) {
            System.out.println(investmentCompanyContainer.getCompanyList().get(i));
        }

    }
