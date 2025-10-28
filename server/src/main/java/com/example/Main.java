package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        String array[] = new String[9];

        ServerSocket ss = new ServerSocket(3000);
  
        Socket s1 = ss.accept();
        System.out.println("Connected.");

        BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
        PrintWriter out1 = new PrintWriter(s1.getOutputStream(), true);

        out1.println("wait");

        Socket s2 = ss.accept();
        System.out.println("Connected.");

        BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);

        out2.println("ready");
        out1.println("ready");

        int mossa = in1.read();
        array[mossa] = "x";
            

    }
}