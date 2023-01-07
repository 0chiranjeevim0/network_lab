import java.io.*;
import java.net.*;

public class serverSocket{
	private static DataOutputStream dataOutputStream = null;
	private static DataInputStream dataInputStream = null;


	public static void main(String[] args){
		try(ServerSocket serverSocket = new ServerSocket(5000)){
			//starting server on LOCALHOST PORT 5000
			System.out.println("Listening on Port:5000");
			Socket clientSocket = serverSocket.accept();
			//waiting for client to connect
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());


			String message;
			while(true){
				message = dataInputStream.readUTF();
				System.out.println(message);
				if(message.equalsIgnoreCase("exit()")){
					break;
				}
			}
			//closing the socket connection when the loop breaks
			clientSocket.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}