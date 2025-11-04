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
        int giocatore = 1;
  
        Socket s1 = ss.accept();
        System.out.println("Connected.");

        BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
        PrintWriter out1 = new PrintWriter(s1.getOutputStream(), true);

        out1.println("WAIT");

        Socket s2 = ss.accept();
        System.out.println("Connected.");

        BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);

        Tris t = new Tris(out1, out2, s1, s2, ss); 

        t.iniziallizaArray();

        out2.println("READY");
        out1.println("READY");

        while(true){

            boolean valido = false;

            if(giocatore == 1){

                while(!valido){
                    valido = t.inserisciMossa(in1, "1");
                }
                
                giocatore = 2;
            }
             else{

                while(!valido){
                    valido = t.inserisciMossa(in2, "2");
                }
                
                giocatore = 1;
             }

        }


        

        
            

    }

}