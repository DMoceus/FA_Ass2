package primeService.socket;
import java.net.*;
import java.io.*;
import primeService.server.AllPrimeQueries;
import primeService.util.CheckPrime;
import primeService.util.Debug;

/*
 * Handles Input from Client to Server Thread
 */
public class PrimeServerWorker{
	private static final int SETUP = 0;
	private static final int NOCOMMAND = 1;
	private static final int GETQUERY = 2;
	private static final int GETNAME = 3;
	
	private int state = SETUP;

	private AllPrimeQueries history;
	private String clientName = "<Anonymouse>";
	private String lastServerResponse = "<NONE>";

	private static final String MENU1 = "&&&Please Choose an Option:";
	private static final String MENU2 = "&&&[1] Set Client Name";
	private static final String MENU3 = "&&&[2] Enter number to query for prime";
	private static final String MENU4 = "&&&[3] What is the server response?";
	private static final String MENU5 = "&&&[4] Quit";

	/*
	 * Constructor for PrimeServerWorker
	 * @param histIn Shared AllPrimeQueries Object
	 */
	public PrimeServerWorker(AllPrimeQueries histIn){
		Debug.printMessage(0);
		history = histIn;
	}
	
	/*
	 * Adds specified Value to shared AllPrimeQueries object
	 * @param value Integer to add
	 */
	private void addToHistory(Integer value){
		if(!clientName.equalsIgnoreCase("<Anonymouse>")){
			history.addElement(clientName,value);
		}
	}

	/*
	 * Handles input from Client
	 * @param inString Input from Client
	 * @return Server Response
	 */
	public String processInput(String inString){
		String outString = null;
		if(SETUP == state){
			outString = "Client - Server Setup Correctly" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
			state = NOCOMMAND;
		}
		else if(NOCOMMAND == state){
			if(inString.equalsIgnoreCase("1")){
				outString = "Input New Client Name: ";
				state = GETNAME;
			}
			else if(inString.equalsIgnoreCase("2")){
				outString = "Input Prime Query: ";
				state = GETQUERY;
			}
			else if(inString.equalsIgnoreCase("3")){
				outString = lastServerResponse +  MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
			}
			else if(inString.equalsIgnoreCase("4")){
				if(!clientName.equalsIgnoreCase("<Anonymouse>")){
					history.setInactive(clientName);
				}
				outString = "Bye!";
			}
			else{
				outString = "Not Accepted Input, Try again" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
			}
		}
		else if(GETQUERY == state){
			try{
				int inQuery = Integer.parseInt(inString);
				if(inQuery < 3){
					outString = "I don't Know" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
					lastServerResponse = Integer.toString(inQuery) + " Might Be a Prime";
				}
				else if(CheckPrime.isPrime(inQuery)){
					outString = "Yes" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
					lastServerResponse = Integer.toString(inQuery) + " Is a Prime";
				}
				else{
					outString = "No" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
					lastServerResponse = Integer.toString(inQuery) + " Is Not a Prime";
				}
				addToHistory(inQuery);
			}
			catch(NumberFormatException e){
				outString = "!!!Query <" + inString + "> not in Integer Format!!!" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
			}
			state = NOCOMMAND;
		}
		else if(GETNAME == state){
			if(history.registerName(inString)){
				clientName = inString;
				outString = "Client Name Set" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
			}
			else{
				outString = "Client name " + inString + " taken and currently active" + MENU1 + MENU2 + MENU3 + MENU4 + MENU5;
			}
			state = NOCOMMAND;
		}
		return outString;
	}
}
