package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_server_test {

	public static void main(String[] args){

		int portNumber = 10;
		String response = " ";

		do{
			System.out.println("Awaiting client...");
			try(
					ServerSocket serverSocket = new ServerSocket(portNumber);
					Socket clientSocket = serverSocket.accept();
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			){
				//where all the magic happens
				System.out.println("Connected");
				
				while(!(response = in.readLine()).equals("exit")){
					System.out.println(".: " + response);
				}


			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			catch (NullPointerException e){
				//e.printStackTrace();
				response = "b";
			}

			System.out.println("disconnected");

		}while(!response.equals("exit"));

		System.out.println("terminating server");

	}

}
