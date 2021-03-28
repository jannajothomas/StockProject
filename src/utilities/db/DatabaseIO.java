/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.db;

import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.InvestorDataContainer;
import datacontainers.StockQuoteDataContainer;
import datamodels.Broker;
import datamodels.InvestmentCompany;
import datamodels.Investor;
import datamodels.InvestorStockQuote;
import datamodels.StockQuote;
import exceptionhandlers.DatabaseException;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;

import static org.junit.Assert.assertEquals;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class contains methods to write to and read from, a sql database I
 * suppose I could have put them in the I/O class but I decided to leave all the
 * database code together.
 */
public class DatabaseIO {

    /**
     * Store all stockquotes to database
     */
    public static void storeStockQuotes(StockQuoteDataContainer stockquoteDataContainer) throws DatabaseException {

        // We are looping through the list of stock quotes. Each stock quote will
        // create its own statement in a try/catch block so that if one insert
        // fails, they all won't fail.  We could have put the for loop inside the
        // try/catch block but then all the inserts will fail after the first failure
        for (StockQuote quote : stockquoteDataContainer.getStockQuoteList()) {
            try {
                // Retrieve the database connection and create the statement object
                Connection connection = DatabaseUtilities.openDatabaseConnection();
                Statement insertStatement = connection.createStatement();

                // Create the string for the sql statement
                String command = "INSERT INTO stockquote (tickersymbol, value, date)"
                        + "VALUES ('" 
                		+ quote.getTickerSymbol() + "','"
                        + quote.getValue() + "','" 
                        + DatabaseDateUtilities.getSqlFormattedDate(quote.getQuoteDate()) + "')";
                
                // Execute the statement
                insertStatement.executeUpdate(command);

            } catch (SQLException error) {
                throw new DatabaseException("A database error occured updating"
                        + " stock quote table " + error.getMessage());
            }
        }
    }

    /**
     * retrieve data from database
     */
    public static List<StockQuote> retrieveStockQuotes() throws DatabaseException {

        ArrayList<StockQuote> listOfStockQuotes = new ArrayList<>();

        try {
            // Retrieve the database connection and create the statement object
            Connection connection = DatabaseUtilities.openDatabaseConnection();
            Statement queryStatement = connection.createStatement();

            // Create the string for the statement object
            String command = "SELECT tickersymbol, value, date FROM stockquote ORDER BY tickersymbol";

            // Execute the statement object 
            ResultSet results = queryStatement.executeQuery(command);

            // Call private helper method to parse the result set into the array list
            listOfStockQuotes = parseStockQuote(results);

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieve data from the stock quote table " + error.getMessage());
        }

        return listOfStockQuotes;
    }

