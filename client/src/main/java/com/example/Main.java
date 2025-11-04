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
        int p = 2;

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        while(true){

            String risposta = in.readLine();
            
            if(risposta.equals("WAIT")){
                System.out.println("Sei il primo giocatore aspetta");
                p = 1;
            }
             else if(risposta.equals("READY") && p == 1){
                System.out.println("Sei il primo giocatore inserisci una mossa");
                out.println(sc.nextLine());
            }
             else if(risposta.equals("READY")){
                System.out.println("Sei il secondo giocatore");
             }
             else {

                String[] cells = risposta.split(",");

                if(cells[8].equals("W")){
                    System.out.println("Hai vinto");
                }
                else if(cells[8].equals("L")){
                    System.out.println(risposta);
                }
                else if(risposta.equals("KO")){
                    System.out.println("Inserisci un'altra mossa (mossa non valida)");
                    out.println(sc.nextLine());
                } else {
                    System.out.println("Inserisci mossa ");
                    out.println(sc.nextLine());
                }

             }

            

        }
        
    }
}