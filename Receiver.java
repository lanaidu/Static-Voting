import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;


public class Receiver implements Runnable {

	int portNumber; 
	ServerSocket serverSocket;
	String WhoAmI = null;
	
	public Receiver(int portNumber) {
		
		this.portNumber= portNumber; 
		try {
			serverSocket = new ServerSocket(portNumber); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while(true){
			
		Socket clientSocket;
		try {
			
			System.out.println("Receiver Listening...");
			clientSocket = serverSocket.accept();
			System.out.println(" Receiver Accepted..."+clientSocket.getInetAddress());
		
		PrintWriter out =
	            new PrintWriter(clientSocket.getOutputStream(), true);                   
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(clientSocket.getInputStream()));
	
		System.out.println("RECEIVED MODE IS"+ in.readLine());
		
		String [] trial= ManagementFactory.getRuntimeMXBean().getName().split("@");
		WhoAmI=trial[1];
		
		out.println("RECEIVER RESPONSE from"+WhoAmI);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
