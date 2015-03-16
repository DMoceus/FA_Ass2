package primeService.server;
import java.net.*;
import java.io.*;
import primeService.util.Debug;

/*
 * Driver Class for Server
 */
public class ServerDriver{

	private String portString;

	/*
	 * Constructor for Server
	 * @param portIn Port to open on
	 */
	public ServerDriver(String portIn){
		Debug.printMessage(0);
		portString = portIn;
	}	
	
	/*
	 * Main Function for Server
	 */
	public void serverMain() throws IOException{
		AllPrimeQueries history = new AllPrimeQueries();
		ServerThreadSpawner threadManager;
		ServerSocket serverSocket;
		int portNumber;
		try{
			portNumber = Integer.parseInt(portString);
		}
		catch(NumberFormatException e){
			System.err.println("Port Number not in Integer Format");
			System.err.println(e.getMessage());
			portNumber = 69;
			System.exit(-1);
		}
		serverSocket = new ServerSocket(portNumber);
		threadManager = new ServerThreadSpawner(serverSocket,history);
		threadManager.start();
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		try{
			String inLine, outLine;
			ServerMenu handler = new ServerMenu(history);
			outLine = handler.processInput(null);
			System.out.println(outLine);
			
			while((inLine = stdIn.readLine()) != null){
				outLine = handler.processInput(inLine);
				outLine = outLine.replaceAll("&&&","\n");
				System.out.println(outLine);
				if(outLine.equals("Bye!")){
					break;
				}
			}
			if(!serverSocket.isClosed()){
				serverSocket.close();
			}
			threadManager.join();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		//ADD SERVER MENU HERE
	}
}
