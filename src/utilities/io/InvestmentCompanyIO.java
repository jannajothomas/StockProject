/*
 *  This Class contains methods to write out the InvestmentCompany objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.StockQuoteDataContainer;

import java.io.PrintWriter;
import datamodels.InvestmentCompany;
import datamodels.StockQuote;
import exceptionhandlers.MyFileException;
import utilities.date.DateFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class InvestmentCompanyIO implements Serializable {

   /**
    * Constructor is declared private because the IO classes are utilities which
    * contain static methods and should never be instantiated
    */
   private InvestmentCompanyIO() {
   }
   
   
   /**
    * Writes out a text file containing all stock quotes in the stock quote data
    * container
    *
    * The format of the text file is:
    *
    * Example: FA301,STOCKQUOTE
    */
   public static void writeTextFile(String fileLocation, InvestmentCompanyDataContainer datacontainer) throws MyFileException {

	      PrintWriter textFile = null;

	      try {

	         // Create output file
	         // We are putting it in a location specified when the program is run
	         // This is done via a command line argument
	         textFile = new PrintWriter(fileLocation + "companies.ser");

	         // Loop through the array list of stockquotes and print delimited text to a file
	         for (InvestmentCompany company : datacontainer.getcompanyList()) {
	        	 textFile.println(company.getCompanyName() + "," + company.getListOfBrokers());
	           // textFile.println(quote.getTickerSymbol() + "," + quote.getValue()
	                 //   + "," + DateFunctions.dateToString(quote.getQuoteDate()));
	         }
	      } catch (FileNotFoundException exp) {
	         throw new MyFileException(exp.getMessage());
	      } finally {
	         // Flush the output stream and close the file
	         if (textFile != null) {
	            textFile.flush();
	            textFile.close();
	         }
	      }
	   }
   

   /**
    * Creates an xml output file containing all InvestmentCompanys in the InvestmentCompany
    * data container using the JAXB libraries
    */
   public static void writeXMLFile(String fileLocation, InvestmentCompanyDataContainer InvestmentCompanyDataContainer) throws MyFileException {
      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
         // Create the marshaller
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         // Create nicely formatted xml
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //Marshal the investors list into an xml file
         jaxbMarshaller.marshal(InvestmentCompanyDataContainer, new File(fileLocation + "/InvestmentCompanys.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Writes out the InvestmentCompany data in JSON format containing all InvestmentCompanys
    * in the stock quote data container
    *
    */
   public static void writeJSONFile(String fileLocation, InvestmentCompanyDataContainer datacontainer) throws MyFileException {

      PrintWriter jsonFile = null;

      try {
         // Create output file
         jsonFile = new PrintWriter(fileLocation + "/InvestmentCompanys.json");

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // Convert stock quote list to JSON format
         gson.toJson(datacontainer.getcompanyList(), jsonFile);

      } catch (JsonIOException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      } finally {
         // Flush the output stream and close the file
         if (jsonFile != null) {
            jsonFile.flush();
            jsonFile.close();
         }
      }
   }

   /**
    * Read in an XML file of InvestmentCompany objects
    *
    * @param fileLocation
    * @return
    */
   public static InvestmentCompanyDataContainer readXMLFile(String fileLocation) throws MyFileException {

      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
         // Create the unmarshaller
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         //Unmarshal the file
         return (InvestmentCompanyDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/InvestmentCompanys.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

   /**
    * Reads a JSON formatted file of stock quotes and returns an array list of
    * InvestmentCompanys.
    *
    */
   public static ArrayList<InvestmentCompany> readJSONFile(String fileLocation) throws MyFileException {

      //ArrayList<InvestmentCompany> listOfInvestors = new ArrayList<>();
      ArrayList<InvestmentCompany> listOfCompanies = new ArrayList<>();

      try {
         // Create input file
         BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/InvestmentCompanys.json"));

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // fromJson returns an array
         InvestmentCompany[] investmentCompanyArray = gson.fromJson(jsonFile, InvestmentCompany[].class);

         
         //this.investmentCompanyDataContainer.getcompanyList().add(newInvestmentCompany);
         
         
         //listOfStockQuotes.addAll(Arrays.asList(stockquoteArray));
         // Convert to arraylist for the data model
         listOfCompanies.addAll(Arrays.asList(investmentCompanyArray));
         //companyList.addAll(Arrays.asList(listOfInvestmentCompanies)))
        // listOfInvestmentCompanys.addAll(Arrays.asList(InvestmentCompanyArray));
         return listOfCompanies;
      } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }
}