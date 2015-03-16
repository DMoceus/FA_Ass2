package primeService.server;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import primeService.socket.PrimeServerSocket;
import primeService.util.Debug;

/*
 * Class to manage all worker threads for Server
 */
public class ServerThreadSpawner extends Thread{
	
	private AllPrimeQueries history;
	public ServerSocket socket;
	private ArrayList<PrimeServerSocket> threadList;

	/*
	 * Constructor for ServerThreadSpawner
	 * @param socketIn Socket to open threads on
	 * @param histIn Shared AllPrimeQueries Object
	 */
	public ServerThreadSpawner(ServerSocket socketIn, AllPrimeQueries histIn){
		super("PrimeServerThreadSpawner on socket: " + Integer.toString(socketIn.getLocalPort()));
		Debug.printMessage(0);
		this.history = histIn;
		this.socket = socketIn;
		this.threadList = new ArrayList<PrimeServerSocket>();
	}
	
	private void joinThreads(){
		for(PrimeServerSocket t : threadList){
			try{
				t.closeSocket();
				t.join();
			}
			catch(InterruptedException e){}
		}
	}
	
	/*
	 * Active Thread Function for ServerThreadSpawner
	 */
	public void run(){
		while(!socket.isClosed()){
			try{
				PrimeServerSocket clientThread = new PrimeServerSocket(socket.accept(),history);
				clientThread.start();
				threadList.add(clientThread);
			}
			catch(IOException e){
			}
		}
		joinThreads();
		
	}
}
