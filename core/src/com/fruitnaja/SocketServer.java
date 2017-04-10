package com.fruitnaja;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nutno on 6/4/2560.
 */
public class SocketServer {
    private final int PORT = 1025;
    private int connected_client;
    private ArrayList<Socket> socket = new ArrayList();


    public SocketServer()  {
        try{
            ServerSocket listener = new ServerSocket(PORT);
        }
        catch (IOException e){
            System.out.println("Can't created new server");
        }





               /* try {
                    ServerSocket listener = new ServerSocket(PORT);
                    System.out.println("Socket server is running at "+ Inet4Address.getLocalHost().getHostAddress()+":"+PORT);

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
                } */



    }

    public void start(){
        while (true){
            try {

            }
            catch (Exception e){
                System.out.println("Socket server can't start");
            }


        }

    }
}
