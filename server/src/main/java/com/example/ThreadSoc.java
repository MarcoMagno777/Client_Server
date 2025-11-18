package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadSoc extends Thread{
    
    private Socket s;
    private static String password = "";
    private static ArrayList<Zona> zone;
    private boolean accesso;

    ThreadSoc(Socket s){
        this.s = s;
        if(password.isEmpty()){
            password = "12345";
        }
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

                                if(comandoCompleto(parti[2])){

                                    out.println(inserimento(parti[2])); 

                                }
                                 else {
                                    out.println("ERROR");
                                 }

                                break;
                            case "STATUS":
                                out.println(stampaZone());
                                break;
                            case "DIS":
                                 disattiva();
                                 out.println("OFF");
                                break;
                            case "EXIT":
                                out.println("BYE");
                                s.close();
                                break;
                            case "SETPIN":
                                 if(pinNuovoValido(parti[2])){
                                    out.println("OK");
                                 }
                                  else {
                                    out.println("ERROR");
                                  }
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
                        out.println("WELCOME");
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

    private boolean pinNuovoValido(String parte){

        if(!parte.isEmpty() && parte.length() == 5){
            password = parte;
            return true;
        }

        return false;

    }

    private boolean pinValido(String pin){
        return password.equals(pin);
    }

    private String inserimento(String parte){

        for(Zona z : zone){
            if(z.getNome().equals(parte)){
                if(z.getStato()){
                    return "ALREADY";
                }
                 else {
                    z.setStato(true);
                    return "ON";
                 }
            }
        }

        return "ERROR";

    }

    private void disattiva(){

        for(Zona z : zone){
            z.setStato(false);
        }

    }

    private String stampaZone(){

        String status = "";

        for(Zona z : zone){
            status += z.getNome();
            if(z.getStato()){
                status += " ON, ";
            }
             else {
                status += " OFF, ";
             }
        }
        return status;
    }

    private void iniziallizzaZone(){
        Zona a = new Zona("A");
        Zona b = new Zona("B");
        Zona c = new Zona("C");
        Zona d = new Zona("D");

        zone.add(a);
        zone.add(b);
        zone.add(c);
        zone.add(d);
    }

    private boolean comandoCompleto(String parte){

        for(Zona z : zone){
            if(z.getNome().equals(parte)){
                return true;
            }
        }

        return false;

    }

}