    /**
     * Populate the array list with data from the database
     */
    private static ArrayList<StockQuote> parseStockQuote(ResultSet results) throws DatabaseException {

        ArrayList<StockQuote> listOfStockquotes = new ArrayList<>();

        try {
            while (results.next()) {
                StockQuote quote = new StockQuote();
                quote.setTickerSymbol(results.getString(1));
                quote.setValue(Double.parseDouble(results.getString(2)));
                quote.setQuoteDate(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("date")));
                listOfStockquotes.add(quote);
            }
        } catch (MyFileException | NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " stockquote table " + e.getMessage());
        }

        return listOfStockquotes;
    }

	public static void storeInvestors(InvestorDataContainer investorDataContainer) throws DatabaseException {
		  for (Investor investor : InvestorDataContainer.getInvestorList()) {
	            try {
	                // Retrieve the database connection and create the statement object
	                Connection connection = DatabaseUtilities.openDatabaseConnection();
	                Statement insertStatement = connection.createStatement();
	                

	                
	                // Create the string for the sql statement
	                String command1 = "INSERT INTO investor (id, name, address, dateOfBirth, memberSince)"
	                		+ "VALUES ('" 
	                		+ investor.getId() + "','"
	                		+ investor.getName() + "','"
	                		+ investor.getAddress() + "','" 
	                		+ DatabaseDateUtilities.getSqlFormattedDate(investor.getDateOfBirth()) + "','" 
	                		+ DatabaseDateUtilities.getSqlFormattedDate(investor.getMemberSince()) +  "')";

	                // Execute the statement
	                insertStatement.executeUpdate(command1);
	                
	                
	                //Loop through list of stocks
	                for (InvestorStockQuote quote: investor.getListOfStocks()){
	                	//For each stock create an investorquote entry
		                String command2 = "INSERT INTO investorquote (investor_id, quote_tickersymbol, shares)"
		                		+ "VALUES ('"
		                		+ investor.getId()  + "','"
		                		+ quote.getStock().getTickerSymbol()  + "','"
		                		+ quote.getShares() + "')";

		                // Execute the statement
		                insertStatement.executeUpdate(command2);
	                }

	            } catch (SQLException error) {
	                throw new DatabaseException("A database error occured updating"
	                        + " investor table " + error.getMessage());
	            }
	        }
	}

	public static List<Investor> retrieveInvestors() throws DatabaseException {
		  ArrayList<Investor> listOfInvestors = new ArrayList<>();
		 
	        try {
	            // Retrieve the database connection and create the statement object
	            Connection connection = DatabaseUtilities.openDatabaseConnection();
	            Statement queryStatement = connection.createStatement();
	            
	            // Create the string for the statement object
	            String command = "SELECT id, name, address, dateOfBirth, memberSince FROM investor ORDER BY name";
	           
	            
	            // Execute the statement object 
	            ResultSet investorResults = queryStatement.executeQuery(command);

	            command = "SELECT investor_id, quote_tickersymbol, shares FROM investorquote ORDER BY name";
	            ResultSet stockResults = queryStatement.executeQuery(command);
	            // Call private helper method to parse the result set into the array list
	            listOfInvestors = parseInvestorResults(investorResults, stockResults);

	        } catch (SQLException error) {
	            throw new DatabaseException("A database error occured retrieving data from the investor table " + error.getMessage());
	        }

	        return listOfInvestors;
	}
	
    private static ArrayList<Investor> parseInvestorResults(ResultSet investorResults, ResultSet stockResults) throws DatabaseException{

        ArrayList<Investor> listOfInvestors = new ArrayList<>();

        try {
            while (investorResults.next()) {
                Investor investor = new Investor();
                investor.setId(investorResults.getLong(1));
                investor.setName(investorResults.getString(2));
                investor.setAddress(investorResults.getString(3));
                investor.setDateOfBirth(DatabaseDateUtilities.getJavaFormattedDate(investorResults.getDate("dateOfBirth")));
                investor.setMemberSince(DatabaseDateUtilities.getJavaFormattedDate(investorResults.getDate("memberSince")));
                
                while(stockResults.next() ) {
                	Long invID = investorResults.getLong(1);
                	Long thisID = stockResults.getLong(1);

                	if (thisID.equals(invID)){
                		
                		//HOW Do i reference the stock quote to make an investor stock quote
                	}
     
                }
                
                
                
                
                
                
                
                listOfInvestors.add(investor);
            }
        } catch (NumberFormatException | SQLException | InvalidDataException | MyFileException e) {
            throw new DatabaseException("Error parsing database results"
                    + " investor table " + e.getMessage());
        }
       
        return listOfInvestors;
    }
	
	public static void storeBrokers(BrokerDataContainer brokerDataContainer) throws DatabaseException {
		for (Broker broker : BrokerDataContainer.getBrokerList()) {
            try {
                // Retrieve the database connection and create the statement object
                Connection connection = DatabaseUtilities.openDatabaseConnection();
                Statement insertStatement = connection.createStatement();
                
                
         
                
                // Create the string for the sql statement
                String command1 = "INSERT INTO broker (name, address, dateOfBirth, id, dateOfHire, dateOfTermination, salary, status)"
                		+ "VALUES ('" 
                		+ broker.getName() + "','"
                		+ broker.getAddress() + "','" 
                		+ DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfBirth()) + "','" 
                		+ broker.getId() + "','"
                		+ DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfHire()) + "','"
                		+ DatabaseDateUtilities.getSqlFormattedDate(broker.getDateOfTermination()) + "','"
                		+ broker.getSalary() + "','" 
                		+ broker.getStatus() + "')";

                // Execute the statement
                insertStatement.executeUpdate(command1);               
             
                //Loop through list of stocks
                for (Investor client: broker.getListOfClients()){
                	//For each stock create an investorquote entry
	                String command2 = "INSERT INTO brokerToClient (investor_id, broker_id)"
	                		+ "VALUES ('"
	                		+ client.getId()  + "','"
	                		+ broker.getId() + "')";

	                // Execute the statement
	                insertStatement.executeUpdate(command2);
                }
                
                

            } catch (SQLException error) {
                throw new DatabaseException("A database error occured updating"
                        + " broker table " + error.getMessage());
            }
        }
	}

	public static void storeInvestmentCompany(InvestmentCompanyDataContainer investmentCompanyDataContainer) throws DatabaseException {
		for (InvestmentCompany company : InvestmentCompanyDataContainer.getInvestmentCompanyList()) {
            try {
                // Retrieve the database connection and create the statement object
                Connection connection = DatabaseUtilities.openDatabaseConnection();
                Statement insertStatement = connection.createStatement();

                
                // Create the string for the sql statement
                String command = "INSERT INTO investmentCompany (companyName, listOfBrokers)"
                		+ "VALUES ('" 
                		+ company.getCompanyName() + "','"
                		+ company.getListOfBrokers() + "')";

                // Execute the statement
                insertStatement.executeUpdate(command);

            } catch (SQLException error) {
                throw new DatabaseException("A database error occured updating"
                        + " investment company table " + error.getMessage());
            }
        }	
	}

	public static List<Broker> retrieveBrokers() throws DatabaseException {
		
		 ArrayList<Broker> listOfBrokers = new ArrayList<>();

	        try {
	            // Retrieve the database connection and create the statement object
	            Connection connection = DatabaseUtilities.openDatabaseConnection();
	            Statement queryStatement = connection.createStatement();
	            
	            // Create the string for the statement object
	            String command = "SELECT name, address, dateOfBirth, id, dateOfHire, dateOfTermination, salary, status, listOfClients FROM broker ORDER BY name";

	            // Execute the statement object 
	            ResultSet results = queryStatement.executeQuery(command);
	            // Call private helper method to parse the result set into the array list
	            listOfBrokers = parseBrokerResults(results);

	        } catch (SQLException error) {
	            throw new DatabaseException("A database error occured retrieve data from the broker quote table " + error.getMessage());
	        }

	        return listOfBrokers;
	}

	private static ArrayList<Broker> parseBrokerResults(ResultSet results) throws DatabaseException {
        ArrayList<Broker> listOfBrokers = new ArrayList<>();

        try {
            while (results.next()) {
                Broker broker = new Broker();
                broker.setName(results.getString(1));
                broker.setAddress(results.getString(2));
                broker.setDateOfBirth(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfBirth")));
                broker.setId(results.getLong(4));
                broker.setDateOfHire(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfHire")));
                broker.setDateOfTermination(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfTermination")));
                broker.setSalary(results.getDouble(7));
                broker.setStatus(results.getString(8));
                listOfBrokers.add(broker);
            }
        } catch (NumberFormatException | SQLException | InvalidDataException e) {
            throw new DatabaseException("Error parsing database results"
                    + " investor table " + e.getMessage());
        }
       
        return listOfBrokers;
	}

	public static List<InvestmentCompany> retrieveInvestmentCompanies() throws DatabaseException, MyFileException {
		 ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

	        try {
	            // Retrieve the database connection and create the statement object
	            Connection connection = DatabaseUtilities.openDatabaseConnection();
	            Statement queryStatement = connection.createStatement();
	            
	            // Create the string for the statement object
	            String command = "SELECT companyName, listOfBrokers FROM investmentCompany ORDER BY companyName";
	            
	            // Execute the statement object 
	            ResultSet results = queryStatement.executeQuery(command);
	            // Call private helper method to parse the result set into the array list
	            listOfCompanies = parseCompanyResults(results);

	        } catch (SQLException error) {
	            throw new DatabaseException("A database error occured retrieve data from the investment company table " + error.getMessage());
	        }

	        return listOfCompanies;
	}

	private static ArrayList<InvestmentCompany> parseCompanyResults(ResultSet results) throws DatabaseException, MyFileException {
		ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

        try {
            while (results.next()) {
                InvestmentCompany company = new InvestmentCompany();
                company.setCompanyName(results.getString(1));
                listOfCompanies.add(company);
            }
        } catch (NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " investor table " + e.getMessage());
        }
       
        return listOfCompanies;
	}

}
