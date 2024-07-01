package pack;

import java.io.*;
import java.net.*;

import pack.map.Map;

public class Runner {
    public static void main(String[] ar) {

        Map.initialize();
        new Tick().initialize();
        Magazine.update();
        ServerSocket srvSocket = null;

        try {
            try {
                int i = 0;
                InetAddress ia = InetAddress.getByName("localhost");
                srvSocket = new ServerSocket(6666, 0, ia);

                System.out.println("Сервер запущен");

                while(true) {
                    Socket socket = srvSocket.accept();
                    System.err.println("Подключен новый клиент");
                    new Server().setSocket(i++, socket);
                }
            } catch(Exception e) {
                System.out.println("Exception : " + e);
            }
        } finally {
            try {
                if (srvSocket != null)
                    srvSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
