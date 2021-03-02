package testclasses;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Calendar;

import datamodels.*;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;

public class TestDataModelsClassesJT {

    double testValue = 300;
    String testSymbol = "AAPL";
    String testName = "Alex Jones";
    String testAddress = "300 University Ave";
    double testSalary = 50000.00;
    String testStatus = "Fulltime";
    long testId = 1234567;
    String testCompanyName = "Fidelity Investments";
    Calendar testDate = Calendar.getInstance();

    //StockQuote testQuoteNoParameters = new StockQuote();
    //StockQuote testQuoteWithParameters = new StockQuote(testValue, testSymbol, testDate);

    
    Investor testInvestorWithParameters = new Investor();
    Broker testBrokerWithParameters = new Broker(testName, testAddress, testDate, testId, testDate,
                    testDate, testSalary, testStatus);
    //InvestmentCompany testInvestmentCompanyWithParameters
           // = new InvestmentCompany(testCompanyName);

    
    
    public TestDataModelsClassesJT() {
        super();
    }
    
    /**
     * Try to add an investor to the brokers list of clients.  Make sure size is right after each addition
     * and make sure the list is populated with the investors where they are expected.
     * @throws ParseException
     */
    @Test
    public void testBrokerAddInvestorToList() throws ParseException {
    	
    	 Investor investor1  = new Investor("Janna Thomas", "14 Spooky way", ParseDate.parseDate("10/31/1999"), testId, ParseDate.parseDate("10/31/2020"));
    	 Investor investor2 = new Investor("Kanna Uhomas", "15 Spookier way", ParseDate.parseDate("11/31/1999"), testId, ParseDate.parseDate("11/31/2020"));
    	 Broker broker1 = new Broker("Broker Brokerson", "BB Broker Way", ParseDate.parseDate("01/01/2020"), testId, ParseDate.parseDate("02/02/2020"),
                 testDate, testSalary, testStatus);
    	 
    	assertEquals("Verify list is empty",0,broker1.getListOfClients().size());
    	broker1.addClient(investor1);

    	assertEquals("Verify list length is 1",1,broker1.getListOfClients().size());
    	assertEquals("Verify client is testInvestorWithParameters",investor1,broker1.getListOfClients().get(0));
    	
    	broker1.addClient(investor2);
    	assertEquals("Verify list length is 2",2,broker1.getListOfClients().size());
    	assertEquals("Verify client in spot 0 is investor 1",investor1,broker1.getListOfClients().get(0));
    	assertEquals("Verify client is spot 1 is investor 2",investor2,broker1.getListOfClients().get(1));   
    }
    
    /**
     * See  if a broker initialized with an empty constructor is a valid constructor.  
     * @throws ParseException 
     * @throws InvalidDataException 
     * 
     */
    //TODO: Lots of copy and paste here.  When I have more time it would be fun to write this more elegantly and in a way that I could easily add Investor and Person paramters just to be complete.
	@Test
    public void testBrokerEmptyConstructor() throws ParseException, InvalidDataException{
    	
    	Broker broker0 = new Broker();
    	
    	//Check initial value of name
    	assertEquals("Check uninitialized broker name",null,broker0.getName());
    	
    	//set name
    	String name = "Broker0";
    	broker0.setName(name);
    	assertEquals("Verify Broke rName",name,broker0.getName());
    	
    	//check initial value of address
    	assertEquals("Check uninitialized broker address",null,broker0.getAddress());
    	
    	//add address
    	String address = "55 Deer Crossing";
    	broker0.setAddress(address);
    	assertEquals("Verify Broker Address",address,broker0.getAddress());
    	
    	//check initial value of dateOfBirth
    	assertEquals("Check uninitialized broker dateOfBirth",null,broker0.getDateOfBirth());
    	
    	//add dateOfBirth
    	Calendar dateOfBirth = ParseDate.parseDate("2/14/2019");
    	broker0.setDateOfBirth(dateOfBirth);
    	assertEquals("Verify Broker Date Of Birth",dateOfBirth,broker0.getDateOfBirth());
    	
    	//check initial value of dateOfHire
    	assertEquals("Check uninitialized broker dateOfHire",null,broker0.getDateOfHire());
    	
    	//add dateOfHire
    	Calendar dateOfHire = ParseDate.parseDate("2/14/2021");
    	broker0.setDateOfHire(dateOfHire);
    	assertEquals("Verify Broker Date Of Hire",dateOfHire,broker0.getDateOfHire());
    	
    	//check initial value of dateOfTermination
    	assertEquals("Check uninitialized broker dateOfTermination",null,broker0.getDateOfTermination());
    	
    	//add dateOfTermiation
    	Calendar dateOfTermination = ParseDate.parseDate("2/15/2021");
    	broker0.setDateOfTermination(dateOfTermination);
    	assertEquals("Verify Broker Date Of Termination",dateOfTermination,broker0.getDateOfTermination());
    	
    	//check initial value of salary
    	assertEquals(0.0, broker0.getSalary(), 0.1);
    	
    	//add salary
    	double salary = 25000;
    	broker0.setSalary(salary);
    	assertEquals(salary, broker0.getSalary(), 0.1);
    	
    	//check initial value of status
    	assertEquals("Check uninitialized broker status",null,broker0.getStatus());
    	
    	//add status
    	String status = "Fulltime";
    	broker0.setStatus(status);
    	assertEquals("Verify Broker status",status,broker0.getStatus());
    	
    	//check initial list of investors
    	assertEquals("Verify list is empty",0,broker0.getListOfClients().size());
    	
    	//add an investor
    	Investor investor1  = new Investor("Janna Thomas", "14 Spooky way", ParseDate.parseDate("10/31/1999"), testId, ParseDate.parseDate("10/31/2020"));
    	broker0.addClient(investor1);
    	assertEquals("Verify list length is 1",1,broker0.getListOfClients().size());
    	assertEquals("Verify client is testInvestorWithParameters",investor1,broker0.getListOfClients().get(0));
    	 
    }
    
