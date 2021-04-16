package client.mediator;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client implements RemoteModel{
    @Override
    public void getAllUsers() throws RemoteException {
        
    }

    @Override
    public void addLog(String log) throws IOException, RemoteException {

    }

    @Override
    public ArrayList<String> getLog() throws RemoteException {
        return null;
    }

    @Override
    public int getConnectedUsersInt() throws RemoteException {
        return 0;
    }

    @Override
    public void getConnectedUsers() throws RemoteException {

    }

    @Override
    public void addUser(String name) throws RemoteException {

    }

    @Override
    public void removeUser(String name) throws RemoteException {

    }
}
