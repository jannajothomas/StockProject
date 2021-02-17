package testclasses;

import datamodels.Broker;
import datamodels.InvestmentCompany;
import datamodels.StockQuote;
import exceptionhandlers.InvalidDataException;

import java.util.ArrayList;

/**
 * This test is an adaptation of the test class provided by the instructor for INFO3010.
 * This class adds a test to ensure a negative salary yields an error.  It also tests the 
 * proper entering of each type of data changed to ensure that a valid entry does not 
 * yield an error. 
 */

public class TestErrorHandlingAllClassesJT {
    static boolean success;
    static int testNum=1;
    static ArrayList<Boolean> successArray = new ArrayList<Boolean>();
    
    public static void main(String[] args) {
    	
    	System.out.println("-----------------Part 1: Testing valid input________________\n");
    	/**
         * Test setting an valid string for ticker symbol
         */
    	setup("valid string for ticker symbol");
        try {
            StockQuote quote = new StockQuote();
            quote.setTickerSymbol("ABCD");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success=false;
        }
        cleanup();
        
        /**
         * Test setting a valid string for name
         */
        setup("valid string for name");
        try {
            Broker broker = new Broker();
            broker.setName("Tony");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
        cleanup();

        /**
         * Test setting a valid string for address
         */
        setup("valid string for address");
        try {
            Broker broker = new Broker();
            broker.setAddress("22 Broker Street");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
        cleanup();
        
        /**
         * Test setting a valid id
         */
        setup("valid id");
        try {
            Broker broker = new Broker();
            broker.setId(123);
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            success = false;
        }
        cleanup();
        
        /**
        * Test setting a valid broker status
        */
        setup("valid broker status");
        try {
           Broker broker = new Broker();
           broker.setStatus("Fulltime");
        } catch (InvalidDataException exp) {
           System.out.println(exp.getMessage());
           success = false;
        }
        cleanup();
        
       /**
       * Test setting a valid broker status
       */   
       setup("valid broker status");
       try {
          Broker broker = new Broker();
          broker.setStatus("Parttime");
       } catch (InvalidDataException exp) {
          System.out.println(exp.getMessage());
          success = false;
       }
       cleanup();
       
      /**
      * Test setting a valid broker salary
      */
      
      setup("valid broker salary");
      try {
         Broker broker = new Broker();
         broker.setSalary(10000);
      } catch (InvalidDataException exp) {
         System.out.println(exp.getMessage());
         success = false;
      }
      cleanup();
      
       /**
        * Test setting a valid string for company name
        */
      setup("valid string for company name");
       try {
           InvestmentCompany company = new InvestmentCompany();
           company.setCompanyName("Really Great Company");
       } catch (InvalidDataException exp) {
           System.out.println(exp.getMessage());
           success = false;
       }
       cleanup();
       
       //END OF TEST 1
       int passed = 0;
       int failed = 0;
       int total = successArray.size();
       for(Boolean testResult: successArray) {
    	   if(testResult) {
    		   passed++;
    	   }else {
    		   failed++;
    	   }
       }
       System.out.print("\nTest 1 summary: ");
       System.out.print(total + " tests were run.  ");
       System.out.println(passed + " passed and " + failed + " failed");
  
       successArray.removeAll(successArray);
       
   	System.out.println("\n---------Part 2: Throwing Errors for invalid output__________\n");

       
   		/**
        * Test setting an empty string for ticker symbol
        */
    	setup("empty string for ticker symbol");
        try {
            StockQuote quote = new StockQuote();
            quote.setTickerSymbol("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }  
        cleanup();
        
        /**
         * Test setting an empty string for name
         */
        setup("empty string for name");
        try {
            Broker broker = new Broker();
            broker.setName("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
            //count ++;
        }
        cleanup();

        /**
         * Test setting an empty string for address
         */
        setup("empty string for address");
        try {
            Broker broker = new Broker();
            broker.setAddress("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }
        cleanup();
        
        /**
         * Test setting an invalid id
         */
        setup("invalid id");
        try {
            Broker broker = new Broker();
            broker.setId(-123);
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }
        cleanup();
        
         /**
         * Test setting an invalid broker status
         */
        setup("invalid broker status");
        try {
            Broker broker = new Broker();
            broker.setStatus("Temp");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }
        cleanup();
        
        /**
        * Test setting an invalid broker salary
        */
        setup("invalid broker salary");
        try {
           Broker broker = new Broker();
           broker.setSalary(-10000);
        } catch (InvalidDataException exp) {
           System.out.println(exp.getMessage());
        }
       cleanup();
       
        /**
         * Test setting an empty string for company name
         */
       	setup("empty string for company name");
        try {
            InvestmentCompany company = new InvestmentCompany();
            company.setCompanyName("");
        } catch (InvalidDataException exp) {
            System.out.println(exp.getMessage());
        }  
        cleanup();
        
        //TEST 2 END
        
        passed = 0;
        failed = 0;
        total = successArray.size();
        for(Boolean testResult: successArray) {
     	   if(testResult) {
     		   passed++;
     	   }else {
     		   failed++;
     	   }
        }
        System.out.print("\nTest 2 summary: ");
        System.out.print(total + " tests were run.  ");
        System.out.println(passed + " passed and " + failed + " failed");
    }
    
    private static void setup(String testName) {
    	success=true;
    	System.out.println("test " + testNum + ": " + testName);
    }
    

    
    private static void cleanup() {
     	successArray.add(success);	
     	System.out.print(String.format("%90s", "test " + testNum));
    	if (success) {
    		System.out.println(" passed");
    	}else {
    		System.out.println(" failed");
    	}
        testNum++;
    }
    
    
    
   
    
}
