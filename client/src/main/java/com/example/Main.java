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
        boolean connesso = s.isConnected();
        Scanner sc = new Scanner(System.in);
        boolean accesso = false;
        String password = "";

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        
        do{

            while(!accesso){
                System.out.println("Inserisci la password");
                password = sc.nextLine();
                out.println("PIN " + password);
                accesso = in.readLine().equals("WELCOME");
            }

            System.out.println("Inserisci un comando :");
            System.out.println("g + zona da attivare : attiva una zona");
            System.out.println("STATUS : visualizzare lo stato delle zone");
            System.out.println("DIS : disattivare tutte le zone");
            System.out.println("SETPIN + nuovo pin (5 numeri) : cambia il pin");
            System.out.println("EXIT : per uscire");

            out.println(password + " " + sc.nextLine());

            String risposta = in.readLine();

            switch(risposta){
                case "ALREADY":
                    System.out.println("Zona già attivata");
                    break;
                case "ON":
                    System.out.println("Zona attivata");
                    break;
                case "OFF":
                    System.out.println("Disattivate tutte le zone");
                    break;
                case "BYE":
                    System.out.println("Disconesso");
                    break;
                case "ERROR":
                    System.out.println("Errore nel comando");
                    break;
                default:
                    System.out.println(risposta);
            }

            connesso = s.isConnected();

        }while(connesso);
        
    }
}