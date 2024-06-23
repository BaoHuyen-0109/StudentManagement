package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("RECEIVED: " + sentence);

                // Handle received data and send response if necessary
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

