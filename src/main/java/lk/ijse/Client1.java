package lk.ijse;

import java.io.*;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",3001);
        while (true) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(() ->{
                try {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    System.out.println(dataInputStream.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            dataOutputStream.writeUTF(bufferedReader.readLine());
            dataOutputStream.flush();

        }
    }
}
