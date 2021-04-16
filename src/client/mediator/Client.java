package client.mediator;

import client.model.Model;
import server.model.UserList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;
import utility.observer.subject.RemoteSubject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements ClientInterface, RemoteListener<String,String> {

    private Model model;
    private RemoteModel remoteModel;
    private PropertyChangeHandler<String,String> propertyChangeHandler;


    public Client(Model model) throws MalformedURLException, NotBoundException, RemoteException {
        this.model = model;
        this.remoteModel = (RemoteModel) Naming.lookup("rmi://localhost:1099/messageServer");
        UnicastRemoteObject.exportObject(this,0);
        propertyChangeHandler = new PropertyChangeHandler<>(this,true);
    }

    @Override
    public UserList getAllUsers() throws RemoteException {
        return remoteModel.getAllUsers();
    }

    @Override
    public void addLog(String log) throws IOException, RemoteException {
        remoteModel.addLog(log);
    }

    @Override
    public ArrayList<String> getLog() throws RemoteException {
        return remoteModel.getLog();
    }

    @Override
    public int getConnectedUsersInt() throws RemoteException {
        return remoteModel.getConnectedUsersInt();
    }

    @Override
    public ArrayList<String> getConnectedUsers() throws RemoteException {
        return remoteModel.getConnectedUsers(); //NOTE TO CHANGE IN THE SERVER
    }

    @Override
    public void addUser(String name) throws RemoteException {
        remoteModel.addUser(name);
    }

    @Override
    public void removeUser(String name) throws RemoteException {
        remoteModel.removeUser(name);
    }

    @Override
    public void propertyChange(ObserverEvent event) throws RemoteException {
        propertyChangeHandler.firePropertyChange(event);
    }

    @Override
    public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) {
        return false;
    }
}
