package com.fruitnaja.desktop;

import com.fruitnaja.SocketClient;
import com.fruitnaja.SocketServer;
import javafx.event.ActionEvent;

/**
 * Created by nutno on 6/4/2560.
 */
public class NetworkController {

    private void initServer(ActionEvent actionEvent){

        SocketServer server = new SocketServer();
        SocketClient client = new SocketClient("161.246.6.21");

    }

    private void initClient(ActionEvent actionEvent){

        SocketClient client = new SocketClient("161.246.6.21");

    }

}
