package testclasses;

import datamodels.Broker;
import datamodels.InvestmentCompany;
import datamodels.StockQuote;
import exceptionhandlers.InvalidDataException;
import java.util.Calendar;

/**
 * This test class will test the setters for the Broker, InvestmentCompany, and
 * StockQuote classes. If a setter fails, an error is thrown and the set method
 * that failed, exits with an error message and the error is returned and
 * printed to the console. Note, not all set methods will throw an error. We are
 * only testing the set methods that are capable of throwing an error.
 */
public class TestErrorHandlingAllClasses {
    
    public static void main(String[] args) {

        /**
         * Test setting an empty string for ticker symbol
         */
        try {
            StockQuote quote = new StockQuote();
            quote.setTickerSymbol("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }

        /**
         * Test setting an empty string for name
         */
        try {
            Broker broker = new Broker();
            broker.setName("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }

        /**
         * Test setting an empty string for address
         */
        try {
            Broker broker = new Broker();
            broker.setAddress("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }

        /**
         * Test setting an invalid id
         */
        try {
            Broker broker = new Broker();
            broker.setId(-123);
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }

         /**
         * Test setting an invalid broker status
         */
        try {
            Broker broker = new Broker();
            broker.setStatus("Temp");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }
        
        /**
         * Test setting an emptry string for company name
         */
        try {
            InvestmentCompany company = new InvestmentCompany();
            company.setCompanyName("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }
    }
    
}