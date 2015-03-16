package primeService.util;

public final class Debug{
	private static final int CONSTRUCTORIN = 0;
	private static final int HISTORYWRITE = 1;
	private static final int PRIMECHECK = 2;
	private static int DEBUG = 0;
	
	public static void setDebug(int value){
		if((value >= CONSTRUCTORIN) && (value <= PRIMECHECK)){
			DEBUG = value;
		}
	}

	public static void printMessage(int value){
		if(DEBUG == value){
			if(CONSTRUCTORIN == DEBUG){
				System.out.println("Constructor Called");
			}
			else if(HISTORYWRITE == DEBUG){
				System.out.println("History Edited");
			}
			else{
				System.out.println("Prime Checked");
			}
		}
	}
}
