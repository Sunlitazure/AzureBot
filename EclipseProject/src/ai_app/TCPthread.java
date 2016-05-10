package ai_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class TCPthread extends Thread{
	
	private int portNumber = 0;
	protected BlockingQueue<byte[]> queueIn = null;
	protected BlockingQueue<String> queueOut = null;
	private boolean end = false;
	private boolean connected = false;
	private boolean start = false;
	
	
	public TCPthread(int port, BlockingQueue<byte[]> queueIn, BlockingQueue<String> queueOut){
		
		portNumber = port;
		this.queueIn = queueIn;
		this.queueOut = queueOut;

	}
	
	
	public void run(){
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		byte[] message = new byte[3];
		
		do{
			connected = false;
			System.out.println("Awaiting client...");
			try{
				serverSocket = new ServerSocket(portNumber);
				serverSocket.setReuseAddress(true);
				clientSocket = serverSocket.accept();
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			}			catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			catch (NullPointerException e){
				//e.printStackTrace();
			}
			
			
			try{
				//where all the magic happens
				System.out.println("Connected");
				end = false;
				connected = true;
				
				
				while(!start){
					System.out.print("");
				}
				
				
				while(!end && !clientSocket.isClosed()){
					clientSocket.getOutputStream().write(queueIn.take());
				}


			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("client disconnected");
				try {
					serverSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(!end);

		
		
		
		System.out.println("terminating server");
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Socket shut down.");
		
		this.interrupt();

	}
	
	public void setEnd(){
		end = true;
	}
	
	public boolean isConnected(){
		return connected;
	}
	
	public void setStart(){
		start = true;
	}
	

}
