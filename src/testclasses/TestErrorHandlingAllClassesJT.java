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
public class TestErrorHandlingAllClassesJT {
    
    public static void main(String[] args) {
    	boolean success = true;
    	
    	System.out.println("//------------Testing valid input________________//");
        /**
         * Test setting an valid string for ticker symbol
         */
        try {
            StockQuote quote = new StockQuote();
            quote.setTickerSymbol("ABCD");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
        
        /**
         * Test setting a valid string for name
         */
        try {
            Broker broker = new Broker();
            broker.setName("Tony");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
    	
        /**
         * Test setting a valid string for address
         */
        try {
            Broker broker = new Broker();
            broker.setAddress("22 Broker Street");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
        
        /**
         * Test setting a valid id
         */
        try {
            Broker broker = new Broker();
            broker.setId(123);
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
        
        /**
        * Test setting a valid broker status
        */
       try {
           Broker broker = new Broker();
           broker.setStatus("Fulltime");
       } catch (InvalidDataException exp) {
           System.out.println(exp.getMessage());
           success = false;
       }
       
       /**
       * Test setting a valid broker status
       */
      try {
          Broker broker = new Broker();
          broker.setStatus("Parttime");
      } catch (InvalidDataException exp) {
          System.out.println(exp.getMessage());
          success = false;
      }
      
      /**
      * Test setting a valid broker salary
      */
     try {
         Broker broker = new Broker();
         broker.setSalary(10000);
     } catch (InvalidDataException exp) {
         System.out.println(exp.getMessage());
         success = false;
     }
      
       /**
        * Test setting a valid string for company name
        */
       try {
           InvestmentCompany company = new InvestmentCompany();
           company.setCompanyName("Really Great Company");
       } catch (InvalidDataException exp) {
           System.out.println(exp.getMessage());
           success = false;
       }
       
       if(success) {
    	   System.out.println("All tests passed");
       }else {
    	   System.out.println(".....Tests were not successful");
       }
        
    	System.out.println("\n//------------Testing invalid input________________//");
    	int count = 0;
        /**
         * Test setting an empty string for ticker symbol
         */
        try {
            StockQuote quote = new StockQuote();
            quote.setTickerSymbol("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            count ++;
        }
        

        /**
         * Test setting an empty string for name
         */
        try {
            Broker broker = new Broker();
            broker.setName("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            count ++;
        }

        /**
         * Test setting an empty string for address
         */
        try {
            Broker broker = new Broker();
            broker.setAddress("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            count ++;
        }

        /**
         * Test setting an invalid id
         */
        try {
            Broker broker = new Broker();
            broker.setId(-123);
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            count ++;
        }

         /**
         * Test setting an invalid broker status
         */
        try {
            Broker broker = new Broker();
            broker.setStatus("Temp");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            count ++;
        }
        
        /**
        * Test setting an invalid broker salary
        */
       try {
           Broker broker = new Broker();
           broker.setSalary(-10000);
       } catch (InvalidDataException exp) {
           System.out.println(exp.getMessage());
           count ++;
       }
       

        
        /**
         * Test setting an empty string for company name
         */
        try {
            InvestmentCompany company = new InvestmentCompany();
            company.setCompanyName("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            count++;
        }
        
        if(count == 7) {
     	   System.out.println("All tests passed");
        }else {
     	   System.out.println((7-count) + " .....Tests were not successful");
        }
    }
    
}
