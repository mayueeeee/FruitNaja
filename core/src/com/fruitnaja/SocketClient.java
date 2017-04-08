package com.fruitnaja;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by nutno on 6/4/2560.
 */
public class SocketClient {
    private Socket socket;

    public SocketClient(final String ip){
       Thread client = new Thread(){
           public void run() {
               try{
                   socket = new Socket(ip, 1025);
                   System.out.println("Socket connected to "+ ip);
                   DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                   // Send first message
                   dOut.writeByte(1);
                   dOut.writeUTF("This is the first type of message.");
                   dOut.flush(); // Send off the data

               }catch (Exception e){
                   System.out.println("Can't register new client");
                   //e.printStackTrace();
               }

           }
       };
       client.start();

    }

    public void updateScore(int score){

    }

    public void updateStatus(){

    }
}
