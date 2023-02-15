import java.io.*;
import java.net.*;
import java.util.*;
class serverSocket
{
            public static void main(String args[])
            {
            try
            {
                        DatagramSocket server=new DatagramSocket(1309);
                        while(true)
                        {
                                    byte[] sendbyte=new byte[1024];
                                    byte[] receivebyte=new byte[1024];
                                    DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
                                    server.receive(receiver);

                                    String str=new String(receiver.getData());
                                    String s=str.trim();

                                    System.out.println(s);
                                    


                        }
            }
            catch(Exception e)
            {
                        System.out.println(e);
            }
            }
}