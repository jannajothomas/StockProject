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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

        //Loo pthrough the list of stock quotes. 
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
                String command = "INSERT INTO investmentCompany (name)"
                		+ "VALUES ('" 
                		+ company.getCompanyName() + "')";

                // Execute the statement
                insertStatement.executeUpdate(command);
                
              //Loop through list of brokers
                for (Broker broker: company.getListOfBrokers()){
                	//For each stock create an investorquote entry
	                String command2 = "INSERT INTO investmentCompanyToBroker (broker_id, investmentCompany_name)"
	                		+ "VALUES ('"
	                		+ broker.getId()  + "','"
	                		+ company.getCompanyName() + "')";

	                // Execute the statement
	                insertStatement.executeUpdate(command2);
                }
                

            } catch (SQLException error) {
                throw new DatabaseException("A database error occured updating"
                        + " investment company table " + error.getMessage());
            }
        }	
	}

    

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

	
	
	public static List<Investor> retrieveInvestors() throws DatabaseException {
		  ArrayList<Investor> listOfInvestors = new ArrayList<>();
		 
	        try {
	            // Retrieve the database connection and create the statement object
	            Connection connection = DatabaseUtilities.openDatabaseConnection();
	            Statement queryStatement = connection.createStatement();
	            
	            //Get a list of IDs from the db.  This let me know how many and gives me a list to iterate through
	            String command = "SELECT id, name, address, dateOfBirth, memberSince FROM investor";
	            ResultSet investors = queryStatement.executeQuery(command);
	            listOfInvestors = parseInvestorListResults(investors);
	           
	            for (Investor investor: listOfInvestors) {
	           //   Create a sql statement to retrieve all the stocks associated with the investor ID
	            	command = "SELECT quote_tickersymbol, shares FROM investorquote WHERE investor_id = ?";
	            	PreparedStatement pstmt = connection.prepareStatement(command);
	            	pstmt.setLong(1, investor.getId());
	            	ResultSet unparsedInvestorStockQuotes = pstmt.executeQuery();
	            	ArrayList<InvestorStockQuote> listOfParsedStockQuotes = parseInvestorStockQuotes(unparsedInvestorStockQuotes);
	            	for(InvestorStockQuote parsedInvestorStockQuote: listOfParsedStockQuotes) {
	            		investor.addStock(parsedInvestorStockQuote);
	            	}
	            }
	        } catch (SQLException | MyFileException error) {
	            throw new DatabaseException("A database error occured retrieving data from the investor table " + error.getMessage());
	        }

	        return listOfInvestors;
	}
	
	private static ArrayList<InvestorStockQuote> parseInvestorStockQuotes( ResultSet investorQuotes) throws SQLException, MyFileException {
		ArrayList<InvestorStockQuote> investorStockQuotes = new ArrayList<>();
		 
		
			Connection connection = DatabaseUtilities.openDatabaseConnection();
			while (investorQuotes.next()) {
				// Retrieving the stock quotes using this list 
				String command = "SELECT tickersymbol, value, date FROM stockquote WHERE tickersymbol IN (?) ";
				PreparedStatement pstmt = connection.prepareStatement(command);
				pstmt = connection.prepareStatement(command);
				pstmt.setString(1, investorQuotes.getString(1));
				ResultSet stockQuotes = pstmt.executeQuery();
     		
				stockQuotes.next();
     		
				StockQuote stockQuote = new StockQuote();
				stockQuote.setTickerSymbol(stockQuotes.getString(1));
				stockQuote.setValue(stockQuotes.getFloat(2));
				stockQuote.setQuoteDate(DatabaseDateUtilities.getJavaFormattedDate(stockQuotes.getDate("date")));
     		
				InvestorStockQuote investorStockQuote = new InvestorStockQuote();
				investorStockQuote.setStock(stockQuote);
				investorStockQuote.setShares(investorQuotes.getInt(2));

     		investorStockQuotes.add(investorStockQuote);
		 }

		return investorStockQuotes;
	}

	private static ArrayList<Investor> parseInvestorListResults(ResultSet results) throws DatabaseException {
        ArrayList<Investor> listOfInvestors = new ArrayList<>();

        try {
            while (results.next()) {
                Investor investor = new Investor();
                investor.setId(results.getLong(1));
                investor.setName(results.getString(2));
                investor.setAddress(results.getString(3));
                investor.setDateOfBirth(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("dateOfBirth")));
                investor.setMemberSince(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("memberSince")));

                listOfInvestors.add(investor);
            }
        } catch (NumberFormatException | SQLException | InvalidDataException e) {
            throw new DatabaseException("Error parsing database results"
                    + " investor table " + e.getMessage());
        }
       
        return listOfInvestors;
	}
	
	public static List<Broker> retrieveBrokers() throws DatabaseException {
		 ArrayList<Broker> listOfBrokers = new ArrayList<>();

	        try {
	            // Retrieve the database connection and create the statement object
	            Connection connection = DatabaseUtilities.openDatabaseConnection();
	            Statement queryStatement = connection.createStatement();
	            
	            // Create the string for the statement object
	            String command = "SELECT name, address, dateOfBirth, id, dateOfHire, dateOfTermination, salary, status FROM broker ORDER BY name";
      		
	            // Execute the statement object 
	            ResultSet results = queryStatement.executeQuery(command);
	            // Call private helper method to parse the result set into the array list
	            listOfBrokers = parseBrokerResults(results);

	            for(Broker broker: listOfBrokers) {
	            	//   Create a sql statement to retrieve all the investor associated with the broker ID
	            	command = "SELECT id, name, address, dateOfBirth, memberSince "
	            			+ "FROM investor INNER JOIN brokerToClient ON brokerToClient.investor_id=investor.id "
	            			+ "WHERE brokerToClient.broker_id = ?";
          		//command = "SELECT investor_id FROM brokerToClient WHERE broker_id = ?";
          		PreparedStatement pstmt = connection.prepareStatement(command);
          		pstmt.setLong(1, broker.getId());
          		ResultSet investors = pstmt.executeQuery();
          		List<Investor> listOfInvestors = parseInvestorListResults(investors);
          		for (Investor investor : listOfInvestors) {
          			broker.addClient(investor);
          		}
	            }	            
	            
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
	            String command = "SELECT name FROM investmentCompany ORDER BY name";
	            
	            // Execute the statement object 
	            ResultSet results = queryStatement.executeQuery(command);
	            // Call private helper method to parse the result set into the array list
	            listOfCompanies = parseCompanyResults(results);
	            
	            for(InvestmentCompany company : listOfCompanies) {
	            	command = "SELECT name, address, dateOfBirth, id, dateOfHire, dateOfTermination, salary, status "
	            			+ "FROM broker INNER JOIN investmentCompanyToBroker ON investmentCompanyToBroker.broker_id=broker.id "
	            			+ "WHERE investmentCompanyToBroker.investmentCompany_name = ? ";
	            	
	            	PreparedStatement pstmt = connection.prepareStatement(command);
	           		pstmt.setString(1, company.getCompanyName());
	           		ResultSet brokers = pstmt.executeQuery();
	           		List<Broker> listOfBrokers = parseBrokerResults(brokers);
	           		for (Broker broker : listOfBrokers) {
	           			company.addBroker(broker);
	           		}
		            }	  

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
