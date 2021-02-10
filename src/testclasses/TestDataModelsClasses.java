package testclasses;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Calendar;
//import java.util.List;

import datamodels.*;

public class TestDataModelsClasses {

    double testValue = 300;
    String testSymbol = "AAPL";
    String testName = "Alex Jones";
    String testAddress = "300 University Ave";
    double testSalary = 50000.00;
    String testStatus = "Fulltime";
    long testId = 1234567;
    String testCompanyName = "Fidelity Investments";
    Calendar testDate = Calendar.getInstance();

    StockQuote testQuoteNoParameters = new StockQuote();
    //StockQuote testQuoteWithParameters = new StockQuote(testValue, testSymbol, testDate);
    //Investor testInvestorWithParameters
    //        = new Investor();
    Broker testBrokerWithParameters
            = new Broker(testName, testAddress, testDate, testId, testDate,
                    testDate, testSalary, testStatus);
    InvestmentCompany testInvestmentCompanyWithParameters
            = new InvestmentCompany(testCompanyName);

    public TestDataModelsClasses() {
        super();
    }

    /*@Test
    public void testPersonToString() throws ParseException{
    	Calendar testDate = ParseDate.parseDate("10/31/1980");
    	Person testPerson = new Person("JannaThomas","35 Hog Hill Rd",testDate, 300);
    	assertEquals("VerifyPersonStringIsCorrect","Person: \n"
    			+ "Name: JannaThomas\n"
    			+ "Address: 35 Hog Hill Rd\n"
    			+ "Date Of Birth: 10/31/1980\n"
    			+ "Id: 300",testPerson.toString());
    }*/
    
   /* @Test
    public void testInvestorToString() throws ParseException{
    	Calendar testDate = ParseDate.parseDate("10/31/1980");
    	Person testPerson = new Person("JannaThomas","35 Hog Hill Rd",testDate, 300);
    	assertEquals("VerifyPersonStringIsCorrect","Person: \n"
    			+ "Name: JannaThomas\n"
    			+ "Address: 35 Hog Hill Rd\n"
    			+ "Date Of Birth: 10/31/1980\n"
    			+ "Id: 300",testPerson.toString());
    }*/
    
    /**
     * Try to add an investor to the brokers list of clients.  Make sure size is right after each addition
     * and make sure the list is populated with the investors where they are expected.
     * @throws ParseException
     */
    @Test
    public void testBrokerAddInvestorToList() throws ParseException {
    	
    	 //Investor investor1
        // = new Investor("Janna Thomas", "14 Spooky way", ParseDate.parseDate("10/31/1999"), testId, ParseDate.parseDate("10/31/2020"));
    	 //Investor investor2
        // = new Investor("Kanna Uhomas", "15 Spookier way", ParseDate.parseDate("11/31/1999"), testId, ParseDate.parseDate("11/31/2020"));
    	 Broker broker1
         = new Broker("Broker Brokerson", "BB Broker Way", ParseDate.parseDate("01/01/2020"), testId, ParseDate.parseDate("02/02/2020"),
                 testDate, testSalary, testStatus);
    	 
    	assertEquals("Verify list is empty",0,broker1.getListOfClients().size());
    	
    	//broker1.addClient(investor1);

    	assertEquals("Verify list length is 1",1,broker1.getListOfClients().size());
    	
    	//assertEquals("Verify client is testInvestorWithParameters",investor1,broker1.getListOfClients().get(0));
    	
    	//broker1.addClient(investor2);
    	assertEquals("Verify list length is 2",2,broker1.getListOfClients().size());
    	//assertEquals("Verify client in spot 0 is investor 1",investor1,broker1.getListOfClients().get(0));
    	//assertEquals("Verify client is spot 1 is investor 2",investor2,broker1.getListOfClients().get(1));   
    }
    
    //Add a test for adding clients to lsit

}