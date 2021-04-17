package server;

import server.mediator.Server;
import server.model.Model;
import server.model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServerMain {
    public static void main(String[] args) {
        try {
            Model serverHandler = new ModelManager();
            Server server = new Server(serverHandler);
            server.startServer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
