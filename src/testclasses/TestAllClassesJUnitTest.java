package testclasses;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Calendar;
import datamodels.*;
import exceptionhandlers.MyFileException;

public class TestAllClassesJUnitTest {

    double testValue = 12.34;
    String testSymbol = "ABCD";
    String testName = "Alex Jones";
    String testAddress = "100 University Ave";
    double testSalary = 50000.00;
    String testStatus = "Fulltime";
    long testId = 1234567;
    String testCompanyName = "Fidelity Investments";
    Calendar testDate = Calendar.getInstance();
    StockQuote testQuoteNoParameters = new StockQuote();
    StockQuote testQuoteWithParameters = new StockQuote();
    
    
    Investor testInvestorWithParameters
            = new Investor(testName, testAddress, testDate, testId, testDate);
    Broker testBrokerWithParameters
            = new Broker(testName, testAddress, testDate, testId, testDate,
                    testDate, testSalary, testStatus);
    InvestmentCompany testInvestmentCompanyWithParameters
            = new InvestmentCompany(testCompanyName);

    public TestAllClassesJUnitTest() {
        super();
    }

    // StockQuote tests
    @Test
    public void testGetValue() {
        assertEquals("Verify value is 0.0", 0.0, testQuoteNoParameters.getValue(), 0.001);
        assertEquals("Verify value is correct", testValue, testQuoteWithParameters.getValue(), 0.001);
    }

    @Test
    public void testGetTickerSymbol() throws MyFileException {
        assertNull("Verify ticker symbol is null", testQuoteNoParameters.getTickerSymbol());
        testQuoteWithParameters.setValue(testValue);
		testQuoteWithParameters.setTickerSymbol(testSymbol);
        assertEquals("Verify ticker symbol is correct", testSymbol, testQuoteWithParameters.getTickerSymbol());
    }

    @Test
    public void testToStringAndDateToString() {
    	System.out.println(testQuoteNoParameters.toString());
    	System.out.println(testQuoteWithParameters.toString());
        assertEquals("Verify toString() is correct", "{\"StockQuote\":{\"value\":\"0.0\", \"tickerSymbol\":\"null\", \"quoteDate\":\"null\"}}", testQuoteNoParameters.toString());
        assertEquals("Verify toString() is correct", "{\"StockQuote\":{\"value\":\"12.34\", \"tickerSymbol\":\"ABCD\", \"quoteDate\":\"null\"}}", testQuoteWithParameters.toString());
    }

    @Test
    public void testGetQuoteDate() throws ParseException, MyFileException {
        Calendar quoteDate = ParseDate.parseDate("4/5/2019");
        StockQuote testQuoteWithDate = new StockQuote(testValue, testSymbol, quoteDate);
        assertEquals("Verify quote date is correct", quoteDate, testQuoteWithDate.getQuoteDate());
    }

    @Test
    public void testEqualsAndHashCodeForStockClass() throws ParseException, MyFileException {
        Calendar quoteDate = ParseDate.parseDate("4/5/2019");
        StockQuote quote1 = new StockQuote(testValue, testSymbol, quoteDate);
        StockQuote quote2 = new StockQuote(testValue, testSymbol, quoteDate);
        StockQuote quote3 = new StockQuote(43.21, testSymbol, quoteDate);
        assertEquals("Verify StockQuotes are equal", quote1, quote2);
        assertNotEquals("Verify StockQuotes are not equal", quote1, quote3);
        assertEquals("Verify hashCodes are equal", quote1.hashCode(), quote2.hashCode());
        assertNotEquals("Verify hashCodes are not equal", quote1.hashCode(), quote3.hashCode());
    }

