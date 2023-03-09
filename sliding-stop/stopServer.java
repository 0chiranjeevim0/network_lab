import java.io.*;
import java.net.*;

public class stopServer {

    private static final int PACKET_SIZE = 1024;
    private static final int SERVER_PORT = 8888;
    private static final int TIMEOUT_MS = 2000;

    public static void main(String[] args) throws IOException {

        // Create server socket and accept connection from client
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // Set timeout on socket to handle lost packets
        socket.setSoTimeout(TIMEOUT_MS);

        byte[] packet = new byte[PACKET_SIZE];
        DatagramPacket datagramPacket = new DatagramPacket(packet, packet.length);

        int sequenceNumber = 0;

        while (true) {
            // Wait for packet from client
            try {
                inputStream.read(packet);
                String message = new String(packet).trim();
                if (message.equals("Packet " + sequenceNumber)) {
                    System.out.println("Received packet " + sequenceNumber);
                    // Construct and send acknowledgement packet
                    String ackMessage = "ACK " + sequenceNumber;
                    byte[] data = ackMessage.getBytes();
                    DatagramPacket ackPacket = new DatagramPacket(data, data.length, InetAddress.getByName(socket.getInetAddress().getHostName()), socket.getPort());
                    outputStream.write(data);
                    sequenceNumber = (sequenceNumber + 1) % 2;
                }
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout waiting for packet...");
            }

            // Exit the loop if all packets have been received and acknowledged
            if (sequenceNumber == 0) {
                break;
            }
        }

        // Close the socket and streams
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
