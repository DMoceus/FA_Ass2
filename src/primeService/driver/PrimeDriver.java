package primeService.driver;
import primeService.client.ClientDriver;
import primeService.server.ServerDriver;
import java.io.IOException;
import primeService.util.Debug;

/*
 * Directs Execution to either Server or Client mains
 * Pretty much a dummy class that was shoehorned in
 * to fit professor's requirements.
 */
public class PrimeDriver{
	public static void main(String[] args){
		try{
			if(args[0].equalsIgnoreCase("server")){
				Debug.setDebug(Integer.parseInt(args[2]));
				ServerDriver driver = new ServerDriver(args[1]);
				driver.serverMain();
			}
			else if(args[0].equalsIgnoreCase("client")){
				Debug.setDebug(Integer.parseInt(args[3]));
				ClientDriver driver = new ClientDriver(args[1],args[2]);
				driver.clientMain();
			}
			else{
				System.out.println("No.");
			}
		}
		catch(IOException e){
			System.err.println("Bad.");
		}
	}
}
