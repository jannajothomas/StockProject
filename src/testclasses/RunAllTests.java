package testclasses;

import datamodels.InvestmentCompany;
import datamodels.StockQuote;
import exceptionhandlers.InvalidDataException;
import datamodels.Broker;
import datamodels.Investor;
import datamodels.InvestorStockQuote;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import testclasses.TestAllClassesClassCreationAssign2;

public class RunAllTests {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InvalidDataException, FileNotFoundException {
		
		try {
		    System.setOut(new PrintStream(new File("testProgramOutput.txt")));
		} catch (Exception e) {
		     e.printStackTrace();
		}
		
		TestAllClassesClassCreationAssign2.main(args);
		
		TestAllClassesClassCreationAssign2.main(args);
		
		TestErrorHandlingAllClasses.main(args);
		
		TestErrorHandlingAllClassesJT.main(args);
		
		TestStockQuoteDataContainer.main(args);
		
	}

}