    @Test
    public void testGetName() {
        assertEquals("Verify invester name is correct", testName, testInvestorWithParameters.getName());
        assertEquals("Verify broker name is correct", testName, testBrokerWithParameters.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("Verify invester address is correct", testAddress, testInvestorWithParameters.getAddress());
        assertEquals("Verify broker address is correct", testAddress, testBrokerWithParameters.getAddress());
    }

    @Test
    public void testGetId() {
        assertEquals("Verify invester id is correct", testId, testInvestorWithParameters.getId());
        assertEquals("Verify broker id is correct", testId, testBrokerWithParameters.getId());
    }

    @Test
    public void testGetBirthDate() throws ParseException {
        assertEquals("Verify broker birth date is correct", testDate, testBrokerWithParameters.getDateOfBirth());
        assertEquals("Verify investor birth date is correct", testDate, testInvestorWithParameters.getDateOfBirth());
    }

    @Test
    public void testHireFireDate() throws ParseException {
        assertEquals("Verify hire date is correct", testDate, testBrokerWithParameters.getDateOfHire());
        assertEquals("Verify fire date is correct", testDate, testBrokerWithParameters.getDateOfTermination());
    }

    @Test
    public void testSalaryStatus() throws ParseException {
        assertEquals("Verify salary is correct", testSalary, testBrokerWithParameters.getSalary(), 0.001);
        assertEquals("Verify status is correct", testStatus, testBrokerWithParameters.getStatus());
    }

    @Test
    public void testInvestmentCompanyName() throws ParseException {
        assertEquals("Verify company name is correct", testCompanyName, testInvestmentCompanyWithParameters.getCompanyName());
    }

    @Test
    public void testEqualsAndHashCodeForAllClasses() throws ParseException, MyFileException {
        Calendar testDate = ParseDate.parseDate("4/5/2019");
        StockQuote quote1 = new StockQuote(testValue, testSymbol, testDate);
        StockQuote quote2 = new StockQuote(testValue, testSymbol, testDate);
        StockQuote quote3 = new StockQuote(43.21, testSymbol, testDate);
        assertEquals("Verify StockQuotes are equal", quote1, quote2);
        assertNotEquals("Verify StockQuotes are not equal", quote1, quote3);
        assertEquals("Verify hashCodes are equal", quote1.hashCode(), quote2.hashCode());
        assertNotEquals("Verify hashCodes are not equal", quote1.hashCode(), quote3.hashCode());
        Investor investor1 = new Investor(testName, testAddress, testDate, testId, testDate);
        Investor investor2 = new Investor(testName, testAddress, testDate, testId, testDate);
        assertEquals("Verify Investors are equal", investor1, investor2);
        assertEquals("Verify Investor hashCodes are equal", investor1.hashCode(), investor2.hashCode());
        Broker broker1 = new Broker(testName, testAddress, testDate, testId, testDate, testDate, testSalary, testStatus);
        Broker broker2 = new Broker(testName, testAddress, testDate, testId, testDate, testDate, testSalary, testStatus);
        Broker broker3 = new Broker(testName, testAddress, null, testId, null, null, testSalary, testStatus);
        assertEquals("Verify Brokesr are equal", broker1, broker2);
        assertEquals("Verify Brokers hashCodes are equal", broker1.hashCode(), broker2.hashCode());
        InvestmentCompany investmentCompany1 = new InvestmentCompany(testCompanyName);
        InvestmentCompany investmentCompany2 = new InvestmentCompany(testCompanyName);
        InvestmentCompany investmentCompany3 = new InvestmentCompany("Charles Schwab");
        assertEquals("Verify InvestmentCompanies  are equal", investmentCompany1, investmentCompany2);
        assertEquals("Verify InvestmentCompanies hashCodes are equal", investmentCompany1.hashCode(), investmentCompany2.hashCode());
        assertNotEquals("Verify InvestmentCompanies hashCodes are not equal", investmentCompany1.hashCode(), investmentCompany3.hashCode());
        assertNotEquals("Verify InvestmentCompanies are not equal", investmentCompany1.hashCode(), investmentCompany3.hashCode());
    }
} 