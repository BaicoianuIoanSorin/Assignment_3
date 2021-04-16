package client.mediator;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends Remote {

    void getAllUsers() throws RemoteException;
    void addLog(String log) throws IOException,RemoteException;
    ArrayList<String> getLog() throws RemoteException;
    int getConnectedUsersInt() throws RemoteException;
    void getConnectedUsers() throws RemoteException;
    void addUser(String name) throws RemoteException;
    void removeUser(String name) throws RemoteException;
}
