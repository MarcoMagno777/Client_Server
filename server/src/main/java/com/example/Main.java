package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        System.out.println("Connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        while (true) {
            String myString = in.readLine();

            if(myString.equals("!")){
                ss.close();
                break;
            }

            String convertita = myString.toUpperCase();
            System.out.println(convertita);
            out.println(convertita);
            
        }
        
        
    }
}