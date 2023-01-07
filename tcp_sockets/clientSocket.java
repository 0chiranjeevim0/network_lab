import java.io.*;
import java.net.*;
import java.util.Scanner;



public class clientSocket{
	static DataOutputStream dataOutputStream = null;
	static Scanner scanner = new Scanner(System.in);
	
	static void readData(){
	 	try{
	 		FileReader file = new FileReader("./text.txt");
	 		while(true){
				String st;
				BufferedReader br = new BufferedReader(file);
				while((st = br.readLine()) != null){
					dataOutputStream.writeUTF(st);
				}
				break;
			}
	 	}catch(Exception e){
	 		System.out.println(e.getMessage());
	 	}
	 }

	public static void main(String args[])throws IOException{
		
		try(Socket socket = new Socket("localhost",5000)){
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			int option = 1;
			Scanner s = new Scanner(System.in);

			while(true){
				if(option == 1){
					readData();
				}else{
					dataOutputStream.writeUTF("exit()");
					break;
				}
				System.out.print("Enter 1 To Resend the text / Enter any number to Exit:");
				option = s.nextInt();

			}
			

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}