package primeService.client;
import java.io.*;
import java.net.*;
import primeService.util.Debug;

/*
 * Base Class for Client Program
 */
public class ClientDriver{

	private String hostName;
	private String portString;
	
	/*
	 * Constructor for Client
	 * @param hostIn Name of Host
	 * @param portIn Number of Port (String Format)
	 */
	public ClientDriver(String hostIn,String portIn){
		Debug.printMessage(0);
		this.hostName = hostIn;
		this.portString = portIn;
	}

	/*
	 * Main Driver for Client Program
	 */
	public void clientMain() throws IOException {
		int portNumber = Integer.parseInt(portString);
		
		try(
			Socket clientSocket = new Socket(hostName, portNumber);
			PrintWriter outStream = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		){
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String serverIn;
			String clientIn;
			
			while(null != (serverIn = inStream.readLine())){
				serverIn = serverIn.replaceAll("&&&","\n");
				System.out.println("Server: " + serverIn);
				if(serverIn.equals("Bye!")){
					break;
				}
				clientIn = stdIn.readLine();
				if(null != clientIn){
					System.out.println("Client: " + clientIn);
					outStream.println(clientIn);
				}
			}
		}
		catch(UnknownHostException e){
			System.err.println("Host Unknown: " + hostName);
			System.exit(1);
		}
		catch(IOException e){
			System.err.println("Couldn't Connect to " + hostName);
			System.exit(1);
		}
	}
}
