package server.mediator;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import server.model.Model;
import server.model.User;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;
import utility.observer.subject.RemoteSubject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements RemoteModel {

    private Model model;
    private PropertyChangeHandler<ArrayList<String>,String> property;

    public Server(Model model) throws RemoteException, MalformedURLException{
        this.model = model;
        property = new PropertyChangeHandler<>(this,true);

        startServer();
    }

    @Override
    public void getAllUsers() throws RemoteException {
        model.getAllUsers();
    }

    @Override
    public void addLog(String log) throws IOException, RemoteException {
        model.addLog(log);
    }

    @Override
    public ArrayList<String> getLog() throws RemoteException {
        return model.getLog();
    }

    @Override
    public int getConnectedUsersInt() throws RemoteException {
        return model.getConnectedUsersInt();
    }

    @Override
    public ArrayList<String> getConnectedUsers() throws RemoteException {
        return model.getConnectedUsers();
    }

    @Override
    public void addUser(String name) throws RemoteException {
        model.addUser(name);
    }

    @Override
    public void removeUser(String name) throws RemoteException {
        model.removeUser(name);
    }

    @Override
    public boolean addListener(GeneralListener<ArrayList<String>, String> listener, String... propertyNames) throws RemoteException {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<ArrayList<String>, String> listener, String... propertyNames) throws RemoteException {
        return false;
    }

    public void startServer() throws RemoteException, MalformedURLException
    {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");

            UnicastRemoteObject.exportObject(this,0);
            Naming.rebind("Message",this);
            System.out.println("Server started...");
        }
        catch (java.rmi.server.ExportException e)
        {
            System.out.println("Registry already started? Message: " + e.getMessage());
        }

    }
}
