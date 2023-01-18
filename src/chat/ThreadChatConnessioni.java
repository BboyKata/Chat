/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.net.*;
import java.io.*;

/**
 *
 * @author Matteo
 */
public class ThreadChatConnessioni implements Runnable {

    private ThreadGestioneServizioChat gestoreChat;
    private Socket client = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    Thread me;

    public ThreadChatConnessioni(ThreadGestioneServizioChat gestoreChat, Socket client) {
        this.gestoreChat = gestoreChat;
        this.client = client;
        
        try{
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(this.client.getOutputStream(),true);
        }catch(Exception e){
            output.println("Errore nella lettura");
        }
        me = new Thread(this);
        me.start();
    }
    
    public void run(){
        while(true){
             try{
                 String mex = null;
                 //rimango in attesa dei messaggi mandati dal client
                 while((mex = input.readLine()) ==null){
                 }
                 
                 //invoco il metodo del gestoreChat per ripetere a tutti il messaggio ricevuto
                 gestoreChat.spedisciMessaggio(mex);
             }catch(Exception e){
                 output.println("Errore nella spedizione del messaggio a tutti.");
             }
        }
    }
    
    public void spedisciMessaggioChat(String messaggio){
        try{
            output.println(messaggio);
        }catch(Exception e){
            output.println("Errore nella spedizione del singolo messaggio");
        }
    }
}
