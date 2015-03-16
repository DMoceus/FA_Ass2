package primeService.server;
import java.util.Hashtable;
import java.util.ArrayList;
import primeService.util.Debug;

/*
 * Data Structure for Query History
 */
public class AllPrimeQueries{
	private Hashtable<String,ArrayList<Integer>> history;

	/*
	 * Constructor for AllPrimeQueries Class
	 */
	public AllPrimeQueries(){
		Debug.printMessage(0);
		history = new Hashtable<String,ArrayList<Integer>>();
	}

	/*
	 * Adds a new entry to the History at key nameIn
	 * @param nameIn Key to add to
	 */
	private void newEntry(String nameIn){
		Debug.printMessage(1);
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		history.put(nameIn,temp);
	}
	
	/*
	 * Sets Current Client Name nameIn as active
	 * @param nameIn Client key to activate
	 */
	private void setActive(String nameIn){
		Debug.printMessage(1);
		ArrayList<Integer> temp = history.get(nameIn);
		temp.set(0,1);
		history.put(nameIn,temp);
	}
	
	/*
	 * Sets Current ClientName nameIn as Inactive
	 * @param nameIn Client key to deactivate
	 */
	public void setInactive(String nameIn){
		Debug.printMessage(1);
		ArrayList<Integer> temp = history.get(nameIn);
		temp.set(0,0);
		history.put(nameIn,temp);
	}
	
	/*
	 * Registers name in Database
	 * @param nameIn Client name to register
	 * @return False if nameIn is currently Active
	 */
	public boolean registerName(String nameIn){
		Debug.printMessage(1);
		if(history.containsKey(nameIn)){
			if(1 == history.get(nameIn).get(0)){
				return false;
			}
			else{
				setActive(nameIn);
				return true;
			}
		}
		else{
			newEntry(nameIn);
			return true;
		}
	}

	/*
	 * Returns a list of all Registerd Keys
	 * @return ArrayList<String> of all Keys Currently Registered
	 */
	public ArrayList<String> getKeys(){
		ArrayList<String> temp = new ArrayList<String>();
		for(String key : history.keySet()){
			temp.add(key);
		}
		return temp;
	}

	/*
	 * Adds Element to Registered Key
	 * @param nameIn ClientName to add element to
	 * @param value Integer Query to add
	 */
	public void addElement(String nameIn, Integer value){
		Debug.printMessage(1);
		ArrayList<Integer> temp = history.get(nameIn);
		temp.add(value);
		history.put(nameIn,temp);
	}

	/*
	 * Verifies that key nameIn is registered
	 * @param nameIn Key to Check
	 * @return True/False on whether key is registered
	 */
	public boolean verifyName(String nameIn){
		return history.containsKey(nameIn);
	}

	//IMPORTANT: ONLY CALL IF verifyName(nameIn) RETURNS TRUE
	/*
	 * Returns List associated with given key
	 * @param nameIn ClientName to retrieve
	 * @return ArrayList<Integer> Associated with key nameIn
	 */
	public ArrayList<Integer> getList(String nameIn){
		return history.get(nameIn);
	}
}
