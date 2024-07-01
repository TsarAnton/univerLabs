package pack;

import java.net.*;
import java.io.*;

import java.util.Scanner;

public class Runner{

    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите URL");
        String url = scanner.next();

        System.out.println("Введите порт");
        int port = scanner.nextInt();

        scanner.close();

        String path[] = url.split("/");

        StringBuilder pageBuff = new StringBuilder();

        String host = path[2];
        if(path.length == 3) {
            pageBuff.append("/");
        } else {
            for(int i = 3; i < path.length; i++) {
                pageBuff.append("/").append(path[i]);
            }
        }

        String page = pageBuff.toString();

        try {
            Socket client = new Socket(host, port);
            
            PrintWriter wtr = new PrintWriter(client.getOutputStream());
            wtr.println("GET " + page + " HTTP/1.1");
            wtr.println("Host: " + host);
            wtr.println("");
            wtr.flush();

            BufferedReader bufRead = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String outStr;

            Writer writer = new FileWriter("index.html");
            BufferedWriter buffWriter = new BufferedWriter(writer);
            
            String statusLine = bufRead.readLine();
            if(statusLine.contains("20")) {
                System.out.println("Идет сохранение страницы...");

                while((outStr = bufRead.readLine()) != null){
                    if(outStr.equals("<!DOCTYPE html>") ||
                        outStr.equals("<html>") ||
                        outStr.equals("<HTML>") ||
                        outStr.equals("<!DOCTYPE HTML>")) {
                            
                        buffWriter.write(outStr);
                        buffWriter.write("\n");
                        break;
                    }
                }

                while((outStr = bufRead.readLine()) != null){
                        buffWriter.write(outStr);
                        buffWriter.write("\n");
                        if(outStr.equals("</http>") || outStr.equals("</HTTP>")) {
                            break;
                        }
                }

                System.out.println("Сохранение страницы завершено");
            } else if(statusLine.contains("30")) {
                String status = statusLine.substring(9, 12);
                System.out.println("Перенаправление: код " + status);
            } else if(statusLine.contains("40") || statusLine.contains("41")) {
                String status = statusLine.substring(9, 12);
                System.out.println("Ошибка клиента: код " + status);
            } else if(statusLine.contains("50")) {
                String status = statusLine.substring(9, 12);
                System.out.println("Ошибка сервера: код " + status);
            } 

            buffWriter.close();
            bufRead.close();
            client.close();
        } catch(FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch(UnknownHostException e) {
            System.out.println("Неизвестный хост");
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода");
        } 
    }

}