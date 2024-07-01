package pack;

import java.net.*;
import java.io.*;

import java.util.Scanner;

public class Client
{
    private  static final int serverPort = 6666;
    private  static final String localhost  = "127.0.0.1";

    public static void main(String[] args) {

        Socket socket = null;
        Scanner scanner = new Scanner(System.in, "cp866");
        try{
            try {
                System.out.println("Подключение к серверу...\n\t" +
                             "(IP-адрес " + localhost + 
                             ", порт " + serverPort + ")");
                InetAddress ipAddress;
                ipAddress = InetAddress.getByName(localhost);
                socket = new Socket(ipAddress, serverPort);
                System.out.println("Подключение установлено");
                System.out.println(
                        "\tЛокальный порт = " + 
                               socket.getLocalPort() + 
                        "\n\tАдрес хоста = " + 
                               socket.getInetAddress().getHostAddress());

                InputStream  sin  = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                DataInputStream  in ;
                DataOutputStream out;
                in  = new DataInputStream (sin );
                out = new DataOutputStream(sout);

                System.out.println(in.readUTF());
                String line = null;
                boolean con = true;
                boolean attack = false;
                boolean loose = false;
                boolean nextEnd = false;
                while (con) {
                    if(nextEnd) {
                        break;
                    }
                    if(!attack) {
                        System.out.print("Команда: ");
                        line = scanner.nextLine();
                    } else {
                        line = "attack";
                    }
                    if(loose) {
                        line = "loose";
                        nextEnd = true;
                    }
                    if(line.equals("выход")) {
                        out.writeUTF(line);
                        out.flush();
                        line = in.readUTF();
                        System.out.println(line);
                        break;
                    }
                    out.writeUTF(line);
                    out.flush();
                    line = in.readUTF();
                    if(line.startsWith("attack")) {
                        attack = true;
                        line = line.substring(6);
                    } else if(line.startsWith("win")) {
                        line = line.substring(3);
                        attack = false;
                    } else if(line.startsWith("loose")) {
                        loose = true;
                        line = line.substring(5);
                    } 
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            scanner.close();
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

