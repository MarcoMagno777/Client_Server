package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadSoc extends Thread{
    
    private Socket s;

    ThreadSoc(Socket s){
        this.s = s;
    }

    public void run() {
        
        try {

            while (true) {

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
                String myString = in.readLine();
    
                if(myString.equals("!")){
                    break;
                }
    
                String convertita = myString.toUpperCase();
                System.out.println(convertita);
                out.println(convertita);
                
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
