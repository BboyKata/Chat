/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.net.*;
import java.awt.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Matteo
 */
public class ThreadGestioneServizioChat implements Runnable{
    private int nrMaxConnessioni;
    private List lista;
    private ThreadChatConnessioni[] listaConnessioni;
    Thread me;
    private ServerSocket serverChat;

    public ThreadGestioneServizioChat(int numeroMaxConnessioni, List lista) {
        this.nrMaxConnessioni = nrMaxConnessioni -1;
        this.lista = lista;
        this.listaConnessioni = new ThreadChatConnessioni[this.nrMaxConnessioni];
        me = new Thread(this);
        me.start();
    }
    

    @Override
    public void run() {
        boolean continua = true;
        //instanzio la connessione del server per la chat
        try{
            serverChat = new ServerSocket(6789);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Impossibile instanziare il server");
            continua = false;
        }
        if(continua){
            //accetto le connessioni chat
            try{
                for (int xx = 0; xx < nrMaxConnessioni; xx++) {
                    Socket tempo = null;
                    tempo = serverChat.accept();
                    listaConnessioni[xx] = new ThreadChatConnessioni(this,tempo);
                }
                serverChat.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Impossibile instanziare server chat");
            }
        }
    }
    
    public void spedisciMessaggio(String mex){
        //scrivo il mex nella mia lista
        lista.add(mex);
        lista.select(lista.getItemCount()-1);
        //mando il messaggio agli altri
        for (int xx = 0; xx < nrMaxConnessioni; xx++) {
            if(listaConnessioni[xx]!=null){
                listaConnessioni[xx].spedisciMessaggioChat(mex);
            }
        }
    }
}
