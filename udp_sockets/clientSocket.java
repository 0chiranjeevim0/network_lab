import java.io.*;
import java.util.*;
import java.net.*;




public class clientSocket{
      public static void main(String args[]){
           try{
           		DatagramSocket client = new DatagramSocket();
            	InetAddress add = InetAddress.getByName("127.0.0.1");
            	BufferedReader s = new BufferedReader(new InputStreamReader(System.in));

            
            	byte[] sendbyte = new byte[1024];
            	byte[] recievebyte = new byte[1024];

            	System.out.println("Enter DOMAIN NAME");
            	String str = s.readLine();
            	sendbyte = str.getBytes();

            	DatagramPacket sender = new DatagramPacket(sendbyte,sendbyte.length,add,1309);
            	DatagramPacket reciever = new DatagramPacket(recievebyte,recievebyte.length);
            	client.send(sender);


            	client.receive(reciever);

            	String ip = new String(reciever.getData());
            	System.out.println("IP ADDRESS:"+ip.trim());

            	client.close();

           }catch(Exception e){
                  System.out.println(e.getMessage());
           }



      }
}