/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.logic;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Luca
 */
public class ChatManager {
    
    private static ChatManager _instance = null;
    private List<MessageListener> messageListeners = new ArrayList<>();
    
    public static ChatManager getInstance() {
        if (_instance == null) {
            _instance = new ChatManager();
        }
        return _instance;
    }
    
    private ChatManager() {
        super();
    }
    
    public void addMessageListener(MessageListener listener){
        this.messageListeners.add(listener);
    }
    
    public void newMessage(String user, String message){
        for (MessageListener messageListener : messageListeners) {
            messageListener.newMessage(user, message);
        }
    }
    
    
    
}
