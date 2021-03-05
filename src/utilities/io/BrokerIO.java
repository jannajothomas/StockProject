/*
 *  This Class contains methods to write out the Broker objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.BrokerDataContainer;
import datacontainers.StockQuoteDataContainer;

import java.io.PrintWriter;
import datamodels.Broker;
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

public class BrokerIO implements Serializable {

   /**
    * Constructor is declared private because the IO classes are utilities which
    * contain static methods and should never be instantiated
    */
   private BrokerIO() {
   }

   public static void writeTextFile(String fileLocation, BrokerDataContainer datacontainer) throws MyFileException {

	      PrintWriter textFile = null;

	      try {

	         // Create output file
	         // We are putting it in a location specified when the program is run
	         // This is done via a command line argument
	         textFile = new PrintWriter(fileLocation + "brokers.txt");

	         // Loop through the array list of stockquotes and print delimited text to a file
	         for (Broker broker : datacontainer.getBrokerList()) {
	        	 textFile.println(broker.getName() + "," + broker.getAddress() + "," + broker.getId() + "," + DateFunctions.dateToString(broker.getDateOfBirth()) + "," + DateFunctions.dateToString(broker.getDateOfHire()) + "," + DateFunctions.dateToString(broker.getDateOfTermination()) +  "," + broker.getSalary() +  "," + broker.getStatus());
	            //textFile.println(quote.getTickerSymbol() + "," + quote.getValue()
	                //    + "," + DateFunctions.dateToString(quote.getQuoteDate()));
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
    * Creates an xml output file containing all Brokers in the Broker
    * data container using the JAXB libraries
    */
   public static void writeXMLFile(String fileLocation, BrokerDataContainer BrokerDataContainer) throws MyFileException {
      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(BrokerDataContainer.class);
         // Create the marshaller
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         // Create nicely formatted xml
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //Marshal the Brokers list into an xml file
         jaxbMarshaller.marshal(BrokerDataContainer, new File(fileLocation + "/brokers.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Writes out the Broker data in JSON format containing all Brokers
    * in the stock quote data container
    *
    */
   public static void writeJSONFile(String fileLocation, BrokerDataContainer datacontainer) throws MyFileException {

      PrintWriter jsonFile = null;

      try {
         // Create output file
         jsonFile = new PrintWriter(fileLocation + "/brokers.json");

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // Convert stock quote list to JSON format
         gson.toJson(datacontainer.getBrokerList(), jsonFile);

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
    * Read in an XML file of Broker objects
    *
    * @param fileLocation
    * @return
    */
   public static BrokerDataContainer readXMLFile(String fileLocation) throws MyFileException {

      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(BrokerDataContainer.class);
         // Create the unmarshaller
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         //Unmarshal the file
         return (BrokerDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/brokers.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

   /**
    * Reads a JSON formatted file of stock quotes and returns an array list of
    * Brokers.
    *
    */
   public static ArrayList<Broker> readJSONFile(String fileLocation) throws MyFileException {

      ArrayList<Broker> listOfBrokers = new ArrayList<>();

      try {
         // Create input file
         BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/brokers.json"));

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // fromJson returns an array
         Broker[] BrokerArray = gson.fromJson(jsonFile, Broker[].class);

         // Convert to arraylist for the data model
         listOfBrokers.addAll(Arrays.asList(BrokerArray));
         return listOfBrokers;
      } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

public static ArrayList<Broker> readTextFile(String fileLocation) throws MyFileException{
	ArrayList<Broker> listOfBrokers = new ArrayList<>();
	BufferedReader textFile = null;
	try {
		boolean eof = false;
		textFile = new BufferedReader(new FileReader(fileLocation + "/brokers.txt"));
		while(!eof) {
			String lineFromFile = textFile.readLine();
			if(lineFromFile == null) {
				eof = true;
			} else {
				Broker broker = new Broker();
				
				String[] lineElements = lineFromFile.split(",");
				System.out.println(lineElements);
				broker.setName(lineElements[0]);
				broker.setAddress(lineElements[1]);
				broker.setId(Long.parseLong(lineElements[2]));
				broker.setDateOfBirth(DateFunctions.stringToDate(lineElements[3]));
				broker.setDateOfHire(DateFunctions.stringToDate(lineElements[4]));
				broker.setDateOfTermination(DateFunctions.stringToDate(lineElements[5]));
				broker.setSalary(Double.parseDouble(lineElements[6]));
				System.out.println(":" + lineElements[7] + ":");
				broker.setStatus(lineElements[7]);
				listOfBrokers.add(broker);
			}
		}
		textFile.close();
		return listOfBrokers;
	} catch ( IOException | InvalidDataException exp) {
	    throw new MyFileException(exp.getMessage());
	 } 
	
}
}