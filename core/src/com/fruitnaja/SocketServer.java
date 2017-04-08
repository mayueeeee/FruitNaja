package com.fruitnaja;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by nutno on 6/4/2560.
 */
public class SocketServer {

    public SocketServer()  {
        //Start socket server
        Thread network = new Thread(){
            public void run(){
                //System.out.println("Thread Running");
                final int PORT = 1025;
                try {
                    System.out.println("Socket server is running at "+ Inet4Address.getLocalHost().getHostAddress()+":"+PORT);
                    ServerSocket listener = new ServerSocket(PORT);
                    //listener.accept();
                    Socket socket = listener.accept();

                    DataInputStream dIn = new DataInputStream(socket.getInputStream());

                    boolean done = false;
                    while(!done) {
                        byte messageType = dIn.readByte();

                        switch(messageType)
                        {
                            case 1: // Type A
                                System.out.println("Message A: " + dIn.readUTF());
                                break;
                            case 2: // Type B
                                System.out.println("Message B: " + dIn.readUTF());
                                break;
                            case 3: // Type C
                                System.out.println("Message C [1]: " + dIn.readUTF());
                                System.out.println("Message C [2]: " + dIn.readUTF());
                                break;
                            default:
                                done = true;
                        }
                    }

                    dIn.close();






                }
                catch (Exception e){
                    System.out.println("Socket server can't start");
                }
            }
        };
        network.start();



    }
}
