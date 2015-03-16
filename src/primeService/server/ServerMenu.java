package primeService.server;
import java.util.ArrayList;
import primeService.util.Debug;

/*
 * Class to Handle Server Menu
 */
public class ServerMenu{
	private static final int SETUP = 0;
	private static final int NOCOMMAND = 1;
	private static final int GETNAME = 2;
	private static final String MENU = "\nPlease Choose an Option:\n[1] Client Name / History\n[2] All Client Queries\n[3] Quit";

	private int state = SETUP;
	

	private AllPrimeQueries history;

	/*
	 * Constructor for ServerMenu
	 * @param histIn Shared AllPrimeQueries Object
	 */
	public ServerMenu(AllPrimeQueries histIn){
		Debug.printMessage(0);
		history = histIn;
	}

	/*
	 * Processes input and returns appropriate value
	 * @param inString Input string from user
	 * @return Processed output String from Server
	 */
	public String processInput(String inString){
		String outString = null;
		if(SETUP == state){
			outString = "ServerMenu Setup Correctly"+MENU;
			state = NOCOMMAND;
		}
		else if(NOCOMMAND == state){
			if(inString.equalsIgnoreCase("1")){
				outString = "Input Client Name:";
				state = GETNAME;
			}
			else if(inString.equalsIgnoreCase("2")){
				ArrayList<String> keyList = history.getKeys();
				outString = "";
				for(int i=0; i<keyList.size();i++){
					ArrayList<Integer> currentLog = history.getList(keyList.get(i));
					outString = outString + "\n" + keyList.get(i) + ":";
					for(int j=1; j<currentLog.size(); j++){
						outString = outString + " " + currentLog.get(j);
					}
				}
				outString = outString + MENU;
			}
			else if(inString.equalsIgnoreCase("3")){
				outString = "Bye!";
			}
			else{
				outString = "Not Accepted Input, Try Again!" + MENU;
			}
		}
		else if(GETNAME == state){
			if(!history.verifyName(inString)){
				outString = "Client with name " + inString + " Not Registered" + MENU;
			}
			else{
				ArrayList<Integer> temp = history.getList(inString);
				outString = "History for " + inString + ":";
				for(int i=1; i<temp.size(); i++){
					outString = outString + " " + temp.get(i);
				}
				outString = outString + MENU;
			}
			state = NOCOMMAND;
		}
		return outString;
	}
}
