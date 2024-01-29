package lk.ijse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
       ServerSocket serverSocket;
       ArrayList<Socket> sockets = new ArrayList<>();
        System.out.println("Server Started!!");
       serverSocket = new ServerSocket(3001);
       while (true){
           System.out.println("Listning!!!");
           Socket socket = serverSocket.accept();
           System.out.println("Client Connected!!!");
           sockets.add(socket);
           new Thread(() ->{
               try {
                   DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                   String msg = dataInputStream.readUTF();
                   sendMsg(socket,msg,sockets);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }).start();
       }

    }

    private static void sendMsg(Socket socket, String msg, ArrayList<Socket> sockets) throws IOException {
        for (Socket socket1 : sockets) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket1.getOutputStream());
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        }
    }
}
