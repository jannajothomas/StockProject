/*
 *  This Class contains methods to write out the investor objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.InvestorDataContainer;
import datacontainers.StockQuoteDataContainer;

import java.io.PrintWriter;

import datamodels.Broker;
import datamodels.Investor;
import datamodels.InvestorStockQuote;
import datamodels.StockQuote;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;
import utilities.date.DateFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class InvestorIO implements Serializable {

   /**
    * Constructor is declared private because the IO classes are utilities which
    * contain static methods and should never be instantiated
    */
   private InvestorIO() {
   }

   /**
    * Writes out a text file containing all stock quotes in the stock quote data
    * container
    *
    * The format of the text file is:
    *
    * Example: FA301,STOCKQUOTE
    */
   public static void writeTextFile(String fileLocation, InvestorDataContainer datacontainer) throws MyFileException {

      PrintWriter textFile = null;

      try {

         // Create output file
         // We are putting it in a location specified when the program is run
         // This is done via a command line argument
         textFile = new PrintWriter(fileLocation + "investors.txt");

         // Loop through the array list of stockquotes and print delimited text to a file
         for (Investor investor : datacontainer.getInvestorList()) {
        	 textFile.println(investor.getName() + "," + investor.getAddress() + "," + investor.getId() + "," + DateFunctions.dateToString(investor.getDateOfBirth()) + "," + DateFunctions.dateToString(investor.getMemberSince()) + "," + investor.getListOfStocks()  + "," + investor.getListOfStocks());
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
    * Creates an xml output file containing all investors in the investor
    * data container using the JAXB libraries
    */
   public static void writeXMLFile(String fileLocation, InvestorDataContainer investorDataContainer) throws MyFileException {
      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(InvestorDataContainer.class);
         // Create the marshaller
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         // Create nicely formatted xml
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //Marshal the investors list into an xml file
         jaxbMarshaller.marshal(investorDataContainer, new File(fileLocation + "/investors.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Writes out the investor data in JSON format containing all investors
    * in the stock quote data container
    *
    */
   public static void writeJSONFile(String fileLocation, InvestorDataContainer datacontainer) throws MyFileException {

      PrintWriter jsonFile = null;

      try {
         // Create output file
         jsonFile = new PrintWriter(fileLocation + "/investors.json");

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // Convert stock quote list to JSON format
         gson.toJson(datacontainer.getInvestorList(), jsonFile);

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
    * Read in an XML file of investor objects
    *
    * @param fileLocation
    * @return
    */
   public static InvestorDataContainer readXMLFile(String fileLocation) throws MyFileException {

      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(InvestorDataContainer.class);
         // Create the unmarshaller
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         //Unmarshal the file
         return (InvestorDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/investors.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

   /**
    * Reads a JSON formatted file of stock quotes and returns an array list of
    * investors.
    *
    */
   public static ArrayList<Investor> readJSONFile(String fileLocation) throws MyFileException {

      ArrayList<Investor> listOfInvestors = new ArrayList<>();

      try {
         // Create input file
         BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/investors.json"));

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // fromJson returns an array
         Investor[] investorArray = gson.fromJson(jsonFile, Investor[].class);

         // Convert to arraylist for the data model
         listOfInvestors.addAll(Arrays.asList(investorArray));
         return listOfInvestors;
      } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   public static ArrayList<Investor> readTextFile(String fileLocation) throws MyFileException {
		ArrayList<Investor> listOfInvestors = new ArrayList<>();
		BufferedReader textFile = null;
		try {
			boolean eof = false;
			textFile = new BufferedReader(new FileReader(fileLocation + "/investors.txt"));
			while(!eof) {
				String lineFromFile = textFile.readLine();
				if(lineFromFile == null) {
					eof = true;
				} else {
					Investor investor = new Investor();
					
					String[] lineElements = lineFromFile.split(",");
					
					System.out.println(lineElements[0]);
					System.out.println(lineElements[1]);
					System.out.println(lineElements[2]);
					System.out.println(lineElements[3]);
					System.out.println(lineElements[4]);
					System.out.println("5" + lineElements[5]);
					
					
					System.out.println("6" + lineElements[6]);
					System.out.println(lineElements[7]);
					System.out.println(lineElements[8]);
					System.out.println(lineElements[9]);
					System.out.println(lineElements[10]);
					
					investor.setName(lineElements[0]);
					investor.setAddress(lineElements[1]);
					investor.setId(Long.parseLong(lineElements[2]));
					investor.setDateOfBirth(DateFunctions.stringToDate(lineElements[3]));
					investor.setMemberSince(DateFunctions.stringToDate(lineElements[4]));
					/*int x = 5;
					while(lineElements[x] != null) {
						InvestorStockQuote investorQuote = new InvestorStockQuote();
						
						quote.setStock(lineElements[x]);
						quote.setShares(lineElements[x+1]);
						investor.addStock(quote);

			               
			               // The third element is the date
			              quote.setQuoteDate(DateFunctions.stringToDate(lineElements[x+2]));
			              investor.
			              investor.addStock(quote);
						
					}
					//get total number of line elements
					//itterate throught ot add stocks
					
					investor.addStock();
					investor.setAccountValue(Double.parseDouble(lineElements[5]))*/
					listOfInvestors.add(investor);
				}
			}
			textFile.close();
			return listOfInvestors;
		} catch ( IOException | InvalidDataException exp) {
		    throw new MyFileException(exp.getMessage());
		 }
		

	}
}

