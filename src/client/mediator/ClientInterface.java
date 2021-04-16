package client.mediator;

import server.model.User;
import server.model.UserList;
import utility.observer.subject.LocalSubject;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends LocalSubject<String,String> {

    UserList getAllUsers() throws RemoteException;
    void addLog(String log) throws IOException,RemoteException;
    ArrayList<String> getLog() throws RemoteException;
    int getConnectedUsersInt() throws RemoteException;
    ArrayList<String> getConnectedUsers() throws RemoteException;
    void addUser(String name) throws RemoteException;
    void removeUser(String name) throws RemoteException;
}
