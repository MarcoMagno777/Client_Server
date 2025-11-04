package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Tris {
    
    String array[] = new String[9];
    PrintWriter p1, p2;
    Socket s1, s2;
    ServerSocket ss;

    Tris(PrintWriter p1, PrintWriter p2, Socket s1, Socket s2, ServerSocket ss){
        this.p1 = p1;
        this.p2 = p2;
        this.s1 = s1;
        this.s2 = s2;
        this.ss = ss;
    }

    public boolean inserisciMossa(BufferedReader in, String p) {

        int mossa = -1;
    
        try {
    
            if (!(s1.isClosed() || s2.isClosed())) {
    
                String mossaInput = in.readLine(); 
                
                try {
                    mossa = Integer.parseInt(mossaInput);
                } catch (NumberFormatException e) {
                    
                    if (p.equals("1")) {
                        p1.println("KO");  
                    } else {
                        p2.println("KO"); 
                    }
                    return false;
                }
    
                if (mossaValida(mossa)) {
                    if (p.equals("1")) {
                        array[mossa] = "1";
    
                        if (controlloPareggio()) {
                            p1.println("P");
                            p2.println("P");
                            ss.close();  
                        } else if (controlloVittoria().equals("1")) {
                            p1.println("W");
                            p2.println(stampaSta("L"));
                            ss.close();  
                        } else {
                            p1.println("OK");
                            p2.println(stampaSta(""));  
                        }
                    } else {
                        array[mossa] = "2";
    
                        if (controlloPareggio()) {
                            p1.println("P");
                            p2.println("P");
                            ss.close();  
                        } else if (controlloVittoria().equals("2")) {
                            p2.println("W");
                            p1.println(stampaSta("L"));
                            ss.close(); 
                        } else {
                            p2.println("OK");
                            p1.println(stampaSta("")); 
                        }
                    }
                    
                } else {
                    
                    if (p.equals("1")) {
                        p1.println("KO");  
                    } else {
                        p2.println("KO");  
                    }
                    return false;
                }
    
            } else {
                
                p1.println("DISCONNECTED");
                p2.println("DISCONNECTED");
                ss.close();  
            }
    
        } catch (IOException e) {
            e.printStackTrace();  
        }
    
        return true;
    }
    

    public String stampaSta(String stato){

        String griglia = "";

        for(int i = 0;i < array.length; i++){
            griglia += array[i] + ",";
        }

        if(!stato.equals("")){
            griglia += stato;
        }

        return griglia;
    }

    public String controlloVittoria() {
         
        int[][] combinazioniVittoria = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] combo : combinazioniVittoria) {
            String a = array[combo[0]];
            String b = array[combo[1]];
            String c = array[combo[2]];

            if (!a.equals("0") && a.equals(b) && b.equals(c)) {
                return a;
            }
        }

        return "0";
    }


    public boolean controlloPareggio() {

        for (String cella : array) {
            if (cella.equals("0")) {
                return false; 
            }
        }

        return true;
    }
    

    public boolean mossaValida(int mossa){

        boolean isValid = true;

        if(mossa < 0 || mossa > 8){
            isValid = false;
        }

        if(array[mossa].equals("1") || array[mossa].equals("2")){
            isValid = false;
        }

        return isValid;

    }

    public void iniziallizaArray(){

        for(int i = 0;i < 9; i++){
            array[i] = "0";
        }

    }

}
