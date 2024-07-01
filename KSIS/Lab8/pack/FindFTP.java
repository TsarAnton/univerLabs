package pack;

import java.net.*;
import java.io.*;

import java.util.Scanner;

public class FindFTP {

    private static final int portFTP = 21;
    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Start IP (вместо точек ставить /, потому что у меня split по точке не делался)");

        String startStringIP = scanner.next();

        String[] buffArray1 = startStringIP.split("/");

        int currentIP[] = new int[buffArray1.length];
        for(int i = 0; i < currentIP.length; i++) {
            currentIP[i] = Integer.parseInt(buffArray1[i]);
        }

        System.out.println("End IP");

        String endStringIP = scanner.next();

        scanner.close();

        String[] buffArray2 = endStringIP.split("/");

        int endIP[] = new int[buffArray2.length];
        for(int i = 0; i < endIP.length; i++) {
            endIP[i] = Integer.parseInt(buffArray2[i]);
        }

        // int currentIP[] = {127, 0, 0, 1};
        // int endIP[] = {127, 0, 0, 10};

        try {
            for(int i = 0; i < currentIP.length; i++) {
                while(true) {
                    if(currentIP[i] < endIP[i]) {
                        currentIP[i]++;
                        StringBuilder currentIpString = new StringBuilder();
                        currentIpString.append(currentIP[0]).append(".").append(currentIP[1]).append(".").append(currentIP[2]).append(".").append(currentIP[3]);
                        InetAddress currentIpAddress;
                        currentIpAddress = InetAddress.getByName(currentIpString.toString());
                        Socket socket = new Socket(currentIpAddress, portFTP);
                        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String str = bufRead.readLine();
                        if(str.contains("FTP")) {
                            System.out.println("Found FTP server: " + currentIpString);
                        }
                        socket.close();
                        bufRead.close();
                    } else {
                        break;
                    }
                }
            }
        } catch(UnknownHostException e) {
            System.out.println("Неизвестный хост");
        } catch(ConnectException e) {
            System.out.println("Ошибка подключения");
        }
    }
}
