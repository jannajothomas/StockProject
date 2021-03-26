/*
 *  This Class contains methods to write out the investor objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import datacontainers.InvestorDataContainer;
import datamodels.Investor;
import exceptionhandlers.MyFileException;

public class InvestorIO implements Serializable {

	private static final long serialVersionUID = 1L;

	private InvestorIO() {
	}

	/**
	 * Creates an xml output file containing all investors in the investor data
	 * container using the JAXB libraries
	 */
	public static void writeXMLFile(String fileLocation, InvestorDataContainer investorDataContainer)
			throws MyFileException {
		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(InvestorDataContainer.class);
			// Create the marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// Create nicely formatted xml
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Marshal the investors list into an xml file
			jaxbMarshaller.marshal(investorDataContainer, new File(fileLocation + "/investors.xml"));
		} catch (JAXBException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}

	/**
	 * Writes out the investor data in JSON format containing all investors in the
	 * stock quote data container
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
			// Unmarshal the file
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
}
