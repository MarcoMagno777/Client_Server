package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket s = new Socket("localhost", 3000);
        System.out.println("Connected.");
        Scanner sc = new Scanner(System.in);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        while(true){

            System.out.println("Inserisci frase da converitre in maiuscolo");
            String frase = sc.nextLine();
            if(frase.equals("exit")){
                out.println("!");
                s.close();
                break;
            }
            out.println(frase);
            String risultato = in.readLine();
            System.out.println("Stringa convertita: " + risultato);

        }
        
    }
}