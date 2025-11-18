package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadSoc extends Thread{
    
    private Socket s;
    private String password;
    private ArrayList<Zone> zone;
    private boolean accesso;

    ThreadSoc(Socket s){
        this.s = s;
        password = "12345";
        zone = new ArrayList();
        accesso = false;
    }

    public void run() {
        
        try {

            iniziallizzaZone();

            while (true) {

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
                String myString = in.readLine();
                String[] parti = myString.split(" ", 3);

                if(accesso){

                    if(pinValido(parti[0])){

                        switch(parti[1]){
                            case "g":

                                break;
                            case "STATUS":

                                break;
                            case "DIS":

                                break;
                            case "EXIT":
                                out.println("BYE");
                                s.close();
                                break;
                            default:
                                out.println("ERROR");
                        }


                    }
                    else {
                        out.println("ERROR");
                    }

                }
                 else {

                    if(parti[0].equals("PIN") && pinValido(parti[1])){
                        accesso = true;
                    }
                     else {
                        out.println("ERROR");
                     }

                 }

                
                
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private boolean pinValido(String pin){
        return password.equals(pin);
    }

    private void iniziallizzaZone(){
        Zone a = new Zone("A");
        Zone b = new Zone("B");
        Zone c = new Zone("C");
        Zone d = new Zone("D");

        zone.add(a);
        zone.add(b);
        zone.add(c);
        zone.add(d);
    }

    private boolean comandoCompleto(String parte){

        String[] com = {"PIN", "g", "STATUS", "DIS"};

        for (String c : com) {
            if(parte.equals(c)){
                return true;
            }
        }

        return false;

    }

}
