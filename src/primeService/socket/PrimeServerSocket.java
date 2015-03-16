package primeService.socket;
import java.net.*;
import java.io.*;
import primeService.server.AllPrimeQueries;
import primeService.util.Debug;

/*
 * Handles Socket I/O for a single Client
 */
public class PrimeServerSocket extends Thread{
	private Socket socket = null;
	private AllPrimeQueries history;
	
	/*
	 * Constructor for PrimeServerSocket
	 * @param socket Socket to connect to
	 * @param histIn Shared AllPrimeQueries Object
	 */
	public PrimeServerSocket(Socket socket, AllPrimeQueries histIn){
		super("PrimeServerThread on socket: " + Integer.toString(socket.getLocalPort()));
		Debug.printMessage(0);
		this.socket = socket;
		this.history = histIn;
	}
	
	/*
	 * Closes the current Socket, used to close Server
	 */
	public void closeSocket(){
		try{
			socket.close();
		}
		catch(IOException e){}
	}
	
	/*
	 * Runnable function for PrimeServerSocket
	 */
	public void run(){
		try(PrintWriter outStream = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader inStream = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));)
		{

			String inLine, outLine;
			PrimeServerWorker ppp = new PrimeServerWorker(history);
			outLine = ppp.processInput(null);
			outStream.println(outLine);
			
			while(!socket.isClosed() && ((inLine = inStream.readLine()) != null)){
				outLine = ppp.processInput(inLine);
				outStream.println(outLine);
				if(outLine.equals("Bye!")){
					break;
				}
			}
			socket.close();
		}
		catch(IOException e){
		}
	}
}
