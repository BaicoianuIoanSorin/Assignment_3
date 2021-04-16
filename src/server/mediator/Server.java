package server.mediator;

import server.model.Model;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.RemoteSubject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Server implements RemoteModel {

    private Model model;

    public Server(Model model){
        this.model = model;
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
}
