package Testconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import testconnect.BarcodeGenerator;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;
    BarcodeGenerator generator = new BarcodeGenerator();

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port " + port);
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local IP Address: " + localHost.getHostAddress());

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Xử lý luồng dữ liệu tại đây, ví dụ: đọc và ghi dữ liệu
                // Xử lý luồng dữ liệu tại đây, ví dụ: đọc và in ra dữ liệu được gửi từ client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    // Thêm logic xử lý dữ liệu ở đây
                    generator.generateAndSaveBarcode(inputLine, inputLine);

                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int port = 12245;  // Port của server
        Server server = new Server(port);
        server.start();
    }
}