	 /**
     * Check to see if two different people with the same Broker characters are equal. (shouldn't be)
     * @throws ParseException
     */
    @Test
    public void testBrokersWithSameBrokerIdentityButDifferentPersonIdentities() throws ParseException {
    	Broker broker2 = new Broker("Broker Brokerson", "BB Broker Way", ParseDate.parseDate("01/01/2020"), testId, ParseDate.parseDate("02/02/2020"),
                 testDate, testSalary, testStatus);
    	Broker broker3 = new Broker("A secondBroker", "CC Broker Way", ParseDate.parseDate("01/02/2020"), testId, ParseDate.parseDate("02/02/2020"),
                testDate, testSalary, testStatus);
    	 
    	assertEquals("Are Brokers equal",false,broker2.equals(broker3));
    }
    
    /**
     * Try to add an investor to the brokers list of clients.  Make sure size is right after each addition
     * and make sure the list is populated with the investors where they are expected.
     * @throws ParseException
     * @throws MyFileException 
     */
    @Test
    public void testInvestorAddStockQuoteToList() throws ParseException, MyFileException {
    	
    	 Investor investor  = new Investor("Janna Thomas", "14 Spooky way", ParseDate.parseDate("10/31/1999"), testId, ParseDate.parseDate("10/31/2020"));
    	 StockQuote testQuote1 = new StockQuote(100, "MIME", ParseDate.parseDate("01/02/2020"));
    	 StockQuote testQuote2 = new StockQuote(testValue, testSymbol, testDate);
    	 InvestorStockQuote ISQ1 = new InvestorStockQuote(testQuote1, 200);
    	 InvestorStockQuote ISQ2 = new InvestorStockQuote(testQuote2, 300);
    	  
    	assertEquals("Verify list is empty",0,investor.getListOfStocks().size());
    	investor.addStock(ISQ1);

    	assertEquals("Verify list length is 1",1,investor.getListOfStocks().size());
    	assertEquals("Verify stock quote is not null",ISQ1,investor.getListOfStocks().get(0));
    	
    	investor.addStock(ISQ2);
    	assertEquals("Verify list length is 2",2,investor.getListOfStocks().size());
    	assertEquals("Verify stock quote is not null",ISQ1,investor.getListOfStocks().get(0));
    	assertEquals("Verify stock quote is not null",ISQ2,investor.getListOfStocks().get(1)); 
    }
    
    /**
     * Try to add an investor to the brokers list of clients.  Make sure size is right after each addition
     * and make sure the list is populated with the investors where they are expected.
     * @throws ParseException
     */
    @Test
    public void testInvestmentCompanyAddBrokerToList() throws ParseException {
    	
    	InvestmentCompany testInvestmentCompany1 = new InvestmentCompany();
    	InvestmentCompany testInvestmentCompany2 = new InvestmentCompany(testCompanyName);
    	
    	Broker testBroker1 = new Broker(testName, testAddress, testDate, testId, testDate,
                 testDate, testSalary, testStatus);
    	Broker testBroker2 = new Broker("Broky", "BB Broker Drive", testDate, testId, testDate,
                testDate, testSalary, testStatus);
    	
    	assertEquals("Verify list is empty",0,testInvestmentCompany1.getListOfBrokers().size());
    	assertEquals("Verify list is empty",0,testInvestmentCompany2.getListOfBrokers().size());
    	testInvestmentCompany1.addBroker(testBroker1);
    	testInvestmentCompany2.addBroker(testBroker2);
    	assertEquals("Verify list is empty",1,testInvestmentCompany1.getListOfBrokers().size());
    	assertEquals("Verify list is empty",1,testInvestmentCompany2.getListOfBrokers().size());
    	assertEquals("Verify listOfBrokers is not null",testBroker1,testInvestmentCompany1.getListOfBrokers().get(0));
    	assertEquals("Verify listOfBrokers is not null",testBroker2,testInvestmentCompany2.getListOfBrokers().get(0));
    	testInvestmentCompany1.addBroker(testBroker2);
    	testInvestmentCompany2.addBroker(testBroker1);
    	assertEquals("Verify list is empty",2,testInvestmentCompany1.getListOfBrokers().size());
    	assertEquals("Verify list is empty",2,testInvestmentCompany2.getListOfBrokers().size());
    	assertEquals("Verify listOfBrokers is not null",testBroker1,testInvestmentCompany1.getListOfBrokers().get(0));
    	assertEquals("Verify listOfBrokers is not null",testBroker2,testInvestmentCompany1.getListOfBrokers().get(1));
    	assertEquals("Verify listOfBrokers is not null",testBroker2,testInvestmentCompany2.getListOfBrokers().get(0));
    	assertEquals("Verify listOfBrokers is not null",testBroker1,testInvestmentCompany2.getListOfBrokers().get(1));
    	
    	InvestmentCompany testInvestmentCompany3 = new InvestmentCompany("NewCompany",testInvestmentCompany1.getListOfBrokers());
    	assertEquals("Verify list is empty",2,testInvestmentCompany3.getListOfBrokers().size());
    	assertEquals("Verify listOfBrokers is not null",testBroker1,testInvestmentCompany3.getListOfBrokers().get(0));
    	assertEquals("Verify listOfBrokers is not null",testBroker2,testInvestmentCompany3.getListOfBrokers().get(1));
    }
    
}