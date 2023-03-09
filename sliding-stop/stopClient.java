import java.io.*;
import java.net.*;

public class stopClient {

    private static final int PACKET_SIZE = 1024;
    private static final int SERVER_PORT = 8888;
    private static final String SERVER_HOSTNAME = "localhost";
    private static final int TIMEOUT_MS = 2000;
    private static final int MAX_TRIES = 3;

    public static void main(String[] args) throws IOException {

        // Create socket and streams for communication
        Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        // Set timeout on socket to handle lost packets
        socket.setSoTimeout(TIMEOUT_MS);

        byte[] packet = new byte[PACKET_SIZE];
        DatagramPacket datagramPacket = new DatagramPacket(packet, packet.length);

        int sequenceNumber = 0;
        int ackNumber = 0;

        while (true) {
            // Construct the packet to be sent
            String message = "Packet " + sequenceNumber;
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getByName(SERVER_HOSTNAME), SERVER_PORT);
            outputStream.write(data);

            // Wait for acknowledgement from server
            int tries = 0;
            while (true) {
                try {
                    inputStream.read(packet);
                    String ackMessage = new String(packet).trim();
                    if (ackMessage.equals("ACK " + sequenceNumber)) {
                        ackNumber = sequenceNumber;
                        System.out.println("Received acknowledgement for packet " + sequenceNumber);
                        break;
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout waiting for acknowledgement for packet " + sequenceNumber);
                    tries++;
                    if (tries >= MAX_TRIES) {
                        System.out.println("Reached max tries for packet " + sequenceNumber + ", resending packet...");
                        break;
                    }
                    // Resend packet if acknowledgement is not received within timeout period
                    outputStream.write(data);
                }
            }

            if (ackNumber == sequenceNumber) {
                // Move on to the next sequence number
                sequenceNumber = (sequenceNumber + 1) % 2;
            }

            // Exit the loop if all packets have been sent and acknowledged
            if (sequenceNumber == 0 && ackNumber == 1) {
                break;
            }
        }

        // Close the socket and streams
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
