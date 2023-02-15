import java.io.*;
import java.util.*;
import java.net.*;




public class clientSocket{
      public static void main(String args[]){
           try{
           		DatagramSocket client = new DatagramSocket();
           		String[] clients = {"127.0.0.1","127.0.0.1","127.0.0.1","127.0.0.1"};
            	BufferedReader s = new BufferedReader(new InputStreamReader(System.in));

            
            	byte[] sendbyte = new byte[1024];
            	byte[] recievebyte = new byte[1024];

            	System.out.println("Enter data");
            	String str = s.readLine();
            	sendbyte = str.getBytes();
              for(int i = 0;i<clients.length;i++){
                InetAddress add = InetAddress.getByName(clients[i]);
                DatagramPacket sender = new DatagramPacket(sendbyte,sendbyte.length,add,1309);
                client.send(sender);
              }	
            	client.close();

           }catch(Exception e){
                  System.out.println(e.getMessage());
           }



      }
}